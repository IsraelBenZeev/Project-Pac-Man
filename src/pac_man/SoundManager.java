package pac_man;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private static Clip eatClip;
    private static Clip startClip;
    private static Clip diedClip;
    private static Clip eatCoinClip;
    private static Clip nextLevelClip;

    static {
        try {
            // פתיחת הסאונדים
            AudioInputStream eatStream = AudioSystem.getAudioInputStream(new File(fullPath("eating")));
            eatClip = AudioSystem.getClip();
            eatClip.open(eatStream);

            AudioInputStream startStream = AudioSystem.getAudioInputStream(new File(fullPath("start2")));
            startClip = AudioSystem.getClip();
            startClip.open(startStream);

            AudioInputStream diedStream = AudioSystem.getAudioInputStream(new File(fullPath("died2")));
            diedClip = AudioSystem.getClip();
            diedClip.open(diedStream);

            AudioInputStream eatCoinStream = AudioSystem.getAudioInputStream(new File(fullPath("eat_coin")));
            eatCoinClip = AudioSystem.getClip();
            eatCoinClip.open(eatCoinStream);

            AudioInputStream nextLevelStream = AudioSystem.getAudioInputStream(new File(fullPath("next_level")));
            nextLevelClip = AudioSystem.getClip();
            nextLevelClip.open(nextLevelStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    // נתיבי הסאונדים
//    private static final String DOT_EAT = fullPath("pacman_eating3");
//    private static final String GAME_START = fullPath("start_game");
//    private static final String GHOST_EAT = "C:\\Users\\i0548\\Downloads\\Pack-Man\\sound\\ghost_eat.wav";
//    private static final String POWER_UP = "C:\\Users\\i0548\\Downloads\\Pack-Man\\sound\\power_up.wav";

    public static String fullPath(String p) {
        return "C:\\Users\\i0548\\Downloads\\Pack-Man\\sound\\" + p + ".wav"; // ודא שהנתיב נכון
    }

    public static void playEat() {
        if (eatClip != null && !eatClip.isRunning()) { // אם הסאונד לא מתנגן
            eatClip.setFramePosition(0);  // מתחיל את הסאונד מההתחלה
            eatClip.start();
        }
    }

    public static void playStart() {
        if (startClip != null && !startClip.isRunning()) { // אם הסאונד לא מתנגן
            startClip.setFramePosition(0);  // מתחיל את הסאונד מההתחלה
            startClip.start();
        }
    }

    public static void playEatCoin() {
        if (eatCoinClip != null && !eatCoinClip.isRunning()) { // אם הסאונד לא מתנגן
            if (eatClip.isRunning()) eatClip.stop();
            eatCoinClip.setFramePosition(0);  // מתחיל את הסאונד מההתחלה
            eatCoinClip.start();
        }
    }

    public static void playDied() {
        if (diedClip != null && !diedClip.isRunning()) {
            diedClip.setFramePosition(0);
            diedClip.start();
        }
    }

    public static void playNextLevel() {
        if (nextLevelClip != null && !nextLevelClip.isRunning()) {
            stopAll();
            nextLevelClip.setFramePosition(0);
            nextLevelClip.start();
        }
    }
    public static void stopAll(){
        eatClip.stop();
        startClip.stop();
        eatCoinClip.stop();
        diedClip.stop();
        nextLevelClip.stop();
    }

    public static void main(String[] args) throws InterruptedException {

       while (true){
           playNextLevel();
           Thread.sleep(12);
       }

    }
}
