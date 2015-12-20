package org.prototype.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 30.11.2015.
 */
public class Order {

    private String orderID;
    private Date orderTime;
    private Date pickupTime;
    private List<OrderItem> itemsList = new ArrayList<OrderItem>();


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public List<OrderItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<OrderItem> itemsList) {
        this.itemsList = itemsList;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderTime=" + orderTime +
                ", pickupTime=" + pickupTime +
                ", itemsList=" + itemsList +
                '}';
    }

}
