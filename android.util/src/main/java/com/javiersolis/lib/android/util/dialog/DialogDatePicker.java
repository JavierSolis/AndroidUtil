package com.javiersolis.lib.android.util.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 22/11/15.
 */
public class DialogDatePicker
implements
        DatePickerDialog.OnDateSetListener
{
    private Calendar myCalendar ;
    private IListenerDialogDatePicker iListenerDialogDatePicker;

    public DialogDatePicker(IListenerDialogDatePicker iListenerDialogDatePicker)
    {
        this.iListenerDialogDatePicker = iListenerDialogDatePicker;
        this.myCalendar = Calendar.getInstance();
    }

    public void setDate( Calendar newCalendar)
    {

        this.myCalendar.set(Calendar.YEAR,newCalendar.get(Calendar.YEAR));
        this.myCalendar.set(Calendar.MONTH,newCalendar.get(Calendar.MONTH));
        this.myCalendar.set(Calendar.DAY_OF_MONTH,newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    public void showDialog(Context context)
    {
        new DatePickerDialog(
                context,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                this,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        this.iListenerDialogDatePicker.updateDate(this.myCalendar);
    }
//////

    public Calendar getMyCalendar() {
        return myCalendar;
    }

    public void setMyCalendar(Calendar myCalendar) {
        this.myCalendar = myCalendar;
    }


}
