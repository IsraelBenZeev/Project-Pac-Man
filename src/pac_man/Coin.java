package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Coin extends Entity implements MyFunctions {
    BufferedImage imageCoin;
    int coinSize = titleSize / 4;
    static int score = 0;
    static int level = 1;
    Pacman pacman;
    BigCoin bigCoin = new BigCoin();
    Fruits fruits = new Fruits();
    int xF, yF;
    static int currentTimer = 300;
    static int timerFruit = currentTimer;

    public Coin(GamePanel gp, Pacman pacman) throws IOException {
        this.gp = gp;
        this.pacman = pacman;
        setValues();
    }

    @Override
    public void setValues() {
        try {
            imageCoin = ImageIO.read(getClass().getResourceAsStream(fullPath("coinsGold")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int saveLocation;
    public void setFruitRandom() {
        Random random = new Random();
        boolean b = true;
        while (b) {
            yF = random.nextInt(titleSize * 2, titleSize * 20);
            yF += (titleSize - Fruits.size) / 2;
            xF = random.nextInt(titleSize, titleSize * 20);
            xF += (titleSize - Fruits.size) / 2;
            if ((Board.map[yF / titleSize][xF / titleSize] == 1 || Board.map[yF / titleSize][xF / titleSize] == 0) && xF % 32 == 0 && yF % 32 == 0) {
                saveLocation = Board.map[(yF + (titleSize - Fruits.size) / 2) / 32][(xF + (titleSize - Fruits.size) / 2) / 32];
                Board.map[(yF + (titleSize - Fruits.size) / 2) / 32][(xF + (titleSize - Fruits.size) / 2) / 32] = random.nextInt(-5, -1);
                b = false;
            }
        }
    }
    //-1 = big coin
    //-2 = apple
    //-3 = chery
    //-4 = orange
    //-5 = strawberry
    public void setEating() {
        if (Pacman.play) {
            if (timerFruit == currentTimer) {
                setFruitRandom();
            }
        }
        if (Board.map[pacman.y / titleSize][pacman.x / titleSize] == 1) {
            Board.map[pacman.y / titleSize][pacman.x / titleSize] = 0;
            score += 10;
        }
        else if ((Board.map[pacman.y / titleSize][pacman.x / titleSize] == -1)) {
            SoundManager.playEatCoin();
            Board.map[pacman.y / titleSize][pacman.x / titleSize] = -6;
            score += 50;
            pacman.canEat = true;
            pacman.timer = 300;
        } else if ((Board.map[pacman.y / titleSize][pacman.x / titleSize] <= -2) && Board.map[pacman.y / titleSize][pacman.x / titleSize] >= -5) {
            Board.map[pacman.y / titleSize][pacman.x / titleSize] = -7;
            timerFruit = currentTimer;
            score += 30;// &&
            SoundManager.playEatCoin();
        }

    }

    public String fullPath(String p) {
        return "/resource/tiles/" + p + ".png";
    }


    public void update() {
    }

    public void draw(Graphics2D g2) {
        setEating();
        for (int i = 0; i < Board.map.length; i++) {
            for (int j = 0; j < Board.map[i].length; j++) {
                switch (Board.map[i][j]) {
                    case 1:
                        int x = j * titleSize + (titleSize - coinSize) / 2;
                        int y = i * titleSize + (titleSize - coinSize) / 2;
                        g2.drawImage(imageCoin, x, y, coinSize, coinSize, null);
                        break;
                    case -1:
                        x = j * titleSize + (titleSize - BigCoin.size) / 2;
                        y = i * titleSize + (titleSize - BigCoin.size) / 2;
                        if (spriteNum == 1) {
                            g2.drawImage(bigCoin.image, x, y, BigCoin.size, BigCoin.size, null);
                        }
                        break;
                    case -2:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;
                        y = i * titleSize + (titleSize - Fruits.size) / 2;
                        drawFruits(g2,fruits.apple,x,y);
//                        g2.drawImage(fruits.apple, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case -3:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;
                        y = i * titleSize + (titleSize - Fruits.size) / 2;
                        drawFruits(g2,fruits.chery,x,y);
//                        g2.drawImage(fruits.chery, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case -4:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;
                        y = i * titleSize + (titleSize - Fruits.size) / 2;
                        drawFruits(g2,fruits.orange,x,y);
//                        g2.drawImage(fruits.orange, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case -5:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;
                        y = i * titleSize + (titleSize - Fruits.size) / 2;
                        drawFruits(g2,fruits.strawberry,x,y);
//                        g2.drawImage(fruits.strawberry, x, y, Fruits.size, Fruits.size, null);
                        break;
                }
            }
            if (spriteCounter > 150) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;
                spriteCounter = 0;
            }
            spriteCounter++;
        }
    }

    public void drawFruits(Graphics2D g2, BufferedImage image, int x, int y) {
        if (timerFruit > 0) {
            g2.drawImage(image, x, y, Fruits.size, Fruits.size, null);
            timerFruit--;
        } else {
            if (saveLocation == 1)Board.map[yF / titleSize][xF / titleSize] = 1;
            else if (saveLocation == 0)Board.map[yF / titleSize][xF / titleSize] = 0;
            timerFruit = currentTimer;
        }
    }
}
