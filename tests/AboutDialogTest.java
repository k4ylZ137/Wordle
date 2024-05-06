package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.Frame;

import javax.swing.*;
import java.awt.*;

import static org.mockito.Mockito.*;

public class AboutDialogTest {

    @Mock
    private Frame frameMock;

    private AboutDialog aboutDialog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aboutDialog = new AboutDialog(frameMock);
    }

    @Test
    void testAboutDialogCreation() {
        assertEquals("About", aboutDialog.getTitle());
        assertTrue(aboutDialog.isModal());
        assertNotNull(aboutDialog.getMainPanel());
        assertNotNull(aboutDialog.getButtonPanel());
    }

    @Test
    void testMainPanelCreation() {
        JPanel mainPanel = aboutDialog.getMainPanel();

        assertNotNull(mainPanel);
        assertEquals(7, mainPanel.getComponentCount());

    }

    @Test
    void testButtonPanelCreation() {
        JPanel buttonPanel = aboutDialog.getButtonPanel();
        assertNotNull(buttonPanel);
        assertEquals(1, buttonPanel.getComponentCount());
    }
}
