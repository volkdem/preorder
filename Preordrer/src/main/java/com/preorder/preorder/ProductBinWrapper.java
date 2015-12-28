package com.preorder.preorder;

import com.preorder.preorder.model.IProductBinChangeListener;
import com.preorder.preorder.model.Product;
import com.preorder.preorder.model.ProductBin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny on 22.12.2015.
 */
public class ProductBinWrapper {
    private ProductBin productBin;
    private List< IProductBinChangeListener > binChangeLisnters = new ArrayList<IProductBinChangeListener>(  );

    public ProductBinWrapper( ProductBin productBin ) {
        this.productBin = productBin;
    }

    public ProductBin getProductBin() {
        return productBin;
    }

    public void addProduct( Product product, int count ) {
        productBin.addProduct( product, count );
        notifyListeners( product );
    }

    public BigDecimal getCost() {
        return productBin.getCost();
    }

    public boolean containsProduct( Product product ) {
        return productBin.containsProduct( product );
    }

    public void removeProduct( Product product ) {
        productBin.removeProduct( product );
        notifyListeners( product );
    }

    public void clear() {
        productBin.clear();
        notifyListeners( null );
    }

    public void addBinChangeLisnter( IProductBinChangeListener binChangeLisnter ) {
        binChangeLisnters.remove( binChangeLisnter );
        binChangeLisnters.add( binChangeLisnter );
    }

    public Map< Product, Integer > getProducts() {
        return productBin.getProducts();
    }

    private void notifyListeners( Product product ) {
        for( IProductBinChangeListener binChangeListener: binChangeLisnters ) {
            binChangeListener.update( this, product );
        }
    }
}
