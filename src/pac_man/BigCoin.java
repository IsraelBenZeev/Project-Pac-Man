package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BigCoin extends Entity implements  MyFunctions{
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
