package game;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;

final public class SoundLib {
    static HashMap<String, File> lib = new HashMap<>();

    static {
        //此处初始化音频文件及对应访问键
        lib.put("干嘛", new File("sound/你干嘛.wav"));
        lib.put("厉害", new File("sound/厉不厉害你坤哥.wav"));
        lib.put("荔枝", new File("sound/荔枝.wav"));
        lib.put("倒鸡", new File("sound/嘛干你.wav"));
        lib.put("只因", new File("sound/只因.wav"));
        lib.put("篮球", new File("sound/篮球.wav"));
        lib.put("过关", new File("sound/过关.wav"));
        lib.put("叮", new File("sound/叮.wav"));
        lib.put("太美", new File("sound/鸡你太美.wav"));
        lib.put("爆炸", new File("sound/爆炸.wav"));
        lib.put("好", new File("sound/好东西.wav"));
        lib.put("坏", new File("sound/坏东西.wav"));
        lib.put("进入", new File("sound/进入.wav"));
        lib.put("出钩", new File("sound/出钩.wav"));
    }

    /**
     * 播放音频<br>
     * 键列表："干嘛" "厉害" "荔枝" "倒鸡" "只因" "篮球" "过关" "叮" "太美" "爆炸" "好" "坏" "进入" "出钩"
     * @param soundName 音频访问键
     */
    public static void playSound(String soundName) {
        SoundPlayer soundPlayer = new SoundPlayer(lib.get(soundName));
        soundPlayer.playSound();
    }

    /** 仅用于初始化加载音频文件 */
    public static void load() {

    }
}


class SoundPlayer {
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;

    SoundPlayer(File soundFile) {
        try {
            loadSound(soundFile);
        } catch (Exception e) {
            System.out.println("音频加载异常");
        }
    }

    private void loadSound(File file) throws Exception {
        System.out.println(file.getAbsoluteFile());
        stream= AudioSystem.getAudioInputStream(file);
        format=stream.getFormat();
    }

    void playSound() {
        info=new DataLine.Info(Clip.class,format);
        try {
            clip=AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            System.out.println("音频播放异常");
        }
    }
}




