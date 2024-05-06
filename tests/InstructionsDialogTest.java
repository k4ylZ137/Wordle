package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.Frame;
import view.InstructionsDialog;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InstructionsDialogTest {

    @Mock
    private Frame frameMock;

    private InstructionsDialog instructionsDialog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instructionsDialog = new InstructionsDialog(frameMock);
    }

    // Checks that the dialog was created
    @Test
    void testInstructionsDialogCreation() {
        // Verify that the dialog was created with the correct title
        assertEquals("Instructions", instructionsDialog.getTitle());

        assertTrue(instructionsDialog.isModal());

        // Verify that the main panel and button panel were created
        assertNotNull(instructionsDialog.getMainPanel());
        assertNotNull(instructionsDialog.getButtonPanel());
    }

    // Checks that the main panel is not null (present)
    @Test
    void testMainPanelCreation() {
        JPanel mainPanel = (JPanel) instructionsDialog.getMainPanel();

        assertNotNull(mainPanel);

        // Verify that the main panel contains a JEditorPane
        assertTrue(mainPanel.getComponent(0) instanceof JScrollPane);
    }

    // Checks to see that the title is present, not null and outputting correctly
    @Test
    void testTitleFont() {
        // Get the font used for titles
        Font titleFont = FakeAppFonts.getTitleFont();

        assertNotNull(titleFont);

        // Verify that the font is the expected title font
        assertEquals(Font.ITALIC, titleFont.getStyle());
        assertEquals(36, titleFont.getSize());

    }

    // Checks to see that the text is present, and is not null
    @Test
    void testTextFont() {
        // Get the font used for text
        Font textFont = FakeAppFonts.getTextFont();

        assertNotNull(textFont);

        // Verify that the font is the expected text font
        assertEquals(Font.PLAIN, textFont.getStyle());
        assertEquals(16, textFont.getSize());
    }

    // Checks to see that the footer is present, and has the expected font
    @Test
    void testFooterFont() {
        // Get the font used for footer
        Font footerFont = FakeAppFonts.getFooterFont();

        assertNotNull(footerFont);

        // Verify that the font is the expected footer font
        assertEquals(Font.PLAIN, footerFont.getStyle());
        assertEquals(12, footerFont.getSize());
    }
}
