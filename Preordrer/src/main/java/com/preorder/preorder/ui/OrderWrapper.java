package com.preorder.preorder.ui;

import com.preorder.preorder.model.IProductBinChangeListener;
import org.prototype.model.Product;
import org.prototype.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny on 22.12.2015.
 */
public class OrderWrapper {
    private Order order;
    private List< IProductBinChangeListener > binChangeLisnters = new ArrayList<IProductBinChangeListener>(  );

    public OrderWrapper( Order order ) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void addProduct( Product product, int count ) {
        order.addProduct( product, count );
        notifyListeners( product );
    }

    public BigDecimal getCost() {
        return order.getCost();
    }

    public boolean containsProduct( Product product ) {
        return order.containsProduct( product );
    }

    public void removeProduct( Product product ) {
        order.removeProduct( product );
        notifyListeners( product );
    }

    public void clear() {
        order.clear();
        notifyListeners( null );
    }

    public void addBinChangeLisnter( IProductBinChangeListener binChangeLisnter ) {
        binChangeLisnters.remove( binChangeLisnter );
        binChangeLisnters.add( binChangeLisnter );
    }

    public Map< Product, Integer > getProducts() {
        return order.getProducts();
    }

    private void notifyListeners( Product product ) {
        for( IProductBinChangeListener binChangeListener: binChangeLisnters ) {
            binChangeListener.update( this, product );
        }
    }

    public Integer getCount( Product product ) {
        Integer count = order.getProducts().get( product );
        if (count == null) {
            return 0;
        }

        return count;
    }
}
