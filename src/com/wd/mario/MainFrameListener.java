package com.wd.mario;

import com.wd.view.MainFrame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrameListener extends KeyAdapter {
    private MainFrame mainFrame;

    public MainFrameListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP:
                mainFrame.mario.up = true;
                break;
            case KeyEvent.VK_LEFT:
                mainFrame.mario.left = true;
                break;
            case KeyEvent.VK_RIGHT:
                mainFrame.mario.right = true;
                break;
            case KeyEvent.VK_A:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP:
                mainFrame.mario.up = false;
                break;
            case KeyEvent.VK_LEFT:
                mainFrame.mario.left = false;
                mainFrame.mario.img = new ImageIcon("img/mari_left1.png").getImage();
                break;
            case KeyEvent.VK_RIGHT:
                mainFrame.mario.right = false;
                mainFrame.mario.img = new ImageIcon("img/mari1.png").getImage();
                break;
            case KeyEvent.VK_A:
                break;
        }
    }
}
