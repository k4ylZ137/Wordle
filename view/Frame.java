package view;

import model.WordleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame {

    private final JFrame frame; // Frame instance to hold the main application window

    private final PanelKeyboard keyboard; // Instance of PanelKeyboard for user keyboard interaction

    private final WordleModel model; // Instance of WordleModel to handle game data

    private final WordleGrid wordleGrid; // Instance of WordleGrid to display the word grid

    // Constructor to initialize the Frame
    public Frame(WordleModel model) {
        this.model = model;
        this.keyboard = new PanelKeyboard(this, model);
        int width = keyboard.getPanel().getPreferredSize().width;
        this.wordleGrid = new WordleGrid(this, model, width);
        this.frame = createGUI();
    }

    // Method to create the main GUI frame
    private JFrame createGUI() {
        JFrame frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() { // Add window listener for closing event
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        frame.add(createTitlePanel(), BorderLayout.NORTH);
        frame.add(wordleGrid, BorderLayout.CENTER); // doesn't centre grid?
        frame.add(keyboard.getPanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    // Method to create the menu bar
    private JMenuBar createMenuBar() {
        JMenuBar menu = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");
        menu.add(helpMenu);

        JMenuItem instructions = new JMenuItem("Instructions...");
        instructions.addActionListener(event -> instructionsDialog(this));
        helpMenu.add(instructions);

        JMenuItem about = new JMenuItem("About...");
        about.addActionListener(event -> aboutDialog(this));
        helpMenu.add(about);

        return menu; // Return the created menu bar
    }

    // Method to create the title panel
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW); // Get input map for panel
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction"); // Add key binding for escape key
        ActionMap actionMap = panel.getActionMap();
        actionMap.put("cancelAction", new CancelAction());

        JLabel label = new JLabel("Wordle");
        label.setFont(AppFonts.getTitleFont());
        panel.add(label);

        return panel;
    }

    // Method to handle application shutdown
    public void shutdown() {
        frame.dispose(); // Dispose the frame
        System.exit(0); // Exit the application
    }

    // Method to reset default colors
    public void resetDefaultColours() {
        keyboard.resetDefaultColors(); // Call resetDefaultColours method of keyboard
    }

    // Getter method to get the frame instance
    public JFrame getFrame() {
        return frame; // Return the frame instance
    }

    // Method to set color for a letter in the keyboard
    public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
        keyboard.setColor(letter, backgroundColor, foregroundColor); // Call setColor method of keyboard
    }

    // Method to repaint the wordle grid
    public void repaintGrid() {
        wordleGrid.repaint();
    }

    // Method to display instructions dialog
    private static void instructionsDialog(Frame frame) {
        // Implement instructions dialog
    }

    // Method to display about dialog
    private static void aboutDialog(Frame frame) {
        // Implement about dialog
    }

    // Inner class representing cancel action
    private class CancelAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent event) {
            shutdown();
        }
    }
}
