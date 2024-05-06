package view;

import java.awt.Font;

public class AppFonts {

    public static Font getTitleFont(){
        return new Font("Dialog", Font.ITALIC, 36);
    }

    public static Font getTextFont(){
        return new Font("Dialog", Font.PLAIN, 16);
    }

    public static Font getFooterFont(){
        return new Font("Dialog", Font.PLAIN, 12);
    }
}

