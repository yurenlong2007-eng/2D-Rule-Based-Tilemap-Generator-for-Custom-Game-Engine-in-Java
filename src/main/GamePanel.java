/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import map.MapHandler;
import scenery_object.ObjectHandler;
import scenery_object.SceneryObject;
import weapen_object.Weapon;
import weapen_object.WeaponHandler;

/**
 *
 * @author yurenlong
 */
public class GamePanel extends Canvas implements Runnable{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//computer display size
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    
    public final int viewWidth = 14;
    public final int viewHeight = 9;
    public final int originalBlockSize = 32;
    public int blockSize;
    public int displayWidth, displayHeight;
    public int displayScaleFactor;
    
    public int gameState = 0;
    public int mapCommand = 0;
    
    Thread gameThread;
    public int FPS = 120;
        
    public ArrayList<SceneryObject> allSceneryObjects = new ArrayList();
    public ArrayList<RenderObject> renderList = new ArrayList();
    public ArrayList<Weapon> allWeapons = new ArrayList();
    
    KeyHandler k;
    MouseHandler m;
    Time t;
    public Player p;
    MapHandler mapH;
    ObjectHandler objH;
    WeaponHandler wh;
    FileHandler fh;
    
    public GamePanel(){
        setUpGamePanel();
        this.setPreferredSize(new Dimension(displayWidth, displayHeight));
        this.setIgnoreRepaint(true);
        this.setFocusable(true);
        Color backgroundColor = new Color(0, 0, 0);
        this.setBackground(backgroundColor);
        
        this.addKeyListener(k);
        this.addMouseMotionListener(m);
        this.addMouseListener(m);
        
//        debugWindow();
    }
    
    public void setUpGamePanel(){
        //find suitable scale factor
        if(screenWidth/viewWidth < screenHeight/viewHeight){
            displayScaleFactor = (screenWidth)/(viewWidth * originalBlockSize);
        }else{
            displayScaleFactor = (screenHeight)/(viewHeight * originalBlockSize);
        }
        blockSize = originalBlockSize * displayScaleFactor;
        displayWidth = viewWidth * blockSize;
        displayHeight = viewHeight * blockSize;
        
        //initialise components
        k = new KeyHandler();
        m = new MouseHandler();
        t = new Time(this);
        objH = new ObjectHandler(this);
        wh = new WeaponHandler(this);
        p = new Player(this, k, m, t);
        mapH = new MapHandler(this,m,k,p);
        fh = new FileHandler(this, mapH);
        
//        fh.backUp();
//        fh.load();
        
    }
    
    public void debugWindow(){
        System.out.println("-GamePanel-");
        System.out.println("blockSize: "+blockSize+" pixels on screen");
        System.out.println("display dimension: "+displayWidth+"(w) x "+displayHeight+"(h)");
        System.out.println("displayScaleFactor: "+displayScaleFactor);
        System.out.println();
    }
    
    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }
        
    @Override
    public void run() {

        final double UPDATE_RATE = FPS;
        final double UPDATE_INTERVAL = 1_000_000_000.0 / UPDATE_RATE;
        long lastTime = System.nanoTime();
        long now;
        double accumulator = 0;

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        ///////* Main Loop *//////
        while (gameThread != null) {

            now = System.nanoTime();
            accumulator += (now - lastTime);
            lastTime = now;
            
            while (accumulator >= UPDATE_INTERVAL) {
                update();
                accumulator -= UPDATE_INTERVAL;
            }
            
            render(bs);
            
//            System.out.println(heighest/1000_000);
//            System.out.println(deltaTime/1000_000+"ms");
        }
    }

    
    public void update(){
        t.updateFrame();
        switch(gameState){
            case 0:
                mapH.generate();
                gameState++;
            case 1:
//                if(p.spacePressed){
//                    gameState = 0;//regenerate map
//                }
                p.update();
                break;
            default:
                break;
        }
    }
    public void render(BufferStrategy bs){
        Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
        g2.clearRect(0, 0, getWidth(), getHeight());
        
        switch(gameState){
            case 0:
                mapH.draw(g2);
                break;
            case 1:
                mapH.draw(g2);
                
                renderList.clear();
                objH.addToRender();
                p.draw(g2);//add player into the list only
                
                //Sort out by their y
                Collections.sort(renderList, new Comparator<RenderObject>(){
                    @Override
                    public int compare(RenderObject obj1, RenderObject obj2){
                        int result = Integer.compare(obj1.y + obj1.getBaseOffset(), obj2.y + obj2.getBaseOffset());
                        return result;
                    }
                });
                
                drawRenderList(g2);
                p.weapon.draw(g2, p.aim_angle);
                break;
        }
        temperaryDrawStats(g2);
//        
        g2.dispose();
        bs.show();
    }
    public void drawRenderList(Graphics2D g2){
        int printX, printY;
        for(int i = 0; i < renderList.size(); i++){
            printX = renderList.get(i).x - p.getCameraWorldX();
            printY = renderList.get(i).y - p.getCameraWorldY();
            
            g2.drawImage(renderList.get(i).image, printX, printY, null);
        }
    }
    
    public void temperaryDrawStats(Graphics2D g2){
        g2.drawRect(0, 0, blockSize, blockSize);
        
        String text;
        g2.setColor(Color.white);
        text = "p. world coordinates: (x: "+p.getWorldX()/blockSize+", y: "+p.getWorldY()/blockSize+", z: "+p.getWorldZ()+")";
        g2.drawString(text,4,blockSize * 3/2);
        text = "c. world coordinates: (x: "+p.getCameraWorldX()/blockSize+", y: "+p.getCameraWorldY()/blockSize+")";
        g2.drawString(text,4,blockSize * 4/2);
        text = "print coordinates: (x: "+p.getPrintX()/blockSize+", y: "+p.getPrintY()/blockSize+")";
        g2.drawString(text, 4, blockSize * 5/2);
        text = "mouse coordinates: (x: "+m.getX()+", y: "+m.getY()+")";
        g2.drawString(text, 4, blockSize * 9/2);
        g2.drawString("map conflicts: "+mapH.tg.getTotalConflict(), 4, blockSize * 10/2);
        g2.drawString("mousePressed: "+m.mousePressed, 4, blockSize * 11/2);
        g2.drawString("direction: "+ p.getDirection(), 4, blockSize * 12/2);
        
//        g2.drawString("motion state: "+p.getMotionState(), 10 * blockSize, blockSize * 3/2);
//        g2.drawString("recieve motion state: "+p.getRecieveMotionState(), 10 * blockSize, blockSize * 4/2);
        g2.drawString("Time: "+System.nanoTime()/1000_000_000, 4, blockSize * 6/2);
        g2.drawString("frame: "+t.getCurrentFrame(), 4, blockSize * 7/2);
//        g2.drawString("frame time: "+deltaTime + "ns", 4, blockSize * 8/2);
        g2.drawString("game state: "+gameState, 10 * blockSize, blockSize * 8/2);
        
        int keySize = 16;
        
        if(k.getwPressed()){
            g2.drawString("[W]", 4 + (keySize * 3), (keySize * 2));
        }
        if(k.getaPressed()){
            g2.drawString("[A]", 4 + (keySize * 2), keySize * (3));
        }
        if(k.getsPressed()){
            g2.drawString("[S]", 4 + (keySize * 3), keySize * (3));
        }
        if(k.getdPressed()){
            g2.drawString("[D]", 4 + (keySize * 4), keySize * (3));
        }
        if(k.getleftPressed()){
            g2.drawString("[<]", 4 + (keySize * 8), (keySize * 3));
        }
        if(k.getupPressed()){
            g2.drawString("[^]", 4 + (keySize * 9), (keySize * 2));
        }
        if(k.getrightPressed()){
            g2.drawString("[>]", 4 + (keySize * 10), (keySize * 3));
        }
        if(k.getdownPressed()){
            g2.drawString("[⌄]", 4 + (keySize * 9), (keySize * 3));
        }
        if(k.getspacePressed()){
            g2.drawString("[SPACE]", 4 + (keySize * 5), (keySize * 5));
        }
    }
    
    public Player getPlayer(){
        return p;
    }
    public MapHandler getMapHandler(){
        return mapH;
    }
    public WeaponHandler getWeaponHandler(){
        return wh;
    }
}
