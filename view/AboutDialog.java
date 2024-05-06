package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private final CancelAction cancelActionImpl;

    public AboutDialog(Frame view) {
        super(view.getFrame(), "About", true);
        this.cancelActionImpl = new CancelActionImpl();

        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        Font titleFont = AppFonts.getTitleFont();
        Font textFont = AppFonts.getTextFont();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 5, 30);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel("About Wordle");
        label.setFont(titleFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        Color backgroundColor = label.getBackground();
        panel.add(label, gbc);

        gbc.gridy++;
        String text = "Wordle was created by software engineer "
                + "and former Reddit employee, Josh Wardle, and "
                + "was launched in October 2021.";
        JTextArea textArea = new JTextArea(4, 25);
        textArea.setBackground(backgroundColor);
        textArea.setEditable(false);
        textArea.setFont(textFont);
        textArea.setLineWrap(true);
        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        panel.add(textArea, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        label = new JLabel("Author:");
        label.setFont(textFont);
        panel.add(label, gbc);

        gbc.gridx++;
        label = new JLabel("Gilbert G. Le Blanc");
        label.setFont(textFont);
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        label = new JLabel("Date Created:");
        label.setFont(textFont);
        panel.add(label, gbc);

        gbc.gridx++;
        label = new JLabel("31 March 2022");
        label.setFont(textFont);
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        label = new JLabel("Version:");
        label.setFont(textFont);
        panel.add(label, gbc);

        gbc.gridx++;
        label = new JLabel("1.0");
        label.setFont(textFont);
        panel.add(label, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
        ActionMap actionMap = panel.getActionMap();
        actionMap.put((Object) "cancelAction", (Action) cancelActionImpl);

        JButton button = new JButton("Cancel");
        button.addActionListener((ActionListener) cancelActionImpl);
        panel.add(button);

        return panel;
    }

    private class CancelActionImpl implements CancelAction {

        private static final long serialVersionUID = 1L;

        @Override
        public void cancel(ActionEvent event) {
            dispose();
        }
    }
}
