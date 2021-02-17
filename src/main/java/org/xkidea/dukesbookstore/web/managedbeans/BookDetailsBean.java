package org.xkidea.dukesbookstore.web.managedbeans;

import org.xkidea.dukesbookstore.entity.Book;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("details")
@SessionScoped
public class BookDetailsBean extends AbstractBean implements Serializable {
    private static final long serialVersionUID = -4325925110230878040L;

    public String add() {
        Book book = (Book) context().getExternalContext().getSessionMap().get("selected");
        System.out.println("--- details -- add() book = " + book);
        cart.add(book.getBookId(),book);
        message(null,"ConfirmAdd", new Object[]{book.getTitle()});
        return "bookcatalog";
    }
}
