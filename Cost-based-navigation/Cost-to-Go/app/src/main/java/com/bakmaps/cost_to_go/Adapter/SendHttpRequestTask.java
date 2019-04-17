package com.bakmaps.cost_to_go.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WORK with FUN on 03-02-2016.
 */
public class SendHttpRequestTask extends AsyncTask<String, String, String> {
    String url;
    Context context;

    SendHttpRequestTask(String url, Context context){
        this.url = url;
        this.context = context;
    }
    void postConnection(String url) {
        /** Create http cliient object to send request to server **/

        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();

        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(url);

        // Building post parameters, key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("email", "user@gmail.com"));
        nameValuePair.add(new BasicNameValuePair("password", "encrypted_password"));

        /** URL Encoding POST data :
         Before making HTTP request you need to encode the post data in order to convert all string data into valid url format.
         **/
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }


        //  execute httpPost using the httpClient created before.
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);

            // writing response to log
            Log.d("Http Response:", response.toString());

        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        //       return null;
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
      //  ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
          //  Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

        }
    }

