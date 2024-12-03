package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    static private GamePanel gamePanel;

    public static GamePanel setGamePanel() throws IOException {
        if (gamePanel != null){
            gamePanel = null;// new GamePanel();
        }
        gamePanel = new GamePanel();
        return gamePanel;
    }

    private GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(49, 99, 99, 246));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(null);

        setImages();

        Ghosts b = new Ghosts(this, keyH, tileSize * 8, tileSize * 8, blueUp, blueDown, blueLeft, blueRight);
        Ghosts p = new Ghosts(this, keyH, tileSize * 9, tileSize * 8, pinkUp, pinkDown, pinkLeft, pinkRight);
        Ghosts o = new Ghosts(this, keyH, tileSize * 10, tileSize * 8, orangeUp, orangeDown, orangeLeft, orangeRight);
        Ghosts r = new Ghosts(this, keyH, tileSize * 11, tileSize * 8, redUp, redDown, redLeft, redRight);
        Ghosts g = new Ghosts(this, keyH, tileSize * 12, tileSize * 8, greenUp, greenDown, greenLeft, greenRight);

        ghosts.add(b);
        ghosts.add(p);
        ghosts.add(o);
        ghosts.add(r);
        ghosts.add(g);
    }

    public final int tileSize = 32;
    public final int maxScreenCol = 22;//עמודות
    public final int maxScreenRow = 22;//שורות
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    final int FPS = 60;
    //    static int lives = 1;
    static boolean gameRunning = true;
    boolean drawWall = true;

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
    Pacman pacman = new Pacman(keyH, this);
    Coin coins = new Coin(this, pacman);

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

    public void updateImages() {
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

    public void imageEat() {
        if (!Pacman.canEat) {
            updateImages();
        } else {
            for (int i = 0; i < ghosts.size(); i++) {
                if (ghosts.get(i).isExit) {
                    ghosts.get(i).up = eat;
                    ghosts.get(i).down = eat;
                    ghosts.get(i).left = eat;
                    ghosts.get(i).right = eat;
                }
            }
        }

    }

    public static String fullPath(String p) {
        return "/resource/ghosts/" + p;
    }

    Thread gameThread;

    public void setGameThread() throws IOException {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        System.out.println("BEFORE");
        System.out.println("MAP");
        print2DArray(Board.level1);
        System.out.println("----------------------");
         SoundManager.playStart();
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            if (keyH.spacePressed && !isReturningToMenu) {
                try {
                    playAgain();
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                if (gameRunning) update();
            } catch (InterruptedException | IOException e) {
            }
            if (gameRunning) repaint();
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
//    private boolean spacePressedPreviously = false; // עוקב אחרי המצב הקודם של המקש

    public void update() throws InterruptedException, IOException {
//        if (keyH.spacePressed && !spacePressedPreviously) {
//            // המקש עבר ממצב לא לחוץ ללחוץ
//            playAgain(); // הפעל את הפעולה
//        }
//        // עדכון המצב הקודם של המקש
//        spacePressedPreviously = keyH.spacePressed;

        ghosts.get(1).update();
        pacman.update();
        imageEat();
        if (keyH.spacePressed) playAgain();
        level_2();
        level_3();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        wall.draw(g2);
        pacman.draw(g2);
        coins.draw(g2);
        for (Ghosts ghost : ghosts) {
            ghost.draw(g2);
        }
        drawLives(g2);
        drawStart(g2);
        drawLogo(g2);
        try {
            gameOver(g2);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        drawName(g2);
        drawScore(g2);
        drawLevel(g2);
        g2.dispose();
    }

    public void drawLives(Graphics2D g2){
        int xLeft = 15;
        for (int i = 0; i < Pacman.lives; i++) {
            g2.drawImage(pacman.left1, tileSize * (xLeft - i) - 20, 27, tileSize - 10, tileSize - 10, null);
        }
    }

    public void drawStart(Graphics2D g2){
        g2.drawImage(start, 0, 11 * tileSize, tileSize + 4, tileSize + 4, null);
    }

    public void drawLogo(Graphics2D g2){
        g2.drawImage(pacman.right1, 30, 10, tileSize + 15, tileSize + 15, null);
        g2.drawImage(pacmanTXT, 80, 20, 130, 35, null);
    }

    public void drawName(Graphics2D g2){
        g2.setFont(new Font("Ariel", Font.BOLD, 15)); // הגדרת פונט לגודל 30
        g2.setColor(new Color(250, 145, 16)); // צבע הטקסט
        g2.drawString("player name: "+ MainMenu.name,190,344);
    }

    public void drawScore(Graphics2D g2) {
        g2.setColor(new Color(250, 145, 16));
        g2.setFont(new Font("Verdana", Font.BOLD, 25));
        g2.drawString("| Score: " + Coin.score + "|", tileSize * 16 - 20, 45);
    }

    public void drawLevel(Graphics2D g2) {
        g2.setColor(new Color(250, 145, 16));
        g2.setFont(new Font("Verdana", Font.BOLD, 25));
        g2.drawString("| LEVEL " + Coin.level + " |", tileSize * 7, 45);
    }

    public void level_2() throws IOException {
        if (Coin.score >= 1250 && Coin.level == 1) {
            if (Pacman.lives == 2 || Pacman.lives == 1) Pacman.lives = 3;
//        Coin coin = new Coin(this,pacman);
            Coin.timerFruit = 200;
            Coin.currentTimer = 200;
            SoundManager.playNextLevel();
            Board.level1 = Board.level1;
            Coin.level = 2;
            for (Ghosts ghost : ghosts) {
                ghost.speed = 2;
            }
        }
    }

    public void level_3() throws IOException {
        if (Coin.score >= 2000 && Coin.level == 2) {
            if (Pacman.lives == 2 || Pacman.lives == 1) Pacman.lives = 3;
//        Coin coin = new Coin(this,pacman);
        Coin.timerFruit = 150;
        Coin.currentTimer = 150;
            Board.level1 = Board.level2;
            SoundManager.playNextLevel();
            Coin.level = 3;
            for (Ghosts ghost : ghosts) {
                ghost.speed = 4;
            }
        }
    }

    public void gameOver(Graphics2D g2) throws IOException, InterruptedException {
        if (Pacman.lives == 0) {
            resultsBoard(); // מציג לוח תוצאות

            String gameOverText = "GAME OVER";
            g2.setFont(new Font("Verdana", Font.BOLD, 40));
            FontMetrics metrics = g2.getFontMetrics();
            int textWidth = metrics.stringWidth(gameOverText);
            int textHeight = metrics.getHeight();

            // חישוב מיקום הרקע והטקסט
            int x = tileSize * 7 - 8; // מיקום אופקי
            int y = tileSize * 8;     // מיקום אנכי
            int rectX = x - 10;       // הוספת שוליים למלבן
            int rectY = y - textHeight + 10;
            int rectWidth = textWidth + 20;
            int rectHeight = textHeight;

            // ציור רקע
            g2.setColor(new Color(250, 145, 16)); // צבע רקע עם שקיפות
            g2.fillRect(rectX, rectY, rectWidth, rectHeight);

            // ציור הטקסט
            g2.setColor(Color.WHITE); // צבע הטקסט
            g2.drawString(gameOverText, x, y);

            gameRunning = false; // מפסיק את המשחק

            // יצירת טיימר שמפעיל מחדש את המשחק אחרי 3 שניות
            Timer timer = new Timer(3000, e -> {
                try {
                    playAgain(); // מפעיל את המשחק מחדש
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            timer.setRepeats(false); // מבטיח שהטיימר יפעל פעם אחת בלבד
            timer.start(); // מתחיל את הטיימר
        }
    }


    public void resultsBoard() throws IOException {
        File result = new File("src/resource/result.txt");
        if (! result.exists()) {
            result.createNewFile();
        }
        else System.out.println("is exist");
        String content = "| name player: "+ MainMenu.name + " | score: " + Coin.score + " | level: " + Coin.level+" |";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(result, true))) {
            writer.write(content + "\n");  // הוספת תוכן לקובץ
        } catch (IOException e) {
        }
    }

    private boolean isReturningToMenu = false;

    public void playAgain() throws IOException {
//        if (isReturningToMenu) return;
//        isReturningToMenu = true;
        MainMenu.menuFrame.setContentPane(MainMenu.menuPanel);
        MainMenu.menuFrame.revalidate();
        MainMenu.menuFrame.repaint();
        gameThread = null; // עצור את חוט המשחק
        System.out.println("print");
        gameRunning = true;
        Coin.score = 0;
        Pacman.lives = 3;
        Coin.level = 1;
        Pacman.canEat = false;
        pacman.movePacmanStartPoint();
        ghosts.get(0).moveGhostStartPoint();
        for (Ghosts ghost : ghosts) {
            ghost.isExit = false;
            ghost.isExitAgain = false;
        }
        keyH.upPressed = keyH.downPressed = keyH.leftPressed = keyH.rightPressed = false;

        changeMapTo1();

        System.out.println("AFTER");
        System.out.println("MAP");
        print2DArray(Board.level1);
        System.out.println("----------------------");
    }

    public void changeMapTo1() {
        Board.level1 = Board.enterTo2DNew(Board.level2);
//        for (int i = 0; i < Board.map.length; i++) {
//            for (int j = 0; j < Board.map[i].length; j++) {
//                if (i == 0 && j == 0) continue;
//                if (Board.map[i][j] == 0) Board.map[i][j] = 1;
//                else if (Board.map[i][j] == -6) Board.map[i][j] = -1;
//            }
//        }
    }

    public void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) { // לולאה על כל השורות
            for (int j = 0; j < array[i].length; j++) { // לולאה על כל העמודות בשורה
                System.out.print(array[i][j] + "\t"); // הדפסת הערך עם טאב להפרדה
            }
            System.out.println(); // ירידת שורה לאחר סיום כל שורה
        }
    }

}


