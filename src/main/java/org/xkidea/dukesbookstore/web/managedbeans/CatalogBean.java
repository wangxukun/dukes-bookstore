package org.xkidea.dukesbookstore.web.managedbeans;

import org.xkidea.dukesbookstore.entity.Book;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("catalog")
@SessionScoped
public class CatalogBean extends AbstractBean implements Serializable {
    private static final long serialVersionUID = 9037055079192910364L;
    private int totalBooks = 0;

    /**
     * @return the currently selected Book instance from the user request
     */
    protected Book book() {
        Book book;
        book = (Book) context().getExternalContext().getSessionMap().get("book");
        return book;
    }

    /**
     * Add the selected item to our shopping cart.
     * @return the navigation page
     */
    public String add() {
        Book book = book();
        cart.add(book.getBookId(),book);
        message(null,"ConfirmAdd", new Object[]{book.getTitle()});
        System.out.println("--- addToCart -- book = " + book);
        return "bookcatalog";
    }

    /**
     * Show the details page for the current book.
     * @return the navigation page
     */
    public String details() {
        context().getExternalContext().getSessionMap().put("selected", book());
        return "bookdetails";
    }

    public int getTotalBooks() {
        totalBooks = cart.getNumberOfItems();
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public int getBookQuantity() {
        int bookQuantity = 0;
        Book book = book();

        if (book == null) {
            System.out.println("--- getBookQuantity() book=null -> quantity = " + bookQuantity);
            return bookQuantity;
        }

        List<ShoppingCartItem> results = cart.getItems();
        for (ShoppingCartItem item : results) {
            Book bd = (Book)item.getItem();

            if ((bd.getBookId().equals(book.getBookId()))) {
                bookQuantity = item.getQuantity();

                break;
            }
        }
        System.out.println("--- getBookQuantity() book<>null -> quantity = " + bookQuantity);
        return bookQuantity;
    }
}
