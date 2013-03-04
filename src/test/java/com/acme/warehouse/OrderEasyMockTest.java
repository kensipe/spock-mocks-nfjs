package com.acme.warehouse;

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;

/**
 * @author kensipe
 */
public class OrderEasyMockTest extends TestCase {
    private static String TALISKER = "Talisker";

    private IMocksControl warehouseControl;
    private Warehouse warehouseMock;

    public void setUp() {
        warehouseControl = EasyMock.createControl();
        warehouseMock = warehouseControl.createMock(Warehouse.class);
    }

    public void testFillingRemovesInventoryIfInStock() {
        //setup - data
        Order order = new Order(TALISKER, 50);

        //setup - expectations
        EasyMock.expect(warehouseMock.hasInventory(TALISKER, 50)).andReturn(true);
        warehouseMock.remove(TALISKER, 50);
        warehouseControl.replay();

        //exercise
        order.fill(warehouseMock);

        //verify
        warehouseControl.verify();
        assertTrue(order.isFilled());
    }

    public void testFillingDoesNotRemoveIfNotEnoughInStock() {
        Order order = new Order(TALISKER, 51);

        EasyMock.expect(warehouseMock.hasInventory(TALISKER, 51)).andReturn(false);
        warehouseControl.replay();

        order.fill(warehouseMock);

        assertFalse(order.isFilled());
        warehouseControl.verify();
    }
}
