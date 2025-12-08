public class CloningReproduction implements ReproductionBehavior {
    @Override
    public void reproduce(Plant plant, Tile tile) {
        Plant clone = new Plant(plant.getSpeciesName());
        tile.addCreature(clone);
        System.out.println(plant + " clones itself -> " + clone);
    }
}