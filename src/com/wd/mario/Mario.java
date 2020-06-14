package com.wd.mario;

import com.wd.entity.Enemy;
import com.wd.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Mario extends Thread{
    public MainFrame mf;
    //马里奥的坐标
    public int x=0,y=358;
    //马里奥的速度
    public int xspeed=5 , yspeed=1;
    //马里奥的宽高
    public int width=30,height=32;
    //马里奥的图片
    public Image img = new ImageIcon("img/mari1.png").getImage();
    public volatile boolean left=false,right=false,down=false,up=false;
    public String Dir_Up="Up",Dir_Left="Left",Dir_Right="Right",Dir_Down="Down";
    //是否贴地
    private boolean isGravity = false;
    private boolean jumpFlag = true;
    public Mario(MainFrame mf) {
        this.mf = mf;
        gravity();
    }

    @Override
    public void run() {
        while(true){
            if (left){

                if(x>=0){
                    this.x-=this.xspeed;
                    this.img=new ImageIcon("img/mari_left.gif").getImage();
                }
                this.xspeed = 5;
            /*    if(x>=200){
                    mf.bgImage.x +=this.xspeed;
                    for (int i = 0; i < mf.enemyList.size(); i++) {
                        Enemy enemy = mf.enemyList.get(i);
                        enemy.x += this.xspeed;
                    }
                }*/

            }
            if (right){
                if(hit(Dir_Right)){
                    this.xspeed = 0;
                }
                if(x<400){
                    this.x+=this.xspeed;
                    this.img = new ImageIcon("img/mari_right.gif").getImage();
                }
                //当超过400时背景和障碍物向左移动
                if(x>=400){
                    mf.bgImage.x -=this.xspeed;
                    for (int i = 0; i < mf.enemyList.size(); i++) {
                        Enemy enemy = mf.enemyList.get(i);
                        enemy.x -= this.xspeed;
                    }

                }
                this.img = new ImageIcon("img/mari_right.gif").getImage();
            }

            if (up){
                if(jumpFlag&&!isGravity){
                    jumpFlag = false;
                    new Thread(()->{
                        jump();
                        jumpFlag = true;
                    }).start();
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void jump() {
        int jumpHeight = 0;
        for (int i = 0; i < 150; i++) {
            this.y -= yspeed;
            jumpHeight++;
            if (hit(Dir_Up)){
                break;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < jumpHeight; i++) {
            this.y += yspeed;
            if (hit(Dir_Down)){
                this.yspeed = 0;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.yspeed = 1;
    }


    private void gravity(){
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){

                    if (!jumpFlag){
                        break;
                    }
                    System.out.println(hit(Dir_Down));
                    if (hit(Dir_Down)){
                        break;
                    }

                    if(y>=358){
                        isGravity = false;
                    }else{
                        isGravity = true;
                        this.y+=yspeed;
                        System.out.println(y);
                    }
                }
            }

        }).start();
    }

    private boolean hit(String dir){
        Rectangle mario = new Rectangle(x,y,width,height);
        Rectangle rectangle = null;
        for (int i = 0; i < mf.enemyList.size(); i++) {
            Enemy enemy = mf.enemyList.get(i);
            if("Right".equals(dir)){
                rectangle = new Rectangle(enemy.x-2,enemy.y,enemy.width,enemy.height);
            }else if("Up".equals(dir)){
                rectangle = new Rectangle(enemy.x,enemy.y-1,enemy.width,enemy.height);
            }else if(dir.equals("Down")){
                rectangle = new Rectangle(enemy.x,enemy.y-2,enemy.width,enemy.height);
            }else if(dir.equals("Left")){
                rectangle = new Rectangle(enemy.x+2,enemy.y,enemy.width,enemy.height);
            }
            if(mario.intersects(rectangle)){
                return true;
            }
        }


        return false;
    }
}
