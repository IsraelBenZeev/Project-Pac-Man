package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends Entity {
    final int size = 32;
//    GamePanel gp;
    public final BufferedImage image;



    public Wall(GamePanel gp) {
        this.gp = gp;
    }
    {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/tile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                if (gp.map[i][j] == 0) {
                    int x = j * size;
                    int y = i * size;
                    g2.drawImage(image, x, y, size, size, null);
                }
            }
        }
    }
}
