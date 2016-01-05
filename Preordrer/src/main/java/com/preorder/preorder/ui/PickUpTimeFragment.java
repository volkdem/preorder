package com.preorder.preorder.ui;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.preorder.preorder.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickUpTimeFragment extends Fragment {
    private static final int nearestTimeInterval = 10; // in minuts
    private SimpleDateFormat dateFormatter = new SimpleDateFormat( "dd MMM" );
    private Date pickupTime;
    private boolean closestTime = true;


    public PickUpTimeFragment() {
        // Required empty public constructor
}


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate( R.layout.fragment_pick_up_time, container, false );
        Calendar pickUpDate = Calendar.getInstance();
        pickUpDate.add( Calendar.MINUTE, nearestTimeInterval );
        NumberPicker datePicker = ( NumberPicker ) parentView.findViewById( R.id.date_picker );
        setPickerContent( datePicker, getDates( pickUpDate ) );
        NumberPicker hourPicker = ( NumberPicker ) parentView.findViewById( R.id.hour_picker );
        setPickerContent( hourPicker, getHours( pickUpDate ) );
        NumberPicker minutePicker = ( NumberPicker ) parentView.findViewById( R.id.minute_picker );
        setPickerContent( minutePicker, getMinutes( pickUpDate ) );
        return parentView;
    }

    private void setPickerContent( NumberPicker picker, String[] values ) {
        picker.setDisplayedValues( values );
        picker.setMinValue( 0 );
        picker.setMaxValue( values.length - 1 );
    }

    private String[] getDates( Calendar curDate) {
        Calendar localCurDate = (Calendar)curDate.clone();
        String[] dates = new String[ 2 ];
        dates[ 0 ] = dateFormatter.format( localCurDate.getTime() );
        localCurDate.add( Calendar.DAY_OF_MONTH, 1 );
        dates[ 1 ] = dateFormatter.format( localCurDate.getTime() );
        return dates;
    }

    private String[] getHours( Calendar curDate ) {
        int hour = 0;
        if( isToday( curDate ) ) {
            hour = curDate.get( Calendar.HOUR_OF_DAY );
        }

        String[] values = new String[ 24 - hour ];
        for( int i = 0; i < values.length; i++, hour++ ) {
            values[ i ] = ("00" + hour);
            values[ i ] = values[ i ].substring( values[ i ].length() - 2 );
        }

        return  values;
    }

    private String[] getMinutes( Calendar curDate ) {
        final int parts = 60 / 5;
        int part = 0;
        if( isToday( curDate ) ) {
            part = curDate.get( Calendar.MINUTE ) / 5;
        }
        String[] values = new String[ parts - part ];
        for( int i = 0; i < values.length; i ++, part ++ ) {
            values[ i ] = "00" + part * 5;
            values[ i ] = values[ i ].substring( values[ i ].length() - 2 );
        }

        return values;
    }

    private boolean isToday( Calendar curDate ) {
        return curDate.get( Calendar.DAY_OF_MONTH ) == Calendar.getInstance().get( Calendar.DAY_OF_MONTH );
    }


}


