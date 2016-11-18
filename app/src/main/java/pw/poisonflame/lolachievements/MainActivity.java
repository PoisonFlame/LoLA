package pw.poisonflame.lolachievements;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements VolleyCallBack {
    TextView test,test2,test3,test4,test5,test6,test7;
    String apiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView)findViewById(R.id.test);
        test2 = (TextView)findViewById(R.id.test2);
        test3 = (TextView)findViewById(R.id.test3);
        test4 = (TextView)findViewById(R.id.test4);
        test5 = (TextView)findViewById(R.id.test5);
        test6 = (TextView)findViewById(R.id.test6);
        apiKey = "563b6bc9-5ff0-4b6d-adaf-b08bf5b2f7ab";
        championMasteryData championMasteryData = new championMasteryData(apiKey,"poisonflame",getBaseContext(),this);
        summonerInfo summonerInfo = new summonerInfo(apiKey,"poisonflame",getBaseContext(),this);
        championMasteryData.loadNumOfPlayedChamps();
        championMasteryData.loadNumOfSomeLevelChamps(5);
        championMasteryData.loadTotalMasteryPoints();
        championMasteryData.loadChampMasteryPoints(89);
        championMasteryData.loadHighestMasteryPoints();
        summonerInfo.loadSummonerId();
    }

    public void onSuccessResponse(Context context, String response, String getRequest){

        switch (getRequest){
            case "numOfChamps":
                test.setText("Total Number of Champs Played: " + response + '\n');
                break;
            case "numOfLevel5Champs":
                test2.setText("Total Lev5 Champs: " + response + '\n');
                break;
            case "totalMasteryPoints":
                test3.setText("Total Mastery Points: " + response + '\n');
                break;
            case "champ89MasteryPoints":
                test4.setText("Leona Mastery Points: " + response + '\n');
                break;
            case "highestMasteryPoints":
                test5.setText("Highest Mastery Point on Any Champ: " + response + '\n');
                break;
            case "summonerId":
                test6.setText("Summoner ID: " + response + '\n');
                break;
        }
        //Log.e("HelloSir", "showResponse: " + response);
    }

}
