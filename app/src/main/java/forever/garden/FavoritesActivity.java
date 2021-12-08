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

public class FavoritesActivity extends AppCompatActivity implements PlantsAdapter.PlantClick {

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
        for (int i = 0; i < plants.size(); i++) {
            if (!plants.get(i).isFavorite()) {
                plants.remove(i);
                i--;
            }
        }
        plantsAdapter = new PlantsAdapter(this, plants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(plantsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onFavClicked(Plant plant, int pos) {

    }

    @Override
    public void onItemClicked(Plant plant) {
        Intent intent = new Intent(FavoritesActivity.this, DetailsActivity.class);
        intent.putExtra("plant", new Gson().toJson(plant));
        startActivity(intent);
    }
}
