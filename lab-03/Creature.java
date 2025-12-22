public class Creature {
    private String name;
    private double weight;
    private String color;

    public Creature(String name, double weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    // Copy constructor
    public Creature(Creature other) {
        this.name = other.name;
        this.weight = other.weight;
        this.color = other.color;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getColor() { return color; }

    public void setName(String name) { this.name = name; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setColor(String color) { this.color = color; }

    public String toCSV() {
        return name + "," + weight + "," + color;
    }

    public static Creature fromCSV(String line) {
        String[] parts = line.split(",");
        return new Creature(
                parts[0],
                Double.parseDouble(parts[1]),
                parts[2]
        );
    }

    @Override
    public String toString() {
        return "Creature{name='" + name + "', weight=" + weight + ", color='" + color + "'}";
    }
}