package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class Coins extends Entity implements MyFunctions {

    int coinSize = titleSize / 4;
    BufferedImage start;
    BufferedImage background;
    static int score = 0;
    static int level = 1;
    Pacman pacman;
    BigCoin bigCoin = new BigCoin();
    Fruits fruits = new Fruits();
    int counterFruit = 0;
    int xF, yF;
    int timerFruit = 200;

    public int[][] eating = {
            //0///1///2//3////4///5///6///7///8///9//10//11//12//13//14//15//16//17//18//19//20//21
            {5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 6}, //0
            {3, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4}, //1
            {2, 1, 1, 1, 14, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 2}, //2
            {2, 1, 5, 9, 6, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 5, 9, 1, 2, 1, 2}, //3
            {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2}, //4
            {2, 1, 3, 9, 4, 1, 2, 1, 9, 9, 10, 9, 9, 1, 2, 1, 2, 1, 9, 8, 1, 2}, //5
            {2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2}, //6
            {2, 1, 1, 1, 1, 1, 3, 9, 9, 1, 2, 1, 9, 9, 4, 1, 5, 9, 6, 1, 1, 2}, //7
            {3, 9, 9, 9, 6, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 7, 9, 9, 4}, //8
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2}, //9
            {7, 9, 9, 1, 2, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 2, 1, 1, 9, 9, 8}, //10
            {1, 19, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 1, 1, 1, 1, 1, 1, 19}, //11
            {5, 9, 9, 9, 6, 1, 2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 2, 1, 5, 9, 9, 6}, //12
            {2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1}, //13
            {3, 9, 9, 9, 8, 1, 7, 9, 9, 1, 2, 1, 5, 9, 9, 9, 8, 1, 7, 9, 9, 6}, //14
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 14, 1, 1, 1, 1, 1, 1, 2}, //15
            {2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 1, 1, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2}, //16
            {2, 14, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //17
            {2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 9, 10, 9, 9, 1, 1, 2, 1, 1, 1, 1, 2}, //18
            {2, 1, 7, 9, 1, 9, 9, 11, 9, 1, 1, 2, 1, 1, 1, 9, 11, 9, 9, 9, 1, 2}, //19
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //20
            {7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}  //21
    };

    public int[][] eating2 = {
            //0///1///2//3////4///5///6///7///8///9//10//11//12//13//14//15//16//17//18//19//20//21
            {5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 6}, //0
            {3, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4}, //1
            {2, 1, 1, 1, 14, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //2
            {2, 1, 5, 9, 6, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 5, 9, 1, 2, 1, 2}, //3
            {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2}, //4
            {2, 1, 3, 9, 4, 1, 2, 1, 9, 9, 10, 9, 9, 1, 2, 1, 2, 1, 9, 8, 1, 2}, //5
            {2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2}, //6
            {2, 1, 1, 1, 1, 1, 3, 9, 9, 1, 2, 1, 9, 9, 4, 1, 5, 9, 6, 1, 1, 2}, //7
            {3, 9, 9, 9, 6, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 7, 9, 9, 4}, //8
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2}, //9
            {7, 9, 9, 1, 2, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 2, 1, 1, 9, 9, 8}, //10
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //11
            {5, 9, 9, 9, 6, 1, 2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 2, 1, 5, 9, 9, 6}, //12
            {2, 20, 20, 20, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1}, //13
            {3, 9, 9, 9, 8, 1, 7, 9, 9, 1, 2, 1, 5, 9, 9, 9, 8, 1, 7, 9, 9, 6}, //14
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 14, 1, 1, 1, 1, 1, 1, 2}, //15
            {2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 1, 1, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2}, //16
            {2, 14, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //17
            {2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 9, 10, 9, 9, 1, 1, 2, 1, 1, 1, 1, 2}, //18
            {2, 1, 7, 9, 1, 9, 9, 11, 9, 1, 1, 2, 1, 1, 1, 9, 11, 9, 9, 9, 1, 2}, //19
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //20
            {7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}  //21
    };

    public void setFruitRandom() {
        Random random = new Random();
        boolean b = true;
        while (b) {
            yF = random.nextInt(titleSize * 2, titleSize * 20);
            yF += (titleSize - Fruits.size) / 2;
            xF = random.nextInt(titleSize, titleSize * 20);
            xF += (titleSize - Fruits.size) / 2;
            if (eating[yF / titleSize][xF / titleSize] == 1 && xF % 32 == 0 && yF % 32 == 0) {
                eating[(yF + (titleSize - Fruits.size) / 2) / 32][(xF + (titleSize - Fruits.size) / 2) / 32] = random.nextInt(15, 18);

                b = false;
            }
        }
    }

    //14 = big coin
    //15 = apple
    //16 = chery
    //17 = orange
    //18 = strawberry
    public void setEating() {
        if ((eating[pacman.y / titleSize][pacman.x / titleSize] == 1)) {// || (eating[pacman.yCoins][pacman.xCoins] >= 14) &&
        }//(eating[pacman.yCoins][pacman.xCoins] <= 18)) {
        if (eating[pacman.y / titleSize][pacman.x / titleSize] == 1) score += 10;
        else if ((eating[pacman.y / titleSize][pacman.x / titleSize] == 14)) {
            SoundManager.playEatCoin();
            score += 50;
            pacman.canEat = true;
            pacman.timer = 300;
        } else if ((eating[pacman.y / titleSize][pacman.x / titleSize] > 14) && eating[pacman.y / titleSize][pacman.x / titleSize] < 19) {
//            setFruitRandom();
            score += 30;// &&
            SoundManager.playEatCoin();
        }
        else if (timerFruit == 200){
            setFruitRandom();
//            timerFruit = 200;
        }
        eating[pacman.y / titleSize][pacman.x / titleSize] = 20;
    }

    public Coins() throws IOException {
    }

    public Coins(GamePanel gp, Pacman pacman) throws IOException {
        this.gp = gp;
        this.pacman = pacman;
        setValues();
    }

    public String fullPath(String p) {
        return "/resource/tiles/" + p + ".png";
    }

    @Override
    public void setValues() {
//        setFruitRandom();
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fullPath("coinsGold")));
            start = ImageIO.read(getClass().getResourceAsStream(fullPath("start")));
            background = ImageIO.read(getClass().getResourceAsStream(fullPath("רקע")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void nextLevel() {
        eating = eating2;
        level = 2;
        for (Ghosts ghost : gp.ghosts) {
            ghost.speed = 2;
        }
    }

    public void update() {
        if (level == 2) SoundManager.playNextLevel();
//        if (pacman.x == xF + 12 && pacman.y == yF +12) setFruitRandom();
    }

    public void draw(Graphics2D g2) {
        if (score >= 7500) {
            nextLevel();
        }

        setEating();
        for (int i = 0; i < Board.map.length; i++) {
            for (int j = 0; j < Board.map[i].length; j++) {
                switch (eating[i][j]) {
                    case 1:
                        int x = j * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אופקי
                        int y = i * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אנכי
                        g2.drawImage(image, x, y, coinSize, coinSize, null);
                        break;
                    case 14:
                        x = j * titleSize + (titleSize - BigCoin.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - BigCoin.size) / 2;  // מרכוז אנכי
                        if (spriteNum == 1) {
                            g2.drawImage(bigCoin.image, x, y, BigCoin.size, BigCoin.size, null);
                        }
                        break;
                    case 15:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אנכי
                        drawFruits(g2,fruits.apple,x,y);
//                        g2.drawImage(fruits.apple, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case 16:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;// מרכוז אנכי
                        drawFruits(g2,fruits.chery,x,y);
//                        g2.drawImage(fruits.chery, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case 17:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;// מרכוז אנכי
                        drawFruits(g2,fruits.orange,x,y);
//                        g2.drawImage(fruits.orange, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case 18:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;// מרכוז אנכי
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
            counterFruit++;
        }
        g2.setColor(new Color(250, 145, 16));
        g2.setFont(new Font("Verdana", Font.BOLD, 25));
        g2.drawString("| Score: " + score + " |", titleSize * 17 - 20, 45);

        g2.setColor(new Color(250, 145, 16));
        g2.setFont(new Font("Verdana", Font.BOLD, 25));
        g2.drawString("| LEVEL " + level + " |", titleSize * 9 - 30, 45);


    }

    public void drawFruits(Graphics2D g2, BufferedImage image, int x, int y) {
        Random random = new Random();
        if (timerFruit > 0) {
            g2.drawImage(image, x, y, Fruits.size, Fruits.size, null);
            timerFruit--;
//            System.out.println(timerFruit);
        } else {
            eating[yF / titleSize][xF / titleSize] = 1;
timerFruit = 200;
        }
    }
}
