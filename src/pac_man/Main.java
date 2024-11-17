package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pac - Man");
        BufferedImage imageIcon = ImageIO.read(Main.class.getResourceAsStream("/resource/pacman/right_1.png"));
        window.setIconImage(imageIcon);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);  // ממרכז את החלון במסך
        window.setVisible(true);


        gamePanel.setGameThread();
//        System.out.println(gamePanel.coins.counter);
    }
}

