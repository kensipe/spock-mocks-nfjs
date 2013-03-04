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
    public void remove(String item, int quantity) {
        int qtyOnHand = getInventory(item);
        if (quantity <= qtyOnHand) {
            products.put(item, qtyOnHand - quantity);
        }
    }

    @Override
    public boolean hasInventory(String item, int quantity) {
        int qtyOnHand = getInventory(item);
        return (qtyOnHand >= quantity);
    }
}
