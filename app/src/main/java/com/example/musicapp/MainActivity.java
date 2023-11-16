package com.example.musicapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> pathurl=new ArrayList<>();


        String[] datas = {"sfgh", "Mohd", "Abid", "dgfghjk"};

//        pathurl.add("shaikh");
//        pathurl.add("Mohd");
//        pathurl.add("Abid");
//        pathurl.add("dgfghjk");
//        pathurl.add("xgjh");
//        pathurl.add("dfvs");
//        pathurl.add("whjvsbk");
//        pathurl.add("wjeahbf");
//        pathurl.add("JSWHAB");

        Bundle bundle = new Bundle();
//        ArrayList<String> list=new ArrayList<>();
//        list.add(String.valueOf(bundle.getStringArrayList("list")));
        ArrayList<Modelclass> list1 = new ArrayList<>();

        Log.i("check", "onCreate: "+list1);

        //Log.i("checklist", "onCreate: "+list1);

        Modelclass modelclass = new Modelclass();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    final String url = "https://deezerdevs-deezer.p.rapidapi.com/search/?rapidapi-key=abffaa1d81msh315a51badb4f741p1ed7eajsna004db75ec2f&q=all";

    RequestQueue queue = Volley.newRequestQueue(this);
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.v("arraydata", "run: " + jsonObject);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                   Log.i("testobject", "run: "+jsonObject1);
//                                   for(int j=0;j<jsonObject1.length();j++){
                                    String path = jsonObject1.getString("preview");
                                    pathurl.add(path);

//
//                                   JSONObject jsonObject2=jsonObject.getJSONObject("preview");
//                                    Log.i("test", "run: "+jsonObject1);
                                }
                                MyAdapter adapter = new MyAdapter(pathurl, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }).run();

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });

        queue.add(stringRequest);
    }


}