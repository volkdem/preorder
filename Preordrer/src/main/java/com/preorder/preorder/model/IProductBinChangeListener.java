package com.preorder.preorder.model;

import com.preorder.preorder.ui.OrderWrapper;

import org.prototype.model.Product;

import java.io.Serializable;

/**
 * Created by Evgeny on 06.12.2015.
 */
public interface IProductBinChangeListener extends Serializable {
    void update( OrderWrapper productBin, Product product );
}
