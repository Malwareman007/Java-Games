/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package 1942.GameObject;

import 1942.1942;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;


public class EnemyPlane extends GameObject {

    int counter = 0;
    Image img;
    Gm1942 vm;
    int sizeX;
    int sizeY;
    boolean check1 = true;
    boolean check2 = false;
    //boolean explosion;

    @Override
    public boolean collision(int x, int y, int w, int h) {
        if (((y + h > this.getY()) && (y < this.getY() + sizeY)) && ((x + w > this.getX()) && (x < this.getX() + sizeX))) {
            this.setVisible(false);
            return true;
        }
        return false;
    }

    public void fire(int type) {
        if (type == 1) {
            Bullet x = new Bullet();
            x.setData(this.getX(), this.getY(), 3, 1, 4);//
            vm.addInEnemyBullet(x);
        }
        if (type == 2) {
            Bullet x = new Bullet();
            x.setData(this.getX(), this.getY(), 3, 1, 4);//
            vm.addInEnemyBullet(x);
        }
        if (type == 3) {
            Bullet x = new Bullet();
            Bullet y = new Bullet();
            Bullet z = new Bullet();
            x.setData(this.getX(), this.getY(), 4, 1, 4);//
            y.setData(this.getX(), this.getY(), 4, 1, 5);
            z.setData(this.getX(), this.getY(), 4, 1, 6);
            vm.addInEnemyBullet(x);
            vm.addInEnemyBullet(y);
            vm.addInEnemyBullet(z);
        }
        if (type == 5) {
            Bullet x = new Bullet();
            Bullet y = new Bullet();
            Bullet z = new Bullet();
            x.setData(this.getX(), this.getY(), 5, 1, 7);//
            y.setData(this.getX(), this.getY(), 5, 1, 8);
            z.setData(this.getX(), this.getY(), 5, 1, 9);
            vm.addInEnemyBullet(x);
            vm.addInEnemyBullet(y);
            vm.addInEnemyBullet(z);

        }
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {

        if (this.getIsVisible()) {

            g.drawImage(img, this.getX(), this.getY(), obs);
            if (counter < 3) {
                counter++;
                img = this.getTable().get("Enemy" + this.getType() + "_" + 2);
            } else if (counter < 6) {
                counter++;
                img = this.getTable().get("Enemy" + this.getType() + "_" + 3);
            } else {
                counter = 0;
            }
            g.drawImage(img, this.getX(), this.getY(), obs);
        } else {
            for (int i = 1; i < 7; i++) {
                Image explosion = this.getTable().get("Explosion1_" + i);
                g.drawImage(explosion, this.getX(), this.getY(), obs);
            }
        }


    }

    public void update(Gm1942 vm) {
        img = this.getTable().get("Enemy" + this.getType() + "_" + 1); //
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        this.vm = vm;
        int x = getX();
        int y = getY();
        int speed = getSpeed();
        int type = this.getType();
        if (type < 4) {
            y += speed;
            if (y > 480) {
                this.setVisible(false);
            } else {
                setY(y);
            }
            if (y == 10) {
                if (type == 1) {
                    fire(1);
                }
                if (type == 2) {
                    fire(2);
                }
                if (type == 3) {
                    fire(3);
                }

            }
        } else if (type == 4) {
            y -= speed;
            if (y < -40) {
                this.setVisible(false);
            } else {
                setY(y);
            }
        } else if (type == 5) {
            if (y != 50) {
                y += speed;
            } else if (x != 30 && check1) {
                x -= speed;
                //check1=false;
            } else if (check1) {
                check1 = false;
                check2 = true;
            }
            if (x != 400 && check2) {
                x += speed;
            } else if (check2) {
                check1 = true;
                check2 = false;
            }
            if(x==100 || x ==300){
                fire(5);
            }
            setY(y);
            setX(x);
        }
        if (type != 5) {
            ArrayList<GameObject> list = vm.getMyPlane();
            if (vm.getIsAlive1()) {
                if (list.get(0).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    vm.getEvent().setValue("explosionE3");
                }
            }
            if (vm.getIsAlive2()) {
                if (list.get(1).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    vm.getEvent().setValue("explosionE4");
                }
            }
            ArrayList<GameObject> P1BulletList = vm.getP1BulletList();
            for (int i = 0; i < P1BulletList.size(); i++) {
                if (P1BulletList.get(i).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    P1BulletList.get(i).setVisible(false);
                    //explode=true;
                    //score += 10;
                    vm.getEvent().setValue("explosionP1");
                }
            }
            ArrayList<GameObject> P2BulletList = vm.getP2BulletList();
            for (int i = 0; i < P2BulletList.size(); i++) {
                if (P2BulletList.get(i).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    P2BulletList.get(i).setVisible(false);
                    vm.getEvent().setValue("explosionP2");
                }
            }
        } else {
            ArrayList<GameObject> P1BulletList = vm.getP1BulletList();
            for (int i = 0; i < P1BulletList.size(); i++) {
                if (P1BulletList.get(i).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    P1BulletList.get(i).setVisible(false);
                    //explode=true;
                    //score += 10;
                    vm.getEvent().setValue("WIN");
                }
            }
            ArrayList<GameObject> P2BulletList = vm.getP2BulletList();
            for (int i = 0; i < P2BulletList.size(); i++) {
                if (P2BulletList.get(i).collision(x, y, sizeX, sizeY)) {
                    this.setVisible(false);
                    P2BulletList.get(i).setVisible(false);
                    vm.getEvent().setValue("WIN");
                }
            }

        }
    }
}
