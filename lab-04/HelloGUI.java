import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class HelloGUI extends JFrame {

    private JLabel greetingLabel;
    private JButton greetButton;
    private Random rand;

    public HelloGUI() {
        super("Hello GUI");

        rand = new Random();

        // The label starts empty
        greetingLabel = new JLabel("", SwingConstants.CENTER);

        // Button with text "Greet"
        greetButton = new JButton("Greet");

        // List of possible greetings
        String[] greetings = {
                "Hello there!",
                "Hey! What's up?",
                "Greetings, proffessor.",
                "Hi! Nice to see you!",
                "Yo! Hope you're good!"
        };

        // Action listener for the button
        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pick = rand.nextInt(greetings.length);
                greetingLabel.setText(greetings[pick]);
            }
        });

        // Layout (simple vertical layout)
        setLayout(new BorderLayout());
        add(greetingLabel, BorderLayout.NORTH);
        add(greetButton, BorderLayout.CENTER);

        // Window settings
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HelloGUI();
    }
}