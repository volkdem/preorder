package org.prototype.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 30.11.2015.
 */
public class OrderItem {


        private String itemName;
        private int itemAmount;


        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getItemAmount() {
            return itemAmount;
        }

        public void setItemAmount(int itemAmount) {
            this.itemAmount = itemAmount;
        }


    @Override
    public String toString() {
        return "OrderItem{" +
                "itemName='" + itemName + '\'' +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
