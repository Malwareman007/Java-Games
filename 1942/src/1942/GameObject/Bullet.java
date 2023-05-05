
package 1942.GameObject;

import 1942.Gm1942;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Bullet extends GameObject { 
    Image img;
    int bulletType; 
    int sizeX;
    int sizeY;
    Gm1942 vm;
    Bullet(){ 
    }
    public void setBulletType(int type){
        bulletType=type;
    }
    @Override
    public boolean collision(int x, int y, int w, int h) {  
        if(((y+h > this.getY())&&(y < this.getY()+sizeY))&&((x+w>this.getX())&&(x<this.getX()+sizeX))) 
            { 
                return true;
            }
            return false;
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {

        g.drawImage(img, this.getX(), this.getY(), obs);
    }
    @Override
    public void update(Gm1942 vm) {
        this.vm=vm;
        int type=this.getType();
        if(type == 1 || type ==2 || type== 3){
            img=this.getTable().get("Bullet_"+type); // for different direction of bullets
        }
        if(type == 4 || type == 5 || type == 6){
            img=this.getTable().get("EnemyBullet_"+1); // for enemy bullet
        }
        if(type == 7 || type == 8 || type == 9){
            img=this.getTable().get("EnemyBullet_"+3); // for enemy bullet 
        }
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        int y=this.getY();
        int x=this.getX();
        int speed =this.getSpeed();
        // for different direction of users' bullets
        //mid
        if(type == 1){
            y-=speed;
            if(y<0){
               this.setVisible(false);
            }
        }
        // Left
        if(type == 2){
            y-=speed;
            x-=speed;
            if(y<0){
               this.setVisible(false);
            }
        }
        // Right 
        if(type == 3){
            y-=speed;
            x+=speed;
            if(y<0){
               this.setVisible(false);
            }
        }
        // for different direction of small bullets(enemy)
        //mid
        if(type == 4){
            y+=speed;
            if(y>440){
                this.setVisible(false);
            }
            checkCollision(x, y, sizeX, sizeY);
        }
        // Left
        if(type == 5){
            y+=speed;
            x-=speed;
            if(y>440){
                this.setVisible(false);
            }
            checkCollision(x, y, sizeX, sizeY);
        }
        // Right
        if(type == 6){
            y+=speed;
            x+=speed;
            if(y>440){
                this.setVisible(false);
            }
            checkCollision(x, y, sizeX, sizeY);
        }
        // for different direction of big bullets(enemy)
        //mid
        if(type == 7){
            y+=speed;
            if(y>440){
                this.setVisible(false);
            }
            checkCollision(x, y, sizeX, sizeY);
        }
        // Left
        if(type == 8){
            y+=speed;
            x-=speed;
            if(y>440){
                this.setVisible(false);
            }
            checkCollision(x, y, sizeX, sizeY);
        }
        // Right
        if(type == 9){
            y+=speed;
            x+=speed;
            if(y>440){
                this.setVisible(false);
            }
            checkCollision(x, y, sizeX, sizeY);
        }
        this.setY(y); 
        this.setX(x);
        

        
    }
    public void checkCollision(int x, int y, int sizeX, int sizeY){
        ArrayList<GameObject> list1 = vm.getMyPlane();
            if (vm.getIsAlive1()) {
                if (list1.get(0).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    vm.getEvent().setValue("explosionE1");
                }
            }
            if (vm.getIsAlive2()) {
                if (list1.get(1).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    vm.getEvent().setValue("explosionE2");
                }
            }
    }
    
}
