package pac_man;

import java.awt.image.BufferedImage;

public class Entity {
     int titleSize = 32;
    int x;
    int y;
    int speed;
    String UP = "up", DOWN = "down", LEFT = "left", RIGHT = "right";
    int numOnMap;
    BufferedImage image;
    BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    GamePanel gp;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
