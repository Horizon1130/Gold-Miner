package game.in_game;

import game.BasedWindow;
import game.SoundLib;

import java.awt.*;

public class KunLv extends LevelBuilder {
    KunLv(BasedWindow basedWindow) {
        this.basedWindow = basedWindow;
        background = Toolkit.getDefaultToolkit().getImage("image/in_game/kun_bg.jpg");
        level = "只因";
        target = "两年半";
    }

    @Override
    public void loadLevel() {
        //鸡
        for (int i = 0; i < 5; ++i) {
            itemList.add(new Chicken());
        }
        //各2个
        for (int i = 0; i < 2; ++i) {
            itemList.add(new Kun(1));
            itemList.add(new Kun(2));
            itemList.add(new Kun(3));
            itemList.add(new Basketball());
            itemList.add(new Litchi());
        }
        //启动
        time = 60;
        timer.start();
    }

    @Override
    Image setSettlement() {
        if (itemList.size() == 0) {
            winLoss = 1;
            return Toolkit.getDefaultToolkit().getImage("image/in_game/kun_win.gif");
        } else {
            winLoss = 2;
            return Toolkit.getDefaultToolkit().getImage("image/in_game/kun_loss.gif");
        }
    }

    @Override
    void ShowWinLoss(Graphics g) {
        //播放音频
        if (winLoss == 1) {
            SoundLib.playSound("厉害");
        } else if (winLoss == 2){
            SoundLib.playSound("干嘛");
        }
        winLoss = 0;
        //绘制界面
        g.drawImage(settlement, 0, 0, 1280, 800, null);
        //退出界面
        if (time < -2) {
            timer.stop();
            basedWindow.state = 1;
            basedWindow.inGameThread.thread.interrupt();
        }
    }
}
