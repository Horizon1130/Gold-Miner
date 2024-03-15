package game.in_game;

import game.SoundLib;

import java.awt.*;
import java.util.ArrayList;

class Item {
    /** 坐标 */
    int x, y;
    /** 宽高 */
    int width, height;
    /** 图片 */
    Image image;
    /** 钓起速度 */
    double speed;
    /** 价格 */
    int value = 0;
    /** 物品列表 - 防重叠 */
    static ArrayList<Item> itemList;

    public static void loadItemList(ArrayList<Item> itemList) {
        Item.itemList = itemList;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    boolean isOverlap(ArrayList<Item> itemList) {
        for (Item item : itemList) {
            if (item.getRectangle().intersects(this.getRectangle()))
                return true;
        }
        return false;
    }

    void playSound() {

    }
}

class Kun extends Item {
    /**
     * @param size - 1: mini, 2: normal, 3: big
     */
    Kun(int size) {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/kun.gif");
        do {
            switch (size) {
                case 1 -> {
                    x = (int) (Math.random() * 1200);
                    y = (int) (Math.random() * 400 + 300);
                    width = 60;
                    height = 94;
                    speed = 0.95;
                }
                case 2 -> {
                    x = (int) (Math.random() * 1150);
                    y = (int) (Math.random() * 350 + 300);
                    width = 83;
                    height = 130;
                    speed = 0.75;
                }
                case 3 -> {
                    x = (int) (Math.random() * 1150);
                    y = (int) (Math.random() * 320 + 300);
                    width = 105;
                    height = 165;
                    speed = 0.5;
                }
            }
        } while (isOverlap(itemList));
    }

    @Override
    void playSound() {
        if (Math.random() > 0.3) {
            SoundLib.playSound("干嘛");
        }
        else {
            SoundLib.playSound("倒鸡");
        }
    }
}

class Chicken extends Item {
    Chicken() {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/chicken.png");
        width = 80;
        height = 80;
        speed = 0.85;
        do {
            x = (int) (Math.random() * 1000 + 200);
            y = (int) (Math.random() * 400 + 300);
        } while (isOverlap(itemList));
    }

    @Override
    void playSound() {
        SoundLib.playSound("只因");
    }
}

class Basketball extends Item {

    Basketball() {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/basketball.png");
        width = 60;
        height = 60;
        speed = 0.95;
        do {
            x = (int) (Math.random() * 1000 + 200);
            y = (int) (Math.random() * 400 + 300);
        } while (isOverlap(itemList));
    }

    @Override
    void playSound() {
        SoundLib.playSound("篮球");
    }
}

class Litchi extends Item {
    Litchi() {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/litchi.png");
        width = 55;
        height = 50;
        speed = 0.95;
        do {
            x = (int) (Math.random() * 1000 + 200);
            y = (int) (Math.random() * 400 + 300);
        } while (isOverlap(itemList));
    }

    @Override
    void playSound() {
        SoundLib.playSound("荔枝");
    }
}

class Gold extends Item {
    /**
     * @param size - 1: mini, 2: normal, 3: big
     */
    Gold(int size)
    {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/gold.png");
        do {
            switch (size) {
                case 1 -> {
                    x = (int) (Math.random() * 1000 + 100);
                    y = (int) (Math.random() * 400 + 300);
                    width = 31;
                    height = 28;
                    speed = 0.95;
                    value = 50;
                }
                case 2 -> {
                    x = (int) (Math.random() * 1220);
                    y = (int) (Math.random() * 440 + 300);
                    width = 62;
                    height = 56;
                    speed = 0.6;
                    value = 250;
                }
                case 3 -> {
                    x = (int) (Math.random() * 1150);
                    y = (int) (Math.random() * 390 + 300);
                    width = 124;
                    height = 112;
                    speed = 0.3;
                    value = 500;
                }
            }
        } while (isOverlap(itemList));
    }

    @Override
    void playSound() {
        SoundLib.playSound("好");
    }
}
class Stone extends Item
{
    /**
     * @param size - 1: normal, 2: big
     */
    Stone(int size)
    {
        do {
            switch (size)
            {
                case 1 -> {
                    image = Toolkit.getDefaultToolkit().getImage("image/in_game/stone_L.png");
                    x = (int) (Math.random() * 1215);
                    y = (int) (Math.random() * 470 + 300);
                    width = 54;
                    height = 45;
                    speed = 0.45;
                    value = 10;
                }
                case 2 -> {
                    image = Toolkit.getDefaultToolkit().getImage("image/in_game/stone_B.png");
                    x = (int) (Math.random() * 1190);
                    y = (int) (Math.random() * 450 + 300);
                    width = 76;
                    height = 68;
                    speed = 0.3;
                    value = 30;
                }
            }
        } while (isOverlap(itemList));
    }

    @Override
    void playSound()
    {
        SoundLib.playSound("坏");
    }
}

class Diamond extends Item {
    Diamond() {
        image = Toolkit.getDefaultToolkit().getImage("image/in_game/diamond.png");
        width = 31;
        height = 28;
        speed = 0.95;
        value = 600;
        do {
            x = (int) (Math.random() * 1000 + 100);
            y = (int) (Math.random() * 400 + 300);
        } while (isOverlap(itemList));
    }

    @Override
    void playSound() {
        SoundLib.playSound("好");
    }
}