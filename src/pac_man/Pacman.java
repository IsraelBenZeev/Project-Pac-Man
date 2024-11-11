package pac_man;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman extends Entity {
    final int size = 48;
//    public GamePanel gp;
    public KeyHandler keyH;

    public Pacman( KeyHandler keyH) {
//        this.gp = gp;
        this.keyH = keyH;
        setImagePacman();
        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void setImagePacman() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/right_2.png"));
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }

    public void update(){
        if (keyH.upPressed){
            direction = "up";
        }
        if (keyH.downPressed) {
            direction = "down";
        }
        if (keyH.leftPressed) {
            direction = "left";
        }
        if (keyH.rightPressed) {
            direction = "right";
        }

    }
    public void draw(Graphics g2){
        BufferedImage image = up1;
        switch (direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
//        if (keyH.upPressed) image = up1;
//        if (keyH.downPressed) image = down1;
//        if (keyH.leftPressed) image = left1;
//        if (keyH.rightPressed) image = right1;
        g2.drawImage(image,x,y,titleSize,titleSize,null);
    }
}

