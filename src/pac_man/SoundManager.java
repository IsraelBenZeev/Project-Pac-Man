package pac_man;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private static Clip eatClip;
    private static Clip startClip;
    private static Clip diedClip;
    private static Clip eatCoinClip;
    private static Clip nextLevelClip;

    public static String fullPath(String p) {
        return "src/resource/sound/" + p + ".wav";
    }

    static {
        try {
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


    public static void playEat() {
        if (eatClip != null && !eatClip.isRunning()) {
            eatClip.setFramePosition(0);
            eatClip.start();
        }
    }

    public static void playStart() {
        if (startClip != null && !startClip.isRunning()) {
            startClip.setFramePosition(0);
            startClip.start();
        }
    }

    public static void playEatCoin() {
        if (eatCoinClip != null && !eatCoinClip.isRunning()) {
            if (eatClip.isRunning()) eatClip.stop();
            eatCoinClip.setFramePosition(0);
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
