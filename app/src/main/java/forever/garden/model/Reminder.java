package forever.garden.model;

public class Reminder {

    private String plantName;
    private String time;

    public Reminder() {
    }

    public Reminder(String plantName, String time) {
        this.plantName = plantName;
        this.time = time;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
