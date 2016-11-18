package pw.poisonflame.lolachievements;

/**
 * Created by Robby Sharma on 11/15/2016.
 */

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class championMasteryData implements VolleyCallBack{
    RequestQueue requestQueue;
    int numOfChamps;
    String api;
    String summonerName;
    Context context;
    String summonerID;
    VolleyCallBack callBack;
    int specifiedLevel;
    int championID;
    Boolean loadNumOfPlayedChamps,loadNumOfSomeLevelChamps,loadTotalMasteryPoints,loadChampMasteryPoints,loadHighestMasteryPoints;


     championMasteryData(String api, String summonerName, Context c, VolleyCallBack callback){
        this.api = api;
        this.callBack = callback;
        this.summonerName = summonerName;
        this.context = c;
         summonerInfo summonerInfo = new summonerInfo(api,summonerName,c, this);
         summonerInfo.loadSummonerId();
         requestQueue = Volley.newRequestQueue(context);
    }

    void loadNumOfPlayedChamps(){
            loadNumOfPlayedChamps = true;
        if(summonerID != null) {
            String apiKey = api;
            String summoner = summonerName;
            String url = "https://na.api.pvp.net/championmastery/location/NA1/player/" + summonerID + "/champions?api_key=" + apiKey;
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //String length = (String)response.length();
                            Log.d("xD", String.valueOf(response.length()));
                            callBack.onSuccessResponse(context, String.valueOf(response.length()), "numOfChamps");
                            numOfChamps = response.length();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("xD", "Error: " + error.toString());
                }
            }


            );
            requestQueue.add(jsonArrayRequest);
        }

    }

    void loadNumOfSomeLevelChamps(final int specifiedLevel){
        loadNumOfSomeLevelChamps = true;
        this.specifiedLevel = specifiedLevel;
        if(summonerID != null) {
            String apiKey = api;
            String summoner = summonerName;
            String url = "https://na.api.pvp.net/championmastery/location/NA1/player/" + summonerID + "/champions?api_key=" + apiKey;
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        int meetingNum;

                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    String level = response.getJSONObject(i).getString("championLevel");
                                    if (level == String.valueOf(specifiedLevel)) {
                                        meetingNum += 1;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                callBack.onSuccessResponse(context, String.valueOf(meetingNum), "numOfLevel" + specifiedLevel + "Champs");
                            }
                            //callBack.onSuccessResponse(context, String.valueOf(response.length()),"numOfChamps");

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("xD", "Error: " + error.toString());
                }
            }


            );
            requestQueue.add(jsonArrayRequest);
        }
    }

    void loadTotalMasteryPoints(){
        loadTotalMasteryPoints = true;
        if(summonerID != null) {
            String apiKey = api;
            String summoner = summonerName;
            String url = "https://na.api.pvp.net/championmastery/location/NA1/player/" + summonerID + "/champions?api_key=" + apiKey;
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        int points;

                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    int champPoints = response.getJSONObject(i).getInt("championPoints");
                                    points += champPoints;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                callBack.onSuccessResponse(context, String.valueOf(points), "totalMasteryPoints");
                            }
                            //callBack.onSuccessResponse(context, String.valueOf(response.length()),"numOfChamps");

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("xD", "Error: " + error.toString());
                }
            }


            );
            requestQueue.add(jsonArrayRequest);
        }
    }

    void loadChampMasteryPoints(final int championID){
        loadChampMasteryPoints = true;
        this.championID = championID;
        if(summonerID != null) {
            String apiKey = api;
            String summoner = summonerName;
            String url = "https://na.api.pvp.net/championmastery/location/NA1/player/" + summonerID + "/champions?api_key=" + apiKey;
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        int points;

                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    int championIds = response.getJSONObject(i).getInt("championId");
                                    if (championIds == championID) {
                                        points = response.getJSONObject(i).getInt("championPoints");
                                        //Log.d("xD",String.valueOf(points));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                callBack.onSuccessResponse(context, String.valueOf(points), "champ" + championID + "MasteryPoints");
                            }
                            //callBack.onSuccessResponse(context, String.valueOf(response.length()),"numOfChamps");

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("xD", "Error: " + error.toString());
                }
            }


            );
            requestQueue.add(jsonArrayRequest);
        }
    }

    void loadHighestMasteryPoints(){
        loadHighestMasteryPoints = true;
        if(summonerID != null) {
            String apiKey = api;
            String summoner = summonerName;
            String url = "https://na.api.pvp.net/championmastery/location/NA1/player/" + summonerID + "/champions?api_key=" + apiKey;
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        int points;
                        int biggestChampID;

                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    int championPoints = response.getJSONObject(i).getInt("championPoints");
                                    if (championPoints > points) {
                                        points = response.getJSONObject(i).getInt("championPoints");
                                        biggestChampID = response.getJSONObject(i).getInt("championId");
                                        //Log.d("xD",String.valueOf(points));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                callBack.onSuccessResponse(context, String.valueOf(points), "highestMasteryPoints");
                                //callBack.onSuccessResponse(context, String.valueOf(name), "highestMasteryPointsName");
                                //champion champion = new champion(api,context,callBack);
                                //champion.loadChampionName(biggestChampID);
                            }
                            //callBack.onSuccessResponse(context, String.valueOf(response.length()),"numOfChamps");

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("xD", "Error: " + error.toString());
                }
            }


            );
            requestQueue.add(jsonArrayRequest);
        }
    }

    @Override
    public void onSuccessResponse(Context context, String result, String getData) {
        if (getData == "summonerId") {
            summonerID = result.toString();
        }else{
            //callBack.onSuccessResponse(context, result.toString(), "highestMasteryPointsName");
        }
        Log.d("ChmpMas",result.toString());
//        if(chosenMethod ==1){
//            loadNumOfPlayedChamps();
//        }else if(chosenMethod ==2){
//            loadNumOfSomeLevelChamps(specifiedLevel);
//        }else if(chosenMethod ==3){
//            loadTotalMasteryPoints();
//        }else if(chosenMethod ==4){
//            loadChampMasteryPoints(championID);
//        }else if(chosenMethod ==5){
//            loadHighestMasteryPoints();
//        }

        if(loadNumOfPlayedChamps == true){
            loadNumOfPlayedChamps();
        }
        if(loadNumOfSomeLevelChamps == true){
            loadNumOfSomeLevelChamps(specifiedLevel);
        }
        if(loadTotalMasteryPoints == true){
            loadTotalMasteryPoints();
        }
        if(loadChampMasteryPoints  == true){
            loadChampMasteryPoints(championID);
        }
        if(loadHighestMasteryPoints == true){
            loadHighestMasteryPoints();
        }


    }
}