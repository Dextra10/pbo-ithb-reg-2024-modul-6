package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class CustomNikFont {
    private Font font;
    private static final int DEFAULT_FONT_SIZE = 12;

    public CustomNikFont(String fontPath) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load font from " + fontPath + ": " + e.getMessage());
            font = new Font(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE);
        }
    }

    public Font getPlainFont(float fontSize) {
        if (font != null) {
            return font.deriveFont(Font.PLAIN, fontSize);
        } else {
            return new Font(Font.SANS_SERIF, Font.PLAIN, (int) fontSize);
        }
    }

    public Font getBoldFont(float fontSize) {
        if (font != null) {
            return font.deriveFont(Font.BOLD, fontSize);
        } else {
            return new Font(Font.SANS_SERIF, Font.BOLD, (int) fontSize);
        }
    }

    public Font getItalicFont(float fontSize) {
        if (font != null) {
            return font.deriveFont(Font.ITALIC, fontSize);
        } else {
            return new Font(Font.SANS_SERIF, Font.ITALIC, (int) fontSize);
        }
    }

    public Font getBoldItalicFont(float fontSize) {
        if (font != null) {
            return font.deriveFont(Font.BOLD | Font.ITALIC, fontSize);
        } else {
            return new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, (int) fontSize);
        }
    }
}
