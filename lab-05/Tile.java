import java.util.ArrayList;
import java.util.List;

public class Tile implements TurnTaker {

    private int water;
    private int temperature;
    private int nutrients;
    private List<Creature> creatures = new ArrayList<>();

    public Tile(int water, int temperature, int nutrients) {
        this.water = water;
        this.temperature = temperature;
        this.nutrients = nutrients;
    }

    public int getWater() { return water; }
    public int getTemperature() { return temperature; }
    public int getNutrients() { return nutrients; }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    @Override
    public void takeTurn() {
        // Copy to avoid modification while iterating
        ArrayList<Creature> copy = new ArrayList<>(creatures);
        for (Creature c : copy) {
            c.takeTurn(this);
        }
    }

    @Override
    public String toString() {
        return "Tile(w=" + water + ", t=" + temperature + ", n=" + nutrients +
                ", creatures=" + creatures.size() + ")";
    }
}