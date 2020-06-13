package com.wd.entity;

import java.awt.Image;

/**
 * 敌人抽象类
 */
public abstract class Enemy {
	//x,y坐标
	public int x,y;
	//高度和宽度
	public int width,height;
	//图片
	public Image img;
	public Enemy(int x, int y, int width, int height, Image img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img=img;
	}
}
