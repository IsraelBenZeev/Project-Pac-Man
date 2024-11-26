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

    public void size() {
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
        if (Pacman.play) {
            int chase = 0;
            int change = random.nextInt(18);
            if (change == 0) {
                chase = random.nextInt(gp.ghosts.size());
            }
            for (int i = 0; i < gp.ghosts.size(); i++) {//מעדכן את מיקום המפלצות
                if (i == chase) {
                    gp.ghosts.get(i).chasePacman(gp.pacman);
//                gp.ghosts.get(i).chasePacman(gp.pacman);
                } else {
                    gp.ghosts.get(i).moveRandom();
                }
            }
        }

    }

    public void chasePacman(Pacman pacman) {
        // קביעת כיוון בהתאם למיקום פקמן
        if (pacman.x < this.x && pastAble("left", y, x, Board.map, numOnMap)) {
            direction = "left";
            x -= speed;
        } else if (pacman.x > this.x && pastAble("right", y, x, Board.map, numOnMap)) {
            direction = "right";
            x += speed;
        } else if (pacman.y < this.y && pastAble("up", y, x, Board.map, numOnMap)) {
            direction = "up";
            y -= speed;
        } else if (pacman.y > this.y && pastAble("down", y, x, Board.map, numOnMap)) {
            direction = "down";
            y += speed;
        }

    }
    public void afterGhost(Pacman pacman){
        int pX = pacman.x;
        int pY = pacman.y;

        if (pX < x){
            if (pastAble("left",y,x,Board.map,numOnMap)) x-=speed;

        }

        else if (pX >x)
            if (pastAble("right",y,x,Board.map,numOnMap)) x+=speed;
        if (pY < y)
            if (pastAble("up",y,x,Board.map,numOnMap))y -= speed;

        else if (pY > y)
                if (pastAble("down",y,x,Board.map,numOnMap))y += speed;

    }

    public void chasePacmanAdvanced(Pacman pacman) {
        int[][] map = Board.map;
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Point> queue = new LinkedList<>();
        Point[][] parent = new Point[map.length][map[0].length];

        int startX = x / titleSize;
        int startY = y / titleSize;
        int targetX = pacman.x / titleSize;
        int targetY = pacman.y / titleSize;

        queue.offer(new Point(startX, startY));
        visited[startY][startX] = true;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == targetX && current.y == targetY) {
                // מצא את הצעד הראשון במסלול
                Point nextStep = backtrackFirstStep(parent, startX, startY, targetX, targetY);
                moveToNextStep(nextStep);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValidMove(newX, newY, map) && !visited[newY][newX]) {
                    queue.offer(new Point(newX, newY));
                    visited[newY][newX] = true;
                    parent[newY][newX] = current;
                }
            }
        }
    }

    private Point backtrackFirstStep(Point[][] parent, int startX, int startY, int targetX, int targetY) {
        Point current = new Point(targetX, targetY);
        Point prev = null;

        while (current.x != startX || current.y != startY) {
            prev = current;
            current = parent[current.y][current.x];
        }

        return prev;
    }

    private void moveToNextStep(Point nextStep) {
        int newX = nextStep.x * titleSize;
        int newY = nextStep.y * titleSize;

        if (newX < x) direction = "left";
        else if (newX > x) direction = "right";
        else if (newY < y) direction = "up";
        else if (newY > y) direction = "down";

        x = newX;
        y = newY;
    }

    private boolean isValidMove(int x, int y, int[][] map) {
        return x >= 0 && x < map[0].length &&
                y >= 0 && y < map.length &&
                map[y][x] == 1;  // בדיקה שהמיקום פנוי
    }

    public void QueueGhost() {
        if (Pacman.collision) {
            Queue<Ghosts> q = new LinkedList<>();
            for (Ghosts ghosts : gp.ghosts) {
                q.offer(ghosts);
            }
            System.out.println(q);
        }
    }

    public void moveRandom() {
        String[] directions = {"up", "down", "left", "right"};
        Random r = new Random();
        int ran = r.nextInt(35);
        if (ran == 0) {
            int index = r.nextInt(4);
            this.direction = directions[index];
        }
        if (direction.equals("up") && pastAble(direction, y, x, Board.map, numOnMap)) {
            y -= speed;
        } else if (direction.equals("down") && pastAble(direction, y, x, Board.map, numOnMap)) {
            y += speed;
        } else if (direction.equals("left") && pastAble(direction, y, x, Board.map, numOnMap)) {
            x -= speed;
        } else if (direction.equals("right") && pastAble(direction, y, x, Board.map, numOnMap)) {
            x += speed;
        } else {
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

        g2.drawImage(image, x, y, titleSize, titleSize, null);
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
        return x1 < Board.map[y1].length && arr[y1][x1] == num;
    }
}
//        }