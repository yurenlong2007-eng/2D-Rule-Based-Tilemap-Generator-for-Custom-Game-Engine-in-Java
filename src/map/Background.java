/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package map;

import entity.ImageReader;
import entity.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.FileHandler;
import main.GamePanel;

/**
 *
 * @author yurenlong
 */
public class Background{
    ImageReader ir = new ImageReader();
    GamePanel gp;
    
    int numberOfFrames = 1;
    BufferedImage[] tileSet = new BufferedImage[numberOfFrames];
    int[][] ruleRef;
    ArrayList<Integer>[][] rules;
    int tileSetSize;
    
    Tile[] tile;
    int tileSetWidth, tileSetHeight;
    
    BufferedImage[] staticMapImage = new BufferedImage[numberOfFrames];//to animate this, make it into ab array, and render each frame
    
    public Background(GamePanel gp, TerainGeneration tg, FileHandler fh){
        super();
        this.gp = gp;
        
        
        for(int i = 0; i < numberOfFrames; i++){
            tileSet[i] = ir.getImage("/Map/travel_tileset_"+i+"_version_27.png", gp.displayScaleFactor);
        }
        tileSetWidth = ir.getNumberOfHorizontalParts(tileSet[0], gp.blockSize);
        tileSetHeight = ir.getNumberOfVerticalParts(tileSet[0], gp.blockSize);
        tileSetSize = tileSetWidth * tileSetHeight;
        tile = new Tile[tileSetSize * numberOfFrames];
        loadTileImage(tile);
        
        rules = new ArrayList[tileSetSize][4];
        rules = tg.initialiseRules(rules, tileSetSize);
        
        ruleRef = fh.getRefIntoArray("res/Map/rules/rules1_row1.csv", 6, 6);
        rules = tg.generateRules(rules, ruleRef);
        ruleRef = fh.getRefIntoArray("res/Map/rules/rules2_row1.csv", 5, 5);
        rules = tg.generateRules(rules, ruleRef);
        ruleRef = fh.getRefIntoArray("res/Map/rules/rules3_row1.csv", 7, 7);
        rules = tg.generateRules(rules, ruleRef);
        ruleRef = fh.getRefIntoArray("res/Map/rules/rules4_row1.csv", 6, 6);
        rules = tg.generateRules(rules, ruleRef);
        ruleRef = fh.getRefIntoArray("res/Map/rules/rules5_row1.csv", 7, 7);
        rules = tg.generateRules(rules, ruleRef);
        ruleRef = fh.getRefIntoArray("res/Map/rules/rules6_row1.csv", 7, 7);
        rules = tg.generateRules(rules, ruleRef);
    }
    
    public void loadTileImage(Tile[] tile){
        int currentTile;
        
        for(int f = 0; f < numberOfFrames; f++){
            for(int i = 0; i < tileSetSize; i++){
                currentTile = i + f * tileSetSize;
                tile[currentTile] = new Tile();
                tile[currentTile].image = ir.getTile(tileSet[f], tileSetWidth, i, gp.blockSize, gp.blockSize);
            }
        }
        
    }
    
    public void convertTerainToBackground(Cell[][] map){
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                switch(map[x][y].layer_0.getobjID()){
                    case 0:
                    case 1:
                    case 2:
                        map[x][y].layer_1.setobjID(13);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        map[x][y].layer_1.setobjID(0);
                        break;
                }
            }
        }
    }
    
    public void setUpStaticMap(Cell[][] map){
        for(int f = 0; f < numberOfFrames; f++){
            int offset = f * tileSetSize;
            
            staticMapImage[f] = new BufferedImage(map.length * gp.blockSize, map.length * gp.blockSize, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)staticMapImage[f].getGraphics();
            
            for(int y = 0; y < map.length; y++){
                for(int x = 0; x < map.length; x++){
                    g2.drawImage(tile[map[x][y].layer_1.getobjID() + offset].image, map[x][y].x, map[x][y].y, null);
                }
            }
            g2.dispose();
        }
    }
    
    public void drawStaticBGMap(Graphics2D g2, Player p) {
        // Coordinate variables for readability
        int sx1 = p.getCameraWorldX(); 
        int sy1 = p.getCameraWorldY();
        int sx2 = sx1 + gp.displayWidth;
        int sy2 = sy1 + gp.displayHeight;

        // Use the 10-parameter drawImage method
        // g2.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
        g2.drawImage(staticMapImage[0], //use get frame from gp and use appropriate frame static map here
            0, 0, gp.displayWidth, gp.displayHeight, // Destination (Screen)
            sx1, sy1, sx2, sy2,                     // Source (Big Map)
            null                                    // ImageObserver
        );
    }
}
