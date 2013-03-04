package com.acme.warehouse;

/**
 * @author kensipe
 */
public interface Warehouse {

    void add(String item, int qty);

    int getInventory(String item);

    boolean remove(String item, int quantity);
}
