package com.example.anna.ppmcalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment {
    private CalendarListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(Objects.requireNonNull(getActivity()), dateSetListener, year, month, day);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (CalendarListener) context;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    listener.setDateOfBirth(year, month, day);

                }
            };


    public interface CalendarListener {

        void setDateOfBirth(int year, int month, int day);

    }

}
