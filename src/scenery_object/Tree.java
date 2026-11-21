/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scenery_object;

import entity.ImageReader;
import main.GamePanel;

/**
 *
 * @author yurenlong
 */
public class Tree extends SceneryObject{
    
    ImageReader ir = new ImageReader();
    
    public Tree(GamePanel gp) {
        super(gp);
        
        name = "RedTree";
        setDimension(6,6);
        setBaseOffset(192);
        
        tileSet = ir.getImage("/Objects/tree.png", gp.displayScaleFactor);
        
        gp.allSceneryObjects.add(this);
    }
    
}
