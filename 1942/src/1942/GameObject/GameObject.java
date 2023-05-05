/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package 1942.GameObject;
import 1942.1942;
import 1942.ImageTable;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;


public abstract class GameObject {
    private static ArrayList<GameObject> list=new ArrayList<GameObject>();
    private int x,y, speed;
    private int hp;
    private ImageTable table=new ImageTable();
    private int type;
    private boolean isVisible;
    public GameObject(){
    }
    public void setData(int x,int y,int speed, int hp, int type){
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.hp=hp;
        this.type=type;
        table.init();
        isVisible=true;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSpeed(){
        return speed;
        
    }
    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp=hp;
    }
    public int getType(){
        return type;
    }

    public void setVisible(boolean isVisible){
        this.isVisible=isVisible;
    }
    public boolean getIsVisible(){
        return isVisible;
    }
    public ImageTable getTable(){
        return table;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public ArrayList<GameObject> getList(){
        return this.list;  
    }
    public void addInList(GameObject x){
        this.list.add(x);
    }
    public int getSize(){
        return this.list.size();
    }
    public abstract boolean collision(int x, int y, int w, int h);
    public abstract void draw(Graphics g, ImageObserver obs);
    public abstract void update(Gm1942 vm);
    
    
}
