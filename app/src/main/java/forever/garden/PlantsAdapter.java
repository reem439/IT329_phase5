package forever.garden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import forever.garden.model.Plant;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.ViewHolder> {

    public List<Plant> plants;
    public Context context;
    PlantClick plantClick;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        private final TextView name;
        private final TextView desc;
        private final ImageView fav;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            name = view.findViewById(R.id.name);
            desc = view.findViewById(R.id.desc);
            fav = view.findViewById(R.id.fav);
        }
    }

    public Plant getValueAt(int position) {
        return plants.get(position);
    }

    public PlantsAdapter(Context context, List<Plant> plants) {
        this.context = context;
        this.plantClick = (PlantClick) context;
        this.plants = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_plant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Plant plant = getValueAt(position);

        holder.name.setText(plant.getName());
        holder.desc.setText(plant.getDescription());

        if (plant.isFavorite()) {
            holder.fav.setImageResource(R.drawable.red_fav);
        } else {
            holder.fav.setImageResource(R.drawable.grey_fav);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantClick.onItemClicked(plant,position);
            }
        });

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantClick.onFavClicked(plant, position);
            }
        });

    }

    interface PlantClick {
        void onFavClicked(Plant plant, int pos);

        void onItemClicked(Plant plant,int pos);
    }
}


