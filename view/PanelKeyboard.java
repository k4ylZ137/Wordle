package view;

import controller.KeyboardInteraction;
import model.AppColors;
import model.WordleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PanelKeyboard {

    private int buttonIndex, buttonCount;

    private final JButton[] buttons;

    private final JPanel panel;

    private final KeyboardInteraction action; // Controller for keyboard interaction

    private final WordleModel model;

    // Constructor
    public PanelKeyboard(Frame view, WordleModel model) {
        this.model = model;
        this.buttonIndex = 0;
        this.buttonCount = firstRow().length + secondRow().length + thirdRow().length;
        this.buttons = new JButton[buttonCount];
        this.action = new KeyboardInteraction(view, model); // Initializing keyboard interaction controller
        this.panel = createMainPanel(); // Creating main panel
    }

    // Method to create the main panel
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5)); // Adding border

        panel.add(createQPanel()); // Adding Q panel
        panel.add(createAPanel()); // Adding A panel
        panel.add(createZPanel()); // Adding Z panel
        panel.add(createTotalPanel()); // Adding total panel

        return panel;
    }

    // Method to create Q panel
    private JPanel createQPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Adding border
        Font textfont = AppFonts.getTextFont(); // Getting font

        String[] letters = firstRow(); // Getting letters for the row

        for (int index = 0; index < letters.length; index++) {
            JButton button = new JButton(letters[index]);
            setKeyBinding(button, letters[index]); // Setting key binding for the button
            button.addActionListener(action); // Adding action listener
            button.setFont(textfont); // Setting font
            buttons[buttonIndex++] = button;
            panel.add(button); // Adding button to the panel
        }
        return panel;
    }

    // Method to get first row letters
    private String[] firstRow() {
        String[] letters = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "Backspace" };
        return letters;
    }

    // Method to create A panel
    private JPanel createAPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Adding border
        Font textfont = AppFonts.getTextFont(); // Getting font

        String[] letters = secondRow(); // Getting letters for the row

        for (int index = 0; index < letters.length; index++) {
            JButton button = new JButton(letters[index]);
            setKeyBinding(button, letters[index]); // Setting key binding for the button
            button.setFont(textfont); // Setting font
            buttons[buttonIndex++] = button;
            panel.add(button); // Adding button to the panel
        }
        return panel;
    }

    // Method to get second row letters
    private String[] secondRow() {
        String[] letters = { "A", "S", "D", "F", "G", "H", "J", "K", "L",
                "Enter" };
        return letters;
    }

    // Method to create Z panel
    private JPanel createZPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Adding border
        Font textfont = AppFonts.getTextFont(); // Getting font

        String[] letters = thirdRow(); // Getting letters for the row

        for (int index = 0; index < letters.length; index++) {
            JButton button = new JButton(letters[index]);
            setKeyBinding(button, letters[index]); // Setting key binding for the button
            button.setFont(textfont); // Setting font
            buttons[buttonIndex++] = button;
            panel.add(button); // Adding button to the panel
        }
        return panel;
    }

    // Method to get third row letters
    private String[] thirdRow() {
        String[] letters = { "Z", "X", "C", "V", "B", "N", "M" };
        return letters;
    }

    // Method to set key binding for a button
    private void setKeyBinding(JButton button, String text) {
        InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        if (text.equalsIgnoreCase("Backspace")) {
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "action");
        } else {
            inputMap.put(KeyStroke.getKeyStroke(text.toUpperCase()), "action");
        }
        ActionMap actionMap = button.getActionMap();
        actionMap.put("action", action); // Mapping action
    }

    // Method to create total panel
    private JPanel createTotalPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Adding border
        Font footerfont = AppFonts.getFooterFont(); // Getting font

        String text = String.format("%,d", model.getTotalWordCount());
        text += " possible " + model.getColumnCount() + "-letter words!";
        JLabel label = new JLabel(text);
        label.setFont(footerfont); // Setting font
        panel.add(label); // Adding label to the panel

        return panel;
    }
}
