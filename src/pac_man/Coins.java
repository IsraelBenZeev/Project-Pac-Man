package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Coins extends Entity implements MyFunctions {

    int coinSize = titleSize / 4;
    BufferedImage start;
    BufferedImage background;
    int counter = 0;
    public int[][] eating = {
            //0//1//2//3//4//5//6//7//8//9//10/11/12/13/14/15/16/17/18/19/20/21
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //1
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0}, //2
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0}, //3
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //4
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0}, //5
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0}, //6
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, //7
            {0, 1, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1}, //8
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0}, //9
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, //10
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //11
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0}, //12
            {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1}, //13
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0}, //14
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //15
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, //16
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //17
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0}, //18
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0}, //19
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //20
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //21
    };


    public void  setEating(){
        xCoins = pacman.xCoins;
        yCoins = pacman.yCoins;
        eating[yCoins][xCoins] = 6;
        counter++;
    }
    Pacman pacman;

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

    public void draw(Graphics2D g2) throws InterruptedException {
        setEating();
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                if (eating[i][j] == 1) {  //&&eating[yCoins][xCoins] != 1
                    if (i == 11 && j == 1 && gp.map[i][j] == 1) continue;
                        int x = j * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אופקי
                        int y = i * titleSize + 12;//+ (size - coinSize) / 2;  // מרכוז אנכי
                    g2.drawImage(image, x, y, coinSize, coinSize, null);



                    g2.drawImage(background, xCoins, yCoins, coinSize, coinSize, null);

                }
            }
        }
        g2.drawImage(start, 0, 11 * 32 + 3, titleSize, titleSize, null);
    }
}
