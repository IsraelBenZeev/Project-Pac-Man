package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ghosts extends Entity {
    final int size = 32;
    BufferedImage green, pink, red, blue,orange;
    GamePanel gp;

    public Ghosts(GamePanel gp) {
        this.gp = gp;
        setValues();
    }

    public void setValues(){
        try {
            green = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_blue.jpg"));
            pink = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_green.jpg"));
            red = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_orange.jpg"));
            blue = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_pink.jpg"));
            orange = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_red.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void  update(){}
    public void draw (Graphics2D g2){
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                if (gp.map[i][j] == 2){
                    int x = j * size;
                    int y = i * size;
                    g2.drawImage(green,x,y,size,size,null);
                }
            }
        }

    }
}
