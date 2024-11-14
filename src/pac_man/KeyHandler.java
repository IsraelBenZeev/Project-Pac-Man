package pac_man;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                upPressed = true;

                downPressed = leftPressed = rightPressed = false;
//                System.out.println("up");
                break;
//            case KeyEvent.VK_UP + KeyEvent.VK_RIGHT:
//                upPressed = rightPressed = true;
//                downPressed = leftPressed = false;
//                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                upPressed = leftPressed = rightPressed = false;
//                System.out.println("down");
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                upPressed = downPressed = rightPressed = false;
//                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                upPressed = downPressed = leftPressed = false;
//                System.out.println("right");
                break;
            case KeyEvent.VK_ENTER:
                upPressed = downPressed = leftPressed = rightPressed = false;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//                int code = e.getKeyCode();
//        if (downPressed || leftPressed || rightPressed){
//            upPressed = false;
//        }
//        if (upPressed || downPressed || leftPressed){
//            rightPressed = false;
//        }
//        if (upPressed || downPressed || rightPressed){
//            leftPressed = false;
//        }
//        if (upPressed || leftPressed || rightPressed){
//            downPressed = false;
//        }
//        switch (code){
//            case KeyEvent.VK_UP:
//                upPressed = false;
//                break;
//            case KeyEvent.VK_DOWN:
//                downPressed = false;
//                break;
//            case KeyEvent.VK_LEFT:
//                leftPressed = false;
//                break;
//            case KeyEvent.VK_RIGHT:
//                rightPressed = false;
//                break;
//        }
//
    }
}
