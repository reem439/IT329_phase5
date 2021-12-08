package forever.garden.model;

public class Plant {

    int id;
    String name;
    String description;
    String soil;
    String amendments;
    String watering;
    boolean favorite;
    float rating;

    public Plant(int id, String name, String description,
                 String soil, String amendments, String watering,
                 boolean favorite, float rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.soil = soil;
        this.amendments = amendments;
        this.watering = watering;
        this.favorite = favorite;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getAmendments() {
        return amendments;
    }

    public void setAmendments(String amendments) {
        this.amendments = amendments;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name;
    }
}
