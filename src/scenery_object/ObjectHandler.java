/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scenery_object;

import java.util.ArrayList;
import main.GamePanel;
import main.RenderObject;

/**
 *
 * @author yurenlong
 */
public class ObjectHandler {
    GamePanel gp;
    
    public TestTree tt;
    public Tree RedTree;
    
    ArrayList<Integer> objIDList = new ArrayList();
    SceneryObject sobj = new SceneryObject(gp);
    
    public ObjectHandler(GamePanel gp){
        this.gp = gp;
        
        tt = new TestTree(gp);
        RedTree = new Tree(gp);
    }
    
    public void addToRender(){
        objIDList.clear();
        int sx = gp.p.getCameraWorldX() / gp.blockSize;
        int sy = gp.p.getCameraWorldY() / gp.blockSize;
        int blockID, worldX, worldY;
        
        for(int x = sx; x < sx + gp.viewWidth + 1; x++){
            for(int y = sy; y < sy + gp.viewHeight + 1; y++){
                if(x >= 0 && x < gp.getMapHandler().map.length && y >=0 && y < gp.getMapHandler().map.length){// within the map
                    if(gp.getMapHandler().map[x][y].getlayer_2().getobjName() != null){// if this block on the map[][] is not empty
                        blockID = gp.getMapHandler().map[x][y].getlayer_2().getobjID();
                        
                        if(!objIDList.contains(blockID)){// list doesn't contain this obj
                            objIDList.add(blockID);

                            sobj = getObj(gp.getMapHandler().map[x][y].getlayer_2().getobjName());
                            worldX = x * gp.blockSize - sobj.getX_PixelOffset(gp.getMapHandler().map[x][y].getlayer_2().getSID());
                            worldY = y * gp.blockSize - sobj.getY_PixelOffset(gp.getMapHandler().map[x][y].getlayer_2().getSID());

                            gp.renderList.add(new RenderObject(worldX, worldY, sobj.getBaseOffset(), sobj.tileSet));//may use animation later
                        }
                    }
                }
            }
        }
    }
    
    public SceneryObject getObj(String name){
        for(int i = 0; i < gp.allSceneryObjects.size(); i++){
            if(gp.allSceneryObjects.get(i).name.equals(name)){
                return gp.allSceneryObjects.get(i);
            }
        }
        System.out.println("Error in finding this scenery obj: "+name);
        return null;
    }
    
}
