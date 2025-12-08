public class AnimalSpecies {
    String name;
    boolean prefersWater;
    int minTemp;
    int maxTemp;

    public AnimalSpecies(String name, boolean prefersWater, int minTemp, int maxTemp) {
        this.name = name;
        this.prefersWater = prefersWater;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    @Override
    public String toString() {
        return "AnimalSpecies{" +
                "name='" + name + '\'' +
                ", prefersWater=" + prefersWater +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                '}';
    }
}