public class SeedReproduction implements ReproductionBehavior {
    @Override
    public void reproduce(Plant plant, Tile tile) {
        if (tile.getNutrients() > 60) {
            Plant baby = new Plant(plant.getSpeciesName());
            tile.addCreature(baby);
            System.out.println(plant + " drops a seed -> " + baby);
        }
    }
}