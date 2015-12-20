package com.preorder.preorder;

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

import com.preorder.preorder.model.IProductBinChangeListener;
import com.preorder.preorder.model.Product;
import com.preorder.preorder.model.ProductBin;

import java.util.ArrayList;

/**
 * Created by Evgeny on 06.12.2015.
 */
public class ProductBinFragment extends Fragment implements IProductBinChangeListener {
    public static final String BIN_KEY = "BIN_KEY";
    public static final String PRODUCT_ORDER_KEY = "PRODUCT_ORDER_KEY";
    private ProductBin productBin = null;
    private ArrayList<Product> productOrder = new ArrayList<Product>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.product_bin_fragment, null );
        ((ListView)view.findViewById(R.id.binContent)).setAdapter( new ProductListAdapter( productOrder, productBin ) );

        Button makeOrderButton = ( Button ) view.findViewById( R.id.make_order );
        makeOrderButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startOrderConfirmationActivityIntent = new Intent( getContext(), OrderConfirmationActivity.class );
                startOrderConfirmationActivityIntent.putExtra( BIN_KEY, productBin );
                startOrderConfirmationActivityIntent.putExtra( PRODUCT_ORDER_KEY, productOrder );
                getActivity().startActivity( startOrderConfirmationActivityIntent );
            }
        } );


        return view;
    }


    @Override
    public void update(ProductBin productBin, Product product ) {
        productOrder.remove( product );
        if (productBin.containsProduct( product ) ) {
            // put to the end of the list
            productOrder.add( product );
        }

        ((ProductListAdapter)((ListView)getView().findViewById(R.id.binContent)).getAdapter()).notifyDataSetChanged();

        // update price
        TextView costView = (TextView) getView().findViewById( R.id.binCost );
        costView.setText( productBin.getCost() + " Р");

    }



    public void setProductBin(ProductBin productBin) {
        this.productBin = productBin;
        productBin.setBinChangeLisnter(this);
        ListView productListView = ( ListView ) getView().findViewById( R.id.binContent );
        ((ProductListAdapter) productListView.getAdapter()).setProductBin( productBin );
    }


}
