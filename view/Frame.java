package view;

import model.WordleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame {

    private final JFrame frame;

    private final PanelKeyboard keyboard;

    private final WordleModel model;

    private final WordleGrid wordleGrid;

    public Frame(WordleModel model) {
        this.model = model;
        this.keyboard = new PanelKeyboard(this, model);
        int width = keyboard.getPanel().getPreferredSize().width;
        this.wordleGrid = new WordleGrid(this, model, width);
        this.frame = createGUI();
    }

    private JFrame createGUI() {
        JFrame frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        frame.add(createTitlePanel(), BorderLayout.NORTH); // Changed method name to camelCase
        frame.add(wordleGrid, BorderLayout.CENTER);
        frame.add(keyboard.getPanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menu = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");
        menu.add(helpMenu);

        JMenuItem instructions = new JMenuItem("Instructions..."); // Changed variable name to camelCase
        instructions.addActionListener(event -> instructionsDialog(Frame.this)); // Changed method name to camelCase
        helpMenu.add(instructions);

        JMenuItem about = new JMenuItem("About..."); // Changed variable name to camelCase
        about.addActionListener(event -> aboutDialog(Frame.this)); // Changed method name to camelCase
        helpMenu.add(about);

        return menu;
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
        ActionMap actionMap = panel.getActionMap();
        actionMap.put("cancelAction", new CancelAction());

        JLabel label = new JLabel("Wordle");
        label.setFont(AppFonts.getTitleFont());
        panel.add(label);

        return panel;
    }

    public void shutdown() {
        frame.dispose();
        System.exit(0);
    }

    public void resetDefaultColours() {
        keyboard.resetDefaultColours();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
        keyboard.setColor(letter, backgroundColor, foregroundColor);
    }

    public void repaintGrid() {
        wordleGrid.repaint();
    }

    private static void instructionsDialog(Frame frame) { // Changed method name to camelCase
        // Implement instructions dialog
    }

    private static void aboutDialog(Frame frame) { // Changed method name to camelCase
        // Implement about dialog
    }

    private class CancelAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent event) {
            shutdown();
        }
    }
}
