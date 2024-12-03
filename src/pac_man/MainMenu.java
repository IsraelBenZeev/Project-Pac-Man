package pac_man;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class MainMenu {
    static String name = "Player 1";
    int titleSize = 32;
    static JPanel menuPanel;
    static JFrame menuFrame;
    public static void openGame(JFrame menu) throws IOException {
        GamePanel gamePanel = new GamePanel();
        menu.setContentPane(gamePanel);

        menu.revalidate();
        menu.repaint();

        gamePanel.requestFocusInWindow();
        gamePanel.setFocusable(true);
        gamePanel.grabFocus();

        gamePanel.setGameThread();
    }

    public static void menu() throws IOException {
        int titleSize = 32;
        BufferedImage imageIcon = ImageIO.read(MainMenu.class.getResourceAsStream("/resource/pacman/right_1.png"));

        menuFrame = new JFrame();
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(false);
        menuFrame.setTitle("Pac - Man");
        menuFrame.setLayout(null);

        menuFrame.setSize(titleSize * 22+16, titleSize * 22+39);
        menuFrame.setBackground(new Color(222, 224, 144));
        menuFrame.setIconImage(imageIcon);
        menuFrame.repaint();

        BufferedImage pacmanTXT = ImageIO.read(MainMenu.class.getResourceAsStream("/resource/pacman/leftEndTxt.png"));

         menuPanel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (pacmanTXT != null) {
                        g.drawImage(pacmanTXT, 50, 490, 600, 160, this); // מציירים את התמונה
                }
            }
        };

            menuPanel.setLayout(null);
            menuPanel.setBounds(0, 0, titleSize * 22, titleSize * 22);
            menuPanel.setBackground(new Color(62, 44, 218, 221));
            menuFrame.add(menuPanel);

            JPanel namePanel = new JPanel();
            namePanel.setBounds(210,20,450,150);
            namePanel.setOpaque(false);
            menuPanel.add(namePanel);


            JLabel enterName = new JLabel("your name:");
            enterName.setBounds(40, 10, 180, 50);
            enterName.setFont(new Font("Verdana", Font.BOLD, 25));
            enterName.setForeground(new Color(250,145,16));
            namePanel.setLayout(null);
            namePanel.add(enterName);

            JTextField nameField = new JTextField();
            nameField.setBounds(10, 56, 230, 50);
            nameField.setBackground(new Color(236, 221, 172));
            nameField.setBorder(null);
            nameField.setForeground(new Color(250, 145, 16));
            nameField.setFont(new Font("Ariel",Font.BOLD,30));

            namePanel.add(nameField);

        // מאזין לשינויים בשדה טקסט
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateName();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateName();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateName();
            }
            private void updateName() {
                name = nameField.getText();
            }
        });

        menuPanel.setVisible(true);

            JButton start = new JButton("start game");
            start.setBackground(new Color(236, 221, 172));
            start.setBounds(250, 150, 180, 70);
            start.setFont(new Font("Verdana", Font.BOLD, 25));
            start.setForeground(new Color(250,145,16));
            start.setBorder(new LineBorder(new Color(250,145,16), 8, true));
            start.setFocusPainted(false);  // ביטול צבע הגבול כאשר הכפתור מקבל פוקוס
            start.setContentAreaFilled(true);
            menuPanel.add(start);

            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        openGame(menuFrame);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JButton exit = new JButton("exit");
            exit.setBackground(new Color(236, 221, 172));
            exit.setBounds(250, 450, 180, 70);
            exit.setFont(new Font("Verdana", Font.BOLD, 25));
            exit.setBorder(new LineBorder(new Color(250,145,16), 8, true));
            exit.setFocusPainted(false);  // ביטול צבע הגבול כאשר הכפתור מקבל פוקוס
            exit.setForeground(new Color(250,145,16));
            exit.setContentAreaFilled(true);
            menuPanel.add(exit);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuFrame.dispose(); // סגירת חלון התפריט
                }
            });

        JButton boardResult = new JButton("Board Result");
        boardResult.setBackground(new Color(236, 221, 172));
        boardResult.setBounds(250, 250, 180, 70);
        boardResult.setFont(new Font("Verdana", Font.BOLD, 20));
        boardResult.setForeground(new Color(250,145,16));
        boardResult.setBorder(new LineBorder(new Color(250,145,16), 8, true));
        boardResult.setFocusPainted(false);  // ביטול צבע הגבול כאשר הכפתור מקבל פוקוס
        boardResult.setContentAreaFilled(true);
        menuPanel.add(boardResult);

        boardResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openResult(menuFrame);
            }
        });
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);

        JButton instructions = new JButton("Instructions");
        instructions.setBackground(new Color(236, 221, 172));
        instructions.setBounds(250, 350, 180, 70);
        instructions.setFont(new Font("Verdana", Font.BOLD, 20));
        instructions.setForeground(new Color(250, 145, 16));
        instructions.setBorder(new LineBorder(new Color(250, 145, 16), 8, true));
        instructions.setFocusPainted(false);
        instructions.setContentAreaFilled(true);
        menuPanel.add(instructions);

        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // יצירת פאנל חדש עבור ההוראות
                JPanel instructionsPanel = new JPanel();
                instructionsPanel.setLayout(null);
                instructionsPanel.setBackground(new Color(62, 44, 218, 221));

                JLabel instructionsText = new JLabel("<html><div style='text-align: center;'>"
                        + "<h1 style='color: orange;'>Game Instructions</h1>"
                        + "<p style='color: white; font-size: 16px;'>"
                        + "1. Use arrow keys to move Pac-Man.<br>"
                        + "2. Collect coins to earn points.<br>"
                        + "3. Avoid ghosts to stay alive.<br>"
                        + "4. Eat big coins to become invincible temporarily.<br>"
                        + "5. Collect fruits for bonus points.</p>"
                        + "</div></html>");
                instructionsText.setBounds(50, 50, 600, 300); // מיקום וגודל הטקסט
                instructionsPanel.add(instructionsText);

                // יצירת כפתור חזרה לתפריט הראשי
                JButton backToMenu = new JButton("BACK TO MENU");
                backToMenu.setBackground(new Color(236, 221, 172));
                backToMenu.setBounds(250, 450, 180, 70);
                backToMenu.setFont(new Font("Verdana", Font.BOLD, 20));
                backToMenu.setForeground(new Color(250, 145, 16));
                backToMenu.setBorder(new LineBorder(new Color(250, 145, 16), 8, true));
                backToMenu.setFocusPainted(false);
                backToMenu.setContentAreaFilled(true);
                instructionsPanel.add(backToMenu);

                backToMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuFrame.setContentPane(menuPanel); // חזרה לפאנל התפריט הראשי
                        menuFrame.revalidate(); // עדכון הממשק
                        menuFrame.repaint();
                    }
                });

                // החלפת תוכן ה-JFrame לפאנל ההוראות
                menuFrame.setContentPane(instructionsPanel);
                menuFrame.revalidate(); // עדכון הממשק
                menuFrame.repaint();
            }
        });
    }





    public static void openResult(JFrame frame) {
        // יצירת פאנל ראשי לתוצאות
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(87, 129, 236));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // ארגון אנכי של הכרטיסים

        // עטיפת הפאנל ב-JScrollPane
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // קביעת מהירות הגלילה
        scrollPane.setBounds(0, 0, 704, 704);

        frame.setContentPane(scrollPane); // הוספת JScrollPane כקונטיינר הראשי

        Queue<String> results = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resource/result.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                results.offer(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        // כותרת ראשית
        JLabel title = new JLabel("Board Result:");
        title.setFont(new Font("Verdana", Font.BOLD, 40));
        title.setForeground(new Color(250, 145, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(title);

        for (String item : results) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBackground(new Color(255, 255, 204));
            card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true));

            JLabel textLabel = new JLabel(item, SwingConstants.CENTER);
            textLabel.setFont(new Font("Arial", Font.BOLD, 16));
            textLabel.setForeground(new Color(250, 145, 16));
            card.add(textLabel, BorderLayout.CENTER);

            // גודל הכרטיס
            card.setPreferredSize(new Dimension(600, 60));
            card.setMaximumSize(new Dimension(600, 60));

            resultPanel.add(card);

            // אנימציה של כניסה עם שקיפות
            Timer animationTimer = new Timer(15, new ActionListener() {
                int xOffset = -650;
                int targetWidth = 600; // רוחב הכרטיס
                int targetHeight = 60; // גובה הכרטיס
                float alpha = 0.0f;

                @Override
                public void actionPerformed(ActionEvent e) {
                    xOffset += 20;
                    alpha = Math.min(1.0f, alpha + 0.05f); // שקיפות

                    card.setBounds(Math.min(xOffset, 0), card.getY(), targetWidth, targetHeight);
                    card.setOpaque(true);
                    card.setBackground(new Color(255, 255, 204, (int) (alpha * 255)));

                    resultPanel.revalidate();

                    if (xOffset >= 0 && alpha >= 1.0f) {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            animationTimer.setInitialDelay(200);
            animationTimer.start();
        }

        // כפתור חזרה
        JButton back = new JButton("BACK");
        back.setFont(new Font("Verdana", Font.BOLD, 40));
        back.setForeground(new Color(250, 145, 16));
        back.setBackground(new Color(236, 221, 172));
        back.setBackground(Color.ORANGE);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 20))); // מרווח לפני הכפתור
        back.setFocusPainted(false);
        back.setContentAreaFilled(true);
        resultPanel.add(back);

        back.addActionListener(e -> {
            frame.setContentPane(menuPanel);
            frame.revalidate();
            frame.repaint();
        });

        frame.revalidate();
        frame.repaint();
    }
}

