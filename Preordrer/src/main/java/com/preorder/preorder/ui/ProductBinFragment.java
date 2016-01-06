package com.preorder.preorder.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.preorder.preorder.OrderSinglton;
import com.preorder.preorder.R;
import com.preorder.preorder.model.IProductBinChangeListener;
import org.prototype.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 06.12.2015.
 */
public class ProductBinFragment extends Fragment implements IProductBinChangeListener{
    private OrderWrapper productBin = null;
    private List<Product> productOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.product_bin_fragment, null );
        productOrder = OrderSinglton.getProductOrder();
        ((ListView)view.findViewById(R.id.binContent)).setAdapter( new BinProductListAdapter( productOrder ) );

        Button makeOrderButton = ( Button ) view.findViewById( R.id.make_order );
        makeOrderButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startOrderConfirmationActivityIntent = new Intent( getActivity().getApplicationContext(), OrderConfirmationActivity.class );
                getActivity().startActivity( startOrderConfirmationActivityIntent );
            }
        } );

        return view;
    }


    @Override
    public void update(OrderWrapper orderWrapper, Product product ) {
        productOrder.remove( product );
        if ( orderWrapper.containsProduct( product ) ) {
            // put to the end of the list
            productOrder.add( 0, product );
        }

        View binFragment = getView();
        if( binFragment == null ) {
            return;
        }

        // update price
        TextView costView = (TextView) binFragment.findViewById( R.id.binCost );
        costView.setText( orderWrapper.getCost() + " Р");

        Button makeOrderButton = ( Button ) getView().findViewById( R.id.make_order );
        makeOrderButton.setEnabled( !orderWrapper.isEmpty() );

    }



    public void setProductBin(OrderWrapper productBin) {
        this.productBin = productBin;
        productBin.addBinChangeLisnter( this );
        ListView productListView = ( ListView ) getView().findViewById( R.id.binContent );
        ((BinProductListAdapter ) productListView.getAdapter()).setOrderWrapper( productBin );
    }

    @Override
    public void onStart() {
        super.onStart();

        ListView productListView = ( ListView ) getView().findViewById( R.id.binContent );
        ((BinProductListAdapter ) productListView.getAdapter()).notifyDataSetChanged();
    }
}
