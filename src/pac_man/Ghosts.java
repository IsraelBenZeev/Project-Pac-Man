package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Ghosts extends Entity implements MyFunctions {
    KeyHandler keyH;
    public void size (){
        this.titleSize = 32;
    }
    int numOnMap = 1;
    Random random = new Random();
    BufferedImage up, down, left, right;

    public Ghosts(GamePanel gp, KeyHandler keyH, int x, int y,
                  BufferedImage up, BufferedImage down, BufferedImage left, BufferedImage right) throws IOException {
        this.gp = gp;
        this.keyH = keyH;
        this.x = x;
        this.y = y;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.speed = 2;
        setValues();
    }

    public void setValues() throws IOException {
        direction = "up";
    }

    @Override
    public void update() {
        for (Ghosts ghosts  : gp.ghosts) {
            ghosts.moveRandom();
        }
//        int chase = 0;
//        int change = random.nextInt(35);
//        if (change == 0) {
//            chase = random.nextInt(gp.ghosts.size());
//        }
//        for (int i = 0; i < gp.ghosts.size(); i++) {//מעדכן את מיקום המפלצות
//            if (i == chase) {
//                gp.ghosts.get(i).chasePacman(gp.pacman);
//            } else {
//                gp.ghosts.get(i).moveRandom();
//            }
//        }
    }

    public void moveRandom (){
        String[] directions = {"up", "down", "left", "right"};
        Random r = new Random();
        int ran = r.nextInt(35);
        if (ran == 0) {
            int index = r.nextInt(4);
            this.direction = directions[index];
        }
        if ( direction.equals("up") && pastAble(direction,y,x, gp.map, numOnMap)){
            y -= speed;
        }
        else if ( direction.equals("down") && pastAble(direction,y,x, gp.map, numOnMap)){
            y += speed;
        }
        else if ( direction.equals("left") && pastAble(direction,y,x, gp.map, numOnMap)){
            x -= speed;
        }
        else if ( direction.equals("right") && pastAble(direction,y,x, gp.map, numOnMap)){
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
//    public void chasePacmanBFS(Pacman pacman) {
//        // המפה
//        int mapHeight = gp.map.length;  // מספר השורות במפה
//        int mapWidth = gp.map[0].length;  // מספר העמודות במפה
//
//        // רשימות עבור BFS
//        Queue<int[]> queue = new LinkedList<>();
//        boolean[][] visited = new boolean[mapHeight][mapWidth];
//
//        // מיקום ההתחלה (מיקום המפלצת)
//        int startX = this.x;
//        int startY = this.y;
//
//        // הוספת המיקום ההתחלתי לתור
//        queue.offer(new int[] {startX, startY});
//        visited[startY][startX] = true;
//
//        // כיוונים אפשריים (מעלה, למטה, שמאלה, ימינה)
//        int[] directionsX = {0, 0, -1, 1}; // שמאל / ימין
//        int[] directionsY = {-1, 1, 0, 0}; // למעלה / למטה
//
//        while (!queue.isEmpty()) {
//            int[] current = queue.poll();
//            int currentX = current[0];
//            int currentY = current[1];
//
//            // אם הגענו לפקמן, עצור
//            if (currentX == pacman.x && currentY == pacman.y) {
//                break;
//            }
//
//            // עבור כל כיוון אפשרי
//            for (int i = 0; i < 4; i++) {
//                int newX = currentX + directionsX[i];
//                int newY = currentY + directionsY[i];
//
//                // בדוק אם המיקום החדש בתווך המפה
//                if (newX >= 0 && newX < mapWidth && newY >= 0 && newY < mapHeight) {
//                    // בדוק אם לא ביקרנו במיקום הזה ואם הוא לא חסום (0)
//                    if (!visited[newY][newX] && gp.map[newY][newX] != 0) {
//                        // הוסף את המיקום החדש לתור
//                        queue.offer(new int[] {newX, newY});
//                        visited[newY][newX] = true;
//                    }
//                }
//            }
//        }
//
//        // אם מצאנו את הפקמן, עדכן את המיקום של המפלצת
//        if (visited[pacman.y][pacman.x]) {
//            moveInDirection(pacman.x, pacman.y); // עדכון המפלצת לפי מיקום פקמן
//        }
//    }
//
//
//
//    public void moveInDirection(int targetX, int targetY) {
//        int deltaX = targetX - this.x;
//        int deltaY = targetY - this.y;
//
//        // התאם את כיוון המפלצת לפקמן
//        if (Math.abs(deltaX) > Math.abs(deltaY)) {
//            if (deltaX > 0) {
//                direction = "right";
//            } else {
//                direction = "left";
//            }
//        } else {
//            if (deltaY > 0) {
//                direction = "down";
//            } else {
//                direction = "up";
//            }
//        }
//
//        // לאחר שהכיוונים התעדכנו, תזוזה במפה
//        if (direction.equals("up") && pastAble(direction, y, x, gp.map, numOnMap)) {
//            y -= speed;
//        } else if (direction.equals("down") && pastAble(direction, y, x, gp.map, numOnMap)) {
//            y += speed;
//        } else if (direction.equals("left") && pastAble(direction, y, x, gp.map, numOnMap)) {
//            x -= speed;
//        } else if (direction.equals("right") && pastAble(direction, y, x, gp.map, numOnMap)) {
//            x += speed;
//        }
//    }























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