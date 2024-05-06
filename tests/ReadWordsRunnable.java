package tests;

import controller.ReadWordsRunnable;
import model.WordleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static org.mockito.Mockito.*;

public class ReadWordsRunnableTest {

    @Mock
    private WordleModel wordleModelMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun() throws IOException {
        // Mock Logger
        Logger loggerMock = mock(Logger.class);
        ReadWordsRunnable readWordsRunnable = new ReadWordsRunnable(wordleModelMock);
        readWordsRunnable.LOGGER = loggerMock; // Replace the logger with the mock

        // Stub method calls
        List<String> wordList = new ArrayList<>();
        wordList.add("test");
        when(wordleModelMock.getColumnCount()).thenReturn(4); // Stub the required column count
        doReturn(wordList).when(wordleModelMock).setWordList(wordList); // Stub setWordList method

        // Invoke the run method
        readWordsRunnable.run();

        // Verify that the methods were called
        verify(loggerMock).info("Created word list of " + wordList.size() + " words.");
        verify(wordleModelMock).generateCurrentWord();
    }

    @Test
    void testRun_IOException() throws IOException {
        // Mock Logger
        Logger loggerMock = mock(Logger.class);
        ReadWordsRunnable readWordsRunnable = new ReadWordsRunnable(wordleModelMock);
        readWordsRunnable.LOGGER = loggerMock; // Replace the logger with the mock

        // Stub IOException when calling createWordList method
        doThrow(new IOException()).when(readWordsRunnable).createWordList();

        // Invoke the run method
        readWordsRunnable.run();

        // Verify that the error was logged
        verify(loggerMock).log(eq(java.util.logging.Level.SEVERE), anyString(), any(IOException.class));
        // Verify that generateCurrentWord method was not called
        verify(wordleModelMock, never()).generateCurrentWord();
    }
}
