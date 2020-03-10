package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import edu.miracosta.cs134.gamersdelight.model.Game;
import edu.miracosta.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DONE: Connect the ListView with the layout
        try {
            gamesList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // DONE:  Populate all games list using the JSONLoader
        gamesListView = findViewById(R.id.gameListView);

        // DONE:  Create a new ListAdapter connected to the correct layout file and list
        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList) ;

        // DONE:  Connect the ListView with the ListAdapter
        gamesListView.setAdapter(gamesListAdapter);



    }

    public void viewGameDetails(View view) {

        Game clickedGame = (Game) view.getTag();

        // DONE: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
        Intent intent = new Intent(this, GameDetailsActivity.class) ;

        //TODO: Key value pairs
        intent.putExtra("Name", clickedGame.getName());
        intent.putExtra("Description", clickedGame.getDescription()) ;
        intent.putExtra("Rating", clickedGame.getRating()) ;
        intent.putExtra("ImageName", clickedGame.getImageName()) ;

        startActivity(intent);

    }

    public void addGame(View view)
    {
        // TODO:  Read information from EditTexts and RatingBar,
        // TODO:  Create a new Game object then add it to the list
        // TODO:  Make sure the list adapter is notified
        // TODO:  Clear all entries the user made (edit text and rating bar)
    }



}
