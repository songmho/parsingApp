package com.example.songm.parsingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView)findViewById(R.id.txt);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?pageNo=1&numOfRows=400&ServiceKey=NCPIDFE%2F7buZ0eIVTd6x6iqFLtZRkGcVW%2FZuKO1g%2BM9cCN8YBQBmPIKzaP%2B9MTfyyNMhXDS3SkK8%2FjiyINYe0A%3D%3D&_returnType=json";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    List<HashMap<String,Object>> map =
                    JSONArray jsonObject = response.getJSONArray("list");

                    for(JSONObject o : jsonObject.size()){

                    }
                    String o = response.getString("totalCount");
                    if(jsonObject!=null)
                        text.setText(jsonObject.);
                    else
                        text.setText("널");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text.setText("뭔가 잘못됨");
            }
        });


        queue.add(jsonRequest);
    }
}
