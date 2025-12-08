import java.util.*;

public class GoodCasino {

    public static double play(Customer c, SlotMachine sm, double amt) {
        double bet = c.spend(amt);
        return sm.pullLever(bet);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer("customer.txt");
        SlotMachine sm = new SlotMachine("slot-machine.txt");

        while (true) {
            System.out.print("Enter bet amount or 'quit': ");
            String input = in.next();
            if (input.equalsIgnoreCase("quit")) break;
            double amt = Double.parseDouble(input);
            if (amt <= 0 || c.checkWallet() <= 0 || sm.getMoneyPot() <= 0) break;
            double win = play(c, sm, amt);
            c.receive(win);
            System.out.println("Slots: " + sm);
            System.out.println("You won: $" + win);
            System.out.println("Wallet: $" + c.checkWallet());
        }

        c.save("customer.txt");
        sm.save("slot-machine.txt");
    }
}