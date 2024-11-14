package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Coins extends Entity implements MyFunctions{

    int coinSize= titleSize/2;

    public Coins(GamePanel gp) {
        this.gp = gp;
        setValues();
    }

    @Override
    public void setValues(){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/coin.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(){
    }
    public void draw (Graphics2D g2){
        for (int i = 0; i < gp.map.length; i++) {
            for (int j = 0; j < gp.map[i].length; j++) {
                if (gp.map[i][j] == 1){
                    int x = j * titleSize + 8;//+ (size - coinSize) / 2;  // מרכוז אופקי
                    int y = i * titleSize + 8;//+ (size - coinSize) / 2;  // מרכוז אנכי
                    g2.drawImage(image,x,y,coinSize,coinSize,null);
                }
            }
        }
    }
}
