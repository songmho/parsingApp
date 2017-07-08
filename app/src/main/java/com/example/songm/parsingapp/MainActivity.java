package com.example.songm.parsingapp;

import android.content.SharedPreferences;
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

        final SharedPreferences preferences =getSharedPreferences("Pref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonObject = response.getJSONArray("list");
                    JSONArray result = new JSONArray();


                    if(jsonObject!=null) {
                        text.setText("" + jsonObject.length());
                        for(int i = 0; i<jsonObject.length();i++){
                            JSONObject o = jsonObject.getJSONObject(i);

                            String addr = o.getString("addr");
                            double dmX = o.getDouble("dmX");
                            double dmY = o.getDouble("dmY");
                            String oper = o.getString("oper");
                            String stationName = o.getString("stationName");

                            text.setText(addr+"    "+dmX+"    "+dmY+"   "+oper+"   "+stationName);

                            JSONObject curObs = new JSONObject();
                            curObs.put("addr",addr);
                            curObs.put("dmX",dmX);
                            curObs.put("dmY",dmY);
                            curObs.put("oper",oper);
                            curObs.put("stationName",stationName);

                            result.put(curObs);

                        }

                        editor.putString("Observe",result.toString());
                        editor.commit();

                        text.setText(""+preferences.getString("Observe","null"));
                    }
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
