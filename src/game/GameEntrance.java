package game;

public class GameEntrance {
    public static void main(String[] args) {
        try {
            SoundLib.load();
            new BasedWindow();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1); //异常终止
        }
        System.exit(0);
    }
}