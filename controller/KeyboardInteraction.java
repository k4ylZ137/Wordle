package controller;

import model.AppColors;
import model.WordleModel;
import model.WordleResponse;
import view.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardInteraction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private final Frame view;

    private final WordleModel model;

    public KeyboardInteraction(Frame view, WordleModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        String text = button.getActionCommand();
        switch (text) {
            case "Enter":
                if (model.getCurrentColumn() >= (model.getColumnCount() - 1)) {
                    boolean moreRows = model.setCurrentRow();
                    WordleResponse[] currentRow = model.getCurrentRow();
                    int greenCount = 0;
                    for (WordleResponse wordleResponse : currentRow) {
                        view.setColor(String.valueOf(wordleResponse.getChar()),
                                wordleResponse.getBackgroundColor(),
                                wordleResponse.getForegroundColor());
                        if (wordleResponse.getBackgroundColor().equals(AppColors.GREEN)) {
                            greenCount++;
                        }
                    }
                    if (greenCount >= model.getColumnCount()) {
                        view.repaintGrid();
                        int currentRowNumber = model.getCurrentRowNumber();
                    } else {
                        view.repaintGrid();
                    }
                }
                break;
            case "Backspace":
                model.backspace();
                view.repaintGrid();
                break;
            default:
                model.setCurrentCol(text.charAt(0));
                view.repaintGrid();
                break;
        }
    }
}
