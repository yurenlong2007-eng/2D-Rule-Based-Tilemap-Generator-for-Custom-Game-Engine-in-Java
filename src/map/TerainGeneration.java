/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author yurenlong
 */
public class TerainGeneration {
    int numberOfTerains = 8;
    int cellSize;
    
    Cell[][] map;
    
    ArrayList<Integer>[][] rules;
    
    ArrayList<Cell> listOfCells = new ArrayList();
    
    int totalConflict = 1;
    
    public TerainGeneration(int sf, int mapWidth, int mapHeight){
        map = new Cell[mapWidth][mapHeight];
        rules = new ArrayList[numberOfTerains][4];//8
        
        cellSize = sf;
        
        rules = initialiseRules(rules, numberOfTerains);
        rules = generateRules(rules, getRef());
    }
    
    public void randomiseMap(Cell[][] map){
        Random r = new Random();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                map[x][y].layer_0.setobjID(r.nextInt(numberOfTerains));
            }
        }
    }
    
    public ArrayList<Integer>[][] initialiseRules(ArrayList<Integer>[][] rules, int numberOfTerains){
        for(int n = 0; n < numberOfTerains; n++){
            for(int d = 0; d < 4; d++){//8
                rules[n][d] = new ArrayList<Integer>();
            }
        }
        return rules;
    }
    public ArrayList<Integer>[][] generateRules(ArrayList<Integer>[][] rules, int[][] ref){
        int[] dx = {0,-1,1,0};//{-1,0,1,-1,1,-1,0,1};
        int[] dy = {-1,0,0,1};//{-1,-1,-1,0,0,1,1,1};
        int nx, ny;
        
        for(int y = 0; y < ref.length; y++){
            for(int x = 0; x < ref.length; x++){
                for(int d = 0; d < 4; d++){//8
                    nx = x + dx[d];
                    ny = y + dy[d];
                    if(nx >= 0 && nx < ref.length 
                            && ny >= 0 && ny < ref.length){
                        if(!rules[ref[x][y]][d].contains(ref[nx][ny]) && ref[nx][ny] != -1){
                            rules[ref[x][y]][d].add(ref[nx][ny]);
                        }
                    }
                }
            }
        }
        return rules;
    }
    
    public void drawMap(Graphics2D g2, Cell[][] map, int mapWidth, int mapHeight){
        for(int x = 0; x < mapWidth; x++){
            for(int y = 0; y < mapHeight; y++){
                switch(map[x][y].layer_1.getobjID()){
                    case 0:
                        g2.setColor(new Color(115, 37, 204));
                        break;
                    case 1:
                        g2.setColor(new Color(51, 37, 204));
                        break;
                    case 2:
                        g2.setColor(new Color(37, 154, 204));
                        break;
                    case 3:
                        g2.setColor(new Color(37, 201, 204));
                        break;
                    case 4:
                        g2.setColor(new Color(37, 204, 45));
                        break;
                    case 5:
                        g2.setColor(new Color(176, 204, 37));
                        break;
                    case 6:
                        g2.setColor(new Color(204, 173, 37));
                        break;
                    case 7:
                        g2.setColor(new Color(204, 101, 37));
                        break;
                    case 13:
                        g2.setColor(Color.blue);
                        break;
                    case 14:
                        g2.setColor(Color.green);
                        break;
                    case 28:
                        g2.setColor(Color.yellow);
                        break;
                    default:
                        g2.setColor(Color.red);
                }
                
                g2.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
    
    public void minConflict(Cell[][] map, ArrayList<Integer>[][] rules, ArrayList<Cell> listOfCells, int mapWidth, int mapHeight, int numberOfTerains, int p, int modifyLayer){
        Random r = new Random();
        
        totalConflict = 0;
        Collections.shuffle(listOfCells);
        ArrayList<Integer> replacements = new ArrayList();
        
        for(int c = 0; c < listOfCells.size(); c++){
            int conflicts = 0;
            switch(modifyLayer){
                case 0:
                    conflicts = countConflicts(map, rules,listOfCells.get(c), listOfCells.get(c).layer_0.getobjID(), mapWidth, mapHeight, modifyLayer);
                    break;
                case 1:
                    conflicts = countConflicts(map, rules,listOfCells.get(c), listOfCells.get(c).layer_1.getobjID(), mapWidth, mapHeight, modifyLayer);
                    break;
            }
            
            if(conflicts > 0){
                totalConflict++;
                replacements.clear();
                
                for(int t = 0; t < numberOfTerains; t++){
                    int newCount = countConflicts(map, rules, listOfCells.get(c), t, mapWidth, mapHeight, modifyLayer);
                    
                    if(newCount <= conflicts || r.nextInt(10000) < p){
                        replacements.add(t);
                    }
                }
                switch(modifyLayer){
                    case 0:
                        listOfCells.get(c).layer_0.setobjID(replacements.get(r.nextInt(replacements.size())));
                        break;
                    case 1:
                        listOfCells.get(c).layer_1.setobjID(replacements.get(r.nextInt(replacements.size())));
                        break;
                }
            }
        }
    }
    public int countConflicts(Cell[][] map, ArrayList<Integer>[][] rules, Cell c, int type, int mapWidth, int mapHeight, int modifyLayer){
        int count = 0;
        int[] dx = {0,-1,1,0};//{-1,0,1,-1,1,-1,0,1};
        int[] dy = {-1,0,0,1};//{-1,-1,-1,0,0,1,1,1};
        int nx, ny;
        
        for(int d = 0; d < 4; d++){//8
            nx = (c.x + dx[d] + mapWidth) % mapWidth;
            ny = (c.y + dy[d] + mapHeight) % mapHeight;
            switch(modifyLayer){
                case 0:
                    if(!rules[type][d].contains(map[nx][ny].layer_0.getobjID())){
                        count++;
                    }
                    break;
                case 1:
                    if(!rules[type][d].contains(map[nx][ny].layer_1.getobjID())){
                        count++;
                    }
                    break;
            }
            
        }
        return count;
    }
    
    public Cell[][] rescaleMap(Cell[][] originalMap, int sf){
        Cell[][] newMap = new Cell[originalMap[0].length * sf][originalMap.length * sf];
        int sx,sy,nx,ny;
        
        for(int y = 0; y < originalMap.length; y++){
            for(int x = 0; x < originalMap[0].length; x++){
                sy = y * sf;
                sx = x * sf;
                for(ny = sy; ny < (sy + sf); ny++){
                    for(nx = sx; nx < (sx + sf); nx++){
                        newMap[nx][ny] = new Cell(nx, ny);
                        newMap[nx][ny].layer_0.setobjID(originalMap[x][y].layer_0.getobjID());
                    }
                }
            }
        }
        return newMap;
    }
    
    public void convertTerainToExtremes(Cell[][] map){
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                switch(map[x][y].layer_0.getobjID()){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        map[x][y].layer_0.setobjID(0);
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        Random r = new Random();
                        if(r.nextInt(2) == 0){
                            map[x][y].layer_0.setobjID(3);
                        }else{
                            map[x][y].layer_0.setobjID(7);
                        }
                        break;
                        
                }
            }
        }
    }
    
    public int[][] getRef(){
        int[][] ref = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,1,0},
            {0,1,2,3,3,3,3,3,3,3,3,3,3,2,1,0},
            {0,1,2,3,4,4,4,4,4,4,4,4,3,2,1,0},
            {0,1,2,3,4,5,5,5,5,5,5,4,3,2,1,0},
            {0,1,2,3,4,5,6,6,6,6,5,4,3,2,1,0},
            {0,1,2,3,4,5,6,7,7,6,5,4,3,2,1,0},
            {0,1,2,3,4,5,6,7,7,6,5,4,3,2,1,0},
            {0,1,2,3,4,5,6,6,6,6,5,4,3,2,1,0},
            {0,1,2,3,4,5,5,5,5,5,5,4,3,2,1,0},
            {0,1,2,3,4,4,4,4,4,4,4,4,3,2,1,0},
            {0,1,2,3,3,3,3,3,3,3,3,3,3,2,1,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,1,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            
        };
        return ref;
    }
    
    public int getTotalConflict(){
        return totalConflict;
    }
}
