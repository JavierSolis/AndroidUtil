package com.javiersolis.lib.android.util.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.javiersolis.lib.android.util.text.Utiltext;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 3/11/15.
 */
public class UtilLocation {

    public static Address getAddressName(Context context, double lat, double lng) throws Exception {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);

            if(addresses.size()<1)
            {
                return null;
            }
            Address obj = addresses.get(0);

            Log.i("DIRECCION_LUGAR_JSON",(new Gson()).toJson(obj));

            try {
                if(obj.getSubAdminArea()!=null)
                {
                    Log.i("DIRECCION_LUGAR DEP-SUB", obj.getSubAdminArea()+"");//DEPARTAMENT
                }
                Log.i("DIRECCION_LUGAR DEP", obj.getAdminArea()+"");//DEPARTAMENT
                Log.i("DIRECCION_LUGAR DIS", obj.getLocality()+"");//DISTRITO
                Log.i("DIRECCION_LUGAR PAI", obj.getCountryName()+"");//PAIS
                Log.i("DIRECCION_LUGAR", obj.getLocale().getISO3Country()+"");
            }catch (Exception ex){ex.printStackTrace();}

            return obj;

            /*
            String add=obj.getAddressLine(0);
            for(int i=1;i<=obj.getMaxAddressLineIndex();i++)
            {
                add=add+","+obj.getAddressLine(i);
            }

            Log.i("DIRECCION_LUGAR",add);
            Log.i("DIRECCION_LUGAR_JSON",(new Gson()).toJson(obj));
            Log.i("DIRECCION_LUGAR",obj.getLocality());//CIUDAD
            Log.i("DIRECCION_LUGAR",obj.getCountryName());//PAIS
            Log.i("DIRECCION_LUGAR",obj.getLocale().getISO3Country());



            return add;^
            */
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void getAddressNameAsync(Context context, double latitude, final double longitude, IGetAdressName iGetAdressName)
    {
        new AsyncTask<double[], Void, Address>() {
            private IGetAdressName iGetAdressName;
            private Context context;
            private double[] location;

            @Override
            protected Address doInBackground(double[]... params) {
                double[] location = params[0];

                try {
                    Address direcccion = UtilLocation.getAddressName(this.context,location[0],location[1]);
                    if(direcccion==null)
                    {
                        return null;
                    }

                    return direcccion;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Address s) {
                //super.onPostExecute(s);
                this.iGetAdressName.onResultAddressName(location[0],location[1],s);
            }

            public void init(Context context,IGetAdressName iGetAdressName,double[] location)
            {
                this.iGetAdressName = iGetAdressName;
                this.context = context;
                this.location = location;
                execute(location);
            }

        }.init(context, iGetAdressName, new double[]{latitude,longitude});
    }


    public static void getDistance(double posIniLat, double posIniLon , double posFinLat, double posFinLon, String mode)
    {

    }

    public class DistanceTask extends AsyncTask<Void,Void,Void>
    {
        private final double posIniLat;
        private final double posIniLon;
        private final double posFinLat;
        private final double posFinLon;
        private final String mode;

        public DistanceTask(double posIniLat, double posIniLon, double posFinLat, double posFinLon, String mode)
        {
            super();
            this.posIniLat = posIniLat;
            this.posIniLon = posIniLon;
            this.posFinLat = posFinLat;
            this.posFinLon = posFinLon;
            this.mode = mode;
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }


    public static String getDistanceInfo(double posIniLat, double posIniLon, double posFinLat, double posFinLon, String mode) {
        try {
            // Create a URL for the desired page
            String url="http://maps.googleapis.com/maps/api/directions/json?origin="+posIniLat+","+posIniLon+"&destination="+posFinLat+","+posFinLon+"&sensor=false&mode="+mode;

            String content = Utiltext.getTextfromURL(url);
            JSONObject jsonObject = new JSONObject();


                jsonObject = new JSONObject(content.toString());

                String status = jsonObject.getString("status");

            if(status.equals("ZERO_RESULTS"))
            {
                return "--";
            }

            //ﬁﬁ
                JSONArray array = jsonObject.getJSONArray("routes");

                JSONObject routes = array.getJSONObject(0);

                JSONArray legs = routes.getJSONArray("legs");

                JSONObject steps = legs.getJSONObject(0);

                JSONObject distance = steps.getJSONObject("distance");

                Log.i("Distance", distance.toString());


            return distance.getString("text");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
