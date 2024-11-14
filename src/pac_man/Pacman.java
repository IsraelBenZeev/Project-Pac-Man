package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman extends Entity {
    int pacmanSize = titleSize -8;
    public KeyHandler keyH;
    int numOnMap = 1;

    public Pacman(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
        setValues();
    }

    public void setValues() {
        y = 384-32;
        x = 32;
        speed = 2;
        direction = "down";
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

    public int changeX (int x){
        return x / 32;
    }

    public boolean pastAble (String direction,int y, int x, int [][] arr,int num){
        switch (direction){
            case "up":
                return isUp(y, x,arr,num);
            case "down":
                return isDown(y, x,arr,num);
            case "left":
                return isLeft(y, x,arr,num);
            case "right":
                return isRight(y, x,arr,num);
        }
    return false;
    }

    public void update() throws InterruptedException {

            direction = "up";
        if (keyH.upPressed) {
            boolean up = true;
            if (pastAble(direction,y,x,gp.map, numOnMap)) y -= speed;
        }
        if (keyH.downPressed) {
            direction = "down";
            if (pastAble(direction,y,x,gp.map, numOnMap)) y += speed;
        }
        if (keyH.leftPressed) {
            direction = "left";
            if (pastAble(direction,y,x,gp.map, numOnMap)) x -= speed;
            if (x < 0) {
                Thread.sleep(1);
                x = (gp.map.length-1) *titleSize;
            }
        }
        if (keyH.rightPressed) {
            direction = "right";
            if (pastAble(direction,y,x,gp.map, numOnMap)) x += speed;
            if (x == (gp.map.length-1) *32){
                Thread.sleep(1);
                x = titleSize;
            }
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
        g2.drawImage(image, x+6, y+6, pacmanSize, pacmanSize, null);
    }
    public boolean isUp (int y, int x, int [][] arr,int num){
        System.out.println("this: x: " + x + ", " + "y: " + y);
        int y1, x1 = 0, x2 = 0;
        y1 = (y - speed) / titleSize;
//        if ((y-speed) % titleSize != 0){
//            y1 = (y - speed) / titleSize;
//        }
//        else y1 = (y - speed) / titleSize;
        if (x % titleSize != 0){
            x1 = x / titleSize +1;
            x2 = x / titleSize;
            return arr[y1][x1] == num && arr[y1][x2] == num;
        }
        else x1 = x / titleSize;
        System.out.println("X & Y of function:\nx: " + y1 + ", " + "y: " + x1);
        return  arr[y1][x1] == num;
    }

    public boolean isLeft(int y, int x, int [][] arr,int num){
        System.out.println("x: " + x + ", " + "y: " + y);
        int y1 = 0,y2,x1 = 0;
        x1 = (x-speed) / titleSize;
//        if ((x - speed) % titleSize != 0){
//            System.out.println("נכנס ל IF");
//            x1 = (x-speed) / titleSize;
//        }
//        else x1 = (x-speed) / titleSize;
        if (y % titleSize != 0){
            y1 = y / titleSize;
            y2 = y / titleSize +1;
            return arr[y1][x1] == num && arr[y2][x1] == num;
        }
        else  y1 = (y-speed) / titleSize+1;
        System.out.println("X & Y of function:\nx: " + x1 + ", " + "y: " + y1);
        return x1 != -1  && arr[y1][x1] == 1;
    }
    public boolean isDown (int y, int x, int[][] arr,int num){
        System.out.println("x: " + x + ", " + "y: " + y);
        int y1, x1 = 0, x2 = 0;
        if ((y + speed)% titleSize != 0) {
            y1 = (y + speed) / titleSize + 1;
        }
        else y1 = (y + speed)/ titleSize;
        if (x % titleSize != 0){
            x1 = x / titleSize;
            x2 = x / titleSize +1;
            return arr[y1][x1] == num && arr [y1][x2] == num;
        }
        else x1 = x / titleSize;
        return arr[y1][x1] == num;
    }
    public boolean isRight(int y, int x, int [][] arr,int num){
        int y1 = 0,y2,x1 = 0;
        System.out.println("x: " + x + ", " + "y: " + y);
        if ((x + speed) % titleSize != 0){
            x1 = (x + speed) / titleSize +1;
        }
        else x1 = (x + speed) / titleSize;
        if (y % titleSize != 0){
            y1 = y / titleSize;
            y2 = y / titleSize +1;
        System.out.println("X & Y of function:\nx: " + x1 + ", " + "y: " + y1);
            return  arr[y1][x1] == num && arr [y2][x1] == num;
        }
        else y1 = y / titleSize;
        return x1 < gp.map[y1].length &&  arr[y1][x1] == num;
    }

}

