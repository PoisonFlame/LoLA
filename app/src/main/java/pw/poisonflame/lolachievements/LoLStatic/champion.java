package pw.poisonflame.lolachievements.LoLStatic;

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

import pw.poisonflame.lolachievements.VolleyCallBack;
/**
 * Created by Robby Sharma on 11/16/2016.
 */

public class champion {
    String apiKey;
    Context context;
    String url;
    VolleyCallBack callBack;
    RequestQueue requestQueue;

    public champion(String apiKey, Context c, VolleyCallBack callBack){
        this.apiKey = apiKey;
        this.context = c;
        this.callBack = callBack;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void loadChampionName(final int champId){
        this.url = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/"+champId+"?api_key=" + apiKey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String champName;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            champName = response.getString("key");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        callBack.onSuccessResponse(context, String.valueOf(champName),"load("+champId+")ChampName");

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

    void loadChampionTitle(final int champId){
        this.url = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/"+champId+"?api_key=" + apiKey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String champName;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            champName = response.getString("title");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        callBack.onSuccessResponse(context, String.valueOf(champName),"load("+champId+")ChampTitle");

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
//Do Stuff
    void loadChampionId(final String champName){
        this.url = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?api_key=" + apiKey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String champName;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            champName = response.getString("key");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        callBack.onSuccessResponse(context, String.valueOf(champName),"load("+champName+")ChampId");

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
