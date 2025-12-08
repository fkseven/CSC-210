import java.util.Arrays;

public class SimulationMain {

    public static void main(String[] args) {

        // -------------------------------
        // 1) Register Plant Species
        // -------------------------------
        Plant.PlantSpecies oak = new Plant.PlantSpecies(
                "oak",
                Plant.Longevity.PERENNIAL,
                Plant.StemType.WOODY,
                Plant.LeafType.DECIDUOUS,
                Plant.GrowthForm.TALL,
                true,
                Arrays.asList(new SeedReproduction())
        );

        Plant.PlantSpecies moss = new Plant.PlantSpecies(
                "moss",
                Plant.Longevity.PERENNIAL,
                Plant.StemType.SOFT,
                Plant.LeafType.EVERGREEN,
                Plant.GrowthForm.MOSS,
                false,
                Arrays.asList(new SporeReproduction())
        );

        try {
            Plant.registerSpecies("oak", oak);
            Plant.registerSpecies("moss", moss);
        } catch (Exception e) {
            System.out.println("Error registering plant species: " + e.getMessage());
            return;
        }

        // -------------------------------
        // 2) Register Animal Species
        // -------------------------------
        AnimalSpecies sparrow = new AnimalSpecies("sparrow", false, 5, 30);
        AnimalSpecies deer = new AnimalSpecies("deer", false, -5, 25);
        AnimalSpecies trout = new AnimalSpecies("trout", true, 0, 20);

        try {
            Bird.registerSpecies("sparrow", sparrow);
            Mammal.registerSpecies("deer", deer);
            Fish.registerSpecies("trout", trout);
        } catch (Exception e) {
            System.out.println("Error registering animal species: " + e.getMessage());
            return;
        }

        // -------------------------------
        // 3) Build a Small 2x2 World
        // -------------------------------
        World world = new World(2, 2);

        Tile tile00 = new Tile(80, 15, 90);  // wet + rich
        tile00.addCreature(new Plant("oak"));
        tile00.addCreature(new Fish("trout"));

        Tile tile10 = new Tile(30, 20, 40);
        tile10.addCreature(new Plant("moss"));
        tile10.addCreature(new Bird("sparrow"));

        Tile tile01 = new Tile(50, 18, 60);
        tile01.addCreature(new Mammal("deer"));

        Tile tile11 = new Tile(70, 10, 30);

        world.setTile(0, 0, tile00);
        world.setTile(1, 0, tile10);
        world.setTile(0, 1, tile01);
        world.setTile(1, 1, tile11);

        // -------------------------------
        // 4) Run Simulation
        // -------------------------------
        for (int turn = 0; turn < 100; turn++) {
            System.out.println("==== TURN " + turn + " ====");
            world.takeTurn();
        }
    }
}