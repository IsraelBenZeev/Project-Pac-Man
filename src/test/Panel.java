package test;

import javax.swing.*;

public class Panel {
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();

    public Panel() {
        setValues();
    }
    Thread panel1 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){

            }
        }
    });
    Thread panel2;
    int speed = 4;



    public void setValues (){
        this.jPanel1.contains(100,100);
        this.jPanel2.contains(100,100);
    }
}
