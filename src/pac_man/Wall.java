package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends Entity implements MyFunctions {
    BufferedImage leftRight, left, right, upDown, up, down, without, upLeft, upRight, downLeft, downRight;



    public Wall(GamePanel gp) {
        this.gp = gp;
        setValues();
    }


    @Override
    public void setValues() {
        try {
            leftRight = ImageIO.read(getClass().getResourceAsStream("/resource/squares/left_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/resource/squares/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/resource/squares/right.png"));
            upLeft = ImageIO.read(getClass().getResourceAsStream("/resource/squares/up_left.png"));
            upRight = ImageIO.read(getClass().getResourceAsStream("/resource/squares/up_right.png"));

            upDown = ImageIO.read(getClass().getResourceAsStream("/resource/squares/up_down.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/resource/squares/down.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/resource/squares/up.png"));

            downLeft = ImageIO.read(getClass().getResourceAsStream("/resource/squares/down_left.png"));
            downRight = ImageIO.read(getClass().getResourceAsStream("/resource/squares/down_right.png"));



            without = ImageIO.read(getClass().getResourceAsStream("/resource/squares/without.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                switch (gp.map[i][j]){
                    case 2:
                    x = j * titleSize;
                    y = i * titleSize;
                    g2.drawImage(leftRight, x, y, titleSize, titleSize, null);
                    break;
                    case 3:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(left, x, y, titleSize, titleSize, null);
                        break;
                    case 4:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(right, x, y, titleSize, titleSize, null);
                        break;
                    case 11:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(down, x, y, titleSize, titleSize, null);
                        break;
                    case 10:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(up, x, y, titleSize, titleSize, null);
                        break;
                    case 9:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(upDown, x, y, titleSize, titleSize, null);
                        break;
                    case 5:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(upLeft, x, y, titleSize, titleSize, null);
                        break;
                    case 6:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(upRight, x, y, titleSize, titleSize, null);
                        break;
                    case 8:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(downRight, x, y, titleSize, titleSize, null);
                        break;
                    case 7:
                        x = j * titleSize;
                        y = i * titleSize;
                        g2.drawImage(downLeft, x, y, titleSize, titleSize, null);
                        break;

                }

//                if (gp.map[i][j] == 2) {
//                    int x = j * titleSize;
//                    int y = i * titleSize;
//                    g2.drawImage(height, x, y, titleSize, titleSize, null);
//                }
            }
        }
    }


}
