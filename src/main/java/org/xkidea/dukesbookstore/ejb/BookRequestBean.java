package org.xkidea.dukesbookstore.ejb;

import org.xkidea.dukesbookstore.entity.Book;
import org.xkidea.dukesbookstore.exception.BookNotFoundException;
import org.xkidea.dukesbookstore.exception.BooksNotFoundException;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateful
public class BookRequestBean {
    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = Logger.getLogger("dukesbookstore.ejb.BookRequestBean");

    public BookRequestBean() throws Exception {
    }

    public void createBook(String bookId, String surname, String firstname, String title
            , Double price, Boolean onsale, Integer calendarYear,
                           String description, Integer inventory) {
        try {
            Book book = new Book(bookId, surname, firstname, title, price,
                    onsale, calendarYear, description, inventory);
            logger.log(Level.INFO, "Create book {0}", bookId);
            em.persist(book);
            logger.log(Level.INFO,"Persisted book {0}", bookId);
        } catch (Exception exception) {
            throw new EJBException(exception.getMessage());
        }
    }

    public List<Book> getBooks() throws BooksNotFoundException {
        try {
            return (List<Book>) em.createNamedQuery("findBooks").getResultList();
        } catch (Exception ex) {
            throw  new BooksNotFoundException("Could not get books: " + ex.getMessage());
        }
    }

    public Book getBook(String bookId) throws BookNotFoundException {
        Book requestedBook = em.find(Book.class, bookId);

        if (requestedBook == null) {
            throw new BookNotFoundException("Couldn't find book: " + bookId);
        }

        return requestedBook;
    }
}
