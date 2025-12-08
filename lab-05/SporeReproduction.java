public class SporeReproduction implements ReproductionBehavior {
    @Override
    public void reproduce(Plant plant, Tile tile) {
        if (tile.getWater() > 60) {
            Plant baby = new Plant(plant.getSpeciesName());
            tile.addCreature(baby);
            System.out.println(plant + " spreads spores -> " + baby);
        }
    }
}