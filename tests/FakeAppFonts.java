package tests;

import java.awt.Font;

public class FakeAppFonts {

    // Fake implementation of getTitleFont method
    public static Font getTitleFont() {
        return new Font("FakeTitleFont", Font.BOLD, 24);
    }

    // Fake implementation of getTextFont method
    public static Font getTextFont() {
        return new Font("FakeTextFont", Font.PLAIN, 14);
    }

    // Fake implementation of getFooterFont method
    public static Font getFooterFont() {
        return new Font("FakeFooterFont", Font.PLAIN, 10);
    }
}

