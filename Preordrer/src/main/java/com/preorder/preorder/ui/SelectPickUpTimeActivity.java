package com.preorder.preorder.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.preorder.preorder.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SelectPickUpTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.content_select_pick_up_time);

        TextView orderDateView = (TextView) findViewById( R.id.order_date );
        orderDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView orderTime = (TextView) findViewById( R.id.order_time );
        SimpleDateFormat formatter = new SimpleDateFormat( "hh:mm", Locale.getDefault());
        orderTime.setText( formatter.format(new Date()));
        orderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timeDialog = new OrderTimePicker();
                timeDialog.show( SelectPickUpTimeActivity.this.getFragmentManager(), "pickUpTime");
            }
        });
    }

}
