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

    private final static Logger LOGGER =
            Logger.getLogger(ReadWordsRunnable.class.getName());

    private final WordleModel model;

    public ReadWordsRunnable(WordleModel model){
        LOGGER.setLevel(Level.INFO);

        try{
            FileHandler fileTxt = new FileHandler("./logging.txt");
            LOGGER.addHandler(fileTxt);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating file handler", e);
        }
        this.model = model;
    }

    @Override
    public void run() {
        List<String> wordlist;

        try {
            wordlist = createWordList();
            LOGGER.info("Created word list of " + wordlist.size() + " words.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating word list", e);
            e.printStackTrace();
            wordlist = new ArrayList<>();
        }
        model.setWordList(wordlist);
        model.generateCurrentWord();
    }


    private List<String> createWordList() throws IOException {
        int min = model.getColumnCount();

        List<String> wordlist = new ArrayList<>();

        String text = "wordle.txt";
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream stream = loader.getResourceAsStream(text);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.length() == min) {
                    wordlist.add(line);
                }
                line = reader.readLine(); // Read the next line
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return wordlist;
    }
}
