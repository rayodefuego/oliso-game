package oliso.resources;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
    private Font robotoFont;

    public void loadRobotoFont() {
        try {
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/oliso/resources/Roboto-Regular.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(robotoFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getRobotoFont() {
        return robotoFont;
    }
}
