package game;

import game.in_game.InGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 主菜单面板 */
class MenuPanel extends JPanel {
    private final Image bg;
    private final Image zhiYin;
    private Image word;
    private final Image buttonWord;
    private final Image buttonWordLight;

    /**
     * 构造函数
     *
     * @param basedWindow - 用于修改窗口状态
     * @param thread      - 插入菜单线程
     */
    MenuPanel(BasedWindow basedWindow, Menu thread) {
        //导入图片
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        bg = toolkit.getImage("image/menu/bg.jpg");
        zhiYin = toolkit.getImage("image/menu/ji.gif");
        buttonWord = toolkit.getImage("image/menu/button.png");
        buttonWordLight = toolkit.getImage("image/menu/button_light.png");
        word = buttonWord;
        //设置隐形按钮
        JButton startB = new JButton();
        startB.setBounds(220, 140, 300, 200);
        startB.setContentAreaFilled(false);
        startB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                word = buttonWordLight;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                word = buttonWord;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                basedWindow.state = 2; //修改状态，进入游戏场景
                basedWindow.theme = "classic";
                basedWindow.inGameThread = new InGame(basedWindow);
                thread.interrupt(); //终止菜单
            }
        });
        //???
        JButton startKun = new JButton();
        startKun.setBounds(1230, 760, 50, 50);
        startKun.setContentAreaFilled(false);
        startKun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                basedWindow.state = 2; //修改状态，进入游戏场景
                basedWindow.theme = "kun";
                basedWindow.inGameThread = new InGame(basedWindow);
                thread.interrupt(); //终止菜单
            }
        });

        //设置JPanel属性
        this.setSize(1280, 800);
        this.setLayout(null);
        this.add(startB);
        this.add(startKun);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, 1280, 800, this);
        g.drawImage(zhiYin, 1220, 760, 70, 40, this);
        g.drawImage(word, 300, 200, this);
    }
}

/** 主菜单管理线程 */
public class Menu extends Thread {
    MenuPanel menuPanel;

    Menu(BasedWindow basedWindow) {
        menuPanel = new MenuPanel(basedWindow, this);
        this.start();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                //noinspection BusyWait
                sleep(1000000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}