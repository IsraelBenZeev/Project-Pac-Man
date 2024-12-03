package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman extends Entity implements MyFunctions {
    public KeyHandler keyH;
    int numOnMap = 1;
    boolean up, down, right, left;
    static boolean canEat = false;
    static boolean collision = false;
    int timer = 0;
    static boolean play = false;
    static int lives = 3;

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
    public String fullPath(String p){
        return "/resource/pacman/" + p + ".png";
    }

    public void setImagePacman() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream(fullPath("up_1")));
            up2 = ImageIO.read(getClass().getResourceAsStream(fullPath("up_2")));
            down1 = ImageIO.read(getClass().getResourceAsStream(fullPath("down_1")));
            down2 = ImageIO.read(getClass().getResourceAsStream(fullPath("down_2")));
            left1 = ImageIO.read(getClass().getResourceAsStream(fullPath("left_1")));
            left2 = ImageIO.read(getClass().getResourceAsStream(fullPath("left_2")));
            right1 = ImageIO.read(getClass().getResourceAsStream(fullPath("right_1")));
            right2 = ImageIO.read(getClass().getResourceAsStream(fullPath("right_2")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ghostsEndPacmanCollision() {
        for (Ghosts ghost : gp.ghosts) {
            int playerX = x / titleSize;
            int playerY = y / titleSize;
            int ghostX = ghost.x / titleSize;
            int ghostY = ghost.y / titleSize;

            if (playerX == ghostX && playerY == ghostY) {
                if (!canEat) {
                    keyH.upPressed = keyH.downPressed = keyH.leftPressed = keyH.rightPressed = false;
                    SoundManager.playDied();
                    x = titleSize * 3;
                    y = titleSize * 11;
                    collision = true;
                    for (Ghosts ghosts : gp.ghosts) {
                        ghosts.isExitAgain = false;
                    }
//                    ghost.myTimer(1000);
                    gp.ghosts.getFirst().moveGhostStartPoint();
                    lives--;
                } else {
                    SoundManager.playEatCoin();
                    ghost.x = titleSize * 10;
                    ghost.y = titleSize * 8;
                    Coin.score += 200;
                }
            }
        }
    }
    public void movePacmanStartPoint(){
        y = 32 * 11;
        x = 32;
        speed = 4;
        direction = "right";
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
        boolean block =
                keyH.upPressed && !pastAble("up", y, x, Board.level1, numOnMap) ||
                        keyH.downPressed && !pastAble("down", y, x, Board.level1, numOnMap) ||
                        keyH.leftPressed && !pastAble("left", y, x, Board.level1, numOnMap) ||
                        keyH.rightPressed && !pastAble("right", y, x, Board.level1, numOnMap);
        if ((keyH.upPressed || block && direction.equals(UP)) && pastAble(UP, y, x, Board.level1, numOnMap)) {
            direction = UP;
            y -= speed;
        }
        if ((keyH.downPressed || block && direction.equals(DOWN)) && pastAble(DOWN, y, x, Board.level1, numOnMap)) {
            direction = DOWN;
            y += speed;
        }
        if ((keyH.leftPressed || block && direction.equals(LEFT)) && pastAble(LEFT, y, x, Board.level1, numOnMap)) {
            direction = LEFT;
            x -= speed;
            if (x < titleSize) {
                x = (Board.level1.length - 1) * titleSize;
            }
        }
        if ((keyH.rightPressed || block && direction.equals(RIGHT)) && pastAble(RIGHT, y, x, Board.level1, numOnMap)) {
            direction = RIGHT;
            x += speed;
            if (x == (Board.level1[0].length - 1) * titleSize) {
                x = titleSize;
            }
        }
        if (keyH.enterPressed) {
            up = down = right = left = false;
        }

        play = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        if (play) SoundManager.playEat();

        ghostsEndPacmanCollision();
        if (timer > 0) timer--;
        else canEat = false;

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
    }

    public boolean isUp(int y, int x, int[][] arr, int num) {
        int y1, x1 = 0, x2 = 0;
        y1 = (y - speed) / titleSize;
        if (x % titleSize != 0) {
            x1 = x / titleSize + 1;
            x2 = x / titleSize;
            return arr[y1][x1] <= num && arr[y1][x2] <= num;
        } else x1 = x / titleSize;
        return arr[y1][x1] <= num;
    }

    public boolean isLeft(int y, int x, int[][] arr, int num) {
        int y1 = 0, y2, x1 = 0;
        x1 = (x - speed) / titleSize;
        if (y % titleSize != 0) {
            y1 = y / titleSize;
            y2 = y / titleSize + 1;
            return arr[y1][x1] <= num && arr[y2][x1] <= num;
        } else y1 = (y - speed) / titleSize + 1;
        return x1 != -1 && arr[y1][x1] <= num;
    }

    public boolean isDown(int y, int x, int[][] arr, int num) {
        int y1, x1 = 0, x2 = 0;
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
        int y1 = 0, y2, x1 = 0;
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

}

