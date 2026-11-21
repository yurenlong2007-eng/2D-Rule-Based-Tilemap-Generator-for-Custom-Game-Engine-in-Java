/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scenery_object;

import main.GamePanel;
import map.Cell;

/**
 *
 * @author yurenlong
 */
public class TestTree extends SceneryObject{
    
    public TestTree(GamePanel gp){
        super(gp);
        
//        this.setName("TestTree");
        this.setDimension(2, 2);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 2 * gp.blockSize;
        solidArea.height = 2 * gp.blockSize;
    }
    
//   public int getOffSet(int ID){
//        switch(ID){
//            //here do obj specific mapping
//            //
//        }
//        
//    }
    
//    public void place(Cell[][] map, int currentX, int currentY){
//        int secondaryID = 0;
//
//        if(currentY + height_block < map.length && currentX + width_block < map.length){
//            for(int y = currentY; y < currentY + height_block; y++){
//                for(int x = currentX; x < currentX + width_block; x++){
//                    map[x][y].setName("TestTree");
//                    map[x][y].setSecondaryID(secondaryID);
//                    //base on the shape of the object the solidArea needs to be planed
//                    secondaryID++;
//                    
//                }
//            }
//        }
//    }
    
}
