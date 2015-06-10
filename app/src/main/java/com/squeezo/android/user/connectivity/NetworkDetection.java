package com.squeezo.android.user.connectivity;

import android.os.AsyncTask;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetworkDetection extends AsyncTask<Void, Void, Boolean> {

    private AsyncResponse listener;

    public NetworkDetection(AsyncResponse listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            //Method 1 - takes longer time to detect, but works always

            /*Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return (returnVal == 0);*/

            //Method 2 - instant detection, but always seems to return "no internet"

            /*HttpURLConnection urlc = (HttpURLConnection)(new URL("http://www.google.com").openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(6000);
            urlc.connect();
            return (urlc.getResponseCode() == 204);*/

            //Method 3 - instant detection, seems to work properly almost every time, but deprecated

            HttpGet httpGet = new HttpGet("http://www.google.com");
            HttpParams httpParameters = new BasicHttpParams();
            // Set the timeout in milliseconds until a connection is established.
            // The default value is zero, that means the timeout is not used.
            int timeoutConnection = 3000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            // Set the default socket timeout (SO_TIMEOUT)
            // in milliseconds which is the timeout for waiting for data.
            int timeoutSocket = 5000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            httpClient.execute(httpGet);
            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        listener.processFinish(aBoolean);
    }

}
