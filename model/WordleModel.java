package model;

import controller.ReadWordsRunnable;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class WordleModel {

    private char[] currentWord, guess;

    private final int columnCount, maximumRows;
    private int currentCol, currentRow;

    private List<String> wordList;

    private final Random random;

    private WordleResponse[][] wordleGrid;

    public WordleModel() {
        this.currentCol = -1;
        this.currentRow = 0;
        this.columnCount = 5;
        this.maximumRows = 6;
        this.random = new Random();

        // Initialize word list asynchronously
        createWordleList();

        // Initialize wordle grid
        this.wordleGrid = initialiseWordleGrid();
        this.guess = new char[columnCount];
    }

    private void createWordleList() {
        ReadWordsRunnable runnable = new ReadWordsRunnable(this);
        new Thread(runnable).start();
    }

    public void initialise() {
        this.wordleGrid = initialiseWordleGrid();
        this.currentCol = -1;
        this.currentRow = 0;
        generateCurrentWord();
        this.guess = new char[columnCount];
    }

    public void generateCurrentWord() {
        String word = getCurrentWord();
        this.currentWord = word.toUpperCase().toCharArray();
    }

    private String getCurrentWord() {
        return wordList.get(getRandomIndex());
    }

    private int getRandomIndex() {
        int size = wordList.size();
        return random.nextInt(size);
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    private WordleResponse[][] initialiseWordleGrid() {
        WordleResponse[][] wordleGrid = new WordleResponse[maximumRows][columnCount];

        for (int row = 0; row < wordleGrid.length; row++) {
            for (int column = 0; column < wordleGrid[row].length; column++) {
                wordleGrid[row][column] = null;
            }
        }
        return wordleGrid;
    }

    public void setCurrentCol(char c) {
        currentCol++;
        currentCol = Math.min(currentCol, (columnCount - 1));
        guess[currentCol] = c;
        wordleGrid[currentRow][currentCol] = new WordleResponse(c, Color.WHITE, Color.BLACK);
    }

    public void backspace() {
        wordleGrid[currentRow][currentCol] = null;
        guess[currentCol] = ' ';
        this.currentCol--;
        this.currentCol = Math.max(currentCol, 0);
    }

    public int getCurrentRowNumber() {
        return currentRow - 1;
    }

    public boolean setCurrentRow() {
        for (int column = 0; column < guess.length; column++) {
            Color backgroundColor = Color.GRAY;
            Color foregroundColor = Color.WHITE;
            if (guess[column] == currentWord[column]) {
                backgroundColor = Color.YELLOW;
            }
            wordleGrid[currentRow][column] = new WordleResponse(guess[column],
                    backgroundColor, foregroundColor);
        }
        currentCol = -1;
        currentRow++;
        guess = new char[columnCount];

        return currentRow < maximumRows;
    }

    private boolean contains(char[] currentWord, char[] guess, int column) {
        for (int index = 0; index < currentWord.length; index++) {
            if (index != column && guess[column] == currentWord[index]) {
                return true;
            }
        }
        return false;
    }

    public WordleResponse[][] getWordleGrid() {
        return wordleGrid;
    }

    public int getMaximumRows() {
        return maximumRows;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getCurrentColumn() {
        return currentCol;
    }

    public int getTotalWordCount() {
        return wordList.size();
    }
}
