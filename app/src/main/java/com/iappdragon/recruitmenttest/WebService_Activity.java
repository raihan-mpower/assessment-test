package com.iappdragon.recruitmenttest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebService_Activity extends Activity {
    EditText name;
    EditText password;
    Button Login;
    SharedPreferences prefs;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = this.getSharedPreferences(
                "com.iappdragon.recruitmenttest", Context.MODE_PRIVATE);
        String is_key_available =prefs.getString("key","");
        if(is_key_available.equalsIgnoreCase("") || is_key_available==null){
            showlogin();
        }else{
            loadListView();
        }


    }

    private void showlogin() {
        setContentView(R.layout.login);
        name = (EditText)findViewById(R.id.name);
        password =(EditText)findViewById(R.id.password);
        Login = (Button)findViewById(R.id.loginbutton);

        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (new AsyncTask(){
                    ProgressDialog dialog;
                    String responseString = "";
                    String username = "";
                    String userpassword = "";
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        username = name.getText().toString();
                        userpassword = password.getText().toString();
                        dialog = ProgressDialog.show(WebService_Activity.this,"processing","please Wait");

                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        dialog.dismiss();
                        if(responseString!=null || !responseString.equalsIgnoreCase("")){
                            parseResponseStringAndGetKey(responseString);
                        }
                        String is_key_available =prefs.getString("key","");
                        if(is_key_available.equalsIgnoreCase("") || is_key_available==null){
                            Toast.makeText(WebService_Activity.this,"check internet connectivity or username password",Toast.LENGTH_LONG).show();
                            showlogin();
                        }else{
//                            setContentView(R.layout.activity_web_service_);
                            loadListView();
                        }
                    }

                    @Override
                    protected Object doInBackground(Object[] params) {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://mdm-dev.matchdayme.com/api/v1.0.0/auth/login/");

                        try {
                            // Add your data
                            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                            nameValuePairs.add(new BasicNameValuePair("username", username));
                            nameValuePairs.add(new BasicNameValuePair("password", userpassword));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                            // Execute HTTP Post Request
                            HttpResponse response = httpclient.execute(httppost);
                            HttpEntity entity = response.getEntity();
                            responseString = EntityUtils.toString(entity, "UTF-8");

                            Log.v("the response",responseString);

                        } catch (ClientProtocolException e) {
                            // TODO Auto-generated catch block
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                        }

                        return null;
                    }

                }).execute();

            }
        });

    }

    private void loadListView() {
        setContentView(R.layout.activity_web_service_);
        (new AsyncTask(){
            String responseString = "";
            @Override
            protected Object doInBackground(Object[] params) {
                prefs = WebService_Activity.this.getSharedPreferences(
                        "com.iappdragon.recruitmenttest", Context.MODE_PRIVATE);
                String is_key_available =prefs.getString("key","");
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://mdm-dev.matchdayme.com/api/v.1.0.0/teams/my/events/resources/462/?squad=0");
                post.setHeader("Accept", "application/json");
//                post.setHeader("User-Agent", "Apache-HttpClient/4.1 (java 1.5)");
                post.setHeader("Authorization",is_key_available);
                try {
                    HttpResponse response =client.execute(post);
                    HttpEntity entity = response.getEntity();
                    responseString = EntityUtils.toString(entity, "UTF-8");

                    Log.v("the response",responseString);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Toast.makeText(WebService_Activity.this,responseString,Toast.LENGTH_LONG).show();
            }
        }).execute();

    }

    private void parseResponseStringAndGetKey(String responseString) {
        try {
            JSONObject response = new JSONObject(responseString);
            String key = response.getString("key");
            Log.v("key",key);
            prefs.edit().putString("key",key).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
