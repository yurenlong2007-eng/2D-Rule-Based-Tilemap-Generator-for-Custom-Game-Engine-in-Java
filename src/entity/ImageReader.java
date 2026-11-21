/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Utility;

/**
 *
 * @author yurenlong
 */
public class ImageReader {
    public BufferedImage getImage(String filePath, int sf){
        Utility u = new Utility();
        
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
            image = u.scaleImage(image, sf);
        }catch(IOException e){
            System.out.println("Faild to laod file: " + filePath);
        }
        
        return image;
    }
    
    public BufferedImage getTile(BufferedImage spriteSheet, int tilesetWidth, int tileID, int blockWdith, int blockHeight){
        Utility u = new Utility();
        
        int xPosition = tileID % tilesetWidth;
        int yPosition = tileID / tilesetWidth;
        
        return u.seperateSpriteSheet(spriteSheet, xPosition, yPosition, blockWdith, blockHeight);
        
    }
    
    public int getNumberOfHorizontalParts(BufferedImage tileset, int width){
        return tileset.getWidth() / width;
    }
    public int getNumberOfVerticalParts(BufferedImage tileset, int height){
        return tileset.getHeight() / height;
    }
}
