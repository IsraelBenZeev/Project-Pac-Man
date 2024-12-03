package pac_man;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Ghosts extends Entity implements MyFunctions {
    KeyHandler keyH;
    int numOnMap = 1;
    Random random = new Random();
    BufferedImage up, down, left, right;
    boolean isExit = false;
    boolean isExitAgain = false;
    static boolean onTimer = true;
    static int sumCoins = counterCoin();

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
        System.out.println(sumCoins);
    }

    public void setValues() throws IOException {
        direction = "up";
    }

    public static int counterCoin() {
        int remainder;
        int counterCoin = 0;
        for (int i = 0; i < Board.level1.length; i++) {
            for (int j = 0; j < Board.level1[i].length; j++) {
                if (Board.level1[i][j] == 1) counterCoin++;
            }
        }
        remainder = counterCoin % 4;
        if (remainder <= 2)
            return counterCoin - remainder;
        else return counterCoin + remainder;
    }

    private int currentGhostIndex = 0; // מנהל את האינדקס של המפלצת הנוכחית

    public void myTimer(int time) {
        currentGhostIndex = 0;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentGhostIndex < gp.ghosts.size()) {
                    Ghosts ghost = gp.ghosts.get(currentGhostIndex);
                    if (ghost.isExit) {
                        ghost.isExitAgain = true;
                    }
                    currentGhostIndex++;
                } else {
                    timer.cancel();
                    Pacman.collision = false;
                }
            }
        }, 0, time);
    }

    public void exitStart() {
//        int sum = (sumCoins / 4) * 10;
//        gp.ghosts.get(0).isExit = true;
//        gp.ghosts.get(1).isExit = true;
//        if (Coin.score >= sum) gp.ghosts.get(2).isExit = true;
//        if (Coin.score >= sum * 3) gp.ghosts.get(3).isExit = true;
//        if (Coin.score >= sum * 4) gp.ghosts.get(4).isExit = true;
        int sum = 200;
        if (Coin.score >= sum) gp.ghosts.get(0).isExit = true;
        if (Coin.score >= sum * 2) gp.ghosts.get(1).isExit = true;
        if (Coin.score >= sum * 3) gp.ghosts.get(2).isExit = true;
        if (Coin.score >= sum * 4) gp.ghosts.get(3).isExit = true;
        if (Coin.score >= sum * 5) gp.ghosts.get(4).isExit = true;
    }

    public void randomStart() {
        int chase = 0;
        int change = random.nextInt(18);
        if (change == 0) {
            chase = random.nextInt(gp.ghosts.size());
        }
        for (int i = 0; i < gp.ghosts.size(); i++) {
            if (i == chase && gp.ghosts.get(i).isExit) {
                gp.ghosts.get(i).chasePacman(gp.pacman);
            } else {
                if (gp.ghosts.get(i).isExit) gp.ghosts.get(i).moveRandom();
            }
        }
    }

    public void randomCollision() {
        int chase = 0;
        int change = random.nextInt(18);
        if (change == 0) {
            chase = random.nextInt(gp.ghosts.size());
        }
        for (int i = 0; i < gp.ghosts.size(); i++) {
            if (i == chase && gp.ghosts.get(i).isExit && gp.ghosts.get(i).isExitAgain) {
                gp.ghosts.get(i).chasePacman(gp.pacman);
            } else {
                if (i != chase && gp.ghosts.get(i).isExit && gp.ghosts.get(i).isExitAgain)
                    gp.ghosts.get(i).moveRandom();
            }
        }
    }

    @Override
    public void update() {
        exitStart();
        if (Pacman.play) {
            if (!Pacman.canEat && !Pacman.collision) {
                randomStart();
            } else if (Pacman.play && Pacman.collision && !Pacman.canEat) {
                myTimer(3000);
                randomCollision();
            } else if (Pacman.canEat) {
                for (Ghosts ghost : gp.ghosts) {
                    if (ghost.isExit) ghost.moveRandom();
                }
            }
        }
    }

    public void chasePacman(Pacman pacman) {
        if (pacman.x < this.x && pastAble("left", y, x, Board.level1, numOnMap)) {
            direction = "left";
            x -= speed;
        } else if (pacman.x > this.x && pastAble("right", y, x, Board.level1, numOnMap)) {
            direction = "right";
            x += speed;
        } else if (pacman.y < this.y && pastAble("up", y, x, Board.level1, numOnMap)) {
            direction = "up";
            y -= speed;
        } else if (pacman.y > this.y && pastAble("down", y, x, Board.level1, numOnMap)) {
            direction = "down";
            y += speed;
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
        if (direction.equals(UP) && pastAble(direction, y, x, Board.level1, numOnMap)) {
            y -= speed;
        } else if (direction.equals(DOWN) && pastAble(direction, y, x, Board.level1, numOnMap)) {
            y += speed;
        } else if (direction.equals(LEFT) && pastAble(direction, y, x, Board.level1, numOnMap)) {
            x -= speed;
        } else if (direction.equals(RIGHT) && pastAble(direction, y, x, Board.level1, numOnMap)) {
            x += speed;
        } else {
            int move = r.nextInt(4);
            direction = directions[move];
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (direction.equals(UP)) image = up;
        if (direction.equals(DOWN)) image = down;
        if (direction.equals(LEFT)) image = left;
        if (direction.equals(RIGHT)) image = right;

        g2.drawImage(image, x, y, titleSize, titleSize, null);
    }

    public void moveGhostStartPoint() {
        int a = 8;
        for (Ghosts ghost1 : gp.ghosts) {
            ghost1.x = titleSize * a;
            ghost1.y = titleSize * 8;
            a += 1;
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

    public boolean isUp(int y, int x, int[][] arr, int num) {
        int y1, x1, x2;
        y1 = (y - speed) / titleSize;
        if (x % titleSize != 0) {
            x1 = x / titleSize + 1;
            x2 = x / titleSize;
            return arr[y1][x1] <= num && arr[y1][x2] <= num;
        } else x1 = x / titleSize;
        return arr[y1][x1] <= num;
    }

    public boolean isLeft(int y, int x, int[][] arr, int num) {
        int y1, y2, x1;
        x1 = (x - speed) / titleSize;
        if (y % titleSize != 0) {
            y1 = y / titleSize;
            y2 = y / titleSize + 1;
            return arr[y1][x1] <= num && arr[y2][x1] <= num;
        } else y1 = (y - speed) / titleSize + 1;
        return x1 != -1 && arr[y1][x1] <= num;
    }

    public boolean isDown(int y, int x, int[][] arr, int num) {
        int y1, x1, x2;
        if ((y + speed) % titleSize != 0) {
            y1 = (y + speed) / titleSize + 1;
        } else y1 = (y + speed) / titleSize;
        if (x % titleSize != 0) {
            x1 = x / titleSize;
            x2 = x / titleSize + 1;
            return arr[y1][x1] <= num && arr[y1][x2] <= num;
        } else x1 = x / titleSize;
        return arr[y1][x1] <= num;
    }

    public boolean isRight(int y, int x, int[][] arr, int num) {
        int y1, y2, x1;
        if ((x + speed) % titleSize != 0) {
            x1 = (x + speed) / titleSize + 1;
        } else x1 = (x + speed) / titleSize;
        if (y % titleSize != 0) {
            y1 = y / titleSize;
            y2 = y / titleSize + 1;
            return arr[y1][x1] <= num && arr[y2][x1] <= num;
        } else y1 = y / titleSize;
        return x1 < Board.level1[y1].length && arr[y1][x1] <= num;
    }


    public boolean isStartGhost() {
        return gp.ghosts.get(0).x == titleSize * 8 && gp.ghosts.get(0).y == titleSize * 8 &&
                gp.ghosts.get(1).x == titleSize * 9 && gp.ghosts.get(1).y == titleSize * 8 &&
                gp.ghosts.get(2).x == titleSize * 10 && gp.ghosts.get(2).y == titleSize * 8 &&
                gp.ghosts.get(3).x == titleSize * 11 && gp.ghosts.get(3).y == titleSize * 8 &&
                gp.ghosts.get(4).x == titleSize * 12 && gp.ghosts.get(4).y == titleSize * 8;
    }
}
//        }