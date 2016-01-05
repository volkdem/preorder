package com.preorder.preorder.ui;

import android.content.Context;
import android.content.DialogInterface;
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

import com.preorder.preorder.R;
import com.preorder.preorder.model.IProductBinChangeListener;
import org.prototype.model.Product;
import org.prototype.model.ProductsCatalog;

import java.util.List;

/**
 * Created by Evgeny on 01.12.2015.
 */
public class RestraintMenuFragment extends ListFragment {
    private ProductsCatalog catalog;
    private OrderWrapper orderWrapper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView)inflater.inflate( R.layout.restraint_menu_fragment_layout, null );
        MenuAdapter adapter = new MenuAdapter( catalog.getProducts() );
        orderWrapper.addBinChangeLisnter( adapter );
        view.setAdapter( adapter );
        return view;
    }

    public void setCatalog(ProductsCatalog catalog) {
        this.catalog = catalog;
    }

    public void setOrderWrapper( OrderWrapper orderWrapper ) {
        this.orderWrapper = orderWrapper;
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
        public View getView(int position, View convertView, final ViewGroup parent) {
            View view = convertView;
            if (convertView == null ) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate( R.layout.product_layout, null );
            }
            final Product product = products.get(position);

            view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    int count = orderWrapper.getCount( product );
                    orderWrapper.addProduct( product, count + 1 );
                }
            } );

            TextView productView = (TextView) view.findViewById( R.id.product) ;
            productView.setText(product.getName());

            TextView costView = (TextView) view.findViewById( R.id.cost) ;
            costView.setText( product.getCost().toString() );


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
            notifyDataSetChanged();
        }
    }
}


