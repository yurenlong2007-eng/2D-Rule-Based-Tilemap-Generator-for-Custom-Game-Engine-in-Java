/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author yurenlong
 */
public class Utility {
    public BufferedImage scaleImage(BufferedImage originalImage, int sf){
        int scaledWidth = sf * originalImage.getWidth();
        int scaledHeight = sf * originalImage.getHeight();
        
        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2.dispose();
        
        return scaledImage;
    }
    
    public BufferedImage seperateSpriteSheet(BufferedImage scaledSpriteSheet, int xPositionOnSheet, int yPositionOnSheet, int width, int height){
        int x = xPositionOnSheet * width;
        int y = yPositionOnSheet * height;
        
        BufferedImage image = scaledSpriteSheet.getSubimage(x, y, width, height);
        return image;
    }
}
