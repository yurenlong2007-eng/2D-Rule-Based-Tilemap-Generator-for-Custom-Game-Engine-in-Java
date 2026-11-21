/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package map;

/**
 *
 * @author yurenlong
 */
public class Layer_OBJ extends Layer{
    String objName;
    int sID;//sequenceaid
    
    public void setobjName(String name){
        objName = name;
    }
    public void setSID(int id){
        sID = id;
    }
    
    public String getobjName(){
        return objName;
    }
    public int getSID(){
        return sID;
    }
}
