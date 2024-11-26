package pac_man;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButtonExample extends JPanel {
    private boolean buttonHovered = false; // מצביע אם העכבר מעל הכפתור
    private boolean buttonClicked = false; // מצביע אם הכפתור נלחץ
    private boolean showButton = false; // דגל שמציין אם להציג את הכפתור

    public CustomButtonExample() {
        // מאזין לאירועי עכבר
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isInsideButton(e.getX(), e.getY())) {
                    buttonClicked = true; // לחיצה
                    repaint();
                    JOptionPane.showMessageDialog(null, "Button clicked!");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isInsideButton(e.getX(), e.getY())) {
                    buttonHovered = true; // עכבר מעל הכפתור
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonHovered = false; // עכבר עזב את הכפתור
                repaint();
            }
        });

        // מאזין לגרירת עכבר
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                buttonHovered = isInsideButton(e.getX(), e.getY());
                repaint();
            }
        });
    }

    // פונקציה שמציגה את הכפתור
    public void showPlayAgainButton() {
        showButton = true; // הפעלת הצגת הכפתור
        repaint(); // לצייר מחדש את הפאנל
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // הגדרת איכות ציור
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // מיקום וגדלי הכפתור
        int buttonX = getWidth() / 2 - 75;
        int buttonY = getHeight() / 2 - 25;
        int buttonWidth = 150;
        int buttonHeight = 50;

        // אם הדגל showButton דולק, לצייר את הכפתור
        if (showButton) {
            // צבע הכפתור
            if (buttonHovered) {
                g2d.setColor(Color.ORANGE); // שינוי צבע אם העכבר מעל הכפתור
            } else {
                g2d.setColor(Color.GREEN);
            }
            g2d.fillRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 20, 20);

            // מסגרת הכפתור
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 20, 20);

            // טקסט בכפתור
            g2d.setFont(new Font("Verdana", Font.BOLD, 18));
            FontMetrics fm = g2d.getFontMetrics();
            String buttonText = "Play Again";
            int textX = buttonX + (buttonWidth - fm.stringWidth(buttonText)) / 2;
            int textY = buttonY + ((buttonHeight - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(Color.BLACK);
            g2d.drawString(buttonText, textX, textY);
        }
    }

    // פונקציה לבדיקת מיקום העכבר
    private boolean isInsideButton(int x, int y) {
        int buttonX = getWidth() / 2 - 75;
        int buttonY = getHeight() / 2 - 25;
        int buttonWidth = 150;
        int buttonHeight = 50;
        return x >= buttonX && x <= buttonX + buttonWidth && y >= buttonY && y <= buttonY + buttonHeight;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Button with Graphics2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        CustomButtonExample panel = new CustomButtonExample();
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // למשל, להפעיל את הכפתור אחרי 3 שניות
        new Timer(3000, e -> panel.showPlayAgainButton()).start();
    }
}
