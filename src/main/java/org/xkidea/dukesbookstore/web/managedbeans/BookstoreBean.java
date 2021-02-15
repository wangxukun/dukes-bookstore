package org.xkidea.dukesbookstore.web.managedbeans;

import org.xkidea.dukesbookstore.ejb.BookRequestBean;
import org.xkidea.dukesbookstore.entity.Book;
import org.xkidea.dukesbookstore.exception.BookNotFoundException;
import org.xkidea.dukesbookstore.exception.BooksNotFoundException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("store")
@SessionScoped
public class BookstoreBean extends AbstractBean implements Serializable {
    private static final Logger logger =
            Logger.getLogger("dukesbookstore.web.managedBeans.BookStoreBean");
    private static final long serialVersionUID = 201225149094311575L;
    private Book featured = null;
    protected String title;

    @EJB
    BookRequestBean bookRequestBean;

    /**
     * @return the Book for the featured book.
     */
    public Book getFeatured() {
        int featuredBookPos = 4;
        if (featured == null) {
            try {
                featured = (Book) bookRequestBean.getBooks().get(featuredBookPos);
            } catch (BooksNotFoundException e) {
                throw new FacesException("Could not get books: " + e);
            }
        }
        return (featured);
    }

    /**
     * Add the featured item to our shopping cart.
     * @return the navigation page
     */
    public String add() {
        Book book = getFeatured();
        cart.add(book.getBookId(),book);
        message(null,"ConfirmAdd",new Object[]{book.getTitle()});
        return "bookcatalog";
    }

    public String addSelected() {
        logger.log(Level.INFO,"Entering BookstoreBean.addSelected");
        try {
            String bookId = (String) context().getExternalContext().getSessionMap().get("bookId");
            Book book = bookRequestBean.getBook(bookId);
            cart.add(bookId, book);
            message(null, "ConfirmAdd", new Object[]{book.getTitle()});
        } catch (BookNotFoundException e) {
            throw new FacesException("Could not get book: " + e);
        }
        return "bookcatalog";
    }

    /**
     * Show the details page for the featured book.
     * @return the navigation page
     */
    public String details() {
        context().getExternalContext().getSessionMap().put("selected",getFeatured());
        return "bookdetails";
    }

    public String selectedDetails() {
        logger.log(Level.INFO,"Entering BookstoreBean.SelectedDetails");
        try {
            String bookId = (String) context().getExternalContext().getSessionMap().get("bookId");
            Book book = bookRequestBean.getBook(bookId);
            context().getExternalContext().getSessionMap().put("selected", book);
        } catch (BookNotFoundException e) {
            throw new FacesException("Could not get book: " + e);
        }
        return "bookdetails";
    }

    public String getSelectedTitle() {
        logger.log(Level.INFO,"Entering BookstoreBean.getSelectedTitle");
        try {
            String bookId = (String) context().getExternalContext().getSessionMap().get("bookId");
            Book book = bookRequestBean.getBook(bookId);
            title = book.getTitle();
        } catch (BookNotFoundException e) {
            throw new FacesException("Could not get book title: " + e);
        }
        return title;
    }

    public List<Book> getBooks() {
        try {
            return bookRequestBean.getBooks();
        } catch (BooksNotFoundException e) {
            throw new FacesException("Exception: " + e);
        }
    }
}