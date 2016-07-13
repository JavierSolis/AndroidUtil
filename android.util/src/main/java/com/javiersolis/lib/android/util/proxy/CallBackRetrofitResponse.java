package com.javiersolis.lib.android.util.proxy;

import android.util.Log;

import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 6/11/15.
 */
public class CallBackRetrofitResponse<T> implements Callback<T> {


    public static final String TAG="MyRetrofit.CallBack";

    private IListenerRetrofitResponse listener;
    private Object request;
    public CallBackRetrofitResponse(IListenerRetrofitResponse listener)
    {
        this.listener= listener;
    }

    public CallBackRetrofitResponse(IListenerRetrofitResponse listener, Object request)
    {
        this.listener= listener;
        this.request = request;
    }

    public void setRequest(Object object)
    {
        this.request= object;
    }




    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {

        try
        {
            Log.d(TAG,">Response:" + (new Gson()).toJson(response));
            Log.d(TAG, ">Response Body:" + (new Gson()).toJson(response.body()));

            T result = response.body();
            if (response.raw().code() == 200) {
                this.listener.onSuccess(result, this.request);
                return;
            }
            this.listener.onFailureCode(result, this.request,response.raw().code());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            listener.onSuccessError(response.raw().body(), this.request,ex);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        Log.d(TAG,"INI>>>Trowable Service");
        t.printStackTrace();
        Log.d(TAG, "FIN>>>Trowable Service");


        MyExpectionRetrofit expection = new MyExpectionRetrofit();
        expection.setCause(t.getCause());
        expection.message=t.getMessage()+"";
        if(expection.message.isEmpty() && t.getCause()!=null)
        {
            expection.message = t.getCause().getMessage();
        }

        expection.code=-1;
        this.listener.onFailure(expection,this.request);
    }
}
