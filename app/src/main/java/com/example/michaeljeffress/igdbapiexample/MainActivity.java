package com.example.michaeljeffress.igdbapiexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michaeljeffress.igdbapiexample.models.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.michaeljeffress.igdbapiexample.AppData.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView nameView;
    private TextView releaseDateView;
    private String gameName;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_game);
        button = (Button) findViewById(R.id.button);

        nameView = (TextView) findViewById(R.id.name);
        releaseDateView = (TextView) findViewById(R.id.releaseDate);

//        HttpResponse<String> response = Unirest.get("https://igdbcom-internet-game-database-v1.p.mashape.com/games/?fields=name&limit=10&offset=0&order=release_dates.date%3Adesc&search=zelda")
//                .header("X-Mashape-Key", "6yTDKncVSomshYYc8QtijLfDRNx4p1qVYt4jsnlZ7ar8erjgpO")
//                .header("Accept", "application/json")
//                .asString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameName = editText.getText().toString();
                getGameInfo(gameName);
            }
        });

    }

    protected void getGameInfo(String gameName) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IGDBService service = retrofit.create(IGDBService.class);

            Call<List<Game>> call = service.getGame(AppData.APP_KEY,
                    "name,developer,platform" , 10, 0, "release_dates.date:desc", gameName);

            Log.d(TAG, "getGameInfo: gameName = " + gameName + "   <–––> API key = " + AppData.APP_KEY);

            call.enqueue(new Callback<List<Game>>() {
                @Override
                public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                    Log.d(TAG,"Call completed!");
                    try {
                        Game game = response.body().get(0);

                        String gameNameAfterApiCall = game.getName();

                        Log.d(TAG, "onResponse: gameNameAfterApiCall = " + gameNameAfterApiCall);

                        List finalReleaseDates = game.getReleaseDates();

                        Log.d(TAG, "onResponse: finalReleaseDates = " + finalReleaseDates);


                        nameView.setText("Game Name: " + gameNameAfterApiCall);
                        releaseDateView.setText("Release Date: " + finalReleaseDates.get(0));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<List<Game>> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "No network connection", Toast.LENGTH_LONG).show();
        }
    }

}
