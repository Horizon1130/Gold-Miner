package game.in_game;

import game.SoundLib;

import java.awt.*;

/** 关卡中的钩索线 */
public class Line {
    /** 起点坐标 */
    int start_x = 640, start_y = 100;
    /** 终点坐标 */
    int end_x, end_y;
    /** 线长 */
    double length = 30;
    /** 线摆角的系数 */
    double coefficient = 0.1;
    /** 摆动方向 -1:向右 1:向左 */
    int direction = 1;
    /** 状态 0:左右摇摆 1:抓取 2:收回 3:抓取收回 */
    int state = 0;
    /** 力量 */
    public double strength = 1;
    /** 钩爪图片 */
    Image hook1 = Toolkit.getDefaultToolkit().createImage("image/in_game/hook1.png"),
            hook2 = Toolkit.getDefaultToolkit().createImage("image/in_game/hook2.png");
    /** 对应关卡器 */
    LevelBuilder levelBuilder;
    /** 准备移除的物品 */
    Item item;

    Line(LevelBuilder levelBuilder) {
        this.levelBuilder = levelBuilder;
    }

    /** 与所有物品的碰撞检测 */
    private void logic() {
        Rectangle hook = new Rectangle(end_x - 15, end_y - 2, 30, 15);
        for (Item temp : levelBuilder.itemList) {
            if (temp.getRectangle().intersects(hook)) {
                item = temp;
                item.playSound();
                state = 3;
                if (item instanceof SpecialItem)
                    ((SpecialItem) item).startupFunction(levelBuilder);
                break;
            }
        }
    }

    /** 计算下一帧 */
    void calculate () {
        switch (state) {
            case 0:
                coefficient = coefficient + 0.005 * direction;
                if (coefficient < 0.1)
                    direction = 1;
                else if (coefficient > 0.9)
                    direction = -1;
                break;
            case 1:
                logic();
                if (length < 850)
                    length += 7;
                else
                    state = 2;
                break;
            case 2:
                if (length > 30)
                    length -= 15;
                else {
                    length = 30;
                    state = 0;
                }
                break;
            case 3:
                if (length > 30) {
                    length -= item.speed * 7 * strength;
                    item.x = end_x - item.width / 2;
                    item.y = end_y;
                }
                else {
                    length = 30;
                    if (item instanceof SpecialItem)
                        ((SpecialItem) item).startupFunction(levelBuilder);
                    levelBuilder.money += item.value;
                    if (item.value != 0) SoundLib.playSound("叮");
                    levelBuilder.itemList.remove(item);
                    state = 0;
                }
                break;
        }
    }

    /** 绘制线和钩子 */
    void printLine(Graphics g) {
        double radian = coefficient * Math.PI;
        end_x = (int) (start_x + length * Math.cos(radian));
        end_y = (int) (start_y + length * Math.sin(radian));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4.0f));
        g2.setColor(Color.black);
        g2.drawLine(start_x, start_y, end_x, end_y);

        if (state == 3)
            g.drawImage(hook2, end_x - 25, end_y - 2, 50, 25, null);
        else
            g.drawImage(hook1, end_x - 25, end_y - 2, 50, 25, null);
    }
}
