package com.wd.mario;

import javax.swing.*;
import java.awt.*;

public class Mario extends Thread{
    //马里奥的坐标
    public int x=0,y=358;
    //马里奥的速度
    public int xspeed=5 , yspeed=1;
    //马里奥的宽高
    public int width=30,height=32;
    //马里奥的图片
    public Image img = new ImageIcon("img/mari1.png").getImage();
    public volatile boolean left=false,right=false,down=false,up=false;

    @Override
    public void run() {
        while(true){
            if (left){
                if(x>=0){
                    this.x-=this.xspeed;
                    this.img=new ImageIcon("img/mari_left.gif").getImage();
                }

            }
            if (right){
                this.x+=this.xspeed;
                this.img = new ImageIcon("img/mari_right.gif").getImage();
                System.out.println("right");
            }


            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
