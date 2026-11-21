/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weapen_object;

import java.util.ArrayList;
import main.GamePanel;

/**
 *
 * @author yurenlong
 */
public class WeaponHandler {
    GamePanel gp;
    
    Sord sord;
    
    public WeaponHandler(GamePanel gp){
        this.gp = gp;
        sord = new Sord(gp);
    }
    
    public Weapon getWeapon(String name){
        for(int i = 0; i < gp.allWeapons.size(); i++){
            if(gp.allWeapons.get(i).name.equals(name)){
                return gp.allWeapons.get(i);
            }
        }
        System.out.println("ERROR: cannot find '"+name+"'");
        return null;
    }
}
