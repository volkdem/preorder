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

import java.text.ParseException;
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

        final NumberPicker datePicker = ( NumberPicker ) parentView.findViewById( R.id.date_picker );
        setPickerContent( datePicker, getDates( pickUpDate ) );

        final NumberPicker hourPicker = ( NumberPicker ) parentView.findViewById( R.id.hour_picker );
        setPickerContent( hourPicker, getHours( pickUpDate ) );

        final NumberPicker minutePicker = ( NumberPicker ) parentView.findViewById( R.id.minute_picker );
        setPickerContent( minutePicker, getMinutes( pickUpDate ) );

        datePicker.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange( NumberPicker picker, int oldVal, int newVal ) {
                Calendar pickupDatetime = getNotPastPickupDatetime();
                setPickerContent( hourPicker, getHours( pickupDatetime ) );
                setPickerContent( minutePicker, getMinutes( pickupDatetime ) );
            }
        } );

        hourPicker.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange( NumberPicker picker, int oldVal, int newVal ) {
                Calendar pickupDatetime = getNotPastPickupDatetime();
                setPickerContent( minutePicker, getMinutes( pickupDatetime ) );
            }
        } );

        return parentView;
    }

    private Calendar getNotPastPickupDatetime() {
        Calendar selectedDate = getPickupDatetime();
        Calendar currentDate = Calendar.getInstance();
        if ( selectedDate.compareTo( currentDate ) > 0 ) {
            return selectedDate;
        } else {
            currentDate.add( Calendar.MINUTE, nearestTimeInterval );
            return currentDate;
        }
    }



    public Calendar getPickupDatetime() {
        View view = getView();
        final NumberPicker datePicker = ( NumberPicker ) view.findViewById( R.id.date_picker );
        final NumberPicker hourPicker = ( NumberPicker ) view.findViewById( R.id.hour_picker );
        final NumberPicker minutePicker = ( NumberPicker ) view.findViewById( R.id.minute_picker );

        Calendar pickupDatetime = Calendar.getInstance();
        try {
            Date date = dateFormatter.parse( datePicker.getDisplayedValues()[ datePicker.getValue() ] );
            Calendar monthAndDay = Calendar.getInstance();
            monthAndDay.setTime( date );
            pickupDatetime.set( Calendar.MONTH, monthAndDay.get( Calendar.MONTH ) );
            pickupDatetime.set( Calendar.DAY_OF_MONTH, monthAndDay.get( Calendar.DAY_OF_MONTH) );
        } catch ( ParseException e ) {
            // TODO
            e.printStackTrace();
        }
        pickupDatetime.set( Calendar.HOUR_OF_DAY, Integer.valueOf( hourPicker.getDisplayedValues()[ hourPicker.getValue() ] ) );
        pickupDatetime.set( Calendar.MINUTE, Integer.valueOf( minutePicker.getDisplayedValues()[ minutePicker.getValue() ] ) );
        return pickupDatetime;
    }

    private void setPickerContent( NumberPicker picker, String[] values ) {
        int oldValue = 0;
        if (picker.getDisplayedValues() != null && picker.getDisplayedValues().length > 0 ) {
            String value = picker.getDisplayedValues()[ picker.getValue() ];
            for( int i = 0; i < values.length; i ++ ) {
                if( values[ i ].equals( value ) ) {
                    oldValue = i;
                    break;
                }
            }
        }
        picker.setValue( 0 );
        picker.setMinValue( 0 );
        picker.setMaxValue( 0 );
        picker.setDisplayedValues( values );
        picker.setMaxValue( values.length - 1 );
        if( picker.getMinValue() <= oldValue && oldValue <= picker.getMaxValue() ) {
            picker.setValue( oldValue );
        }
    }

    private String[] getDates( Calendar curDate) {
        Calendar localCurDate = (Calendar)curDate.clone();
        String[] dates = new String[ 2 ];
        dates[ 0 ] = dateFormatter.format( localCurDate.getTime() );
        localCurDate.add( Calendar.DAY_OF_MONTH, 1 );
        dates[ 1 ] = dateFormatter.format( localCurDate.getTime() );
        return dates;
    }

    private String[] getHours( Calendar date ) {
        int hour = 0;
        if( isToday( date ) ) {
            hour = date.get( Calendar.HOUR_OF_DAY );
        }

        String[] values = new String[ 24 - hour ];
        for( int i = 0; i < values.length; i++, hour++ ) {
            values[ i ] = ("00" + hour);
            values[ i ] = values[ i ].substring( values[ i ].length() - 2 );
        }

        return  values;
    }

    private boolean isCurrentHour( Calendar date ) {
        return isToday( date ) && date.get( Calendar.HOUR_OF_DAY ) == Calendar.getInstance().get( Calendar.HOUR_OF_DAY );
    }

    private String[] getMinutes( Calendar date ) {
        final int parts = 60 / 5;
        int part = 0;
        if( isCurrentHour( date ) ) {
            part = date.get( Calendar.MINUTE ) / 5;
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


