/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.image.BufferedImage;

/**
 *
 * @author yurenlong
 */
public class RenderObject {
    int x, y;
    int base_offset;
    BufferedImage image;
    
    public RenderObject(int X, int Y, int offset, BufferedImage Image){
        x = X;
        y = Y;
        base_offset = offset;
        image = Image;
    }
    
    public void setY(int Y){
        y = Y;
    }
    public void setX(int X){
        x = X;
    }
    public void setImage(BufferedImage i){
        image = i;
    }
    
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
    public int getBaseOffset(){
        return base_offset;
    }
    public BufferedImage getImage(){
        return image;
    }
}
