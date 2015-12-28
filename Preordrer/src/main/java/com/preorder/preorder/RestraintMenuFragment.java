package com.preorder.preorder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
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
import com.preorder.preorder.model.ProductsCatalog;

import java.util.List;

/**
 * Created by Evgeny on 01.12.2015.
 */
public class RestraintMenuFragment extends ListFragment {
    private ProductsCatalog catalog;
    private ProductBinWrapper productBinWrapper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView)inflater.inflate( R.layout.restraint_menu_fragment_layout, null );
        MenuAdapter adapter = new MenuAdapter( catalog.getProducts() );
        productBinWrapper.addBinChangeLisnter( adapter );
        view.setAdapter( adapter );
        return view;
    }

    public void setCatalog(ProductsCatalog catalog) {
        this.catalog = catalog;
    }

    public void setProductBinWrapper( ProductBinWrapper productBinWrapper ) {
        this.productBinWrapper = productBinWrapper;
    }


    class MenuAdapter extends BaseAdapter implements IProductBinChangeListener {
        private List<Product> products;

        public MenuAdapter(List<Product> products) {
            this.products = products;
        }

        @Override
        public int getCount() {
            return products.size();

        }

        @Override
        public Object getItem(int position) {
            return products.get( position );
        }

        @Override
        public long getItemId(int position) {
            return products.get( position ).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null ) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate( R.layout.product_layout, null );
            }
            final Product product = products.get(position);

            TextView productView = (TextView) view.findViewById( R.id.product) ;
            productView.setText(product.getName());

            TextView costView = (TextView) view.findViewById( R.id.cost) ;
            costView.setText(product.getCost().toString() );

            CheckBox checkBox = (CheckBox) view.findViewById( R.id.productSelectionChecker);
            checkBox.setChecked( productBinWrapper.containsProduct( product ) );
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    buttonView.setChecked(isChecked);
                    if (isChecked) {
                        productBinWrapper.addProduct( product, 1 );
                    } else {
                        productBinWrapper.removeProduct( product );
                    }
                }
            });

            return view;
        }

        @Override
        public void update( ProductBinWrapper productBin, Product product ) {
            notifyDataSetChanged();
        }
    }
}


