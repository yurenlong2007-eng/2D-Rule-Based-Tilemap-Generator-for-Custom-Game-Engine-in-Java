/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package map;

import entity.Player;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import main.FileHandler;
import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import scenery_object.ObjectHandler;

/**
 *
 * @author yurenlong
 */
public class MapHandler {
    int width_block = 20;
    int height_block = 20;
    int sf_block = 5;
//    int mapState = 0;
    
    public Cell[][] map = new Cell[width_block * sf_block][height_block * sf_block];
    ArrayList<Cell> listOfCells = new ArrayList();
    
    GamePanel gp;
    Player p;
    Background bg;
    ObjectHandler objH;
    FileHandler fh;
    public TerainGeneration tg;
    
    
    public MapHandler(GamePanel gp, MouseHandler m, KeyHandler k, Player p){
        this.gp = gp;
        this.p = p;
        
        tg = new TerainGeneration(5,width_block,height_block);
        objH = new ObjectHandler(gp);
        fh = new FileHandler(gp,this);
        bg = new Background(gp, tg, fh);
    }
    
    public void draw(Graphics2D g2){
        bg.drawStaticBGMap(g2, p);
    }
    
    public void generate(){
        TerainGeneration tg = new TerainGeneration(5, width_block, height_block);
        initialiseMap(tg.map);
        tg.randomiseMap(tg.map);
        initialiseListOfCells(tg.map, tg.listOfCells);
        tg.totalConflict++;
        
        while(tg.totalConflict > 0){
            tg.minConflict(tg.map, tg.rules, tg.listOfCells, tg.map.length, tg.map.length, tg.numberOfTerains, 100, 0);
        }
        fh.saveTerrainMap(tg.map, "raw_terain_map", width_block, height_block);
        initialiseMap(map);
        map = tg.rescaleMap(tg.map, sf_block);
        tg.convertTerainToExtremes(map);
        initialiseListOfCells(map, listOfCells);
        tg.totalConflict++;
        
        while(tg.totalConflict > 0){
            tg.minConflict(map, tg.rules, listOfCells, map.length, map.length, tg.numberOfTerains, 50, 0);
        }
        bg.convertTerainToBackground(map);
        tg.totalConflict++;
        
        while(tg.totalConflict > 0){
            tg.minConflict(map, bg.rules, listOfCells, map.length, map.length, bg.tileSetSize, 50, 1);
        }
        rescaleCoordinates(map);
        bg.setUpStaticMap(map);
        setupObjects(map);
    }
    
    public void initialiseListOfCells(Cell[][] map, ArrayList<Cell> listOfCells){
        listOfCells.clear();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                listOfCells.add(map[x][y]);
            }
        }
    }
    public void initialiseMap(Cell[][] map){
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                map[x][y] = new Cell(x, y);
            }
        }
    }
    public void rescaleCoordinates(Cell[][] map){
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                map[x][y].x *= gp.blockSize;
                map[x][y].y *= gp.blockSize;
            }
        }
    }
    
    public void setupObjects(Cell[][] map){
        Random r = new Random();
        int ID = 0;
        
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                
                switch(map[x][y].layer_0.objID){
                    case 3:
                    case 4:
                    case 5:
                        if(r.nextInt(1000) < 100){
                            ID++;
                            objH.RedTree.place(map, x, y, ID);
                        }
                        break;
                }
            }
        }
    }
    
    public Cell[][] getTerrainMap(){
        return tg.map;
    }
    public Cell[][] getMap(){
        return map;
    }
//    public void setMapState(int state){
//        mapState = state;
//    }
}
