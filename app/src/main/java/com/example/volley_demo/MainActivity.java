package com.example.volley_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley_demo.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String url="https://jsonplaceholder.typicode.com/comments";
    ArrayList<model_class> detalist =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RequestQueue queue = Volley.newRequestQueue(this);
       if (isOnline())
       {
           StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   try {
                       JSONArray array = new JSONArray(response);
                       for (int i=0;i<array.length();i++)
                       {
                           String name;
                           String email;
                           String body;
                           JSONObject mainObj=array.getJSONObject(i);
                           name=mainObj.getString("name");
                           email=mainObj.getString("email");
                           body=mainObj.getString("body");
                           model_class model_class = new model_class(name,email,body);
                           detalist.add(model_class);
                       }
                       recycler_adapter adapter = new recycler_adapter(MainActivity.this,detalist);
                       LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false);
                       binding.recyclerView.setLayoutManager(layoutManager);
                       binding.swipeRefresh.setRefreshing(true);
                       binding.recyclerView.setAdapter(adapter);
                   } catch (JSONException e) {
                       throw new RuntimeException(e);
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(stringRequest);

       }
       else {
           finish();
       }
    }

    private boolean isOnline()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo info = manager.getActiveNetworkInfo();
         return(info !=null && info.isConnected());
    }
}