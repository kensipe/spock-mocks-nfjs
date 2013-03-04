package com.acme.warehouse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kensipe
 */
public class WarehouseImpl implements Warehouse {

    Map<String, Integer> products = new HashMap();

    @Override
    public void add(String item, int qty) {

        int qtyOnHand = getInventory(item);
        if (qtyOnHand == 0) {
            products.put(item, qty);
        } else {
            products.put(item, qtyOnHand + qty);
        }
    }

    @Override
    public int getInventory(String item) {
        Integer qtyOnHand = products.get(item);
        return qtyOnHand != null ? qtyOnHand : 0;
    }

    @Override
    public boolean remove(String item, int quantity) {
        boolean filled = false;
        int qtyOnHand = getInventory(item);
        if (quantity <= qtyOnHand) {
            products.put(item, qtyOnHand - quantity);
            filled = true;
        }

        return filled;
    }
}
