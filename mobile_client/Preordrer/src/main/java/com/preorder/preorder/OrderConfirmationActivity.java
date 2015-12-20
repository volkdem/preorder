package com.preorder.preorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.preorder.preorder.model.Product;
import com.preorder.preorder.model.ProductBin;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_confirmation );

        ListView orderListView = (ListView) findViewById( R.id.list_item );
        List< Product > productOrder = (List< Product >)getIntent().getSerializableExtra( ProductBinFragment.PRODUCT_ORDER_KEY );
        ProductBin productBin = (ProductBin) getIntent().getSerializableExtra( ProductBinFragment.BIN_KEY );
        orderListView.setAdapter( new ProductListAdapter( productOrder ) );
    }
}
