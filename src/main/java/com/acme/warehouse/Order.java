package com.acme.warehouse;

/**
 * @author kensipe
 */
public class Order {

    private String item;
    private int quantity;
    private boolean filled;

    public Order(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void fill(Warehouse warehouse) {
        if (warehouse.hasInventory(item, quantity)) {
            warehouse.remove(item, quantity);
            filled = true;
        }
    }

    public boolean isFilled() {
        return filled;
    }
}
