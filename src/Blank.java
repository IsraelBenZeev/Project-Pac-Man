import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Blank {

//    public void update() {
//        int num = 1;
//        int nextX = x, nextY = y;
//        if (keyH.upPressed && y > 2) {
//            direction = "up";
//            System.out.println(direction);
//            this.currentY = (y)/titleSize;
//            this.currentX = (x + speed) /titleSize;
//            System.out.println("tempY: "+ currentY +", "+"tempX: "+currentX);
//            boolean bool = is2(num,arr,currentY,currentX);
//            System.out.println(bool);
//            if (bool) y -= speed;
//            System.out.println("x: " + x + ", " + "y: " + y);
//            System.out.println("---------------------------");
//        }
//        if (keyH.downPressed && y < 576 - titleSize - 2) {
//            direction = "down";
//            System.out.println(direction);
//            this.currentY = (y +speed+titleSize) / titleSize;
//            this.currentX = (x +speed) /titleSize;
//            System.out.println("tempY: "+ currentY +", "+"tempX: "+currentX);
//            boolean bool = is2(num,arr,currentY,currentX);
//            System.out.println(bool);
//            if (bool) y += speed;
//            System.out.println("x: " + x + ", " + "y: " + y);
//            System.out.println("---------------------------");
//        }
//        if (keyH.leftPressed && x > 2) {
//            direction = "left";
//            System.out.println(direction);
//            currentY = (y+speed+titleSize)/titleSize;
//            currentX = (x-titleSize)/titleSize;
//            System.out.println("tempY: "+ currentY +", "+"tempX: "+currentX);
//            boolean bool = is2(num,arr,currentY,currentX);
//            System.out.println(bool);
//            if (bool) x -= speed;
//            System.out.println("x: " + x + ", " + "y: " + y);
//            System.out.println("---------------------------");
//        }
//        if (keyH.rightPressed && x < 768 - titleSize - 2) {
//            System.out.println(direction);
//            direction = "right";
//            currentY = (y) / titleSize;
//            currentX = (x +  titleSize )/titleSize;
//            System.out.println("tempY: "+ currentY +", "+"tempX: "+currentX);
//            boolean bool = is2(num,arr,currentY,currentX);
//            System.out.println(bool);
//            if (bool) x += speed;
//            System.out.println("x: " + x + ", " + "y: " + y);
//            System.out.println("---------------------------");
//
//        }
//
//
//
//    public static void main(String[] args) {
//
//    }
}


//String colorBlue = "Ghost_blue.jpg";
//String colorPink = "Ghost_pink.jpg";
//String colorGreen = "Ghost_green.jpg";
//String colorOrange = "Ghost_orange.jpg";
//String colorRed = "Ghost_red.jpg";
//BufferedImage blue = ImageIO.read(getClass().getResourceAsStream(fullPath(colorBlue)));
//BufferedImage pink = ImageIO.read(getClass().getResourceAsStream(fullPath(colorPink)));
//BufferedImage green = ImageIO.read(getClass().getResourceAsStream(fullPath(colorGreen)));
//BufferedImage orange = ImageIO.read(getClass().getResourceAsStream(fullPath(colorOrange)));
//BufferedImage red = ImageIO.read(getClass().getResourceAsStream(fullPath(colorRed)));
//
//public String fullPath(String p) {
//    return "/resource/ghosts/Ghost_blue/" + p;
//}
//
//String[] directions = new String[]{"up", "down", "right", "left"};
//String prevDirection="";
//public void chasePlayer() {
//    int mapWidth = gamePanel.map[0].length; // רוחב המטריצה
//    int mapHeight = gamePanel.map.length;  // גובה המטריצה
//
//    // תרגום מיקומים לרשת
//    int playerGridX = (player.x + 2) / gamePanel.titleSize;
//    int playerGridY = (player.y + 2) / gamePanel.titleSize;
//    int monsterGridX = (x + 2) / gamePanel.titleSize;
//    int monsterGridY = (y + 2) / gamePanel.titleSize;
//
//    // כיווני תנועה (למעלה, למטה, שמאלה, ימינה)
//    int[] dx = {0, 0, 1, -1};
//    int[] dy = {-1, 1, 0, 0};
//
//    // מטריצה לזיהוי ביקור
//    boolean[][] visited = new boolean[mapHeight][mapWidth];
//    visited[monsterGridY][monsterGridX] = true;
//
//    // מטריצה לאחסון הצעד הקודם
//    int[][] prevX = new int[mapHeight][mapWidth];
//    int[][] prevY = new int[mapHeight][mapWidth];
//
//    // תור ל-BFS
//    Queue<int[]> queue = new LinkedList<>();
//    queue.add(new int[]{monsterGridX, monsterGridY});
//
//    boolean pathFound = false;
//
//    // BFS למציאת המסלול
//    while (!queue.isEmpty()) {
//        int[] current = queue.poll();
//        int currX = current[0];
//        int currY = current[1];
//
//        // אם הגענו לשחקן
//        if (currX == playerGridX && currY == playerGridY) {
//            pathFound = true;
//            break;
//        }
//
//        // בדיקת שכנים
//        for (int i = 0; i < 4; i++) {
//            int newX = currX + dx[i];
//            int newY = currY + dy[i];
//
//            // בדיקת גבולות ושכנים חוקיים (ערך 0)
//            if (newX >= 0 && newX < mapWidth && newY >= 0 && newY < mapHeight &&
//                    !visited[newY][newX] && gamePanel.map[newY][newX]!=1
//                    && !gamePanel.walls.checkCollision(newX*gamePanel.titleSize,newY* gamePanel.titleSize,directions[i])
//            ) {
//
//                visited[newY][newX] = true;
//                queue.add(new int[]{newX, newY});
//                prevX[newY][newX] = currX;
//                prevY[newY][newX] = currY;
//            }
//        }
//    }
//
//    // אם נמצא מסלול, עדכון כיוון המפלצת
//    if (pathFound) {
//        int nextX = playerGridX;
//        int nextY = playerGridY;
//
//        // חזרה דרך המסלול מהשחקן למפלצת
//        while (prevX[nextY][nextX] != monsterGridX || prevY[nextY][nextX] != monsterGridY) {
//            int tempX = prevX[nextY][nextX];
//            int tempY = prevY[nextY][nextX];
//            nextX = tempX;
//            nextY = tempY;
//        }
//
//        // חישוב כיוון התנועה
//        for (int i = 0; i < 4; i++) {
//            if (monsterGridX + dx[i] == nextX && monsterGridY + dy[i] == nextY) {
//                if (!directions[i].equals(direction)){
//                    prevDirection=direction;
//                }
//                direction = directions[i];
//                break;
//            }
//        }
//    }
//    boolean moved = moveMonster();
//    if (!moved){
//        direction = prevDirection;
//        moveMonster();
//    }
//}
//
//
//public boolean moveMonster() {
//    if (direction.equals("up") && !gamePanel.walls.checkCollision(x, y - speed, direction)) {
//        y -= speed;
//    } else if (direction.equals("down")
//            && !gamePanel.walls.checkCollision(x, y + speed, direction)) {
//        y += speed;
//    } else if (direction.equals("left")
//            && !gamePanel.walls.checkCollision(x - speed, y, direction)) {
//        x -= speed;
//    } else if (direction.equals("right")
//            && !gamePanel.walls.checkCollision(x + speed, y, direction)) {
//        x += speed;
//    } else {
//        return false;
//    }
//    return true;
//}
//
//public void moveAutomatic() {//פונקצייה שמרנדמת את התזוזזה של המפלצות
//    wrapAround();
//    int change = random.nextInt(35);
//
//    if (change == 0) {
//        int move = random.nextInt(4); // 0, 1, 2, או 3
//        direction = directions[move];
//    }
//
//    boolean moved = moveMonster();
//    if (!moved) {
//        int move = random.nextInt(4);
//        direction = directions[move];
//    }
//
//}
