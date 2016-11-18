package pw.poisonflame.lolachievements;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Robby Sharma on 11/16/2016.
 */

public class summonerInfo {
    String api;
    String summonerName;
    Context context;
    VolleyCallBack callBack;
    String url;
    RequestQueue requestQueue;
    summonerInfo(String api, String summonerName, Context c, VolleyCallBack callBack){
        this.api = api;
        this.summonerName = summonerName;
        this.context = c;
        this.callBack = callBack;
        this.url = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/"+ summonerName +"?api_key=" + api;
        requestQueue = Volley.newRequestQueue(context);
    }

    void loadSummonerId() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String summonerId;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            summonerId = response.getJSONObject(summonerName.toLowerCase().replace(" ","")).getString("id");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        callBack.onSuccessResponse(context, String.valueOf(summonerId),"summonerId");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("xD","Error: " + error.toString());
            }
        }


        );
        requestQueue.add(jsonObjectRequest);
    }

    void loadSummonerName() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String summonerName;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            summonerName = response.getJSONObject(summonerName.toLowerCase().replace(" ","")).getString("name");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        callBack.onSuccessResponse(context, String.valueOf(summonerName),"summonerName");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("xD","Error: " + error.toString());
            }
        }


        );
        requestQueue.add(jsonObjectRequest);
    }

    void loadSummonerLevel() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String level;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            level = response.getJSONObject(summonerName.toLowerCase().replace(" ","")).getString("summonerLevel");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        callBack.onSuccessResponse(context, String.valueOf(level),"summonerLevel");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("xD","Error: " + error.toString());
            }
        }


        );
        requestQueue.add(jsonObjectRequest);
    }

}
