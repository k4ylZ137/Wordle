package controller;

import model.WordleModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWordsRunnable implements Runnable {

    // Logger for logging messages
    private final static Logger LOGGER =
            Logger.getLogger(ReadWordsRunnable.class.getName());

    private final WordleModel model; // Reference to the WordleModel

    // Constructor to initialize the WordleModel
    public ReadWordsRunnable(WordleModel model){
        // Set logging level
        LOGGER.setLevel(Level.INFO);

        try{
            // Create file handler for logging
            FileHandler fileTxt = new FileHandler("./logging.txt");
            LOGGER.addHandler(fileTxt);
        } catch (IOException e) {
            // Log error if file handler creation fails
            LOGGER.log(Level.SEVERE, "Error creating file handler", e);
        }
        this.model = model;
    }

    @Override
    public void run() {
        List<String> wordlist;

        try {
            // Create word list from file
            wordlist = createWordList();
            // Log information about the word list creation
            LOGGER.info("Created word list of " + wordlist.size() + " words.");
        } catch (IOException e) {
            // Log error if word list creation fails
            LOGGER.log(Level.SEVERE, "Error creating word list", e);
            e.printStackTrace();
            wordlist = new ArrayList<>();
        }
        // Set the word list in the WordleModel
        model.setWordList(wordlist);
        // Generate a random word from the word list
        model.generateCurrentWord();
    }

    // Method to create a word list from a file
    private List<String> createWordList() throws IOException {
        // Minimum word length required
        int min = model.getColumnCount();

        List<String> wordlist = new ArrayList<>();

        // File containing the word list
        String text = "wordle.txt";
        // Get input stream for the file
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream stream = loader.getResourceAsStream(text);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            // Read each line from the file
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                // Add line to the word list if its length matches the minimum required
                if (line.length() == min) {
                    wordlist.add(line);
                }
                line = reader.readLine(); // Read the next line
            }
        } finally {
            // Close the reader
            if (reader != null) {
                reader.close();
            }
        }
        return wordlist;
    }
}
