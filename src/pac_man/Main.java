package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void openGame(JFrame menu) throws IOException {

        GamePanel gamePanel = new GamePanel();
        menu.setContentPane(gamePanel);
        menu.revalidate();
        menu.repaint();

        gamePanel.requestFocusInWindow();
        gamePanel.setFocusable(true);
        gamePanel.grabFocus();
        menu.setSize(menu.getWidth() + 16, menu.getHeight() + 39);


        gamePanel.setGameThread();

    }
    public static void menu() throws IOException {
            int titleSize = 32;
            BufferedImage imageIcon = ImageIO.read(Main.class.getResourceAsStream("/resource/pacman/right_1.png"));

            JFrame menu = new JFrame();
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setResizable(false);
            menu.setTitle("Pac - Man");
            menu.setLayout(null);
            menu.setSize(titleSize * 22, titleSize * 22);
            menu.setBackground(new Color(222, 224, 144));
            menu.setIconImage(imageIcon);

            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(null);
            menuPanel.setBounds(0, 0, titleSize * 22, titleSize * 22);
            menuPanel.setBackground(new Color(49, 99, 99, 246));
            menu.add(menuPanel);

            JButton start = new JButton("start game");
            start.setBackground(Color.GREEN);
            start.setBounds(227, 100, 250, 120);
            start.setFont(new Font("Verdana", Font.BOLD, 25));
            start.setBorder(new LineBorder(Color.BLACK, 8, true));  // קו גבול שחור בעובי 2 פיקסלים עם קצוות מעוגלים
            start.setFocusPainted(false);  // ביטול צבע הגבול כאשר הכפתור מקבל פוקוס
            start.setContentAreaFilled(false);
            menuPanel.add(start);

            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
//                        menu.dispose();
//                        openGame();
                        openGame(menu);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JButton exit = new JButton("exit");
            exit.setBounds(227, 300, 250, 120);
            exit.setFont(new Font("Verdana", Font.BOLD, 25));
            exit.setBorder(new LineBorder(Color.BLACK, 8, true));  // קו גבול שחור בעובי 2 פיקסלים עם קצוות מעוגלים
            exit.setFocusPainted(false);  // ביטול צבע הגבול כאשר הכפתור מקבל פוקוס
            exit.setContentAreaFilled(false);
            menuPanel.add(exit);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.dispose(); // סגירת חלון התפריט
                }
            });


            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        }

        public static void main(String[] args) throws IOException {
            menu();

//        System.out.println(gamePanel.coins.counter);
        }
}

