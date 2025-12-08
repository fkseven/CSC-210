import java.util.HashMap;
import java.util.Map;

public class Fish extends Creature {

    private static Map<String, AnimalSpecies> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, AnimalSpecies species) throws Exception {
        if (speciesRegistry.containsKey(speciesName)) {
            throw new Exception("Fish species already exists: " + speciesName);
        }
        speciesRegistry.put(speciesName, species);
        System.out.println("Registered fish species: " + species);
    }

    private AnimalSpecies species;

    public Fish(String speciesName) {
        super(speciesName);
        species = speciesRegistry.get(speciesName);
        if (species == null) {
            throw new IllegalArgumentException("Unknown fish species: " + speciesName);
        }
    }

    @Override
    public void takeTurn() {
        changeEnergy(-3);
    }

    @Override
    public void takeTurn(Tile tile) {
        int water = tile.getWater();
        int temp = tile.getTemperature();

        if (water > 60 && temp >= species.minTemp && temp <= species.maxTemp) {
            changeEnergy(+3);
        } else {
            changeEnergy(-5);
        }

        System.out.println(this + " swims in " + tile);
    }
}