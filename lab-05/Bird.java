import java.util.HashMap;
import java.util.Map;

public class Bird extends Creature {

    private static Map<String, AnimalSpecies> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, AnimalSpecies spec) throws Exception {
        if (speciesRegistry.containsKey(speciesName)) {
            throw new Exception("Bird species already exists: " + speciesName);
        }
        speciesRegistry.put(speciesName, spec);
        System.out.println("Registered bird species: " + spec);
    }

    private AnimalSpecies species;

    public Bird(String speciesName) {
        super(speciesName);
        species = speciesRegistry.get(speciesName);
        if (species == null) {
            throw new IllegalArgumentException("Unknown bird species: " + speciesName);
        }
    }

    @Override
    public void takeTurn() {
        changeEnergy(-2);
    }

    @Override
    public void takeTurn(Tile tile) {
        int temp = tile.getTemperature();
        if (temp >= species.minTemp && temp <= species.maxTemp) {
            changeEnergy(+2);
        } else {
            changeEnergy(-4);
        }
        System.out.println(this + " flies over " + tile);
    }
}