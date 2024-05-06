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

    private final KeyboardInteraction action; // Corrected type

    private final WordleModel model;

    public PanelKeyboard(Frame view, WordleModel model){ // Corrected constructor name
        this.model = model;
        this.buttonIndex = 0;
        this.buttonCount = firstRow().length + secondRow().length + thirdRow().length;
        this.buttons = new JButton[buttonCount];
        this.action = new KeyboardInteraction(view, model);
        this.panel = createMainPanel();
    }

    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new GridLayout(0,1,0,0));
        panel.setBorder(BorderFactory.createEmptyBorder(10,5,0,5)); // Removed unnecessary semicolon

        panel.add(createQPanel());
        panel.add(createAPanel());
        panel.add(createZPanel());
        panel.add(createTotalPanel());

        return panel;
    }

    private JPanel createQPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0,5)); // Corrected method name
        Font textfont = AppFonts.getTextFont(); // Corrected method name

        String[] letters = firstRow(); // Corrected method name

        for (int index = 0; index < letters.length; index++){
            JButton button = new JButton(letters[index]);
            setKeyBinding(button, letters[index]);
            button.addActionListener(action);
            button.setFont(textfont);
            buttons[buttonIndex++] = button;
            panel.add(button);
        }
        return panel;
    }

    private String[] firstRow(){ // Corrected method name
        String[] letters = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "Backspace" };
        return letters;
    }

    private JPanel createAPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        Font textfont = AppFonts.getTextFont();

        String[] letters = secondRow();

        for (int index = 0; index < letters.length; index++){
            JButton button = new JButton(letters[index]);
            setKeyBinding(button, letters[index]);
            button.setFont(textfont);
            buttons[buttonIndex++] = button;
            panel.add(button);
        }
        return panel;
    }

    private String[] secondRow(){
        String[] letters = { "A", "S", "D", "F", "G", "H", "J", "K", "L",
                "Enter" };
        return letters;
    }

    private JPanel createZPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        Font textfont = AppFonts.getTextFont();

        String[] letters = thirdRow();

        for (int index = 0; index < letters.length; index++){
            JButton button = new JButton(letters[index]);
            setKeyBinding(button, letters[index]);
            button.setFont(textfont);
            buttons[buttonIndex++] = button;
            panel.add(button);
        }
        return panel;
    }

    private String[] thirdRow() {
        String[] letters = {"Z", "X", "C", "V", "B", "N", "M"};
        return letters;
    }

    private void setKeyBinding(JButton button, String text){
        InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        if (text.equalsIgnoreCase("Backspace")){
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),"action");
        }
        else{
            inputMap.put(KeyStroke.getKeyStroke(text.toUpperCase()), "action");
        }
        ActionMap actionMap = button.getActionMap();
        actionMap.put("action", action);
    }

    private JPanel createTotalPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        Font footerfont = AppFonts.getFooterFont();

        String text = String.format("%,d", model.getTotalWordCount());
        text += " possible " + model.getColumnCount() + "-letter words!";
        JLabel label = new JLabel(text);
        label.setFont(footerfont);
        panel.add(label);

        return panel;
    }

    public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
        for (JButton button : buttons) {
            if (button.getActionCommand().equals(letter)) {
                Color color = button.getBackground();
                if (color.equals(AppColors.GREEN)) {
                    // Condition met - don't do anything
                } else if (color.equals(AppColors.YELLOW) && backgroundColor.equals(AppColors.GREEN)) {
                    button.setBackground(backgroundColor);
                    button.setForeground(foregroundColor);
                }
                break;
            }
        }
    }

    public void resetDefaultColours() {
        for (JButton button : buttons) {
            button.setBackground(null);
            button.setForeground(null);
        }
    }

    public JPanel getPanel(){
        return panel;
    }
}
