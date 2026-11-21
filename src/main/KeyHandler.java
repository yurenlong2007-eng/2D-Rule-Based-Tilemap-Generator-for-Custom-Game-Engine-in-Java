/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author yurenlong
 */
public class KeyHandler implements KeyListener{
    
    private boolean k_1Pressed;
    private boolean wPressed, sPressed, aPressed, dPressed, upPressed, downPressed, leftPressed, rightPressed;
    private boolean spacePressed;

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            wPressed = true;
        }
        if(code == KeyEvent.VK_S){
            sPressed = true;
        }
        if(code == KeyEvent.VK_A){
            aPressed = true;
        }
        if(code == KeyEvent.VK_D){
            dPressed = true;
        }
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_1){
            k_1Pressed = true;
        }
        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            wPressed = false;}
        if(code == KeyEvent.VK_S){
            sPressed = false;}
        if(code == KeyEvent.VK_A){
            aPressed = false;}
        if(code == KeyEvent.VK_D){
            dPressed = false;}
        if(code == KeyEvent.VK_UP){
            upPressed = false;}
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;}
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;}
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;}
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;}
        if(code == KeyEvent.VK_0){
            k_1Pressed = false;
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean getwPressed(){
        return wPressed;
    }
    public boolean getsPressed(){
        return sPressed;
    }
    public boolean getaPressed(){
        return aPressed;
    }
    public boolean getdPressed(){
        return dPressed;
    }
    public boolean getupPressed(){
        return upPressed;
    }
    public boolean getdownPressed(){
        return downPressed;
    }
    public boolean getleftPressed(){
        return leftPressed;
    }
    public boolean getrightPressed(){
        return rightPressed;
    }
    public boolean getspacePressed(){
        return spacePressed;
    }
    public boolean getk_1Pressed(){
        return k_1Pressed;
    }
}
