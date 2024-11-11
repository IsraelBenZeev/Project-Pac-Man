package pac_man;

import java.awt.image.BufferedImage;

public class Entity {
    final int titleSize = 48;
    int x;
    int y;
    int speed;
    BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
