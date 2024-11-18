package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman extends Entity implements MyFunctions {
    //int pacmanSize = titleSize - 8;
    public KeyHandler keyH;
    int numOnMap = 1;
    boolean up, down, right, left;

    public Pacman(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
        setValues();
    }

    @Override
    public void setValues() {
        y = 32 * 11;
        x = 32;
        speed = 4;
        direction = "right";
        setImagePacman();
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

    public boolean pastAble(String direction, int y, int x, int[][] arr, int num) {
        switch (direction) {
            case "up":
                return isUp(y, x, arr, num);
            case "down":
                return isDown(y, x, arr, num);
            case "left":
                return isLeft(y, x, arr, num);
            case "right":
                return isRight(y, x, arr, num);
        }
        return false;
    }

    public void update() {
        boolean bol =
                keyH.upPressed && !pastAble("up", y, x, gp.map, numOnMap) ||
                keyH.downPressed && !pastAble("down", y, x, gp.map, numOnMap) ||
                keyH.leftPressed && !pastAble("left", y, x, gp.map, numOnMap) ||
                keyH.rightPressed && !pastAble("right", y, x, gp.map, numOnMap);

        if ((keyH.upPressed || bol && direction.equals("up")) && pastAble("up", y, x, gp.map, numOnMap)) {
            direction = "up";
            y -= speed;
        }
        if ((keyH.downPressed || bol && direction.equals("down")) && pastAble("down", y, x, gp.map, numOnMap)) {
            direction = "down";
            y += speed;
        }
        if ((keyH.leftPressed || bol && direction.equals("left")) && pastAble("left", y, x, gp.map, numOnMap)) {
            direction = "left";
            x -= speed;
            if (x < 32) {
                x = (gp.map.length - 1) * titleSize;
            }
        }
        if ((keyH.rightPressed || bol && direction.equals("right")) && pastAble("right", y, x, gp.map, numOnMap)) {
            direction = "right";
            x += speed;
            if (x == (gp.map.length - 1) * 32) {
                x = titleSize;
            }
        }
        if (keyH.enterPressed) up = down = right = left = false;
        spriteCounter++;
        if (spriteCounter > 11) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
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
        xCoins = x / titleSize;
        yCoins = y / titleSize;
    }

    public boolean isUp(int y, int x, int[][] arr, int num) {
//        System.out.println("this: x: " + x + ", " + "y: " + y);
        int y1, x1 = 0, x2 = 0;
        y1 = (y - speed) / titleSize;
        if (x % titleSize != 0) {
            x1 = x / titleSize + 1;
            x2 = x / titleSize;
            return arr[y1][x1] == num && arr[y1][x2] == num;
        } else x1 = x / titleSize;
//        System.out.println("X & Y of function:\nx: " + y1 + ", " + "y: " + x1);
        return arr[y1][x1] == num;
    }

    public boolean isLeft(int y, int x, int[][] arr, int num) {
//        System.out.println("x: " + x + ", " + "y: " + y);
        int y1 = 0, y2, x1 = 0;
        x1 = (x - speed) / titleSize;
        if (y % titleSize != 0) {
            y1 = y / titleSize;
            y2 = y / titleSize + 1;
//            System.out.println("X & Y of function:\nx: " + x1 + ", " + "y: " + y1);
            return arr[y1][x1] == num && arr[y2][x1] == num;
        } else y1 = (y - speed) / titleSize + 1;
//        System.out.println("X & Y of function:\nx: " + x1 + ", " + "y: " + y1);
        return x1 != -1 && arr[y1][x1] == 1;
    }

    public boolean isDown(int y, int x, int[][] arr, int num) {
//        System.out.println("x: " + x + ", " + "y: " + y);
        int y1, x1 = 0, x2 = 0;
        if ((y + speed) % titleSize != 0) {
            y1 = (y + speed) / titleSize + 1;
        } else y1 = (y + speed) / titleSize;
        if (x % titleSize != 0) {
            x1 = x / titleSize;
            x2 = x / titleSize + 1;
            return arr[y1][x1] == num && arr[y1][x2] == num;
        } else x1 = x / titleSize;
        return arr[y1][x1] == num;
    }

    public boolean isRight(int y, int x, int[][] arr, int num) {
        int y1 = 0, y2, x1 = 0;
//        System.out.println("x: " + x + ", " + "y: " + y);
        if ((x + speed) % titleSize != 0) {
            x1 = (x + speed) / titleSize + 1;
        } else x1 = (x + speed) / titleSize;
        if (y % titleSize != 0) {
            y1 = y / titleSize;
            y2 = y / titleSize + 1;
//            System.out.println("X & Y of function:\nx: " + x1 + ", " + "y: " + y1);
            return arr[y1][x1] == num && arr[y2][x1] == num;
        } else y1 = y / titleSize;
        return x1 < gp.map[y1].length && arr[y1][x1] == num;
    }

}

