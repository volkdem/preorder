package com.preorder.preorder.model;

import java.io.Serializable;

/**
 * Created by Evgeny on 06.12.2015.
 */
public interface IProductBinChangeListener extends Serializable {
    void update( ProductBin productBin, Product product );
}
