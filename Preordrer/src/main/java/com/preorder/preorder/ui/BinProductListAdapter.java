package com.preorder.preorder.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.preorder.preorder.R;
import com.preorder.preorder.model.IProductBinChangeListener;
import org.prototype.model.Product;

import java.util.List;

/**
 * Created by Evgeny on 20.12.2015.
 */
public class BinProductListAdapter extends BaseAdapter implements IProductBinChangeListener {
    private List< Product > productOrder;
    private OrderWrapper orderWrapper;

    public BinProductListAdapter( List< Product > productOrder ) {
        this.productOrder = productOrder;
    }

    public BinProductListAdapter( List< Product > productOrder, OrderWrapper orderWrapper ) {
        this.productOrder = productOrder;
        this.orderWrapper = orderWrapper;
        orderWrapper.addBinChangeLisnter( this );
    }

    public void setOrderWrapper( OrderWrapper orderWrapper ) {
        this.orderWrapper = orderWrapper;
        orderWrapper.addBinChangeLisnter( this );
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
                    orderWrapper.removeProduct( product );
                }
            }
        } );

        return view;
    }

    @Override
    public void update( OrderWrapper productBin, Product product ) {
        productOrder.remove( product );
        if (productBin.containsProduct( product )) {
            productOrder.add( 0, product );
        }

        notifyDataSetChanged();
    }
}
