package com.acme.warehouse;


import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * @author kensipe
 */
public class OrderjMockTest extends MockObjectTestCase {
    private static String TALISKER = "Talisker";

    public void testFillingRemovesInventoryIfInStock() {
        //setup - data
        Order order = new Order(TALISKER, 50);
        Mock warehouseMock = mock(Warehouse.class);

        //setup - expectations
        warehouseMock.expects(once()).method("hasInventory")
                .with(eq(TALISKER), eq(50))
                .will(returnValue(true));
        warehouseMock.expects(once()).method("remove")
                .with(eq(TALISKER), eq(50))
                .after("hasInventory");

        //exercise
        order.fill((Warehouse) warehouseMock.proxy());

        //verify
        warehouseMock.verify();
        assertTrue(order.isFilled());
    }

    public void testFillingDoesNotRemoveIfNotEnoughInStock() {
        Order order = new Order(TALISKER, 51);
        Mock warehouse = mock(Warehouse.class);

        warehouse.expects(once()).method("hasInventory")
                .withAnyArguments()
                .will(returnValue(false));

        order.fill((Warehouse) warehouse.proxy());

        assertFalse(order.isFilled());
    }
}