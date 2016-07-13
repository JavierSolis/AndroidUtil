package com.javiersolis.lib.android.util.proxy;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 8/03/16.
 */
public class MyExpectionRetrofit {
    public String message;
    public int code;
    public Throwable cause;

    public void setCause(Throwable t)
    {
        this.cause=t;
    }
}
