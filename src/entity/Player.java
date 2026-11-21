/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import main.Time;
import weapen_object.Weapon;
import weapen_object.WeaponHandler;

/**
 *
 * @author yurenlong
 */
public class Player extends Entity{
    int printX, printY;
    int cameraWorldX, cameraWorldY, camera_offset_x, camera_offset_y;
    
    int x, y;
    boolean diagonal;
    
    WeaponHandler wh = gp.getWeaponHandler();
    public Weapon weapon = wh.getWeapon("Sord");
    String aim_direction;
    int vx, vy;
    public double aim_angle, aim_angle_target;
    
    public boolean wPressed, sPressed, aPressed, dPressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean spacePressed;
    public boolean k_1Pressed;
    
    KeyHandler k;
    MouseHandler m;
    
    public Player(GamePanel gp, KeyHandler k, MouseHandler m, Time t){
        super(gp,t);
        this.k = k;
        this.m = m;
        
        setUpPlayer();
    }
    public void setUpPlayer(){
        setUpStats();
        setUpSpriteSheet("/Player/p_sprite_sheet_0.png");
        setUpCoordinates();
    }
    public void setUpStats(){
        setDimension(2,2);
        
        direction = "null";
        speed = gp.displayScaleFactor * 2;
        diagonalSpeed = (int)Math.sqrt(Math.pow(speed,2)/2);
    }
    public void setUpCoordinates(){
        printX = (gp.displayWidth/2) - (width_pixel/2);
        printY = (gp.displayHeight/2) - (height_pixel/2);
        
        worldX = 0;
        worldY = 0;
        worldZ = 0;
        
        solidArea.x = worldX;
        solidArea.y = worldY;
        solidArea.width = width_pixel;
        solidArea.height = height_pixel;
        
        cameraWorldX = worldX - printX;
        cameraWorldY = worldY - printY;
        camera_offset_x = printX;
        camera_offset_y = printY;
    }
    
    public void updateKey(){
        wPressed = k.getwPressed();
        sPressed = k.getsPressed();
        aPressed = k.getaPressed();
        dPressed = k.getdPressed();
        upPressed = k.getupPressed();
        downPressed = k.getdownPressed();
        leftPressed = k.getleftPressed();
        rightPressed = k.getrightPressed();
        spacePressed = k.getspacePressed();
        k_1Pressed = k.getk_1Pressed();
    }
    
    public void findDirectionVector(){
        if(wPressed){y--;}
        if(sPressed){y++;}
        if(aPressed){x--;}
        if(dPressed){x++;}
        if(x == -1 && y == -1){diagonal = true;}
        if(x == 1 && y == -1){diagonal = true;}
        if(x == -1 && y == 1){diagonal = true;}
        if(x == 1 && y == 1){diagonal = true;}
    }
    public void findDirection(){
        direction = "null";
        if(y == -1){direction = "W";}
        if(y == 1){direction = "S";}
        if(x == -1){direction = "A";}
        if(x == 1){direction = "D";}
        if(x == -1 && y == -1){diagonal = true;direction = "WA";}
        if(x == 1 && y == -1){diagonal = true;direction = "WD";}
        if(x == -1 && y == 1){diagonal = true;direction = "SA";}
        if(x == 1 && y == 1){diagonal = true;direction = "SD";}
    }
    public void findAim_directionVector_key(){
        if(upPressed){vy--;}
        if(downPressed){vy++;}
        if(leftPressed){vx--;}
        if(rightPressed){vx++;}
    }
    public void findAim_direction_key(){
        aim_direction = "null";
        if(vy == -1){aim_direction = "Up";aim_angle_target = Math.PI/2;}
        if(vy == 1){aim_direction = "Down";aim_angle_target = -Math.PI/2;}
        if(vx == -1){aim_direction = "Left";aim_angle_target = Math.PI;}
        if(vx == 1){aim_direction = "Right";aim_angle_target = 0;}
        if(vy == -1 && vx == -1){aim_direction = "UpLeft";aim_angle_target = Math.PI * 3/4;}
        if(vy == -1 && vx == 1){aim_direction = "UpRight";aim_angle_target = Math.PI * 1/4;}
        if(vy == 1 && vx == -1){aim_direction = "DownLeft";aim_angle_target = -Math.PI * 3/4;}
        if(vy == 1 && vx == 1){aim_direction = "DownRight";aim_angle_target = -Math.PI * 1/4;}
    }
    public void findAim_angle_mouse(){
        double dx = m.getX() - (printX + width_pixel/2);
        double dy = m.getY() - (printY + height_pixel/2);
        aim_angle = -Math.atan2(dy, dx);
    }
    public void updateWorldPosition(){
        x = 0;
        y = 0;
        diagonal = false;
        
        findDirectionVector();
        findDirection();
        
        if(diagonal){
            worldX += x * diagonalSpeed;
            worldY += y * diagonalSpeed;
            solidArea.x += x * diagonalSpeed;
            solidArea.y += y * diagonalSpeed;
        }else{
            worldX += x * speed;
            worldY += y * speed;
            solidArea.x += x * speed;
            solidArea.y += y * speed;
        }
    }
    public void updateAim(){
        vx = 0;
        vy = 0;
        
        findAim_directionVector_key();
        findAim_direction_key();
        findAim_angle_mouse();
    }
    
    public void repositionCamera(){
        double delta = 0.2;
        int diffX = (worldX - camera_offset_x) - cameraWorldX;
        int diffY = (worldY - camera_offset_y) - cameraWorldY;

        if(Math.abs(diffX) > 0){
            int dx = (int)(diffX * delta);
            if(Math.abs(dx) > 0){
                cameraWorldX += dx;
            }
        }
        if(Math.abs(diffY) > 0){
            int dy = (int)(diffY * delta);
            if(Math.abs(dy) > 0){
                cameraWorldY += dy;
            }
        }
    }
    
    public void updatePrintPosition(){
        printX = worldX - cameraWorldX;
        printY = worldY - cameraWorldY;
    }
    
    public void update(){
        updateKey();
        updateWorldPosition();
        updateAim();
        repositionCamera();
        updatePrintPosition();
    }
    
    public void draw(Graphics2D g2){
        g2.drawRect(printX, printY, width_pixel, height_pixel);
        draw(g2, direction, printX, printY);
    }
    
    public int getCameraWorldX(){
        return cameraWorldX;
    }
    public int getCameraWorldY(){
        return cameraWorldY;
    }
    public int getPrintX(){
        return printX;
    }
    public int getPrintY(){
        return printY;
    }
}
