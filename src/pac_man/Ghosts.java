package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Ghosts extends Entity implements MyFunctions {
    KeyHandler keyH;
    int numOnMap = 1;
//    BufferedImage blueUp, blueDown, BlueLeft, blueRight;
//    BufferedImage pinkUp, pinkDown, pinkLeft, pinkRight;
//    BufferedImage orangeUp, orangeDown, orangeLeft, orangeRight;
//    BufferedImage redUp, redDown, redLeft, redRight;
    BufferedImage up, down, left, right;


    public Ghosts(GamePanel gp, KeyHandler keyH, int x, int y, int speed,
                  BufferedImage up, BufferedImage down, BufferedImage left, BufferedImage right) throws IOException {
        this.gp = gp;
        this.keyH = keyH;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;


        setValues();
    }

    public void setValues() throws IOException {
//        String blueUp = GamePanel.fullPath("Blue_up.png");
//        String blueDown = GamePanel.fullPath("blue_down.png");
//        String blueLeft = GamePanel.fullPath("blue_left.png");
//        String blueRight = GamePanel.fullPath("blue_right.png");
//        up = ImageIO.read(getClass().getResourceAsStream(blueUp));
//        down = ImageIO.read(getClass().getResourceAsStream(blueDown));
//        left = ImageIO.read(getClass().getResourceAsStream(blueLeft));
//        right = ImageIO.read(getClass().getResourceAsStream(blueRight));
        direction = "up";
    }





    @Override
    public void update() {
        moveRandom();

    }

    public void moveRandom (){
        String[] directions = {"up", "down", "left", "right"};
        Random r = new Random();
        int ran = r.nextInt(35);
        if (ran == 0) {
            int index = r.nextInt(4);
            this.direction = directions[index];
        }
        if (direction.equals("up") && pastAble(direction,y,x, gp.map, numOnMap)){
            y -= speed;
        }
        else if (direction.equals("down") && pastAble(direction,y,x, gp.map, numOnMap)){
            y += speed;
        }
        else if (direction.equals("left") && pastAble(direction,y,x, gp.map, numOnMap)){
            x -= speed;
        }
        else if (direction.equals("right") && pastAble(direction,y,x, gp.map, numOnMap)){
            x += speed;
        }
        else{
            int move = r.nextInt(4);
            direction = directions[move];
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (direction.equals("up")) image = up;
        if (direction.equals("down")) image = down;
        if (direction.equals("left")) image = left;
        if (direction.equals("right")) image = right;

        g2.drawImage(image,x,y,titleSize,titleSize,null);

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