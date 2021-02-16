package org.xkidea.dukesbookstore.web.managedbeans;

import org.xkidea.dukesbookstore.entity.Book;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("cart")
@SessionScoped
public class ShoppingCart extends AbstractBean implements Serializable {

    private static final Logger logger =
            Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
    private static final long serialVersionUID = 2748074116940521965L;
    HashMap<String, ShoppingCartItem> items = null;
    int numberOfItems = 0;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    /**
     * 线程同步方法，锁，用来保护代码片段，任何时刻只能有一个线程执行被保护的代码。
     * @param bookId
     * @param book
     */
    public synchronized void add(String bookId, Book book) {
        if (items.containsKey(bookId)) {
            ShoppingCartItem scitem = (ShoppingCartItem) items.get(bookId);
            scitem.incrementQuantity();
        } else {
            ShoppingCartItem newItem = new ShoppingCartItem(book);
            items.put(bookId, newItem);
        }
    }

    public synchronized void remove(String bookId) {
        if (items.containsKey(bookId)) {
            ShoppingCartItem scitem = (ShoppingCartItem) items.get(bookId);
            scitem.decrementQuantity();

            if (scitem.getQuantity() <= 0) {
                items.remove(bookId);
            }

            numberOfItems--;
        }
    }

    public synchronized List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> results = new ArrayList<>();
        results.addAll(this.items.values());

        return results;
    }

    public synchronized int getNumberOfItems() {
        numberOfItems = 0;
        for (ShoppingCartItem item : getItems()) {
            numberOfItems += item.getQuantity();
        }
        return numberOfItems;
    }

    public synchronized double getTotal() {
        double amount = 0.0;
        for (ShoppingCartItem item : getItems()) {
            Book bookDetails = (Book)item.getItem();
            amount += (item.getQuantity()*bookDetails.getPrice());
        }
        return roundOff(amount);
    }

    private double roundOff(double x) {
        long val = Math.round(x * 100);
        return val / 100.0;
    }

    public synchronized void clear() {
        logger.log(Level.INFO,"Clearing cart.");
        items.clear();
        numberOfItems = 0;
        message(null,"CartCleared");
    }

    public String buy(){
        if (getNumberOfItems() < 1) {
            message(null,"CartEmpty");
            return null;
        } else {
            return "bookcashier";
        }
    }
}
