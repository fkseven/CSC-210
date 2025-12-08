public abstract class Creature implements TurnTaker {

    protected String speciesName;
    protected String individualName;
    protected int energy;

    public Creature(String speciesName) {
        this(speciesName, speciesName + "_" + System.nanoTime(), 100);
    }

    public Creature(String speciesName, String individualName, int energy) {
        this.speciesName = speciesName;
        this.individualName = individualName;
        this.energy = energy;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getIndividualName() {
        return individualName;
    }

    public int getEnergy() {
        return energy;
    }

    public void changeEnergy(int delta) {
        energy += delta;
    }

    @Override
    public abstract void takeTurn();

    public void takeTurn(Tile tile) {
        takeTurn();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "(" + individualName
                + ", species=" + speciesName
                + ", energy=" + energy + ")";
    }
}