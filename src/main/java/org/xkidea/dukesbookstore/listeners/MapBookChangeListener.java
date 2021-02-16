package org.xkidea.dukesbookstore.listeners;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Action listener for the image map on the index page.
 */
public class MapBookChangeListener implements ActionListener {

    private static final Logger logger = Logger.getLogger("dukesbookstore.listeners.MapBookChangeListener");
    private HashMap<String,String> books = null;

    public MapBookChangeListener() {
        books = new HashMap<String, String>(6);
        String book1 = books.put("Duke", "201");
        String book2 = books.put("Jeeves", "202");
        String book3 = books.put("Masterson", "203");
        String book4 = books.put("Novation", "205");
        String book5 = books.put("Thrilled", "206");
        String book6 = books.put("Coding", "207");
    }

    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        logger.log(Level.INFO,"Entering MapBookChangeListener.processAction");
        AreaSelectedEvent event = (AreaSelectedEvent) actionEvent;
        String current = event.getMapComponent().getCurrent();
        FacesContext context = FacesContext.getCurrentInstance();
        String bookId = books.get(current);
        // TODO MapBookChangeListener()-processAction()：把被选择的书的bookId设置到会话session中
        context.getExternalContext().getSessionMap().put("bookId", bookId);
    }
}
