package com.acme.warehouse

import spock.lang.Specification

/**
 *
 * @author kensipe
 */
class OrderSpockSpec extends Specification {

    def warehouse = Mock(Warehouse)

    def "filling removes inventory if in stock"() {
        def order = new Order("Talisker", 50)

        when:
        order.fill(warehouse)

        then:
        1 * warehouse.hasInventory("Talisker", 50) >> true
        1 * warehouse.remove("Talisker", 50)
        order.isFilled()
    }

    def "filling does not remove from warehouse if not enough stock"() {

        def order = new Order("Talisker", 51)

        when:
        order.fill(warehouse)

        then:
        1 * warehouse.hasInventory(_, 51) >> false
        0 * warehouse.remove(_, _)
        !order.isFilled()
    }
}
