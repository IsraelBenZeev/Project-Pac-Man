package pac_man;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fruits{

    static int size = 20;
    BufferedImage apple, chery, orange, strawberry;
    public String fullPath (String p){
        return "/resource/fruits/" + p + ".png";
    }

    public Fruits() throws IOException {
        setValues();
    }

    public void setValues() throws IOException {
        apple = ImageIO.read(getClass().getResourceAsStream(fullPath("apple")));
        chery = ImageIO.read(getClass().getResourceAsStream(fullPath("chery")));
        orange = ImageIO.read(getClass().getResourceAsStream(fullPath("orange")));
        strawberry = ImageIO.read(getClass().getResourceAsStream(fullPath("strawberry")));
    }
}
