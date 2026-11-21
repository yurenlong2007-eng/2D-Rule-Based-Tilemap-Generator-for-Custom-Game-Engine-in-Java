/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scenery_object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import map.Cell;

/**
 *
 * @author yurenlong
 */
public class SceneryObject{
    GamePanel gp;
    
    String name;
    
    int width_block, height_block;
    int base_offset;
    
    Rectangle solidArea = new Rectangle();
    
    BufferedImage tileSet;
    
    public SceneryObject(GamePanel gp){
        this.gp = gp;
    }
    
    public boolean canPlace(Cell[][] map, int sx, int sy){
        if(sx + width_block < map.length && sy + height_block < map.length){
            for(int x = sx; x < sx + width_block; x++){
                for(int y = sy; y < sy + height_block; y++){
                    if(map[x][y].getlayer_2().getobjName() != null){
                        return false;
                    }
                }
            }
        }else{
            return false;
        }
        return true;
    }
    public void place(Cell[][] map, int x, int y, int ID){
        if(canPlace(map,x,y)){
            int sID = 0;

            for(int j = y; j < y + height_block; j++){
                for(int i = x; i < x + width_block; i++){
                    map[i][j].getlayer_2().setobjName(name);
                    map[i][j].getlayer_2().setobjID(ID);
                    map[i][j].getlayer_2().setSID(sID);
                    sID++;
                }
            }
        }
    }
    
    public int getX_PixelOffset(int secID){
        return gp.blockSize * (secID % width_block);
    }
    public int getY_PixelOffset(int secID){
        return gp.blockSize * (secID / width_block);//given that the first block starts with secID = 0
    }
    public int getBaseOffset(){
        return base_offset;
    }
    
    public void setDimension(int width, int height){
        width_block = width;
        height_block = height;
    }
    public void setBaseOffset(int offset_pixel){
        base_offset = offset_pixel * gp.displayScaleFactor;
    }
}
