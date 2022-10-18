import java.awt.*;

public class Main {
    public static String fontName = "Bradley Hand";
    public static Color bgColor = new Color(0xFED1FF);
    public static Color green = new Color(0,153,0);
    public static Color red = new Color(204, 0, 0);

    /**
     * Returns the game font in bold in given size
     * @param size the size of the font
     * @return the font with the right size
     */
    public static Font getFontBold(int size){
        return new Font(fontName, Font.BOLD, size);
    }

    /**
     * Returns the game font in given size
     * @param size the size of the font
     * @return the font with the right size
     */
    public static Font getFontPlain(int size){
        return new Font(fontName, Font.PLAIN, size);
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
    }
}
