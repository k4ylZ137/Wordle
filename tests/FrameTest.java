package tests;

import model.WordleModel;
import org.junit.jupiter.api.Test;
import view.StubbedFrameTest;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameTest {

    @Test
    public void testSetColor() throws IOException {
        StubbedFrameTest frame = new StubbedFrameTest(new WordleModel());

        // Set colors using setColor method
        Color backgroundColor = Color.RED;
        Color foregroundColor = Color.BLUE;
        String letter = "A";
        frame.setColor(letter, backgroundColor, foregroundColor);

        // Ensure that the parameters are correct
        assertEquals(backgroundColor, frame.getBackgroundColor());
        assertEquals(foregroundColor, frame.getForegroundColor());
        assertEquals(letter, frame.getLetter());
    }
}
