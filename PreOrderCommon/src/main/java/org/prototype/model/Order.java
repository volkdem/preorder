package org.prototype.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny on 05.12.2015.
 */
public class Order implements Serializable {
    private Long restraintId;
    private String id;
    private Date orderTime;
    private Date pickupTime;
    private Map<Product, Integer> products = new HashMap<>();

    public Order( Long restraintId ) {
        this.restraintId = restraintId;
    }


    public Long getRestraintId() {
        return restraintId;
    }

    public void setRestraintId( Long restraintId ) {
        this.restraintId = restraintId;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime( Date orderTime ) {
        this.orderTime = orderTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime( Date pickupTime ) {
        this.pickupTime = pickupTime;
    }

    public void addProduct(Product product, int count) {
        products.put(product, count);

    }

    public BigDecimal getCost() {
        BigDecimal cost = new BigDecimal( 0 ).setScale( 2 );
        for( Product product: products.keySet() ) {
            cost = cost.add( product.getCost().multiply( new BigDecimal( products.get( product ) ) ) );
        }

        return cost;
    }

    public boolean containsProduct( Product product ) {
        return  products.containsKey( product );
    }


    public void removeProduct(Product product) {
        products.remove( product );

    }


    public void clear() {
        products.clear();
    }



    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + id + '\'' +
                ", orderTime=" + orderTime +
                ", pickupTime=" + pickupTime +
                ", itemsList=" + products +
                '}';
    }
}
