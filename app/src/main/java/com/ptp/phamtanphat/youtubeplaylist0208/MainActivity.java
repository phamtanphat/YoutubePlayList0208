package com.ptp.phamtanphat.youtubeplaylist0208;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String Youtube_API_Key = "AIzaSyAP2FTlcyJPYQxY2wcBAtoYPzp8jUF69CA";
    String IDPlayList = "PLzrVYRai0riSRJ3M3bifVWWRq5eJMu6tv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVideoYoutube(IDPlayList);
    }

    private void getVideoYoutube(String idPlayList) {
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+idPlayList+"&key=AIzaSyAP2FTlcyJPYQxY2wcBAtoYPzp8jUF69CA&maxResults=50";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0 ; i<jsonArray.length();i++){
                        JSONObject jsonObjectitem = jsonArray.getJSONObject(i);

                        JSONObject jsonObjectsnippet = jsonObjectitem.getJSONObject("snippet");
                        String title = jsonObjectsnippet.getString("title");

                        JSONObject jsonObjectthumnail = jsonObjectsnippet.getJSONObject("thumbnails");
                        JSONObject jsonObjectdefault = jsonObjectthumnail.getJSONObject("default");
                        String urlimage = jsonObjectdefault.getString("url");

                        JSONObject jsonObjectresourceId = jsonObjectsnippet.getJSONObject("resourceId");
                        String resourceid = jsonObjectresourceId.getString("videoId");

                        Log.d("BBBB",resourceid.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
