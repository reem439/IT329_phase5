package forever.garden;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import forever.garden.model.Plant;
import forever.garden.model.SharedManger;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);

        String json = getIntent().getExtras().getString("plant", null);
        Type type = new TypeToken<Plant>() {
        }.getType();
        Plant plant = new Gson().fromJson(json, type);

        int pos = getIntent().getExtras().getInt("pos",0);

        TextView name = findViewById(R.id.name);
        TextView desc = findViewById(R.id.desc);
        TextView soil = findViewById(R.id.soil);
        TextView amendments = findViewById(R.id.amendments);
        TextView watering = findViewById(R.id.watering);
        AppCompatRatingBar appCompatRatingBar = findViewById(R.id.rating);

        name.setText(plant.getName());
        desc.setText(plant.getDescription());
        soil.setText(plant.getSoil());
        amendments.setText(plant.getAmendments());
        watering.setText(plant.getWatering());
        appCompatRatingBar.setRating(plant.getRating());
        appCompatRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                plant.setRating(rating);
                SharedManger sharedManger = new SharedManger(DetailsActivity.this);
                List<Plant> plants = sharedManger.getPlants();
                plants.set(pos,plant);
                sharedManger.setPlants(plants);
            }
        });

    }

}
