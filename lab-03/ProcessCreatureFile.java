import java.io.*;
import java.util.*;

public class ProcessCreatureFile {

    public static void main(String[] args) throws IOException {
        File file = new File("creature-data.csv");
        ArrayList<Creature> creatures = new ArrayList<>();

        // Read file
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                creatures.add(Creature.fromCSV(sc.nextLine()));
            }
        }

        // Add a creature
        creatures.add(new Creature("giant", 800, "blue"));

        // Modify first creature
        creatures.get(0).setWeight(550);

        // Remove second creature
        if (creatures.size() > 1) {
            creatures.remove(1);
        }

        // Write back to file
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            for (Creature c : creatures) {
                out.println(c.toCSV());
            }
        }

        System.out.println("ProcessCreatureFile completed successfully.");
    }
}