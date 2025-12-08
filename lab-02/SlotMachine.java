import java.io.*;
import java.util.*;

public class SlotMachine {
    private char r1, r2, r3;
    private double moneyPot;
    private static final char[] symbols = { '\u263A', '\u2764', '7' };
    private final Random rand = new Random();

    public SlotMachine() { moneyPot = 1_000_000.0; }

    public SlotMachine(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            moneyPot = sc.nextDouble();
        } catch (Exception e) {
            moneyPot = 1_000_000.0;
        }
    }

    public double pullLever(double amt) {
        r1 = symbols[rand.nextInt(3)];
        r2 = symbols[rand.nextInt(3)];
        r3 = symbols[rand.nextInt(3)];
        if (r1 == r2 && r2 == r3) {
            double win = amt * 10;
            moneyPot -= win;
            return win;
        }
        moneyPot += amt;
        return 0.0;
    }

    public String toString() { return "" + r1 + " " + r2 + " " + r3; }

    public double getMoneyPot() { return moneyPot; }

    public void save(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(moneyPot);
        } catch (Exception e) {
            System.out.println("Error saving pot.");
        }
    }
}