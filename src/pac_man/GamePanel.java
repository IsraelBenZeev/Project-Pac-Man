package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = 32;
            ;
    public final int maxScreenCol = 22;//עמודות
    public final int maxScreenRow = 22;//שורות
    public final int screenWidth = tileSize * maxScreenCol;//768
    public final int screenHeight = tileSize * maxScreenRow;//576
    final int FPS = 60;
//    BufferedImage blue = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_blue.jpg"));
//    BufferedImage pink = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_pink.jpg"));
//    BufferedImage green = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_green.jpg"));
//    BufferedImage orange = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_orange.jpg"));
//    BufferedImage red = ImageIO.read(getClass().getResourceAsStream("/resource/ghosts/Ghost_red.jpg"));
    int counter =0;
    public final int[][] map = {
           //0///1///2//3////4///5///6///7///8///9//10//11//12//13//14//15//16//17//18//19//20//21
            {5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 6}, //0
            {3, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4}, //1
            {2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 2}, //2
            {2,  1,  5,  9,  6,  1,  9,  9,  9,  1,  9,  1,  9,  9,  9,  1,  5,  9,  1,  2,  1, 2}, //3
            {2,  1,  2,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  1,  1,  2,  1, 2}, //4
            {2,  1,  3,  9,  4,  1,  2,  1,  9,  9, 10,  9,  9,  1,  2,  1,  2,  1,  9,  8,  1, 2}, //5
            {2,  1,  2,  1,  2,  1,  2,  1,  1,  1,  2,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1, 2}, //6
            {2,  1,  1,  1,  1,  1,  3,  9,  9,  1,  2,  1,  9,  9,  4,  1,  5,  9,  6,  1,  1, 2}, //7
            {3,  9,  9,  9,  6,  1,  2,  1,  0,  0,  0,  0,  0,  1,  2,  1,  2,  1,  7,  9,  9, 4}, //8
            {2,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1, 2}, //9
            {7,  9,  9,  1,  2,  1,  9,  9,  9,  9,  9,  9,  9,  9,  9,  1,  2,  1,  1,  9,  9, 8}, //10
            {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1}, //11
            {5,  9,  9,  9,  6,  1,  2,  1,  9,  9,  6,  1,  9,  9,  9,  1,  2,  1,  5,  9,  9, 6}, //12
            {2,  1,  1,  1,  2,  1,  2,  1,  1,  1,  2,  1,  1,  1,  1,  1,  2,  1,  2,  1,  1, 1}, //13
            {3,  9,  9,  9,  8,  1,  7,  9,  9,  1,  2,  1,  5,  9,  9,  9,  8,  1,  7,  9,  9, 6}, //14
            {2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1, 2}, //15
            {2,  1,  9,  9,  6,  1,  9,  9,  9,  1,  1,  1,  2,  1,  9,  9,  9,  9,  9,  9,  1, 2}, //16
            {2,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 2}, //17
            {2,  1,  2,  1,  1,  1,  1,  2,  1,  1,  9, 10,  9,  9,  1,  1,  2,  1,  1,  1,  1, 2}, //18
            {2,  1,  7,  9,  1,  9,  9,  11, 9,  1,  1,  2,  1,  1,  1,  9, 11,  9,  9,  9,  1, 2}, //19
            {2,  1,  1,  1,  1,  1,  1,   1,  1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 2}, //20
            {7,  9,  9,  9,  9,  9,  9,   9,  9, 9,  9,  9,  9,  9,  9,  9,  9,  9,  9,  9,  9, 8}  //21

    };

    //old arr
    //0//1//2//3//4//5//6//7//8//9//10/11/12/13/14/15/16/17/18/19/20/21
//    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
//    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //1
//    {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0}, //2
//    {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0}, //3
//    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0}, //4
//    {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, //5
//    {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0}, //6
//    {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, //7
//    {0, 1, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1}, //8
//    {0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0}, //9
//    {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, //10
//    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //11
//    {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0}, //12
//    {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1}, //13
//    {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0}, //14
//    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //15
//    {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, //16
//    {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //17
//    {0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0}, //18
//    {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0}, //19
//    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //20
//    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //21
//

    KeyHandler keyH = new KeyHandler();

    Pacman pacman = new Pacman(keyH, this);
    Coins coins = new Coins(this, pacman);
    Wall wall = new Wall(this);
    Ghosts ghosts = new Ghosts(this,keyH);


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setBackground(new Color(219, 241, 219, 246));
        this.setBackground(new Color(49, 99, 99, 246));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    Thread gameThread;
    public void setGameThread() throws IOException {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        System.out.println("pacman x: "+pacman.x+", "+"pacman y: "+ pacman.y);
        double drawInterval = 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
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

//        if (ghostsBlue.isDown(ghostsBlue.x,ghostsBlue.y,ghostsBlue.gp.map, ghostsBlue.numOnMap)) ghostsBlue.y += ghostsBlue.speed;
//        System.out.println(ghosts.ghosts[0].x+", "+ghosts.ghosts[0].y);
//        System.out.println(ghosts.ghosts[0]);
//        ghosts.ghosts[0].update();
        ghosts.update();
//        if (ghosts.ghosts[0] != null) ghosts.ghosts[0].update(); // גישה ל-GOTES[0] רק אם אינו null
//        else System.out.println("GOTES[0] אינו מאותחל");
//        ghosts.ghosts[0].update();

    }

    public void paintComponent (Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pacman.draw(g2);
        wall.draw(g2);
        coins.draw(g2);
        ghosts.draw(g2);
//        ghosts.ghosts[0].draw(g2);
//        g2.drawImage(ghostsBlue.image, ghostsBlue.x, ghostsBlue.y, ghostsBlue.titleSize, ghostsBlue.titleSize, null);
        g2.dispose();


    }

}
