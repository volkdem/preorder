package com.preorder.preorder.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
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
        this( productOrder );
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
    public View getView( int position, View convertView, final ViewGroup parent ) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.product_layout, null );
        }

        final Product product = productOrder.get( position );

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                int count = orderWrapper.getCount( product );
                orderWrapper.addProduct( product, count + 1 );
            }
        } );

        TextView productTileView = (TextView) view.findViewById( R.id.product );
        productTileView.setText( product.getName() );

        TextView productCostView = (TextView) view.findViewById( R.id.cost );
        productCostView.setText( product.getCost().toString() );

        final TextView productCountView = ( TextView ) view.findViewById( R.id.product_count );
        productCountView.setText( orderWrapper.getCount( product ).toString() );
        productCountView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                final NumberPickerDialog numberPickerDialog = new NumberPickerDialog( parent.getContext() );
                numberPickerDialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss( DialogInterface dialog ) {
                        productCountView.setText( String.valueOf( numberPickerDialog.getCount() ) );
                        orderWrapper.addProduct( product, numberPickerDialog.getCount() );
                    }
                } );

                numberPickerDialog.show( orderWrapper.getCount( product ) );
            }
        } );

        return view;
    }

    @Override
    public void update( OrderWrapper productBin, Product product ) {

        if (productBin.containsProduct( product )) {
            if( !productOrder.contains( product )) {
                productOrder.add( 0, product );
            }
        } else {
            productOrder.remove( product );
        }

        notifyDataSetChanged();
    }
}

class NumberPickerDialog extends Dialog {
    private int count;

    public NumberPickerDialog( Context context ) {
        super( context );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.dialog_number_picker );
        NumberPicker countPicker = ( NumberPicker ) findViewById( R.id.count_picker );
        String[] values = new String[ 10 ];
        for( int i = 0; i < values.length; i ++ ) {
            values[ i ] = String .valueOf( i );
        }
        countPicker.setDisplayedValues( values );
        countPicker.setMinValue( 0 );
        countPicker.setMaxValue( values.length - 1 );
        countPicker.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange( NumberPicker picker, int oldVal, int newVal ) {
                 NumberPickerDialog.this.count = newVal;
            }
        } );
    }

    @Override
    public void show() {
        super.show();
        NumberPicker countPicker = ( NumberPicker ) findViewById( R.id.count_picker );
        countPicker.setValue( count );
    }

    public void show(int count) {
        setCount( count );
        show();
    }

    public int getCount() {
        return count;
    }

    public void setCount( int count ) {
        this.count = count;
    }
}
