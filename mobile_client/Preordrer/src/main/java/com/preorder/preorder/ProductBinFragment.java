package com.preorder.preorder;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.preorder.preorder.model.IProductBinChangeListener;
import com.preorder.preorder.model.Product;
import com.preorder.preorder.model.ProductBin;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Evgeny on 06.12.2015.
 */
public class ProductBinFragment extends Fragment implements IProductBinChangeListener {
    private ProductBin productBin = null;
    private List<Product> productOrder = new ArrayList<Product>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.product_bin_fragment, null );
        ((ListView)view.findViewById(R.id.binContent)).setAdapter( new ProductListAdapter() );

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
    }

    class ProductListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return productOrder.size();
        }

        @Override
        public Object getItem(int position) {
            return productOrder.get(position);
        }

        @Override
        public long getItemId(int position) {
            return productOrder.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.product_layout, null);
            }

            final Product product = productOrder.get(position);

            TextView productView = (TextView) view.findViewById(R.id.product);
            productView.setText(product.getName());

            TextView costView = (TextView) view.findViewById(R.id.cost);
            costView.setText(product.getCost().toString());


            return view;
        }
    }
}
