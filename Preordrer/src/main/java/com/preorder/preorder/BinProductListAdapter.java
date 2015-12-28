package com.preorder.preorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.preorder.preorder.model.IProductBinChangeListener;
import com.preorder.preorder.model.Product;
import com.preorder.preorder.model.ProductBin;

import java.util.List;

/**
 * Created by Evgeny on 20.12.2015.
 */
public class BinProductListAdapter extends BaseAdapter implements IProductBinChangeListener {
    private List< Product > productOrder;
    private ProductBinWrapper productBinWrapper;

    public BinProductListAdapter( List< Product > productOrder ) {
        this.productOrder = productOrder;
    }

    public BinProductListAdapter( List< Product > productOrder, ProductBinWrapper productBinWrapper ) {
        this.productOrder = productOrder;
        this.productBinWrapper = productBinWrapper;
        productBinWrapper.addBinChangeLisnter( this );
    }

    public void setProductBinWrapper( ProductBinWrapper productBinWrapper ) {
        this.productBinWrapper = productBinWrapper;
        productBinWrapper.addBinChangeLisnter( this );
    }

    @Override
    public int getCount() {
        return productOrder.size();
    }

    @Override
    public Object getItem( int position ) {
        return productOrder.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return productOrder.get( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.product_layout, null );
        }

        final Product product = productOrder.get( position );

        TextView productTileView = (TextView) view.findViewById( R.id.product );
        productTileView.setText( product.getName() );

        TextView productCostView = (TextView) view.findViewById( R.id.cost );
        productCostView.setText( product.getCost().toString() );

        CheckBox selectCheckerView = ( CheckBox ) view.findViewById( R.id.productSelectionChecker );
        selectCheckerView.setChecked( true );
        selectCheckerView.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
                if (!isChecked ) {
                    productBinWrapper.removeProduct( product );
                }
            }
        } );

        return view;
    }

    @Override
    public void update( ProductBinWrapper productBin, Product product ) {
        productOrder.remove( product );
        if (productBin.containsProduct( product )) {
            productOrder.add( 0, product );
        }

        notifyDataSetChanged();
    }
}