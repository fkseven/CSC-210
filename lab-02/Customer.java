import java.io.*;
import java.util.Scanner;

public class Customer {
    private double wallet;

    public Customer() { wallet = 500.0; }

    public Customer(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            wallet = sc.nextDouble();
        } catch (Exception e) {
            wallet = 500.0;
        }
    }

    public double spend(double amt) {
        if (amt > wallet) {
            double spent = wallet;
            wallet = 0;
            return spent;
        }
        wallet -= amt;
        return amt;
    }

    public void receive(double amt) { wallet += amt; }

    public double checkWallet() { return wallet; }

    public void save(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(wallet);
        } catch (Exception e) {
            System.out.println("Error saving wallet.");
        }
    }
}