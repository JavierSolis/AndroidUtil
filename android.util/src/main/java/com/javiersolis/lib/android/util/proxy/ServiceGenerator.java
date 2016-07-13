package com.javiersolis.lib.android.util.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javiersolis.lib.android.util.parser.DateTypeDeserializer;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 8/03/16.
 */

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseUrl)
    {
        Retrofit retrofit =getRetrofit(baseUrl);
        //Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    //public static Retrofit retrofit;
    private static Retrofit getRetrofit(String baseUrl)
    {
        /*
        if(retrofit!=null)
        {
            return retrofit;
        }
        */
        OkHttpClient httpClient = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();


        httpClient.setConnectTimeout(50, TimeUnit.SECONDS);
        httpClient.setWriteTimeout(50, TimeUnit.SECONDS);
        httpClient.setReadTimeout(50, TimeUnit.SECONDS);


        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);

        Gson gson; /*= new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();*/

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateTypeDeserializer());

        gson=gsonBuilder.create();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)

                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        //retrofit = builder.build();
        //return retrofit;
        return builder.build();
    }



}