package view;

import model.WordleModel;

import java.awt.*;

public class StubbedFrameTest extends Frame {

    private Color backgroundColor;
    private Color foregroundColor;
    private String letter;

    public StubbedFrameTest(WordleModel model) {
        super(model);
    }

    // Override setColor() to capture method parameters
    @Override
    public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
        this.letter = letter;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    // Getter methods to access captured parameters
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public String getLetter() {
        return letter;
    }
}
