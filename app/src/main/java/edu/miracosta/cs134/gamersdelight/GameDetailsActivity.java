package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    ImageView imageDetails;
    TextView nameDetails;
    TextView descriptionDetails;
    RatingBar ratingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);


        imageDetails = findViewById(R.id.gameDetailsImageView);
        nameDetails = findViewById(R.id.gameDetailsNameTextView);
        descriptionDetails = findViewById(R.id.gameDetailsDescriptionTextView) ;
        ratingDetails = findViewById(R.id.gameDetailsRatingBar) ;

        // DONE:  Use the Intent object sent from MainActivity to populate the Views on

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String description = intent.getStringExtra("Description");
        String imageName = intent.getStringExtra("ImageName");
        double rating = intent.getDoubleExtra("Rating", 0f);

        // DONE:  this layout, including the TextViews, RatingBar and ImageView with the Game details.

        try {
            InputStream stream = getAssets().open(imageName);
            Drawable image = Drawable.createFromStream(stream, imageName);
            imageDetails.setImageDrawable(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        nameDetails.setText(name);
        descriptionDetails.setText(description);
        ratingDetails.setRating((float) rating);
    }
}
