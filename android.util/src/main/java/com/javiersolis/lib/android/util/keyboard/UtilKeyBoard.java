package com.javiersolis.lib.android.util.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;




import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 29/10/15.
 */
public class UtilKeyBoard {

    public static void hideKeyBoard(Activity context)
    {
        if(context.getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    private List<View> views;
    private Activity context;
    private int idContainer;

    public UtilKeyBoard(Builder b)
    {
        this.context=b.context;
        this.views = b.views;
        this.idContainer= b.idContainer;
    }

    public void addView(View v)
    {
        this.views.add(v);
    }

    public void hideKeyboard(int idContaner) {


        hideKeyboard();

        RelativeLayout linearLayout = (RelativeLayout) context.findViewById(idContaner);

        linearLayout.requestFocus();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        for (View v:views) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        }
    }



    public static class Builder {
        private List<View> views;
        private Activity context;
        private int idContainer;


        public Builder(Activity activity) {
            this.context= activity;
            this.views = new ArrayList<View>();
        }

        public Builder addView(View v) {
            this.views.add(v);
            return this;
        }

        public Builder setIdContainer(int idContainer) {
            this.idContainer=idContainer;
            return this;
        }


        public UtilKeyBoard build() {
            return new UtilKeyBoard(this);
        }

    }
}
