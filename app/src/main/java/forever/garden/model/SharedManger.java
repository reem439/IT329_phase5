package forever.garden.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedManger {

    private static final String FILE_NAME = "garden";

    private SharedPreferences mSharedPreferences;

    public SharedManger(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setReminderValues(List<Reminder> reminders) {
        mSharedPreferences.edit().putString("reminders", new Gson().toJson(reminders)).apply();
    }

    public List<Reminder> getReminderValues() {
        String json = mSharedPreferences.getString("reminders", null);
        Type type = new TypeToken<List<Reminder>>() {
        }.getType();
        if (json != null)
            return new Gson().fromJson(json, type);
        else
            return new ArrayList<>();
    }

    public void setPlants(List<Plant> plants) {
        mSharedPreferences.edit().putString("plants", new Gson().toJson(plants)).apply();
    }

    public List<Plant> getPlants() {
        String json = mSharedPreferences.getString("plants", getPlantsDaTa());
        Type type = new TypeToken<List<Plant>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    private String getPlantsDaTa() {
        List<Plant> plants = new ArrayList<>();

        Plant plant1 = new Plant(1, "Marigolds", "Marigold seeds germinate quickly, within just a few days, and bloom in about 8 weeks. This quick sense of satisfaction makes them a great first-time gardening project for kids and garden newbies. Not to mention, marigolds are a great companion in your vegetable garden and can help protect your veggies from predators and pests. ", "Marigolds aren’t too picky about their soil, but will be their best in moderately fertile, well-drained soil. If planted in clay soil or an area that doesn’t drain well, they may not perform as expected. ", "When transplanting, a 5-10-5 fertilizer can be added, but is completely optional.", "It is best to water marigolds at the base of the plant and not from overhead. Allow the soil to dry somewhat between waterings, but do water regularly in high heat or dry weather.", false,0);
        plants.add(plant1);
        Plant plant2 = new Plant(2, "ZINNIAS", "They grow quickly and reliably, making them a great choice for first-time flower growers. Add in their low maintenance requirements and the variety of sizes and shapes, and you can’t lose. ", "While zinnias aren’t particularly picky about their soil, they do prefer it to be fertile, humus-rich, well-drained and with a pH of 5.5 to 7.5. ", "Add compost to the area where zinnias will be planted to enrich the soil.", "Water regularly, for a total of about 1 inch per week. Although they can tolerate dry conditions, they will do much better with consistent moisture in the soil. ", false,0);
        plants.add(plant2);
        Plant plant3 = new Plant(3, "PETUNIA", "is an ornamental plant whose showy trumpet-shaped flowers make it popular for summer flower beds and window boxes. Petunia species are mostly annual herbs. The leaves are sessile (e.g., lacking a petiole, or leaf stem) and are usually oval-shaped with smooth margins; some feature fine sticky hairs.", "They grow best in a light, fertile soil that provides good drainage and is slightly acidic (pH 6 to 6.5). In heavy clay soils, work some compost, or other organic matter into the ground before planting. ", "To keep petunias blooming vigorously throughout the summer, apply a monthly dose of liquid fertilizer or a slow-release granular fertilizer at the time of planting. ", "Petunias need regular watering because their shallow root systems dry out quickly. However, make sure the soil is well drained because soggy soil can cause root rot and yellowed foliage.", false,0);
        plants.add(plant3);
        Plant plant4 = new Plant(4, "CALIBRACHOA", "This petunia look-alike is a relative newcomer, first becoming available to home gardeners in the 1990s under the name Million Bells®. Breeding breakthroughs have resulted in a wider range of colors, bigger flowers, and increased vigor.", "For containers and hanging baskets, use a quality all-purpose potting mix that drains well. For bedding displays, soil should be well amended and drain quickly.", "Calibrachoa plants are heavy feeders. Nutrients are quickly leached from the soil by frequent watering, so fertilize regularly for continuous bloom.", "Although fairly drought tolerant, proper watering is essential for best performance. During hot weather, containers can dry out quickly, so they should be checked regularly—even daily. ", false,0);
        plants.add(plant4);
        Plant plant5 = new Plant(5, "Geranium", "Geranium are easy-care abundant bloomers whose bright flowers will blossom from spring until fall. Geranium plants are grown as annuals in most zones, but are considered evergreen perennials in zones 10 and 11. Although commonly called geranium, this well-known potted plant is actually a Pelargonium.", "Geraniums prefer fertile, neutral to alkaline, well-drained soil.", "Apply a balanced liquid fertilizer monthly in spring and early summer.", "Although geraniums have fairly low water requirements once established, consistent watering is best. Not enough water can cause leaf drop and wilting and too much water can cause them to get spindly.", false,0);
        plants.add(plant5);

        return new Gson().toJson(plants);
    }

}
