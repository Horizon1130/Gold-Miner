package game;

import game.in_game.InGame;

import javax.swing.*;

/**
 * 主窗口
 */
public class BasedWindow extends JFrame {
    /**
     * <li>记录窗口状态，谨慎修改<br><br>
     * 1:menu 2:in_game 3:shop 4:close
     */
    public int state = 1;
    /**
     * 当前主题<br>
     * null:未开始 classic:经典 kun:???
     */
    public String theme = "null";
    /** 游戏管理线程 */
    public InGame inGameThread;
    /**
     * 主窗口，游戏入口
     * @throws Exception 游戏异常中断
     */
    BasedWindow() throws Exception {
        this.setTitle("黄金矿工");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 838); //标题栏占高度
        this.setResizable(false);
        this.setVisible(true);
        while (state != 4) {
            switch (state) {
                case 1 -> showMenu();
                case 2 -> startGame();
                case 3 -> goShopping();
                default -> throw new Exception();
            }
        }
    }

    private void showMenu() {
        Menu menuThread = new Menu(this);
        this.setContentPane(menuThread.menuPanel);
        try {
            menuThread.join();
        } catch (InterruptedException e) {
            state = 0;
        }
    }

    private void startGame() throws InterruptedException {
        if (theme.equals("classic"))
            SoundLib.playSound("进入");
        else if (theme.equals("kun"))
            SoundLib.playSound("太美");
        Thread.sleep(500);
        this.setContentPane(inGameThread.inGamePanel);
        inGameThread.thread = new Thread(inGameThread);
        inGameThread.thread.start();
        try {
            inGameThread.thread.join();
        } catch (InterruptedException e) {
            state = 0;
        }
    }

    private void goShopping() {
        Shop shopThread = new Shop(this);
        this.setContentPane(shopThread.shopPanel);
        try {
            shopThread.join();
        } catch (InterruptedException e) {
            state = 0;
        }
    }
}
