package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BigCoin implements  MyFunctions{
    static int size = 24;
    BufferedImage image;
    public BigCoin() throws IOException {
        setValues();
    }

    @Override
    public void setValues() throws IOException {
        image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/coinsGold.png"));
    }


    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void update() throws InterruptedException {

    }
}
