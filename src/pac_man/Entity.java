package pac_man;

import java.awt.image.BufferedImage;

public class Entity {
     int titleSize = 32;
    int x;
    int y;
    int speed;
    int numOnMap;
    BufferedImage image;
    BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    GamePanel gp;
    int xCoins=0, yCoins=0;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
