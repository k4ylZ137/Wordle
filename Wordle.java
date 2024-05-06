import model.WordleModel;
import view.Frame;

import javax.swing.SwingUtilities;
import java.io.IOException;

public class Wordle implements Runnable{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Wordle());
    }

    @Override
    public void run(){
        try {
            new Frame(new WordleModel());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
