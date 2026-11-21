/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import map.Cell;
import map.MapHandler;

/**
 *
 * @author yurenlong
 */
public class FileHandler {
    GamePanel gp;
    MapHandler mh;
    
    public FileHandler(GamePanel gp, MapHandler mh){
        this.gp = gp;
        this.mh = mh;
    }
    
    public void backUp(){
        saveSettings(gp);
        saveAccount(gp);
    }
    public void load(){
        loadSettings(gp);
    }
    
    //System
    public void saveSettings(GamePanel gp){
        File file = new File("res/GameFiles/settings.txt");
        
        try(FileWriter fw = new FileWriter(file)){
            fw.write("FPS,"+gp.FPS+",\n");
        } catch (IOException ex) {
            System.out.println("ERROR:[failed to save file: "+file.getName()+"]");
            ex.printStackTrace();
        }
            
        
    }
    public void loadSettings(GamePanel gp){
        File file = new File("res/GameFiles/settings.txt");
        String line;
        
        try(Scanner scan = new Scanner(file)){
            scan.useDelimiter(",");
            
            line = scan.next();
            gp.FPS = scan.nextInt();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR:[failed reading file:"+file.getName()+"]");
            ex.printStackTrace();
        }
    }
    
    //Account
    public void saveAccount(GamePanel gp){
        Player p = gp.getPlayer();
        File file = new File("res/GameFiles/Account.txt");
        
        try(FileWriter fw = new FileWriter(file)){
            
        }catch(IOException ex){
            System.out.println("ERROR:[failed to save file: "+file.getName()+"]");
            ex.printStackTrace();
        }
    }
    public void loadAccount(GamePanel gp){
        
    }
    
    //Map
    public int[][] getRefIntoArray(String filePath, int upperX, int upperY){
        File file = new File(filePath);
        int[][] ref = new int [upperX][upperY];
        
        try(Scanner scan = new Scanner(file)){
            scan.useDelimiter(",");
            
            for(int y = 0; y < upperY; y++){
                for(int x = 0; x < upperX; x++){
                    ref[x][y] = scan.nextInt();
                }
                scan.nextLine();
            }
            
        }catch(IOException e){
            System.out.println("Error:[failed to get ref.image for file: "+file.getName()+"]");
            e.printStackTrace();
        }
        return ref;
    }
    
    public void loadMapLayer_0(){
        Random r = new Random();
        int num = r.nextInt(1)+1;
        System.out.println("choise of map preset: "+num);
        File file = new File ("res/Map/raw_terain_map_"+num+".txt");
        
        try(Scanner scan = new Scanner(file)){
            scan.useDelimiter(",");
            
            for(int y = 0; y < mh.getTerrainMap().length; y++){
                for(int x = 0; x < mh.getTerrainMap().length; x++){
                    mh.getTerrainMap()[x][y].getlayer_0().setobjID(scan.nextInt());
                }
                scan.nextLine();
            }
        }catch(IOException ex){
            System.out.println("ERROR:[failed to save file: ["+file.getName()+"]");
            ex.printStackTrace();
        }
    }
    
    public void saveTerrainMap(Cell[][] map, String filename, int mapWidth, int mapHeight){
        File file = new File("res/Map/"+filename+".txt");
        String line;
        
        try(FileWriter fw = new FileWriter(file)){
            for(int y = 0; y < mapHeight; y++){
                line = "";
                for(int x = 0; x < mapWidth; x++){
                    line = line + map[x][y].getlayer_0().getobjID() + ",";
                }
                fw.write(line + "\n");
            }
            
        }catch(Exception e){
            System.out.println("Error[something is wrong when writting file: ["+file.getName()+"]");
            e.printStackTrace();
        }
    }
    public void loadMapLayer_1(){
        File file = new File("res/Map/final_map_layer_0.txt");
        
        try(Scanner scan = new Scanner(file)){
            scan.useDelimiter(",");
            
            for(int y = 0; y < mh.getMap().length; y++){
                for(int x = 0; x < mh.getMap().length; x++){
                    mh.getMap()[x][y].getlayer_1().setobjID(scan.nextInt());
                }
                scan.nextLine();
            }
            mh.rescaleCoordinates(mh.getMap());
        }catch(IOException ex){
            System.out.println("ERROR:[failed reading file: ["+file.getName()+"]");
            ex.printStackTrace();
        }
    }
}
