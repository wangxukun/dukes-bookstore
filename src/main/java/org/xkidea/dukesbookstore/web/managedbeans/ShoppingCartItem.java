package org.xkidea.dukesbookstore.web.managedbeans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Managed bean used by ShoppingCart bean.
 */
@Named
@SessionScoped
public class ShoppingCartItem implements Serializable {

    private static final long serialVersionUID = -3000385575321561813L;
    Object item;
    int quantity; //　数量

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Object anItem) {
        this.item = anItem;
        quantity = 1;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public Object getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
