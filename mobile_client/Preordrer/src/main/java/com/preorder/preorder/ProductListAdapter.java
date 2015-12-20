package com.preorder.preorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.preorder.preorder.model.Product;
import com.preorder.preorder.model.ProductBin;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Evgeny on 20.12.2015.
 */
public class ProductListAdapter extends BaseAdapter {
    private List< Product > productOrder;
    private ProductBin productBin;

    public ProductListAdapter( List< Product > productOrder ) {
        this.productOrder = productOrder;
    }

    public ProductListAdapter( List< Product > productOrder, ProductBin productBin ) {
        this.productOrder = productOrder;
        this.productBin = productBin;
    }

    public void setProductBin( ProductBin productBin ) {
        this.productBin = productBin;
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
        if (productBin.containsProduct( product  ) ) {
            selectCheckerView.setChecked( true );
        } else {
            selectCheckerView.setChecked( false );
        }
        selectCheckerView.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
                if (isChecked ) {
                    productBin.addProduct( product, 1 );
                } else {
                    productBin.removeProduct( product );
                }
            }
        } );

        return view;
    }
}
