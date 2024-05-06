import model.WordleModel;
import view.Frame;

import javax.swing.SwingUtilities;

public class Wordle implements Runnable{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Wordle());
    }

    @Override
    public void run(){
        new Frame(new WordleModel());
    }
}
