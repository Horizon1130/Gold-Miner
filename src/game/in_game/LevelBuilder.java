package game.in_game;

import game.BasedWindow;
import game.SoundLib;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class LevelBuilder {
    /** 基础窗口 */
    BasedWindow basedWindow;
    /** 背景 */
    Image background;
    /** 场景物品列表 */
    ArrayList<Item> itemList = new ArrayList<>();
    /** 钩索线 */
    Line line = new Line(this);
    /** 当前关卡号 */
    public String level = String.valueOf(1);
    /** 通过条件 */
    String target;
    /** 当前金钱 */
    public int money = 0;
    /** 结算界面 */
    Image settlement;
    /** 胜利失败状态，用于防止循环播放结算音效<br>
     * 0-无 1-胜利 2-失败
     */
    int winLoss = 0;
    /** 计时器 */
    int time = 60;
    /** Timer组件 */
    Timer timer;
    /** 键盘监听器 */
    KeyAdapter keyAdapter;

    LevelBuilder() {
        Item.loadItemList(itemList);
        timer = new Timer(1000, e -> {
            --time;
            //时间用尽或已清空场景则结束本关
            if (time >= -1 && (time == -1 || itemList.size() == 0)) {
                time = -1;
                settlement = setSettlement();
            }
        });

        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //下键出钩
                if (e.getKeyCode() == KeyEvent.VK_DOWN && line.state == 0) {
                    line.state = 1;
                }
            }
        };
    }

    /** 绘制本关内容 */
    public void drawLevel(Graphics g) {
        if (time < 0) {
            ShowWinLoss(g);
            return;
        }
        //背景
        g.drawImage(background, 0, 0, 1280, 800, null);
        //线和钩子，物品清除判断
        line.calculate();
        line.printLine(g);
        //文字
        Font font = new Font("等线", Font.BOLD, 35);
        g.setFont(font);
        drawCenter(g, font, String.valueOf(level), 1175, 99);
        g.drawString(String.valueOf(time), 1205, 48);
        g.drawString(String.valueOf(money), 110, 45);
        g.drawString(String.valueOf(target), 190, 105);
        //场景物品
        for (Item item : itemList) {
            try {
                g.drawImage(item.image,item.x, item.y, item.width, item.height, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //绘制主题特殊
        levelSpecial(g);
    }

    /**绘制特殊 */
    void levelSpecial(Graphics g) {

    }

    /** 居中绘制字符串 */
    static void drawCenter(Graphics g, Font font, String text, int x, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.drawString(text, x - metrics.stringWidth(text) / 2, y);
    }

    /** 加载本关场景物品 */
    public abstract void loadLevel();

    Image setSettlement() {
        if (money >= Integer.parseInt(target)) {
            winLoss = 1;
            return Toolkit.getDefaultToolkit().getImage("image/in_game/classic_win.jpg");
        } else {
            winLoss = 2;
            return Toolkit.getDefaultToolkit().getImage("image/in_game/classic_loss.jpg");
        }
    }

    /** 绘制结算界面 */
    abstract void ShowWinLoss(Graphics g);
}