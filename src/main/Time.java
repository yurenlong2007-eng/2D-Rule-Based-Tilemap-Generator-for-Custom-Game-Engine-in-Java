/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author yurenlong
 */
public class Time {
    GamePanel gp;
    
    private int currentFrame = 0;
    private int FPS12Counter = 0;
    
    public Time(GamePanel gp){
        this.gp = gp;
    }
    
    public void updateFrame(){
        if(currentFrame < gp.FPS){
            currentFrame ++;
        }else{
            currentFrame = 0;
        }
        try{
            if(currentFrame % (gp.FPS/12) == 0){
                FPS12Counter++;
                if(FPS12Counter >= 12){
                    FPS12Counter = 0;
                }
            }
        }catch(Exception e){
            System.out.println("Error[gp.FPS < 12, can't use FPS12Counter.]");
        }
    }
    
    public int getCurrentFrame(){
        return currentFrame;
    }
    public int getFPS12Counter(){
        return FPS12Counter;
    }
}
