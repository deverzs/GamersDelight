package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracosta.cs134.gamersdelight.model.Game;
import edu.miracosta.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;
    private EditText nameGameEntered;
    private EditText descriptionEntered;
    private RatingBar ratingEntered;
   // private ImageView imageEntered;

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

        nameGameEntered = findViewById(R.id.nameEditText);
        descriptionEntered = findViewById(R.id.descriptionEditText) ;
        ratingEntered = findViewById(R.id.gameRatingBar);


        // TODO:  Read information from EditTexts and RatingBar,
        String nameGame = nameGameEntered.getText().toString() ;
        String descrGame = descriptionEntered.getText().toString();
        float ratingGame = ratingEntered.getRating() ;

        Log.i("MAIN" , "" + ratingGame) ;
        // TODO:  Create a new Game object then add it to the list
        Game addMe = new Game();

        addMe.setName(nameGame);
        addMe.setDescription(descrGame);
        addMe.setRating(ratingGame);
        addMe.setImageName("controller.png");



        // TODO:  Make sure the list adapter is notified
        gamesListAdapter.add(addMe);
        // TODO:  Clear all entries the user made (edit text and rating bar)
    }



}
