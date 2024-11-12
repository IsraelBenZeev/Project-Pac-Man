package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman extends Entity {
    final int size = 32;
//    public GamePanel gp;
    public KeyHandler keyH;
    int currentY;
    int currentX;
    public Pacman(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
        setImagePacman();
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 32;
        y = 32;
        speed = 4;
        direction = "down";
    }

    public void setImagePacman() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/right_2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isTile(int num, int[][] arr, int y, int x) {
        return x > 0 && y > 0 && x < 16 && y < 12 && arr[y][x] == num;
    }

    public void update() {
            int num = 1;
            int nextX = x, nextY = y;
        if (keyH.upPressed) {
            direction = "up";
            if (gp.map [y /gp.tileSize][x/gp.tileSize] == 1) y-=speed;

        }
        if (keyH.downPressed) {
            direction = "down";

        }
        if (keyH.leftPressed) {

        }
        if (keyH.rightPressed) {
            direction = "right";
        }
        spriteCounter++;
        if (spriteCounter > 11) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 1;
        spriteCounter = 0;
        }

    }

    public void draw(Graphics g2) {
        BufferedImage image = up1;
        switch (direction) {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                break;
            case "down":
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                break;
        }
        g2.drawImage(image, x, y, titleSize, titleSize, null);
    }
}

