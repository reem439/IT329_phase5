package forever.garden;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import forever.garden.model.Plant;
import forever.garden.model.SharedManger;

public class PlantsActivity extends AppCompatActivity implements PlantsAdapter.PlantClick {

    SharedManger sharedManger;
    List<Plant> plants;
    PlantsAdapter plantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        RecyclerView recyclerView = findViewById(R.id.rv);
        sharedManger = new SharedManger(this);
        plants = sharedManger.getPlants();
        plantsAdapter = new PlantsAdapter(this, plants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(plantsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onFavClicked(Plant plant, int pos) {
        if (plant.isFavorite()) {
            plant.setFavorite(false);
        } else {
            plant.setFavorite(true);
        }
        plants.set(pos, plant);
        plantsAdapter.plants = plants;
        plantsAdapter.notifyDataSetChanged();
        sharedManger.setPlants(plants);
    }

    @Override
    public void onItemClicked(Plant plant,int pos) {
        Intent intent = new Intent(PlantsActivity.this, DetailsActivity.class);
        intent.putExtra("plant", new Gson().toJson(plant));
        intent.putExtra("pos", pos);
        startActivity(intent);
    }
}
