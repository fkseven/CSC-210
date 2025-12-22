import java.io.*;
import java.util.*;

public class CreatureRegistry {

    private ArrayList<Creature> creatures = new ArrayList<>();
    private File file;

    public CreatureRegistry(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                creatures.add(Creature.fromCSV(sc.nextLine()));
            }
        }
    }

    public int count() {
        return creatures.size();
    }

    public Creature getCreature(int index) {
        if (index < 0 || index >= creatures.size()) {
            throw new IndexOutOfBoundsException();
        }
        return new Creature(creatures.get(index));
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    public void updateCreature(int index, Creature c) {
        if (index < 0 || index >= creatures.size()) {
            throw new IndexOutOfBoundsException();
        }
        creatures.set(index, c);
    }

    public void deleteCreature(int index) {
        if (index < 0 || index >= creatures.size()) {
            throw new IndexOutOfBoundsException();
        }
        creatures.remove(index);
    }

    public void save() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            for (Creature c : creatures) {
                out.println(c.toCSV());
            }
        }
    }

    // Test main
    public static void main(String[] args) throws Exception {
        CreatureRegistry reg = new CreatureRegistry("creature-data.csv");
        System.out.println("Count: " + reg.count());
        System.out.println(reg.getCreature(0));
    }
}