package test;

import pac_man.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int x1 = 0;
        int y1 = 0;

        int x2 = 100;
        int y2 = 100;
        int speed = 4;
        JFrame window = new JFrame();
        window.setLayout(null);
        window.setResizable(false);
        window.setSize(500,500);
        window.setTitle("TEST");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        JPanel jPanel1 = new JPanel();
        jPanel1.setBounds(x1,y1,80,50);
        jPanel1.setBackground(Color.BLACK);
        JPanel jPanel2 = new JPanel();
        jPanel2.setBounds(x2,y2,80,50);
        jPanel2.setBackground(Color.BLUE);
        window.add(jPanel1);
        window.add(jPanel2);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int x = x1;
                int y = y1;
                while (true){
                    jPanel1.setBounds(x+speed,y+speed,80,50);
                }
            }
        });
    }
}
