package summonersTerminal.gameHelpers;

import java.util.Scanner;

/**
 * The Screen class is responsible for creating a text buffer of fixed size<br>
 * that is drawn to the screen once after every game action.
 */
public final class Screen {
    // Singleton instance
    private static Screen _screen;
    private String[] buffer;

    private Screen() {
        buffer = new String[20];
    }

    public Screen getInstance() {
        if (_screen == null) {
            _screen = new Screen();
        }
        return _screen;
    }

    public void draw() {
    }
}
