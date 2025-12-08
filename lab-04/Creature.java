public class Creature {
    private String name;
    private int size;

    public Creature(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void eat(String food) {
        System.out.println(name + " eats " + food + ".");
    }

    public void talk(String words) {
        System.out.println(name + " says: \"" + words + "\"");
    }

    public void move(String direction) {
        System.out.println(name + " moves " + direction + ".");
    }

    public void describe() {
        System.out.println(name + " is a creature of size " + size + ".");
    }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    @Override
    public String toString() {
        return name + "," + size;
    }
}