package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Coins extends Entity implements MyFunctions {

    int coinSize = titleSize / 4;
    BufferedImage start;
    BufferedImage background;
    static int score = -1;
    static int level = 1;
    Pacman pacman;
    BigCoin bigCoin = new BigCoin();
    Fruits fruits = new Fruits();

    public int[][] eating = {
            //0///1///2//3////4///5///6///7///8///9//10//11//12//13//14//15//16//17//18//19//20//21
            {5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 6}, //0
            {3, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4}, //1
            {2, 1, 1, 1, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 2}, //2
            {2, 1, 5, 9, 6, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 5, 9, 1, 2, 1, 2}, //3
            {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2}, //4
            {2, 1, 3, 9, 4, 1, 2, 1, 9, 9, 10, 9, 9, 1, 2, 1, 2, 1, 9, 8, 1, 2}, //5
            {2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 17, 1, 1, 1, 1, 2}, //6
            {2, 1, 14, 1, 1, 1, 3, 9, 9, 1, 2, 1, 9, 9, 4, 1, 5, 9, 6, 1, 1, 2}, //7
            {3, 9, 9, 9, 6, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 7, 9, 9, 4}, //8
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2}, //9
            {7, 9, 9, 1, 2, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 2, 1, 1, 9, 9, 8}, //10
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 1, 1, 1, 1, 1, 18, 1}, //11
            {5, 9, 9, 9, 6, 1, 2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 2, 1, 5, 9, 9, 6}, //12
            {2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1}, //13
            {3, 9, 9, 9, 8, 1, 7, 9, 9, 1, 2, 1, 5, 9, 9, 9, 8, 1, 7, 9, 9, 6}, //14
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 14, 1, 1, 1, 1, 1, 1, 2}, //15
            {2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 1, 1, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2}, //16
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 2}, //17
            {2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 9, 10, 9, 9, 1, 1, 2, 1, 1, 1, 1, 2}, //18
            {2, 1, 7, 9, 1, 9, 9, 11, 9, 1, 1, 2, 1, 1, 1, 9, 11, 9, 9, 9, 1, 2}, //19
            {2, 1, 1, 1, 1, 1, 1, 15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //20
            {7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}  //21
    };

    public int[][] eating2 = {
            //0///1///2//3////4///5///6///7///8///9//10//11//12//13//14//15//16//17//18//19//20//21
            {5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 6}, //0
            {3, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4}, //1
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 2}, //2
            {2, 1, 5, 9, 6, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 5, 9, 1, 2, 1, 2}, //3
            {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2}, //4
            {2, 1, 3, 9, 4, 1, 2, 1, 9, 9, 10, 9, 9, 1, 2, 1, 2, 1, 9, 8, 1, 2}, //5
            {2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2}, //6
            {2, 1, 14, 1, 1, 1, 3, 9, 9, 1, 2, 1, 9, 9, 4, 1, 5, 9, 6, 1, 1, 2}, //7
            {3, 9, 9, 9, 6, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 7, 9, 9, 4}, //8
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2}, //9
            {7, 9, 9, 1, 2, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 2, 1, 1, 9, 9, 8}, //10
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //11
            {5, 9, 9, 9, 6, 1, 2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 2, 1, 5, 9, 9, 6}, //12
            {2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1}, //13
            {3, 9, 9, 9, 8, 1, 7, 9, 9, 1, 2, 1, 5, 9, 9, 9, 8, 1, 7, 9, 9, 6}, //14
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 14, 1, 1, 1, 1, 1, 1, 2}, //15
            {2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 1, 1, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2}, //16
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 14, 1, 2}, //17
            {2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 9, 10, 9, 9, 1, 1, 2, 1, 1, 1, 1, 2}, //18
            {2, 1, 7, 9, 1, 9, 9, 11, 9, 1, 1, 2, 1, 1, 1, 9, 11, 9, 9, 9, 1, 2}, //19
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //20
            {7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}  //21
    };



    public void setEating() {
        if ((eating[pacman.yCoins][pacman.xCoins] == 1) || (eating[pacman.yCoins][pacman.xCoins] >= 14) &&
                (eating[pacman.yCoins][pacman.xCoins] <= 18)) {
            if (eating[pacman.yCoins][pacman.xCoins] == 1) score++;
            else if ((eating[pacman.yCoins][pacman.xCoins] >= 14) &&
            (eating[pacman.yCoins][pacman.xCoins] <= 18)) {
                score += 20;
                pacman.canEat = true;
                pacman.timer = 300;
            }
            eating[pacman.yCoins][pacman.xCoins] = 19;
        }
    }

    public Coins() throws IOException {
    }

    public Coins(GamePanel gp, Pacman pacman) throws IOException {
        this.gp = gp;
        this.pacman = pacman;
        setValues();
    }

    @Override
    public void setValues() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/coinsGold.png"));
            start = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/start.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/רקע.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void nextLevel() {
        eating = eating2;
        level = 2;
        for (Ghosts ghost : gp.ghosts) {
            ghost.speed = 4;
        }
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
        if (score >= 100) {
            nextLevel();
        }
        setEating();
        boolean bol = false;
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                switch (eating[i][j]) {
                    case 1:
                        int x = j * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אופקי
                        int y = i * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אנכי
                        g2.drawImage(image, x, y, coinSize, coinSize, null);
                        break;
                    case 14:
                        x = j * titleSize  + (titleSize - BigCoin.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - BigCoin.size) / 2;  // מרכוז אנכי
                        g2.drawImage(bigCoin.image, x, y, BigCoin.size, BigCoin.size, null);
                        break;
                    case 15:
                         x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                         y = i * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אנכי
                        g2.drawImage(fruits.apple, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case 16:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;// מרכוז אנכי
                        g2.drawImage(fruits.chery, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case 17:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;// מרכוז אנכי
                        g2.drawImage(fruits.orange, x, y, Fruits.size, Fruits.size, null);
                        break;
                    case 18:
                        x = j * titleSize + (titleSize - Fruits.size) / 2;  // מרכוז אופקי
                        y = i * titleSize + (titleSize - Fruits.size) / 2;// מרכוז אנכי
                        g2.drawImage(fruits.strawberry, x, y, Fruits.size, Fruits.size, null);
                        break;
                }
            }
        }
        g2.setColor(new Color(250, 145, 16));
        g2.setFont(new Font("Verdana", Font.BOLD, 25));
        g2.drawString("| Score: " + score+" |", titleSize * 17-20, 45);

        g2.setColor(new Color(250, 145, 16));
        g2.setFont(new Font("Verdana", Font.BOLD, 25));
        g2.drawString("| LEVEL " + level + " |", titleSize * 9-30, 45);


    }
}
