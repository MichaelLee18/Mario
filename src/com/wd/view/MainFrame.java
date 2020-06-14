package com.wd.view;

import com.wd.entity.Brick;
import com.wd.entity.Coin;
import com.wd.entity.Enemy;
import com.wd.entity.Pipe;
import com.wd.mario.MainFrameListener;
import com.wd.mario.Mario;
import com.wd.util.MapUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    public BgImage bgImage;
    //地图信息
    private int[][] mapData;
    public Mario mario;
    public List<Enemy> enemyList = new ArrayList<>();


    {
        mapData = MapUtil.initMap("map.txt");

    }
    public MainFrame() {
        initData();
        this.setSize(800,450);
        this.setTitle("超级玛丽");
        this.setResizable(false);
        // 居中展示窗口
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(new MainFrameListener(this));
        mario.start();
        new Thread(()->{
            while(true){
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    private void initData(){
        mario = new Mario(this);
        bgImage = new BgImage();
        //初始化地图信息
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                switch (mapData[i][j]){
                    case 1:
                        //画砖头
                        Enemy brick = new Brick(j*30,i*30,30,30,new ImageIcon("img/brick.png").getImage());
                        enemyList.add(brick);
                        break;
                    case 2:
                        //画金币
                        Enemy coin = new Coin(j*30,i*30,30,30,new ImageIcon("img/coin_brick.png").getImage());
                        enemyList.add(coin);
                        break;
                    case 3:
                        //画水管
                        Enemy pipe = new Pipe(j*30,i*30,60,120,new ImageIcon("img/pipe.png").getImage());
                        enemyList.add(pipe);
                        break;
                }
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        Graphics bg = image.getGraphics();
        //绘制背景图片
        bg.drawImage(bgImage.img,bgImage.x,bgImage.y,null);
        bg.drawImage(mario.img,mario.x,mario.y,null);
        for(Enemy enemy:enemyList){
            bg.drawImage(enemy.img,enemy.x,enemy.y,enemy.width,enemy.height,null);
        }

        g.drawImage(image,0,0,null);
    }
}
