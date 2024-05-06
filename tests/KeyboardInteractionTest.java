package tests;

import controller.KeyboardInteraction;
import model.AppColors;
import model.WordleModel;
import model.WordleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;

class KeyboardInteractionTest {

    @Mock
    private Frame viewMock;

    @Mock
    private WordleModel modelMock;

    private KeyboardInteraction keyboardInteraction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        keyboardInteraction = new KeyboardInteraction(viewMock, modelMock);
    }

    @Test
    void testActionPerformed_Enter() {
        JButton buttonMock = mock(JButton.class);
        when(buttonMock.getActionCommand()).thenReturn("Enter");

        WordleResponse[] currentRow = {new WordleResponse('A', AppColors.GREEN, Color.BLACK)};
        when(modelMock.getCurrentColumn()).thenReturn(0);
        when(modelMock.getColumnCount()).thenReturn(5);
        when(modelMock.setCurrentRow()).thenReturn(true);
        when(modelMock.getCurrentRow()).thenReturn(currentRow);

        keyboardInteraction.actionPerformed(new ActionEvent(buttonMock, ActionEvent.ACTION_PERFORMED, null));

        verify(viewMock, times(1)).setColor(eq("A"), eq(AppColors.GREEN), eq(Color.BLACK));
        verify(viewMock, times(1)).repaintGrid();
    }

    @Test
    void testActionPerformed_Backspace() {
        JButton buttonMock = mock(JButton.class);
        when(buttonMock.getActionCommand()).thenReturn("Backspace");

        keyboardInteraction.actionPerformed(new ActionEvent(buttonMock, ActionEvent.ACTION_PERFORMED, null));

        verify(modelMock, times(1)).backspace();
        verify(viewMock, times(1)).repaintGrid();
    }

    @Test
    void testActionPerformed_Default() {
        JButton buttonMock = mock(JButton.class);
        when(buttonMock.getActionCommand()).thenReturn("A");

        keyboardInteraction.actionPerformed(new ActionEvent(buttonMock, ActionEvent.ACTION_PERFORMED, null));

        verify(modelMock, times(1)).setCurrentCol('A');
        verify(viewMock, times(1)).repaintGrid();
    }
}
