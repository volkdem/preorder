package com.preorder.preorder;

import org.prototype.model.Order;
import org.prototype.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 06.01.2016.
 */
public class OrderSinglton {
    private static Order order;
    private static List<Product> productOrder = new ArrayList<Product>(10);;

    public static Order newOrder( long restraintId ) {
        order = new Order( restraintId );
        productOrder.clear();
        return order;
    }

    public static Order getOrder() {
        return order;
    }

    public static List<Product> getProductOrder() {
        for( Product product: productOrder ) {
            if( !order.containsProduct( product ) ) {
                productOrder.remove( product );
            }
        }

        return productOrder;
    }

}
