/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weapen_object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;

/**
 *
 * @author yurenlong
 */
public class Weapon {
    String name;
    BufferedImage imageset;
    int height_block, length_block, height_pixel, length_pixel;
    int x1, x2, x3, x4, y1, y2, y3, y4; //hit detection points
    
    GamePanel gp;
    
    public Weapon(GamePanel gp){
        this.gp= gp;
    }
    
    public void rotateHitVerticies(double angle){
        int x, y;
        x = (int)(x1 * Math.cos(angle) - y1 * Math.sin(angle));
        y = (int)(x1 * Math.sin(angle) + y1 * Math.cos(angle));
        x1 = x;
        y1 = y;
        x = (int)(x2 * Math.cos(angle) - y2 * Math.sin(angle));
        y = (int)(x2 * Math.sin(angle) + y2 * Math.cos(angle));
        x2 = x;
        y2 = y;
        x = (int)(x3 * Math.cos(angle) - y3 * Math.sin(angle));
        y = (int)(x3 * Math.sin(angle) + y3 * Math.cos(angle));
        x3 = x;
        y3 = y;
        x = (int)(x4 * Math.cos(angle) - y4 * Math.sin(angle));
        y = (int)(x4 * Math.sin(angle) + y4 * Math.cos(angle));
        x4 = x;
        y4 = y;
    }
    
    public void draw(Graphics2D g2, double aim_angle){
        Graphics2D temp = (Graphics2D) g2.create();
        
        int centerX = gp.getPlayer().getPrintX() + gp.getPlayer().getwidth_pixel()/2;
        int centerY = gp.getPlayer().getPrintY() + gp.getPlayer().getheight_pixel()/2;
        
        temp.translate(centerX, centerY);
        temp.rotate(-aim_angle);//default rotate clockewise
        temp.drawImage(imageset, 0, -height_pixel/2, null);//later determine correct animation
        temp.drawRect(0, -gp.blockSize/2, gp.blockSize * 2, gp.blockSize);
        
        
        temp.dispose();
    }
    
    public void setHitVertices(int x1p, int y1p, int x2p, int y2p, int x3p, int y3p, int x4p, int y4p){
        x1 = x1p;
        x2 = x2p;
        x3 = x3p;
        x4 = x4p;
        y1 = y1p;
        y2 = y2p;
        y3 = y3p;
        y4 = y4p;
    }
    public void setDimension(int height, int length){
        height_block = height;
        length_block = length;
        height_pixel = height_block * gp.blockSize;
        length_pixel = length_block * gp.blockSize;
    }
    
}
