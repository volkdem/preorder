package com.preorder.preorder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Evgeny on 29.11.2015.
 */
public class OrderTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private Calendar selectedDate;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        selectedDate = updateDateTime(updateDateTime(selectedDate));
        selectedDate.set( Calendar.HOUR_OF_DAY, hourOfDay );
        selectedDate.set( Calendar.MINUTE, minute );
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedDate = updateDateTime(selectedDate);
        return new TimePickerDialog( getActivity(), this, selectedDate.get( Calendar.HOUR_OF_DAY ), selectedDate.get( Calendar.MINUTE ), true );
    }

    private Calendar updateDateTime(Calendar oldDateTime) {
        Calendar currentDateTime = Calendar.getInstance();
        if( !isOldTimeValid( oldDateTime, currentDateTime ) ) {
            currentDateTime.add( Calendar.MINUTE, 15 );
            oldDateTime = currentDateTime;
        }

        return oldDateTime;
    }

    private boolean isOldTimeValid(Calendar oldDateTime, Calendar currentDateTime ) {
        final long five_minutes = 5 * 60 * 1000;
        return oldDateTime != null && ( oldDateTime.getTimeInMillis() - currentDateTime.getTimeInMillis() )  > five_minutes;
    }

    public Calendar getSelectedDate() {
        return selectedDate;
    }
}
