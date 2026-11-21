/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weapen_object;

import entity.ImageReader;
import main.GamePanel;

/**
 *
 * @author yurenlong
 */
public class Sord extends Weapon{
    
    ImageReader ir = new ImageReader();
    
    public Sord(GamePanel gp) {
        super(gp);
        
        name = "Sord";
        setDimension(1,2);
        imageset = ir.getImage("/Weapons/sord.png", gp.displayScaleFactor);
        setHitVertices(24,16,42,12,42,19,63,16);
        
        gp.allWeapons.add(this);
    }
    
    
}
