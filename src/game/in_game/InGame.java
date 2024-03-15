package game.in_game;

import game.BasedWindow;

import javax.swing.*;
import java.awt.*;

class InGamePanel extends JPanel {
    /** 关卡管理器 */
    LevelBuilder levelBuilder;

    InGamePanel(BasedWindow basedWindow) {
        if (basedWindow.theme.equals("classic"))
            levelBuilder = new ClassicLv(basedWindow);
        else if (basedWindow.theme.equals("kun"))
            levelBuilder = new KunLv(basedWindow);

        //设置JPanel属性
        this.addKeyListener(levelBuilder.keyAdapter);
        this.setSize(1280, 800);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        //双缓冲绘制
        Image iBuffer = createImage(1280, 800);
        Graphics iBg = iBuffer.getGraphics();
        levelBuilder.drawLevel(iBg);

        g.drawImage(iBuffer, 0, 0, 1280, 800, this);
        this.requestFocus();
    }
}

public class InGame implements Runnable{
    public InGamePanel inGamePanel;
    public Thread thread;

    public InGame(BasedWindow basedWindow) {
        inGamePanel = new InGamePanel(basedWindow);
    }

    @Override
    public void run() {
        inGamePanel.levelBuilder.loadLevel();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                inGamePanel.repaint();
                //noinspection BusyWait
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public LevelBuilder getLevelSystem() {
        return inGamePanel.levelBuilder;
    }
}
