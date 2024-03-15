package game.in_game;

import game.SoundLib;

import java.awt.*;

abstract class SpecialItem extends Item {
    /** 特殊物品触发状态 */
    int state = 0;

    /**
     * 功能函数<br>
     * 触发两次，一次为钩索命中时，一次为勾到矿工处时
     */
    abstract void startupFunction(LevelBuilder levelBuilder);
}

class LuckyBag extends SpecialItem {
    LuckyBag() {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/lucky_bag.png");
        width = 54;
        height = 61;
        if (Math.random() > 0.8) {
            //力量袋
            value = 0;
            speed = 0.55;
        }
        else if (Math.random() < 0.2){
            //炸药袋
            value = -1;
            speed = 0.95;
        }
        else {
            //金钱袋
            value = (int) (Math.random() * 700) + 1;
            speed = Math.random();
            if (speed < 0.3) speed = 0.3;
        }

        do {
            x = (int) (Math.random() * 1220);
            y = (int) (Math.random() * 340 + 400);
        } while (isOverlap(itemList));
    }

    @Override
    void startupFunction(LevelBuilder levelBuilder) {
        if (state == 0) ++state;
        else {
            if (value == 0) levelBuilder.line.strength = 5;
            else if (value == -1) {
                ++((ClassicLv) levelBuilder).bombNum;
                value = 0;
            }
        }
    }

    @Override
    void playSound() {
        SoundLib.playSound("好");
    }
}