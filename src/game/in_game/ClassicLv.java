package game.in_game;

import game.BasedWindow;
import game.SoundLib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassicLv extends LevelBuilder {
    /** 炸弹图标 */
    Image bombImage = Toolkit.getDefaultToolkit().getImage("image/in_game/bomb.png");
    /** 投掷的炸弹 */
    Item bomb = null;
    /** 炸弹数 */
    public int bombNum = 3;
    /** 爆炸动画持续计数 */
    int explodeCount = -1;
    /** 爆炸动画 */
    Item explode = new Item();
    /**第十关后累加所需通关分数*/
    int exTarget = 0;
    /** 超出10关的关卡数目*/
    int exLevelNum = 0;
    /** 大力水生效 */
    public boolean powerFlag;
    /** 钻石水生效 */
    public boolean diamondFlag;
    /** 石头书生效 */
    public boolean stoneFlag;
    /** 退出本关按钮 */
    JButton exitButton;
    /** 退出本关图片 */
    Image exitImage = Toolkit.getDefaultToolkit().getImage("image/in_game/exit.png");

    ClassicLv(BasedWindow basedWindow) {
        this.basedWindow = basedWindow;
        background = Toolkit.getDefaultToolkit().getImage("image/in_game/classic.jpg");
        //退出按钮
        exitButton = new JButton();
        exitButton.setBounds(350, 30, 101, 75);
        exitButton.setContentAreaFilled(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                time = 0;
                exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                basedWindow.inGameThread.inGamePanel.remove(exitButton);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //爆炸效果
        explode.image = Toolkit.getDefaultToolkit().getImage("image/in_game/explode.gif");
        explode.width = 260;
        explode.height = 120;
        //键盘监听
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //下键出钩
                if (e.getKeyCode() == KeyEvent.VK_DOWN && line.state == 0) {
                    line.state = 1;
                    SoundLib.playSound("出钩");
                }
                //上键炸弹
                if (e.getKeyCode() == KeyEvent.VK_UP && line.state == 3) {
                    if (bombNum > 0 && bomb == null) {
                        --bombNum;
                        bomb = new Item();
                        bomb.image = bombImage;
                        bomb.x = line.start_x;
                        bomb.y = line.start_y;
                        bomb.width = 25;
                        bomb.height = 49;
                    }
                }
            }
        };
    }

    @Override
    public void loadLevel() {
        if(Integer.parseInt(level) > 10)exTarget = 15275 + exLevelNum * 2705;
        int temp = Integer.parseInt(level) % 10;
        //TODO 关卡模板
        if (temp == 1) {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(650 + exTarget);
            itemList.add(new Gold(3));
            itemList.add(new Gold(2));
            for(int i = 0; i < 3; i++)
            {
                double random = Math.random();
                if(random < 0.25)
                {
                    itemList.add(new Gold(1));
                    if(i < 2)
                        itemList.add(new Stone(1));
                }else if(random < 0.75)
                {
                    itemList.add(new Gold(2));
                    if(i < 2)itemList.add(new Stone(2));
                }
                else  if(random > 0.85){
                    itemList.add(new Gold(3));
                }
            }
        }
        else if(temp == 2){
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(1195 + exTarget);
            itemList.add(new Gold(3));
            itemList.add(new Gold(2));
            for(int i = 0; i < 5; i++)
            {
                double random = Math.random();
                if(random < 0.20)
                {
                    itemList.add(new Gold(1));
                    itemList.add(new Stone(1));
                }else if(random < 0.65)
                {
                    itemList.add(new Gold(2));
                    itemList.add(new Stone(2));
                }
                else if(random > 0.85)itemList.add(new Gold(3));
            }
        }
        else if(temp == 3)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 2010);
            itemList.add(new Gold(3));
            itemList.add(new Gold(3));
            itemList.add(new Gold(3));
            itemList.add(new Gold(2));
            for(int i = 0; i < 8; i++)
            {
                double random = Math.random();
                if(random < 0.30)
                {
                    itemList.add(new Gold(1));
                    itemList.add(new Stone(1));
                }else if(random < 0.75)
                {
                    itemList.add(new Gold(2));
                    itemList.add(new Stone(2));
                }
                else  if(random > 0.85){
                    itemList.add(new Gold(3));
                }
            }
        }
        else if(temp == 4)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 3095);
            double rd = Math.random();
            int t;
            if(rd <= 0.25)
                t = 4;
            else t = 2;
            while(t>0)
            {
                t--;
                itemList.add(new LuckyBag());
            }
            for(int i = 0; i < 12; i++)
            {
                double random = Math.random();
                if(random < 0.40)
                {
                    itemList.add(new Gold(1));
                    if(i < 10)
                        itemList.add(new Stone(1));
                }else if(random < 0.65)
                {
                    itemList.add(new Gold(2));
                    if(i < 10)itemList.add(new Stone(2));
                }
                else if(random > 0.90)itemList.add(new Gold(3));
            }
        }
        else if(temp == 5)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 4450);
            itemList.add(new LuckyBag());itemList.add(new LuckyBag());itemList.add(new LuckyBag());itemList.add(new LuckyBag());
            double rd = Math.random();
            int t;
            if(rd <= 0.6)t = 3;
            else t = 4;
            while(t > 0){
                itemList.add(new Diamond());
                t--;
            }
            for(int i=0;i<2;i++)//3000
            {
                itemList.add(new Gold(3));
                itemList.add(new Gold(2));
            }
            for(int i = 0; i < 16; i++)
            {
                double random = Math.random();
                if(random < 0.40)
                {
                    itemList.add(new Gold(1));
                    if(i < 12)
                        itemList.add(new Stone(1));
                }else if(random < 0.70)
                {
                    itemList.add(new Gold(2));
                    if(i < 14)itemList.add(new Stone(2));
                }
                else if(random > 0.95)itemList.add(new Gold(3));
            }
        }
        else if(temp == 6)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 6075);
            itemList.add(new LuckyBag());itemList.add(new LuckyBag());itemList.add(new LuckyBag());
            int t = 4;
            while(t > 0)
            {
                itemList.add(new Diamond());
                t--;
            }
            for(int i = 0; i < 20; i++)
            {
                double random = Math.random();
                if(random < 0.20)
                {
                    itemList.add(new Gold(1));
                    if(i < 16)
                        itemList.add(new Stone(1));
                }else if(random < 0.65)
                {
                    itemList.add(new Gold(2));
                    if(i < 18)itemList.add(new Stone(2));
                }
                else if(random < 0.75) itemList.add(new Gold(3));
                else itemList.add(new Stone(2));
            }
        }
        else if(temp == 7)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 7970);
            itemList.add(new LuckyBag());
            for(int i = 0; i < 20; i++)
            {
                double random = Math.random();
                if(random < 0.15)
                {
                    itemList.add(new Gold(1));
                    if(i<18)itemList.add(new Stone(1));
                }
                else if(random < 0.30)
                {
                    itemList.add(new Gold(2));
                    if(i<16)itemList.add(new Stone(2));
                }
                else if(random > 0.90)itemList.add(new Gold(3));
            }
        }
        else if(temp == 8)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 10135);
            itemList.add(new LuckyBag());
            itemList.add(new LuckyBag());
            double rd = Math.random();
            int t;
            if(rd<0.22)t = 2;
            else t = 8;
            while(t>0)
            {
                itemList.add(new Diamond());
                t--;
            }
            for(int i = 0; i < 10; i++)
            {
                double random = Math.random();
                if(random < 0.25)
                {
                    itemList.add(new Gold(1));
                    itemList.add(new Stone(1));
                }
                else if(random < 0.5)
                {
                    itemList.add(new Gold(2));
                    itemList.add(new Stone(2));
                }
                else if(random > 0.85)
                {
                    itemList.add(new Gold(3));
                    itemList.add(new Stone(2));
                }
            }
        }
        else if(temp == 9)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 12570);
            int t = 8;
            itemList.add(new LuckyBag());
            itemList.add(new LuckyBag());
            itemList.add(new LuckyBag());
            itemList.add(new LuckyBag());
            while(t>0)
            {
                itemList.add(new Diamond());
                t--;
            }
            for(int i = 0; i < 20; i++)
            {
                double random = Math.random();
                if(random < 0.5)
                {
                    itemList.add(new Stone(1));
                    if(i < 8)itemList.add(new Gold(1));
                }
                else
                {
                    itemList.add(new Stone(2));
                    if(i < 10)itemList.add(new Gold(2));
                }
            }
        }
        else if(temp == 0)
        {
            if(Integer.parseInt(level) > 10)target = String.valueOf(Integer.parseInt(target) + 2705);
            else target = String.valueOf(exTarget + 15275);
            double rd = Math.random();
            if(rd <= 0.69)
            {
                for(int i = 0; i < 12; i++)
                    itemList.add(new Diamond());
            }
            for(int i = 0; i < 30; i++)
            {
                double random = Math.random();
                if(random < 0.3)
                    itemList.add(new Gold(1));
                else if(random < 0.6)
                    itemList.add(new Gold(2));
                else if(random > 0.75)
                    itemList.add(new Gold(3));
            }
        }
        //道具生效
        if (powerFlag) {
            line.strength = 2;
            powerFlag = false;
        }
        if (diamondFlag) {
            for (Item item : itemList) {
                if (item instanceof Diamond)
                    item.value = 900;
            }
            diamondFlag = false;
        }
        if (stoneFlag) {
            for (Item item : itemList) {
                if (item instanceof Stone)
                    item.value = (int) (Math.random() * 50 + 50);
            }
            stoneFlag = false;
        }
        //启动关卡
        basedWindow.inGameThread.inGamePanel.add(exitButton);
        timer.start();
    }

    @Override
    void ShowWinLoss(Graphics g) {
        //播放音频
        if (winLoss == 1) {
            SoundLib.playSound("过关");
        }
        winLoss = 0;
        //绘制界面
        g.drawImage(settlement, 0, 0, 1280, 800, null);
        if (time < -2) {
            timer.stop();
            bomb = null;
            explodeCount = -1;
            if (money >= Integer.parseInt(target)) {
                basedWindow.state = 3;
//                level = String.valueOf(Integer.parseInt(level) + 1);
                itemList.clear();
                line.length = 30;
                line.state = 0;
                line.strength = 1;
            } else {
                basedWindow.state = 1;
            }
            basedWindow.inGameThread.thread.interrupt();
            time = 60;
        }
    }

    @Override
    void levelSpecial(Graphics g) {
        //绘制退出本关按钮
        if (time > 0) g.drawImage(exitImage, 350, 30, 101, 75, null);
        //绘制炸弹和数量
        g.drawImage(bombImage, 840, 50, 25, 49, null);
        g.drawString("x " + String.valueOf(bombNum), 870, 87);
        //计算和绘制投掷的炸弹
        if (bomb != null) {
            g.drawImage(bomb.image, bomb.x, bomb.y, bomb.width, bomb.height, null);
            bomb.x = (int) (bomb.x + 20 * Math.cos(line.coefficient * Math.PI));
            bomb.y = (int) (bomb.y + 20 * Math.sin(line.coefficient * Math.PI));
            if (bomb.y > line.item.y) {
                //爆炸移除
                SoundLib.playSound("爆炸");
                itemList.remove(line.item);
                //切换线状态
                line.state = 2;
                //爆炸效果坐标和状态
                explode.x = line.end_x - 100;
                explode.y = line.end_y - 30;
                explodeCount = 50;
                //清除炸弹
                bomb = null;
            }
        }
        //绘制爆炸效果
        if (explodeCount >= 0) {
            --explodeCount;
            g.drawImage(explode.image, explode.x, explode.y, explode.width, explode.height, null);
        }
    }
}