import java.util.HashMap;
import java.util.Map;

public class Mammal extends Creature {

    private static Map<String, AnimalSpecies> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, AnimalSpecies species) throws Exception {
        if (speciesRegistry.containsKey(speciesName)) {
            throw new Exception("Mammal species already exists: " + speciesName);
        }
        speciesRegistry.put(speciesName, species);
        System.out.println("Registered mammal species: " + species);
    }

    private AnimalSpecies species;

    public Mammal(String speciesName) {
        super(speciesName);
        species = speciesRegistry.get(speciesName);
        if (species == null) {
            throw new IllegalArgumentException("Unknown mammal species: " + speciesName);
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

        System.out.println(this + " moves across " + tile);
    }
}