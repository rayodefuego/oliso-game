package oliso.resources;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The Fonts class is responsible for loading and managing custom fonts.
 */
public class Fonts {
    private Font robotoFont;

    /**
     * Loads the Roboto font from a file and registers it with the local graphics environment.
     * The font is derived to a size of 12 points.
     * If the font cannot be loaded or registered, an exception stack trace is printed.
     */
    public void loadRobotoFont() {
        try {
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/oliso/resources/Roboto-Regular.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(robotoFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the loaded Roboto font.
     * 
     * @return the Roboto font, or null if the font has not been loaded.
     */
    public Font getRobotoFont() {
        return robotoFont;
    }
}
