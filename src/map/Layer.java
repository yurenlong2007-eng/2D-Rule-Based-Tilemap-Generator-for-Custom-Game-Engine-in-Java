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
public class Layer {
    int objID;//uniquely identify obj within the map int layer_1, or use as tileID in layer_0
    Rectangle solidArea = new Rectangle();
    
    public void setobjID(int id){
        objID = id;
    }
    
    public int getobjID(){
        return objID;
    }
}
