package controller;

import model.AppColors;
import model.WordleModel;
import model.WordleResponse;
import view.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardInteraction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private final Frame view; // Reference to the view (GUI)
    private final WordleModel model; // Reference to the model (data)

    // Constructor to initialize view and model
    public KeyboardInteraction(Frame view, WordleModel model) {
        this.view = view;
        this.model = model;
    }

    // Method handling the action performed by the keyboard
    @Override
    public void actionPerformed(ActionEvent event) {
        // Getting the source of the action (which button was clicked)
        JButton button = (JButton) event.getSource();
        // Getting the text associated with the button
        String text = button.getActionCommand();
        // Switch statement to handle different actions based on the button text
        switch (text) {
            // If "Enter" button is pressed
            case "Enter":
                // Check if the current column is at the end of the row
                if (model.getCurrentColumn() >= (model.getColumnCount() - 1)) {
                    // Move to the next row if available
                    boolean moreRows = model.setCurrentRow();
                    // Get the current row
                    WordleResponse[] currentRow = model.getCurrentRow();
                    // Count the number of correct guesses (green)
                    int greenCount = 0;
                    // Iterate through the current row's responses
                    for (WordleResponse wordleResponse : currentRow) {
                        // Set the color of the corresponding cell in the view
                        view.setColor(String.valueOf(wordleResponse.getChar()),
                                wordleResponse.getBackgroundColor(),
                                wordleResponse.getForegroundColor());
                        // Increment green count if the response is correct
                        if (wordleResponse.getBackgroundColor().equals(AppColors.GREEN)) {
                            greenCount++;
                        }
                    }
                    // If all guesses are correct, repaint the grid
                    if (greenCount >= model.getColumnCount()) {
                        view.repaintGrid();
                        // Get the current row number
                        int currentRowNumber = model.getCurrentRowNumber();
                    } else {
                        // Repaint the grid
                        view.repaintGrid();
                    }
                }
                break;
            // If "Backspace" button is pressed
            case "Backspace":
                // Perform backspace operation in the model
                model.backspace();
                // Repaint the grid in the view
                view.repaintGrid();
                break;
            // For other letters (not "Enter" or "Backspace")
            default:
                // Set the current column to the typed character
                model.setCurrentCol(text.charAt(0));
                // Repaint the grid in the view
                view.repaintGrid();
                break;
        }
    }
}
