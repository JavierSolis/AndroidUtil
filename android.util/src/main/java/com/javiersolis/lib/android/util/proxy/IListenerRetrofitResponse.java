package com.javiersolis.lib.android.util.proxy;

import com.squareup.okhttp.ResponseBody;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 8/03/16.
 */
public interface IListenerRetrofitResponse {
    public void onSuccess(Object response, Object request);
    public void onFailure(MyExpectionRetrofit taxiExpection, Object request);
    public void onSuccessError(ResponseBody body, Object request, Exception ex);

    public void onFailureCode(Object result, Object request, int code);
}
