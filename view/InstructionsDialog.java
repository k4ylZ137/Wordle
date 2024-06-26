package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class InstructionsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private final CancelActionImpl cancelActionImpl;

    private JEditorPane editor;

    public InstructionsDialog(Frame view){
        super(view.getFrame(), "Instructions", true);
        this.cancelActionImpl = new CancelActionImpl();

        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
    }

    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

        URL url = InstructionsDialog.class.getResource("/instruction.htm");

        editor = new JEditorPane();
        editor.setEditable(false);
        editor.setContentType("text/html");
        try{
            editor.setPage(url);
        }catch (IOException e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setPreferredSize(new Dimension(600,480));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

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
