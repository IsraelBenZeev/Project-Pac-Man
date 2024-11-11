package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends Entity {
    public final BufferedImage image;

    {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/wall.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

    }

    int[][] arr = {
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,2,2,2,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
            {1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    public void draw(Graphics2D g2) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    int x = j * titleSize;
                    int y = i * titleSize;
                    g2.drawImage(image, x, y, titleSize, titleSize, null);
                }
            }


        }
    }

}
