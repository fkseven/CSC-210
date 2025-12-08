import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plant extends Creature {

    public enum Longevity { SEASONAL, PERENNIAL }
    public enum StemType { WOODY, SOFT }
    public enum LeafType { EVERGREEN, DECIDUOUS }
    public enum GrowthForm { RUNNER, CLIMBER, TALL, SHORT, MOSS }

    // Component class to define a species
    public static class PlantSpecies {
        String name;
        Longevity longevity;
        StemType stem;
        LeafType leaf;
        GrowthForm form;
        boolean flowers;
        List<ReproductionBehavior> reproMethods;

        public PlantSpecies(String name,
                            Longevity longevity,
                            StemType stem,
                            LeafType leaf,
                            GrowthForm form,
                            boolean flowers,
                            List<ReproductionBehavior> reproMethods) {
            this.name = name;
            this.longevity = longevity;
            this.stem = stem;
            this.leaf = leaf;
            this.form = form;
            this.flowers = flowers;
            this.reproMethods = reproMethods;
        }

        @Override
        public String toString() {
            return "PlantSpecies{" +
                    "name='" + name + '\'' +
                    ", longevity=" + longevity +
                    ", stem=" + stem +
                    ", leaf=" + leaf +
                    ", form=" + form +
                    ", flowers=" + flowers +
                    ", reproCount=" + (reproMethods == null ? 0 : reproMethods.size()) +
                    '}';
        }
    }

    // Static registry
    private static Map<String, PlantSpecies> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String name, PlantSpecies definition) throws Exception {
        if (speciesRegistry.containsKey(name)) {
            throw new Exception("Plant species already exists: " + name);
        }
        speciesRegistry.put(name, definition);
        System.out.println("Registered plant species: " + definition);
    }

    private PlantSpecies species;
    private int age;

    // Overloaded constructors
    public Plant(String speciesName) {
        this(speciesName, speciesName + "_" + System.nanoTime(), 0);
    }

    public Plant(String speciesName, String individualName, int age) {
        super(speciesName, individualName, 50);
        species = speciesRegistry.get(speciesName);
        if (species == null) {
            throw new IllegalArgumentException("Unknown plant species: " + speciesName);
        }
        this.age = age;
    }

    @Override
    public void takeTurn() {
        age++;
        changeEnergy(-1);
    }

    @Override
    public void takeTurn(Tile tile) {
        age++;

        if (tile.getNutrients() > 50) {
            changeEnergy(+2);
        } else {
            changeEnergy(-1);
        }

        if (energy > 40 && species.reproMethods != null) {
            for (ReproductionBehavior r : species.reproMethods) {
                r.reproduce(this, tile);
            }
        }

        System.out.println(this + " age=" + age + " on " + tile);
    }
}