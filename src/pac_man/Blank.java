//package pac_man;
//
//public class Blank {
//    // משתנה לשמירת הכיוון הבא
//    String nextDirection = null;
//
//    // פונקציה לבדיקת מעבר בין כיוונים
//    public void updateDirection(String desiredDirection) {
//        if (desiredDirection.equals("right") && y % titleSize == 0
//                && pastAble("right", y, x, gp.map, numOnMap)) {
//            direction = "right";
//            x += speed;
//        } else if (desiredDirection.equals("left") && y % titleSize == 0
//                && pastAble("left", y, x, gp.map, numOnMap)) {
//            direction = "left";
//            x -= speed;
//        } else if (desiredDirection.equals("up") && x % titleSize == 0
//                && pastAble("up", y, x, gp.map, numOnMap)) {
//            direction = "up";
//            y -= speed;
//        } else if (desiredDirection.equals("down") && x % titleSize == 0
//                && pastAble("down", y, x, gp.map, numOnMap)) {
//            direction = "down";
//            y += speed;
//        }
//    }
//
//// קוד עדכון כיוון
//if (keyH.upPressed) {
//        nextDirection = "up";
//        keyH.upPressed = false;
//    } else if (keyH.downPressed) {
//        nextDirection = "down";
//        keyH.downPressed = false;
//    } else if (keyH.rightPressed) {
//        nextDirection = "right";
//        keyH.rightPressed = false;
//    } else if (keyH.leftPressed) {
//        nextDirection = "left";
//        keyH.leftPressed = false;
//    }
//
//// קריאה לפונקציה רק אם הכיוון המבוקש קיים
//if (nextDirection != null) {
//        updateDirection(nextDirection);
//        // איפוס הכיוון הבא רק אם התנועה התבצעה בהצלחה
//        if (direction.equals(nextDirection)) {
//            nextDirection = null;
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
