
package 1942.GameObject;

import 1942.GameEvents;
import 1942.1942;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class MyPlane extends GameObject implements Observer {

    Image image;
    int counter = 0;
    int counter1 = 0;
    Gm1942 vm;
    int sizeX;
    int sizeY;
    ImageObserver obs;
    private int life = 3;

    @Override
    public boolean collision(int x, int y, int w, int h) {
        if (((y + h > this.getY()) && (y < this.getY() + sizeY)) && ((x + w > this.getX()) && (x < this.getX() + sizeX))) {
            return true;
        }
        return false;
    }

    public int getLife() {
        return life;
    }

    public void fire() {
        Bullet x = new Bullet();
        Bullet y = new Bullet();
        Bullet z = new Bullet();
        x.setData(this.getX() + (sizeX / 4), this.getY(), this.getSpeed(), 1, 1);//
        y.setData(this.getX() + (sizeX / 4), this.getY(), this.getSpeed(), 1, 2);
        //change the position for better performance
        z.setData(this.getX() - 2, this.getY() - 5, this.getSpeed(), 1, 3);
        vm.addInP1Bullet(x);
        vm.addInP1Bullet(y);
        vm.addInP1Bullet(z);
    }

    public void draw(Graphics g, ImageObserver obs) {

        if (this.getIsVisible()) {

            g.drawImage(image, this.getX(), this.getY(), obs);
        } else {
            Image explosion = null;
            if (counter1 < 6) {
                counter1++;
                explosion = this.getTable().get("Explosion2_" + counter1);

            } else {
                counter1 = 0;
                this.setVisible(true);
            }
            g.drawImage(explosion, this.getX(), this.getY(), obs);
        }
    }

    @Override
    public void update(Observable obj, Object arg) {


        int x = this.getX();
        int speed = this.getSpeed();
        int y = this.getY();
        GameEvents ge = (GameEvents) arg;
        if (vm.getIsAlive1()) 
        {
            if (ge.getType() == 1) {
                KeyEvent e = (KeyEvent) ge.getEvent();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        System.out.println("P1_Left");
                        x -= speed;
                        if (x < 0) {
                            break;
                        }
                        this.setX(x);
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("P1_Right");
                        x += speed;
                        if (x > 580) {
                            break;
                        }
                        this.setX(x);
                        break;
                    case KeyEvent.VK_UP:
                        System.out.println("P1_up");
                        y -= speed;
                        if (y < 0) {
                            break;
                        }
                        this.setY(y);
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("P1_down");
                        y += speed;
                        if (y > 400) {
                            break;
                        }
                        this.setY(y);
                        break;
                    case KeyEvent.VK_ENTER:
                        System.out.println("P1_Fire");
                        fire();
                        break;

                    default:

                }
            } else if (ge.getType() == 2) {
                String msg = (String) ge.getEvent();
                if (msg.equals("explosionP1")) {

                    System.out.println("P1 Bullet hits Enemy");
                    vm.addScoreP1(10);
                }

            } else if (ge.getType() == 3) {
                String msg = (String) ge.getEvent();
                if (msg.equals("explosionE1") || msg.equals("explosionE3")) {
                    System.out.println("Enemy hits P1");
                    int hp = getHp();
                    hp--;
                    if (hp == 8) {
                        this.life--;
                        this.setVisible(false);
                        vm.getEvent().addExplosionSound(2);
                    }
                    if (hp == 4) {
                        this.life--;
                        this.setVisible(false);
                        vm.getEvent().addExplosionSound(2);
                    }
                    if (hp == 0) {
                        this.life--;
                        this.setVisible(false);
                        vm.getEvent().addExplosionSound(2);
                    }
                    this.setHp(hp);
                    
                     if(msg.equals("explosionE3")){
                     this.vm.addScoreP1(10);
                     }
                    //System.out.println("MY HP is : " + hp);
                }
            }else if (ge.getType() == 4){
                vm.setIsBossDead();
            }
        }
    }

    @Override
    public void update(Gm1942 vm) {
        int x = getX();
        int y = getY();
        this.vm = vm;
        if (counter < 3) {
            counter++;
            image = this.getTable().get("Myplane" + counter);
        } else {
            counter = 0;
        }
        sizeX = image.getWidth(null);
        sizeY = image.getHeight(null);
    }
}
