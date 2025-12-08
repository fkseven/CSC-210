import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingleCreatureGUI extends JFrame {

    private Creature creature;

    private JTextField nameField;
    private JTextField sizeField;

    private JTextArea displayArea;

    public SingleCreatureGUI() {
        super("Single Creature GUI");

        creature = new Creature("Fluffy", 10);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1, 5, 5));

        leftPanel.add(new JLabel("Name:"));
        nameField = new JTextField(creature.getName());
        leftPanel.add(nameField);

        leftPanel.add(new JLabel("Size:"));
        sizeField = new JTextField("" + creature.getSize());
        leftPanel.add(sizeField);

        JButton saveButton = new JButton("Save");
        leftPanel.add(saveButton);

        JButton verbButton = new JButton("Describe");
        leftPanel.add(verbButton);

        displayArea = new JTextArea(15, 25);
        displayArea.setEditable(false);
        updateDisplay();

        JScrollPane scrollPane = new JScrollPane(displayArea);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    creature.setName(nameField.getText());
                    creature.setSize(Integer.parseInt(sizeField.getText()));
                    updateDisplay();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Size must be a number.");
                }
            }
        });

        verbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.append("\n" + creature.getName() + " is a creature of size " + creature.getSize() + ".\n");
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateDisplay() {
        String info =
            "CREATURE INFO\n" +
            "---------------------\n" +
            "Name: " + creature.getName() + "\n" +
            "Size: " + creature.getSize() + "\n";
        displayArea.setText(info);
    }

    public static void main(String[] args) {
        new SingleCreatureGUI();
    }
}