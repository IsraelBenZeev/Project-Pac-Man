package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = 32;
    public final int maxScreenCol = 22;//עמודות
    public final int maxScreenRow = 22;//שורות
    public final int screenWidth = tileSize * maxScreenCol;//768
    public final int screenHeight = tileSize * maxScreenRow;//576
    final int FPS = 60;
    int counterG = 0;

    int counter = 0;
    public final int[][] map = {
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
    }

    public static String fullPath(String p) {
        return "/resource/ghosts/" + p;
    }
     public void setWall (Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        wall.draw(g2);
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
        Ghosts g = new Ghosts(this, keyH, tileSize * 10, tileSize * 8, blueUp, blueDown, blueLeft, blueRight);
        Ghosts o = new Ghosts(this, keyH, tileSize * 11, tileSize * 8, orangeUp, orangeDown, orangeLeft, orangeRight);
        Ghosts r = new Ghosts(this, keyH, tileSize * 12, tileSize * 8, redUp, redDown, redLeft, redRight);

        ghosts.add(b);
        ghosts.add(p);
        ghosts.add(g);
        ghosts.add(o);
        ghosts.add(r);
    }
    Thread gameThread;

    public void setGameThread() throws IOException {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
//        playAgain();
        System.out.println("pacman x: " + pacman.x + ", " + "pacman y: " + pacman.y);
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null && counterG <= 3) {
            try {
                update();
            } catch (InterruptedException e) {
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

    public void update() throws InterruptedException {
        pacman.update();
//        for (Ghosts ghost : ghosts) {
//            ghost.update();
//        }
        ghosts.get(0).update();
        boolean bol = pacman.ghostsEndPacmanCollision();
        if (bol){
            pacman.x = pacman.titleSize*3;
            pacman.y = pacman.titleSize*11;
            counterG++;
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pacman.draw(g2);
//        wall.draw(g2);
        coins.draw(g2);
        g2.drawImage(start, 0, 11 * tileSize, tileSize + 4, tileSize + 4, null);
        g2.drawImage(pacman.right1, 30, 10, tileSize + 15, tileSize + 15, null);
        g2.drawImage(pacmanTXT, 80, 20, 130, 35, null);
        int xLeft = 16;
        for (int i = 0; i < 3 - counterG; i++) {
            g2.drawImage(pacman.left1, tileSize * (xLeft - i), 30, tileSize - 10, tileSize - 10, null);
        }
        for (Ghosts ghost : ghosts) {
            ghost.draw(g2);
        }
        boolean bol = pacman.ghostsEndPacmanCollision();
        if (bol) counterG++;
        if (counterG == 4) {

            g2.setFont(new Font("Verdana", Font.BOLD, 40));
            g2.drawString("game over", tileSize * 7 - 8, tileSize * 8);
            new javax.swing.Timer(60000, e -> {
                counterG = 0; // איפוס או שינוי מצב המשחק
                repaint(); // עדכון המסך
            }).start();

        }

        g2.dispose();
//        g2.drawString("game over",tileSize*8,tileSize*8);
    }

    public void playAgain (){
        JButton again = new JButton("play again");
        again.setBackground(Color.GREEN);
        again.setBounds(100, 110, 250, 120);
        again.setFont(new Font("Verdana", Font.BOLD, 25));
        again.setBorder(new LineBorder(Color.BLACK, 8, true));  // קו גבול שחור בעובי 2 פיקסלים עם קצוות מעוגלים
        again.setFocusPainted(false);  // ביטול צבע הגבול כאשר הכפתור מקבל פוקוס
        again.setContentAreaFilled(true);


        this.add(again);
        this.revalidate();
        this.repaint();

    }
}


//public boolean ghostsEndPacmanCollision() {
//    boolean b= false;
//    for (Ghosts ghost : ghosts) {
//        int num =28;
//        if ((pacman.x + num == ghost.x && pacman.y == ghost.y) || (pacman.x - num == ghost.x && pacman.y == ghost.y)
//                || (pacman.x  == ghost.x && pacman.y + num == ghost.y) || (pacman.x == ghost.x && pacman.y- num ==  ghost.y)
//                || ( (pacman.x == ghost.x && pacman.y == ghost.y))){
//            pacman.x = pacman.titleSize*3;
//            pacman.y = pacman.titleSize*11;
//            b = true;
//        }
//    }
//    return b;
//}


