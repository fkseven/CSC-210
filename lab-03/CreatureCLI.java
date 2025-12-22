public class CreatureCLI {

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                printHelp();
                System.exit(1);
            }

            CreatureRegistry reg = new CreatureRegistry("creature-data.csv");
            String command = args[0];

            switch (command) {
                case "create":
                    Creature c = parseCreature(args[1]);
                    reg.addCreature(c);
                    reg.save();
                    break;

                case "read":
                    int r = Integer.parseInt(args[1]);
                    System.out.println(reg.getCreature(r));
                    break;

                case "update":
                    int u = Integer.parseInt(args[1]);
                    Creature updated = parseCreature(args[2]);
                    reg.updateCreature(u, updated);
                    reg.save();
                    break;

                case "delete":
                    int d = Integer.parseInt(args[1]);
                    reg.deleteCreature(d);
                    reg.save();
                    break;

                default:
                    printHelp();
                    System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static Creature parseCreature(String input) {
        String[] parts = input.split(" ");
        String name = "";
        double weight = 0;
        String color = "";

        for (String p : parts) {
            String[] kv = p.split(":");
            switch (kv[0]) {
                case "name": name = kv[1]; break;
                case "weight": weight = Double.parseDouble(kv[1]); break;
                case "color": color = kv[1]; break;
            }
        }
        return new Creature(name, weight, color);
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println(" java CreatureCLI create 'name:x weight:y color:z'");
        System.out.println(" java CreatureCLI read <index>");
        System.out.println(" java CreatureCLI update <index> 'name:x weight:y color:z'");
        System.out.println(" java CreatureCLI delete <index>");
    }
}