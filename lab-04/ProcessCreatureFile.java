import java.io.*;
import java.util.ArrayList;

public class ProcessCreatureFile {

    private String filename;

    public ProcessCreatureFile(String filename) {
        this.filename = filename;
    }

    public ArrayList<Creature> loadCreaturesFromFile() {
        ArrayList<Creature> creatures = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int size = Integer.parseInt(parts[1].trim());
                    creatures.add(new Creature(name, size));
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return creatures;
    }

    public void saveCreaturesToFile(ArrayList<Creature> creatures) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Creature c : creatures) {
                writer.write(c.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void addCreature(Creature c) {
        ArrayList<Creature> creatures = loadCreaturesFromFile();
        creatures.add(c);
        saveCreaturesToFile(creatures);
    }

    public void updateCreature(Creature target, String newName, int newSize) {
        ArrayList<Creature> creatures = loadCreaturesFromFile();

        for (Creature c : creatures) {
            if (c.getName().equals(target.getName()) && c.getSize() == target.getSize()) {
                c.setName(newName);
                c.setSize(newSize);
                break;
            }
        }

        saveCreaturesToFile(creatures);
    }

    public void removeCreature(Creature target) {
        ArrayList<Creature> creatures = loadCreaturesFromFile();

        creatures.removeIf(c ->
                c.getName().equals(target.getName()) &&
                c.getSize() == target.getSize()
        );

        saveCreaturesToFile(creatures);
    }
}