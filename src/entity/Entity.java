/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.Time;

/**
 *
 * @author yurenlong
 */
public class Entity{
    //linking
    GamePanel gp;
    ImageReader ir = new ImageReader();
    Time t;
    
    //location and motion
    int worldX, worldY, worldZ;
    int speed, diagonalSpeed;
    String direction;
    
    //collision
    Rectangle solidArea = new Rectangle();
    
    //rendering
    int width_block, height_block;
    int width_pixel, height_pixel;
    BufferedImage spriteSheet;
//    SpriteFrame[] sprite;
    
    public Entity(GamePanel gp, Time t){
        this.gp = gp;
        this.t = t;
    }
    
    public void setDimension(int width, int height){
        width_block = width;
        height_block = height;
        width_pixel = width_block * gp.blockSize;
        height_pixel = height_block * gp.blockSize;
    }
    public void setUpSpriteSheet(String filePath){
        spriteSheet = ir.getImage(filePath, gp.displayScaleFactor);
    }
    public void draw(Graphics2D g2, String direction, int printX, int printY){
        int sx1 = t.getFPS12Counter() * width_pixel;
        int sy1 = getSpriteSheetOffset(direction) * height_pixel;
        int sx2 = sx1 + width_pixel;
        int sy2 = sy1 + height_pixel;
        int dx2 = printX + width_pixel;
        int dy2 = printY + height_pixel;
        
        g2.drawImage(spriteSheet,
                printX, printY, dx2, dy2,
                sx1, sy1, sx2, sy2,
                null);
    }
    public int getSpriteSheetOffset(String direction){
        switch(direction){
            case "null":
                return 0;
            case "W":
            case "A":
            case "D":
            case "S":
            case "WA":
            case "WD":
            case "SA":
            case "SD":
                return 0;
        }
        return -1;
    }
    
    public int getWorldX(){
        return worldX;
    }
    public int getWorldY(){
        return worldY;
    }
    public int getWorldZ(){
        return worldZ;
    }
    public String getDirection(){
        return direction;
    }
    public int getwidth_pixel(){
        return width_pixel;
    }
    public int getheight_pixel(){
        return height_pixel;
    }
}
