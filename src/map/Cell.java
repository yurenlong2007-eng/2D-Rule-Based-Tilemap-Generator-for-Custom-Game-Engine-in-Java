/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package map;

import java.awt.Rectangle;


/**
 *
 * @author yurenlong
 */
public class Cell {
    int x, y;
    
    Layer layer_0 = new Layer();
    Layer_BG layer_1 = new Layer_BG();
    Layer_OBJ layer_2 = new Layer_OBJ();
    
    Rectangle solidArea = new Rectangle();
    
    public Cell(int xPosition, int yPosition){
        this.x = xPosition;
        this.y = yPosition;
    }
    
    public void setXY(int nx, int ny){
        x = nx;
        y = ny;
    }
    public void setSolidArea(int x, int y, int width, int height){
        solidArea.x = x;
        solidArea.y = y;
        solidArea.width = width;
        solidArea.height= height;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Layer getlayer_0(){
        return layer_0;
    }
    public Layer_BG getlayer_1(){
        return layer_1;
    }
    public Layer_OBJ getlayer_2(){
        return layer_2;
    }
    
}
