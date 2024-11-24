package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = 32;
    public final int maxScreenCol = 22;//עמודות
    public final int maxScreenRow = 22;//שורות
    public final int screenWidth = tileSize * maxScreenCol;//768
    public final int screenHeight = tileSize * maxScreenRow;//576
    final int FPS = 60;
    static int lives = 3;

    public int[][] map = {
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
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //11
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

    KeyHandler keyH = new KeyHandler();
    Wall wall = new Wall(this);

    ArrayList<Ghosts> ghosts = new ArrayList<>();
    BufferedImage blueUp, blueDown, blueLeft, blueRight;
    BufferedImage pinkUp, pinkDown, pinkLeft, pinkRight;
    BufferedImage orangeUp, orangeDown, orangeLeft, orangeRight;
    BufferedImage redUp, redDown, redLeft, redRight;
    BufferedImage greenUp, greenDown, greenLeft, greenRight;
    BufferedImage eat = ImageIO.read(getClass().getResourceAsStream(fullPath("eat.png")));
    BufferedImage start, pacmanTXT;
    Pacman pacman = new Pacman(keyH, this, ghosts);
    Coins coins = new Coins(this, pacman);

    public void setImages() throws IOException {
        start = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/start.png"));
        pacmanTXT = ImageIO.read(getClass().getResourceAsStream("/resource/pacman/Pac-Man.png"));


        blueUp = ImageIO.read(getClass().getResourceAsStream(fullPath("blue_up.png")));
        blueDown = ImageIO.read(getClass().getResourceAsStream(fullPath("blue_down.png")));
        blueLeft = ImageIO.read(getClass().getResourceAsStream(fullPath("blue_left.png")));
        blueRight = ImageIO.read(getClass().getResourceAsStream(fullPath("blue_right.png")));

        pinkUp = ImageIO.read(getClass().getResourceAsStream(fullPath("pink_up.png")));
        pinkDown = ImageIO.read(getClass().getResourceAsStream(fullPath("pink_down.png")));
        pinkLeft = ImageIO.read(getClass().getResourceAsStream(fullPath("pink_left.png")));
        pinkRight = ImageIO.read(getClass().getResourceAsStream(fullPath("pink_right.png")));

        orangeUp = ImageIO.read(getClass().getResourceAsStream(fullPath("orange_up.png")));
        orangeDown = ImageIO.read(getClass().getResourceAsStream(fullPath("orange_down.png")));
        orangeLeft = ImageIO.read(getClass().getResourceAsStream(fullPath("orange_left.png")));
        orangeRight = ImageIO.read(getClass().getResourceAsStream(fullPath("orange_right.png")));

        redUp = ImageIO.read(getClass().getResourceAsStream(fullPath("red_up.png")));
        redDown = ImageIO.read(getClass().getResourceAsStream(fullPath("red_down.png")));
        redLeft = ImageIO.read(getClass().getResourceAsStream(fullPath("red_left.png")));
        redRight = ImageIO.read(getClass().getResourceAsStream(fullPath("red_right.png")));

        greenUp = ImageIO.read(getClass().getResourceAsStream(fullPath("green_up.png")));
        greenDown = ImageIO.read(getClass().getResourceAsStream(fullPath("green_down.png")));
        greenLeft = ImageIO.read(getClass().getResourceAsStream(fullPath("green_left.png")));
        greenRight = ImageIO.read(getClass().getResourceAsStream(fullPath("green_right.png")));
    }
    public static String fullPath(String p) {
        return "/resource/ghosts/" + p;
    }

    public GamePanel() throws IOException {
//        int speed = 4;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(49, 99, 99, 246));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(null);

        setImages();

        Ghosts b = new Ghosts(this, keyH, tileSize * 8, tileSize * 8, blueUp, blueDown, blueLeft, blueRight);
        Ghosts p = new Ghosts(this, keyH, tileSize * 9, tileSize * 8, pinkUp, pinkDown, pinkLeft, pinkRight);
        Ghosts o = new Ghosts(this, keyH, tileSize * 11, tileSize * 8, orangeUp, orangeDown, orangeLeft, orangeRight);
        Ghosts r = new Ghosts(this, keyH, tileSize * 12, tileSize * 8, redUp, redDown, redLeft, redRight);
        Ghosts g = new Ghosts(this, keyH, tileSize * 10, tileSize * 8, greenUp, greenDown, greenLeft, greenRight);

        ghosts.add(b);
        ghosts.add(p);
        ghosts.add(o);
        ghosts.add(r);
        ghosts.add(g);
    }

    Thread gameThread;

    public void setGameThread() throws IOException {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        SoundManager.playStart();
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null && lives > 0) {

            try {
                update();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
//                Thread.sleep(500);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void update() throws InterruptedException, IOException {
        pacman.update();
        if (!Pacman.canEat) {
            updateImages();
        } else {
            for (int i = 0; i < ghosts.size(); i++) {
                ghosts.get(i).up = eat;
                ghosts.get(i).down = eat;
                ghosts.get(i).left = eat;
                ghosts.get(i).right = eat;
            }
        }
        ghosts.get(0).update();
//        if (lives == 0) playAgain();
    }

    public void updateImages(){
        ghosts.get(0).up = blueUp;
        ghosts.get(0).down = blueDown;
        ghosts.get(0).left = blueLeft;
        ghosts.get(0).right = blueRight;

        ghosts.get(1).up = pinkUp;
        ghosts.get(1).down = pinkDown;
        ghosts.get(1).left = pinkLeft;
        ghosts.get(1).right = pinkRight;

        ghosts.get(2).up = orangeUp;
        ghosts.get(2).down = orangeDown;
        ghosts.get(2).left = orangeLeft;
        ghosts.get(2).right = orangeRight;

        ghosts.get(3).up = redUp;
        ghosts.get(3).down = redDown;
        ghosts.get(3).left = redLeft;
        ghosts.get(3).right = redRight;

        ghosts.get(4).up = greenUp;
        ghosts.get(4).down = greenDown;
        ghosts.get(4).left = greenLeft;
        ghosts.get(4).right = greenRight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pacman.draw(g2);
        wall.draw(g2);
        coins.draw(g2);
        g2.drawImage(start, 0, 11 * tileSize, tileSize + 4, tileSize + 4, null);
        g2.drawImage(pacman.right1, 30, 10, tileSize + 15, tileSize + 15, null);
        g2.drawImage(pacmanTXT, 80, 20, 130, 35, null);
        int xLeft = 16;
        for (int i = 0; i < lives; i++) {
            g2.drawImage(pacman.left1, tileSize * (xLeft - i)-20, 27, tileSize - 10, tileSize - 10, null);
        }
        for (Ghosts ghost : ghosts) {
            ghost.draw(g2);
        }
        if (lives == 0) {
            SoundManager.playDied();
            g2.setFont(new Font("Verdana", Font.BOLD, 40));
            g2.drawString("game over", tileSize * 7 - 8, tileSize * 8);
        }
        g2.dispose();
    }

//    public void playAgain() {
//        // יצירת הכפתור
//        JButton again = new JButton("Play Again");
//
//        // קביעת הגודל של הכפתור - לדוגמה 150x60
//        again.setPreferredSize(new Dimension(150, 60));
//
//        // שינוי צבע הרקע של הכפתור
//        again.setBackground(Color.GREEN);
//
//        // קביעת הגדרת הגופנים של הכפתור
//        again.setFont(new Font("Verdana", Font.BOLD, 20));
//
//        // קביעת גבול שחור עם קצוות מעוגלים
//        again.setBorder(new LineBorder(Color.BLACK, 8, true));
//
//        // ביטול הצגת גבול כשהכפתור מקבל פוקוס
//        again.setFocusPainted(false);
//
//        // שימו לב שהכפתור לא יכסה את כל הפאנל
//        again.setContentAreaFilled(true);
//
//        // חישוב המיקום של הכפתור במרכז הפאנל
//        int x = 0;// (this.getWidth() - again.getPreferredSize().width) / 2;
//        int y =0;// (this.getHeight() - again.getPreferredSize().height) / 2;
//
//        // הצבת הכפתור במיקום החישוב
//        again.setBounds(x, y, again.getPreferredSize().width, again.getPreferredSize().height);
//
//        // הוספת הכפתור לפאנל
//        this.add(again);
//
//        // עדכון הפאנל כדי שיתעדכן ויתחיל להציג את הכפתור
//        this.revalidate();
//        this.repaint();
//        again.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                lives = 3;
////                Coins.score = 0;
////                Coins.level = 1;
////                run();
//            }
//        });
//    }

}


