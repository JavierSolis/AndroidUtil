package com.javiersolis.lib.android.util.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;


/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @
 */
public class UtilDialogImputText
{
    private Activity context;
    private ListenerDialogInputText listener;
    private int idMsg;

    public  UtilDialogImputText(Activity context, ListenerDialogInputText listener)
    {
        this.context=context;
        this.listener=listener;
    }

    public void setMsg(int idMsg)
    {
        this.idMsg=idMsg;
    }

    private Integer inputType;
    public void setInputType(int inputType)
    {
        this.inputType=inputType;
    }
    public void show(String oldreference)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.context);

        oldreference=oldreference==null?"":oldreference;

        final EditText edittext = new EditText(this.context);
        edittext.setText(oldreference);
        if(this.inputType!=null)
        {
            edittext.setRawInputType(this.inputType);
        }

        alert.setMessage(this.context.getString(this.idMsg));
        //alert.setTitle("Enter Your Title");



        alert.setView(edittext);

        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //Editable YouEditTextValue = edittext.getText();
                //OR

                String YouEditTextValue = edittext.getText().toString();
                listener.acceptInput(YouEditTextValue);
            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                listener.cancelInput();
            }
        });

        alert.show();
    }



//multiple opciones
    //http://developer.android.com/intl/es/guide/topics/ui/dialogs.html


    public interface ListenerDialogInputText
    {
        public void acceptInput(String input);
        public void cancelInput();

    }
}
