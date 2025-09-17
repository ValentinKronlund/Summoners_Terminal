package summonersTerminal.gameHelpers;
/**
 * The Screen class is responsible for creating a text buffer of fixed size<br>
 * that is drawn to the screen once after every game action.
 */
public final class Screen {
    // Singleton instance
    private static Screen _screen;

    private StringBuilder screenBuffer;
    // HADO-CODE-O, BAYBEE!
    private final int screenWidth = 80, screenHeight = 20;
    private Screen() {
        screenBuffer = new StringBuilder(screenWidth * screenHeight);
        for (int i = 0; i < screenBuffer.capacity(); i++) {
            screenBuffer.append(' ');
        }
    }

    public static Screen get() {
        if (_screen == null) {
            _screen = new Screen();
        }
        return _screen;
    }

    /**
     * Will place the formatted text into the screen buffer at the desired position
     * discarding any text that falls outside of the screen buffer.
     * @param formattedText
     */
    private void addToBuffer(int column, int row, String formattedText) {
        int startIndex = row * screenWidth + column;
        // go through the input string, find a newline, determine length of row
        // then compare to remaining row space in buffer, discard overflow
        // find the index for the first column in the row.
        // then put that row into the buffer, character by character...
        //int stringWidth = formattedText.substring()
        for (int it = 0, ib = startIndex; it < formattedText.length() && ib < screenBuffer.length(); it++, ib++) {
            char c = formattedText.charAt(it);
            if (c == '\n') {

            }
            screenBuffer.setCharAt(ib, c);
        }
    }

    /**
     * Adds a message into the status window
     * @param message
     */
    public Screen addStatus(String message) {
        addToBuffer(5, 19, message.substring(0, 63).strip());
        return this;
    }

    /**
     * Add the borders of the statuswindow near the bottom of the buffer
     */
    public Screen addStatusWindow() {
        addToBuffer(
            3, 18,
            """
            ▄■▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀■■▀▄▄
            █                                                                 ■■■■■██▄▄
            ▀■▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄■■▄▀▀
            """
        );
        return this;
    }

    public Screen addIntroMessage() {
        addToBuffer(0, 0, Copy.initialCopy());
        return this;
    }

    public void draw() {
        //Clears the screen if running in a terminal
        System.out.print("\033[H\033[2J");

        int rowStart = 0;
        for (int i = 0; i < screenBuffer.length(); i++) {
            if(i % screenWidth == screenWidth - 1) {
                System.out.println(screenBuffer.substring(rowStart, i));
                rowStart = i + 1;
            }
        }
    }
}
