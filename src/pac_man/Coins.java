package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Coins extends Entity implements MyFunctions {

    int coinSize = titleSize / 4;
    BufferedImage start;
    BufferedImage background;
    int counter = 0;
    Pacman pacman;
    public int[][] eating = {
            //0///1///2//3////4///5///6///7///8///9//10//11//12//13//14//15//16//17//18//19//20//21
            {5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 6}, //0
            {3, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4}, //1
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //2
            {2, 1, 5, 9, 6, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 5, 9, 1, 2, 1, 2}, //3
            {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2}, //4
            {2, 1, 3, 9, 4, 1, 2, 1, 9, 9, 10, 9, 9, 1, 2, 1, 2, 1, 9, 8, 1, 2}, //5
            {2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2}, //6
            {2, 1, 1, 1, 1, 1, 3, 9, 9, 1, 2, 1, 9, 9, 4, 1, 5, 9, 6, 1, 1, 2}, //7
            {3, 9, 9, 9, 6, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 7, 9, 9, 4}, //8
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2}, //9
            {7, 9, 9, 1, 2, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 2, 1, 1, 9, 9, 8}, //10
            {1, 15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //11
            {5, 9, 9, 9, 6, 1, 2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 2, 1, 5, 9, 9, 6}, //12
            {2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1}, //13
            {3, 9, 9, 9, 8, 1, 7, 9, 9, 1, 2, 1, 5, 9, 9, 9, 8, 1, 7, 9, 9, 6}, //14
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //15
            {2, 1, 9, 9, 6, 1, 9, 9, 9, 1, 1, 1, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2}, //16
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //17
            {2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 9, 10, 9, 9, 1, 1, 2, 1, 1, 1, 1, 2}, //18
            {2, 1, 7, 9, 1, 9, 9, 11, 9, 1, 1, 2, 1, 1, 1, 9, 11, 9, 9, 9, 1, 2}, //19
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}, //20
            {7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}  //21

    };

    public void  setEating(){
        if (eating[pacman.yCoins][pacman.xCoins] == 1) {
            counter++;
            eating[pacman.yCoins][pacman.xCoins] = 15;
        }
    }
    public Coins() {
    }

    public Coins(GamePanel gp, Pacman pacman) {
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

    public void update() {
    }



    public void draw(Graphics2D g2) {
        setEating();
        boolean bol = false;
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                if (eating[i][j] == 1) {
                        int x = j * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אופקי
                        int y = i * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אנכי
                    g2.drawImage(image, x, y, coinSize, coinSize, null);
//                    g2.drawImage(background, xCoins+5, yCoins+5, coinSize, coinSize, null);
                }
            }
        }

        g2.setColor(new Color(250,145,16));
        g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
//        g2.setFont(new Font("Verdana", Font.BOLD, 20));
        g2.drawString("Score: " + counter, titleSize*18+10, 45); // מציג את הניקוד בפינה השמאלית העליונה
    }
}
