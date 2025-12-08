import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MultipleCreatureGUI extends JFrame {

    private ArrayList<Creature> creatures;
    private JList<String> creatureList;

    private Creature selectedCreature;

    private JTextField nameField;
    private JTextField sizeField;

    private JTextArea displayArea;

    public MultipleCreatureGUI() {
        super("Multiple Creature GUI");

        creatures = new ArrayList<>();
        creatures.add(new Creature("Fluffy", 10));
        creatures.add(new Creature("Spike", 25));
        creatures.add(new Creature("Zorg", 5));
        creatures.add(new Creature("Milo", 12));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Creature c : creatures) {
            listModel.addElement(c.getName());
        }

        creatureList = new JList<>(listModel);
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Creatures"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(creatureList), BorderLayout.CENTER);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(10, 1, 5, 5));

        editPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        editPanel.add(nameField);

        editPanel.add(new JLabel("Size:"));
        sizeField = new JTextField();
        editPanel.add(sizeField);

        JButton saveButton = new JButton("Save");
        editPanel.add(saveButton);

        JButton verbButton = new JButton("Describe");
        editPanel.add(verbButton);

        displayArea = new JTextArea(15, 25);
        displayArea.setEditable(false);
        JScrollPane displayScroll = new JScrollPane(displayArea);

        setLayout(new BorderLayout());
        add(listPanel, BorderLayout.WEST);
        add(editPanel, BorderLayout.CENTER);
        add(displayScroll, BorderLayout.EAST);

        creatureList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = creatureList.getSelectedIndex();
                if (index >= 0) {
                    selectedCreature = creatures.get(index);
                    nameField.setText(selectedCreature.getName());
                    sizeField.setText("" + selectedCreature.getSize());
                    updateDisplay();
                }
            }
        });

        saveButton.addActionListener(e -> {
            if (selectedCreature != null) {
                try {
                    selectedCreature.setName(nameField.getText());
                    selectedCreature.setSize(Integer.parseInt(sizeField.getText()));
                    updateDisplay();
                    refreshListNames();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Size must be a number.");
                }
            }
        });

        verbButton.addActionListener(e -> {
            if (selectedCreature != null) {
                displayArea.append("\n" + selectedCreature.getName() + " is a creature of size " + selectedCreature.getSize() + ".\n");
            }
        });

        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateDisplay() {
        if (selectedCreature == null) return;
        String info =
            "CREATURE INFO\n" +
            "---------------------\n" +
            "Name: " + selectedCreature.getName() + "\n" +
            "Size: " + selectedCreature.getSize() + "\n";
        displayArea.setText(info);
    }

    private void refreshListNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Creature c : creatures) {
            model.addElement(c.getName());
        }
        creatureList.setModel(model);
    }

    public static void main(String[] args) {
        new MultipleCreatureGUI();
    }
}