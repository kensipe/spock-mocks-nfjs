package com.acme.warehouse;

/**
 * @author kensipe
 */
public interface Warehouse {

    void add(String item, int qty);

    int getInventory(String item);

    void remove(String item, int quantity);

    boolean hasInventory(String item, int quantity);

}
