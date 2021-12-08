package forever.garden;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import forever.garden.model.Plant;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);

        String json = getIntent().getExtras().getString("plant", null);
        Type type = new TypeToken<Plant>() {
        }.getType();
        Plant plant = new Gson().fromJson(json, type);

        TextView name = findViewById(R.id.name);
        TextView desc = findViewById(R.id.desc);
        TextView soil = findViewById(R.id.soil);
        TextView amendments = findViewById(R.id.amendments);
        TextView watering = findViewById(R.id.watering);

        name.setText(plant.getName());
        desc.setText(plant.getDescription());
        soil.setText(plant.getSoil());
        amendments.setText(plant.getAmendments());
        watering.setText(plant.getWatering());

    }

}
