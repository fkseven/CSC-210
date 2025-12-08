import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CreaturesFromFileGUI extends JFrame {

    private JList<Creature> creatureList;
    private DefaultListModel<Creature> listModel;

    private JTextField nameField;
    private JTextField sizeField;

    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;

    private JButton eatButton;
    private JButton talkButton;
    private JButton moveButton;
    private JButton describeButton;

    private ProcessCreatureFile processor;
    private ArrayList<Creature> creatures;

    public CreaturesFromFileGUI() {
        super("Creatures From File");

        processor = new ProcessCreatureFile("creatures.txt");
        creatures = processor.loadCreaturesFromFile();

        // WINDOW LAYOUT
        setLayout(new GridLayout(1, 3));

        // ---------- LEFT PANEL (JList + Add/Remove)
        JPanel leftPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();

        for (Creature c : creatures) listModel.addElement(c);

        creatureList = new JList<>(listModel);
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        leftPanel.add(new JScrollPane(creatureList), BorderLayout.CENTER);

        JPanel leftButtons = new JPanel(new GridLayout(1, 2));
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        leftButtons.add(addButton);
        leftButtons.add(removeButton);

        leftPanel.add(leftButtons, BorderLayout.SOUTH);
        add(leftPanel);

        // ---------- MIDDLE PANEL (Editable Fields)
        JPanel middlePanel = new JPanel(new GridLayout(3, 2));

        nameField = new JTextField();
        sizeField = new JTextField();

        middlePanel.add(new JLabel("Name:"));
        middlePanel.add(nameField);
        middlePanel.add(new JLabel("Size:"));
        middlePanel.add(sizeField);

        saveButton = new JButton("Save");
        middlePanel.add(new JLabel(""));
        middlePanel.add(saveButton);

        add(middlePanel);

        // ---------- RIGHT PANEL (Actions)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        eatButton = new JButton("Eat");
        talkButton = new JButton("Talk");
        moveButton = new JButton("Move");
        describeButton = new JButton("Describe");

        rightPanel.add(eatButton);
        rightPanel.add(talkButton);
        rightPanel.add(moveButton);
        rightPanel.add(describeButton);

        add(rightPanel);

        // EVENT LISTENERS

        // SELECT CREATURE FROM LIST
        creatureList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedCreature();
        });

        // SAVE CHANGES
        saveButton.addActionListener(e -> saveCreatureChanges());

        // ADD NEW CREATURE
        addButton.addActionListener(e -> addCreature());

        // REMOVE SELECTED CREATURE
        removeButton.addActionListener(e -> removeCreature());

        // ACTION BUTTONS
        eatButton.addActionListener(e -> doAction("eat"));
        talkButton.addActionListener(e -> doAction("talk"));
        moveButton.addActionListener(e -> doAction("move"));
        describeButton.addActionListener(e -> doAction("describe"));

        // Finish
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadSelectedCreature() {
        Creature c = creatureList.getSelectedValue();
        if (c != null) {
            nameField.setText(c.getName());
            sizeField.setText(String.valueOf(c.getSize()));
        }
    }

    private void saveCreatureChanges() {
        Creature c = creatureList.getSelectedValue();
        if (c == null) return;

        try {
            String newName = nameField.getText();
            int newSize = Integer.parseInt(sizeField.getText());

            processor.updateCreature(c, newName, newSize);
            refreshList();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Size must be a number!");
        }
    }

    private void addCreature() {
        try {
            String name = nameField.getText();
            int size = Integer.parseInt(sizeField.getText());

            Creature newCreature = new Creature(name, size);
            processor.addCreature(newCreature);
            refreshList();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for new creature.");
        }
    }

    private void removeCreature() {
        Creature selected = creatureList.getSelectedValue();
        if (selected == null) return;

        processor.removeCreature(selected);
        refreshList();
    }

    private void refreshList() {
        creatures = processor.loadCreaturesFromFile();
        listModel.clear();
        for (Creature c : creatures) listModel.addElement(c);
    }

    private void doAction(String type) {
        Creature c = creatureList.getSelectedValue();
        if (c == null) return;

        switch (type) {
            case "eat" -> c.eat("food");
            case "talk" -> c.talk("Hello!");
            case "move" -> c.move("forward");
            case "describe" -> c.describe();
        }
    }

    public static void main(String[] args) {
        new CreaturesFromFileGUI();
    }
}