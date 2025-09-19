package summonersTerminal.gameHelpers;
/**
 * The Screen class creates and holds a text buffer of fixed size<br>
 * which is drawn all at once to the screen. It also has public methods<br>
 * for adding text to the buffer at specific locations on the screen.<br>
 */
public final class Screen {
    // HAD-O-CODE-O, BAY-BEE!
    @SuppressWarnings("FieldCanBeLocal")
    private final int screenWidth = 80, screenHeight = 20;

    // Singleton instance
    private static Screen _screen;
    private final StringBuilder screenBuffer;

    private Screen() {
        screenBuffer = new StringBuilder(screenWidth * screenHeight);
        screenBuffer.repeat("█", screenBuffer.capacity());
    }

    public static Screen get() {
        if (_screen == null) {
            _screen = new Screen();
        }
        return _screen;
    }

    /**
     * Wipe the buffer
     */
    @SuppressWarnings("unused")
    public Screen clearBuffer() {
        screenBuffer.replace(0, screenBuffer.length(), "█".repeat(screenBuffer.length()));
        return this;
    }
    /**
     * Place an arbitrary text into the screen buffer at the desired position<br>
     * discarding any text that falls outside of the screen buffer.<br>
     * Negative coordinates will give undesired results or crash!
     */
    private void addToBuffer(int column, int row, String formattedText) {
        int startIndex = row * screenWidth + column;
        int maxWidth = screenWidth - column;

        String[] texts = formattedText.split("\\R", 0);
        for (var text : texts) {
            if (startIndex >= screenBuffer.length()) {
                break;
            }
            int length = Math.min(text.length(), maxWidth);
            screenBuffer.replace(startIndex, startIndex + length, text.substring(0, length));
            startIndex += screenWidth;
        }
    }

    /**
     * Add a text in the main window(should be no wider than 50 characters)
     */
    public Screen addMainMessage(String message) {
        addToBuffer(7, 1, message);
        return this;
    }
    /**
     * Add the main window border to the buffer
     */
    public Screen addMainWindow() {
        addToBuffer(3, 0, Copy.mainWindowCopy());
        return this;
    }
    /**
     * Add a message into the status window
     */
    public Screen addStatusMessage(String message) {
        if (message.length() > 63) {
            message = message.substring(0, 63);
        }
        addToBuffer(5, 18, message);
        return this;
    }
    /**
     * Add the borders of the status window near the bottom of the buffer
     */
    public Screen addStatusWindow() {
        addToBuffer(3, 17, Copy.statusBarCopy());
        return this;
    }
    /**
     * Add the game instructions text to the buffer
     */
    public Screen addIntroMessage() {
        addToBuffer(0, 0, Copy.initialCopy());
        return this;
    }
    /**
     * Draw the buffer
     */
    public void draw() {
        // Clears the screen if running in a terminal
        System.out.
                print("\033[H\033[2J");

        // Draw the screen buffer
        for (int i = 0; i < screenBuffer.length(); i += screenWidth) {
            System.out.println(screenBuffer.substring(i, i + screenWidth));
        }
    }
}
