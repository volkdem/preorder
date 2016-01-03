package com.preorder.preorder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.preorder.preorder.R;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.prototype.model.Product;
import org.prototype.model.Order;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_confirmation );

        ListView orderListView = (ListView) findViewById( R.id.list_item );
        List< Product > productOrder = (List< Product >)getIntent().getSerializableExtra( ProductBinFragment.PRODUCT_ORDER_KEY );
        final Order order = (Order ) getIntent().getSerializableExtra( ProductBinFragment.BIN_KEY );
        orderListView.setAdapter( new BinProductListAdapter( productOrder , new OrderWrapper( order ) ) );

        Button payButton = ( Button ) findViewById( R.id.pay_button );
        payButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                RequestQueue rQueue = Volley.newRequestQueue( OrderConfirmationActivity.this );
                final String payUrl = "http://localhost:8080/makePreOrder";
                ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String orderString = null;
                try {
                    orderString = writer.writeValueAsString( order );
                } catch ( IOException e ) {
                    e.printStackTrace();
                    // TODO
                    return;
                }

                JsonObjectRequest payOrderRequest = null;
                try {
                    payOrderRequest = new JsonObjectRequest( Request.Method.POST, payUrl, new JSONObject( orderString ), new Response.Listener< JSONObject >() {
                        @Override
                        public void onResponse( JSONObject response ) {
                            TextView orderNumberText = ( TextView )findViewById( R.id.order_number );
                            System.out.println( response.toString() );
                            // TODO
                            orderNumberText.setText( "Response is successfull" );
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse( VolleyError error ) {
                            TextView orderNumberText = ( TextView ) findViewById( R.id.order_number );
                            orderNumberText.setText( error.getMessage() );
                            error.printStackTrace();
                        }
                    } );
                } catch ( JSONException e ) {
                    e.printStackTrace();
                    // TODO
                    return;
                }

                //payOrderRequest.
                rQueue.add( payOrderRequest );

            }
        } );

    }
}
