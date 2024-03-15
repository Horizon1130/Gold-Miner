package game;

import game.in_game.ClassicLv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ShopPanel extends JPanel {
    private final Image bg;
    private Image bomb;
    private Image powerWater;
    private Image betterDiamond;
    private Image stoneBook;
    private Image nextLevel;
    ClassicLv levelBuilder;

    ShopPanel(BasedWindow basedWindow, Shop thread) {
        levelBuilder = (ClassicLv) basedWindow.inGameThread.getLevelSystem();
        //导入图片
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        bg = toolkit.getImage("image/shop/bg.gif");
        //炸弹
        if (Math.random() > 0.3) {
            bomb = toolkit.getImage("image/shop/bomb.png");
            JButton button = new JButton();
            this.add(button);
            button.setBounds(100, 380, 100, 200);
            button.setContentAreaFilled(false);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (levelBuilder.money >= 200) {
                        levelBuilder.money -= 200;
                        ++levelBuilder.bombNum;
                        bomb = null;
                        remove(button);
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        SoundLib.playSound("叮");
                    }
                }
            });
        }
        //大力水
        if (Math.random() > 0.6) {
            powerWater = toolkit.getImage("image/shop/power.png");
            JButton button = new JButton();
            this.add(button);
            button.setBounds(240, 367, 180, 210);
            button.setContentAreaFilled(false);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (levelBuilder.money >= 400) {
                        levelBuilder.money -= 400;
                        levelBuilder.powerFlag = true;
                        powerWater = null;
                        remove(button);
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        SoundLib.playSound("叮");
                    }
                }
            });
        }
        //钻石水
        int lv_mod = Integer.parseInt(levelBuilder.level) % 10;
        if ((lv_mod == 5 || lv_mod == 6 || lv_mod > 7) && Math.random() > 0.3) {
            betterDiamond = toolkit.getImage("image/shop/diamond.png");
            JButton button = new JButton();
            this.add(button);
            button.setBounds(470, 400, 140, 180);
            button.setContentAreaFilled(false);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (levelBuilder.money >= 600) {
                        levelBuilder.money -= 600;
                        levelBuilder.diamondFlag = true;
                        betterDiamond = null;
                        remove(button);
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        SoundLib.playSound("叮");
                    }
                }
            });
        }
        //石头书
        stoneBook = toolkit.getImage("image/shop/stone.png");
        JButton button = new JButton();
        this.add(button);
        button.setBounds(660, 380, 150, 200);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (levelBuilder.money >= 100) {
                    levelBuilder.money -= 100;
                    levelBuilder.stoneFlag = true;
                    stoneBook = null;
                    remove(button);
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    SoundLib.playSound("叮");
                }
            }
        });
        //下一关
        nextLevel = toolkit.getImage("image/shop/next.png");
        JButton next = new JButton();
        this.add(next);
        next.setBounds(0, 0, 148, 64);
        next.setContentAreaFilled(false);
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                basedWindow.state = 2;
                remove(next);
                levelBuilder.level = String.valueOf(Integer.parseInt(levelBuilder.level) + 1);
                thread.interrupt();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                nextLevel = toolkit.getImage("image/shop/next_light.png");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                nextLevel = toolkit.getImage("image/shop/next.png");
            }
        });

        //设置JPanel属性
        this.setSize(1280, 800);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, 1280, 800, this);
        g.drawImage(nextLevel, 0, 0, 148, 64, this);
        g.setFont(new Font("等线", Font.BOLD, 35));
        g.drawString("当前金钱：" + String.valueOf(levelBuilder.money), 400, 700);
        if (bomb != null) g.drawImage(bomb, 100, 380, 100, 200, this);
        if (powerWater != null) g.drawImage(powerWater, 240, 377, 180, 200, this);
        if (betterDiamond != null) g.drawImage(betterDiamond, 470, 400, 140, 180, this);
        if (stoneBook != null) g.drawImage(stoneBook, 660, 380, 150, 200, this);
    }
}

public class Shop extends Thread{
    ShopPanel shopPanel;

    Shop(BasedWindow basedWindow) {
        shopPanel = new ShopPanel(basedWindow, this);
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
