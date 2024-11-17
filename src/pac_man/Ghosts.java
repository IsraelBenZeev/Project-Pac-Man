package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Ghosts extends Entity implements MyFunctions {
    final int size = 32;
    BufferedImage green, pink, red, blue, orange;

//    Ghosts[] ghosts = new Ghosts[5];
    KeyHandler keyH;
    int numOnMap = 1;
    int counterX, counterY;
    boolean isMainGhost;


    public Ghosts(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
//        this.isMainGhost = true;
        setValues();
    }

//    public Ghosts(GamePanel gp, KeyHandler keyH) {
//        this.gp = gp;
//        this.keyH = keyH;
//        this.isMainGhost = false; // זוהי רוח משנית
//    }
    public void setValues() {
        x = 256;
        y = 256;
        speed = 4;
        try {
//            for(int i = 0; i < ghosts.length; i++) {
//                ghosts[i] = new Ghosts(gp, keyH);
//            }
            blue = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_blue.jpg"));
//            ghosts[1].image = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_green.jpg"));
//            ghosts[2].image = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_orange.jpg"));
//            ghosts[3].image = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_pink.jpg"));
//            ghosts[4].image = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_red.jpg"));
//            ghosts[0].x = 8 * titleSize;
//            ghosts[1].x = 9 * titleSize;
//            ghosts[2].x = 10 * titleSize;
//            ghosts[3].x = 11 * titleSize;
//            ghosts[4].x = 12 * titleSize;
//
//            ghosts[0].y = 8 * titleSize;
//            ghosts[1].y = 8 * titleSize;
//            ghosts[2].y = 8 * titleSize;
//            ghosts[3].y = 8 * titleSize;
//            ghosts[4].y = 8 * titleSize;
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

    @Override
    public void update() throws InterruptedException {
        System.out.println("X: "+x+", Y: " + y);
        //        for (int i = 0; i < gp.map.length; i++) {
//            for (int j = 0; j < gp.map[i].length; j++) {
//                if (gp.map[i][j] == 2){
//                    int x = j * size;
//                    int y = i * size;
//        if (keyH.downPressed || keyH.upPressed ||
//                keyH.leftPressed || keyH.rightPressed) {
//            counterX++;
//            counterY++;
//        }
        //&& ghosts != null && ghosts[0] != null
        boolean bol = false;
        bol = pastAble("up",y,x, gp.map, numOnMap);
        System.out.println("up: "+bol);
        if (bol){
            y -= speed;
        }
        else if (pastAble("down",y,x, gp.map, numOnMap)){
            System.out.println("down: "+bol);
//            if (bol)
                y += speed;
        }
        else if((pastAble("left",y,x, gp.map, numOnMap))) {
            System.out.println("left: " + bol);
//        if (bol)
            x -= speed;
        }
         else if ((pastAble("right",y,x, gp.map, numOnMap))){
        System.out.println("right: "+bol);
//        if (bol)
            x += speed;
            }
    }

    @Override
    public void draw(Graphics2D g2) {
//        System.out.println("X: "+ghosts[3].x+", Y: "+ghosts[3].y);
//        for (int i = 0; i < gp.map.length; i++) {
//            for (int j = 0; j < gp.map[i].length; j++) {
//                if (gp.map[i][j] == 2){
//                    int x = j * size;
//                    int y = i * size;
        g2.drawImage(blue, x, y, titleSize, titleSize, null);
//        g2.drawImage(ghosts[1].image, ghosts[1].x, ghosts[1].y, titleSize, titleSize, null);
//        g2.drawImage(ghosts[2].image, ghosts[2].x, ghosts[2].y, titleSize, titleSize, null);
//        g2.drawImage(ghosts[3].image, ghosts[3].x, ghosts[3].y, titleSize, titleSize, null);
//        g2.drawImage(ghosts[4].image, ghosts[4].x, ghosts[4].y, titleSize, titleSize, null);
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
//        }