/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gm1942;

import gm1942.GameObject.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/*
 * Gm1942:
 * P1:  VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT  FIRE: VK_ENTER
 * P2:  VK_W, VK_S, VK_A, VK_D   FIRE: VK_SPACE        
 *                
 */
public class Gm1942 extends JApplet implements Runnable {

    private int gamescoreP1 = 0;
    private int gamescoreP2 = 0;
    private boolean isGameover;
    private boolean isBossDead;
    private int timeline = 0;
    private Random generator = new Random(123456789);
    private Backgroud backG;
    private Thread thread;
    private GameEvents gameEvent = new GameEvents();
    private ImageTable table = new ImageTable();
    private KeyControl key;
    private BufferedImage bimg;
    private ArrayList<GameObject> EnemyList = new ArrayList();
    private ArrayList<GameObject> EnemyBulletList = new ArrayList();
    private ArrayList<GameObject> MyP1BulletList = new ArrayList();
    private ArrayList<GameObject> MyPlaneList = new ArrayList();
    private ArrayList<GameObject> MyP2BulletList = new ArrayList();
    private MyPlane ke;
    private MyPlane2 ke2;
    private EnemyPlane x;
    private boolean isAlive1;
    private boolean isAlive2;

    public Gm1942() {
    }

    public void init() {
        ke = new MyPlane();
        ke2 = new MyPlane2();
        table.init();
        backG = new Backgroud(table, generator);
        ke.setData(200, 260, 20, 12, 1);
        ke2.setData(300, 260, 20, 12, 1);
        MyPlaneList.add(ke);
        MyPlaneList.add(ke2);
        backG.init();
        key = new KeyControl(gameEvent);
        addKeyListener(key);
        gameEvent.addObserver(ke);
        gameEvent.addObserver(ke2);
        isGameover = false;
        isBossDead = false;
        isAlive1 = true;
        isAlive2 = true;
    }

    public void run() {

        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            int size = EnemyList.size();
            double temp = generator.nextDouble();
            //used to create random Enemy planes 
            // 30% for Enemy1 
            // 30% for Enemy2
            // 20% for Enemy3
            // 20% for Enemy4
            if (size == 0 || size < 5) {
                if (temp < 0.3) {
                    x = new EnemyPlane();
                    x.setData(Math.abs(this.generator.nextInt() % (600 - 30)), -100, 2, 1, 1);
                } else if (temp < 0.6) {
                    x = new EnemyPlane();
                    x.setData(Math.abs(this.generator.nextInt() % (600 - 30)), -100, 2, 1, 2);
                } else if (temp < 0.8) {
                    x = new EnemyPlane();
                    x.setData(Math.abs(this.generator.nextInt() % (600 - 30)), -100, 2, 1, 3);
                } else {
                    x = new EnemyPlane();
                    x.setData(Math.abs(this.generator.nextInt() % (600 - 30)), 500, 3, 1, 4);
                }
                this.EnemyList.add(x);
                timeline++;
                //there is a boss coming out when timeline reaches 200
                //just for fan 
                //when the boss is dead, the game is over. Players are win. 
                if (timeline == 200) {
                    x = new EnemyPlane();
                    x.setData(300, -100, 5, 100, 5);
                    this.EnemyList.add(x);
                }
            }

            try {
                thread.sleep(25);//25
            } catch (InterruptedException e) {
                break;
            }

        }

        // thread = null;
    }

    public void addScoreP1(int s) {
        this.gamescoreP1 = this.gamescoreP1 + s;
    }

    public void addScoreP2(int s) {
        this.gamescoreP2 = this.gamescoreP2 + s;
    }

    public void setIsBossDead() {
        this.isBossDead = true;
    }

    public void addInEnemyList(GameObject x) {
        this.EnemyList.add(x);
    }

    public void removeEnemyList(GameObject x) {
        this.EnemyList.remove(x);
    }

    public void addInEnemyBullet(GameObject x) {
        this.EnemyBulletList.add(x);
    }

    public void removeEnemyBullet(GameObject x) {
        this.EnemyBulletList.remove(x);
    }

    public void addInP1Bullet(GameObject x) {
        this.MyP1BulletList.add(x);
    }

    public void removeP1Bullet(GameObject x) {
        this.MyP1BulletList.remove(x);
    }

    public void addInP2Bullet(GameObject x) {
        this.MyP2BulletList.add(x);
    }

    public void removeP2Bullet(GameObject x) {
        this.MyP2BulletList.remove(x);
    }

    public ArrayList<GameObject> getMyPlane() {
        return this.MyPlaneList;
    }

    public ArrayList<GameObject> getEnemyPlane() {
        return this.EnemyList;
    }

    public ArrayList<GameObject> getEnemyBulletList() {
        return this.EnemyBulletList;
    }

    public ArrayList<GameObject> getP1BulletList() {
        return this.MyP1BulletList;
    }

    public ArrayList<GameObject> getP2BulletList() {
        return this.MyP2BulletList;
    }

    public GameEvents getEvent() {
        return this.gameEvent;
    }

    public boolean getIsAlive1() {
        return this.isAlive1;
    }

    public boolean getIsAlive2() {
        return this.isAlive2;
    }

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }
    //FOR DRAW ALL GAME OBJECT
    public void drawGame(Graphics g2) {
        if (!isGameover) {
            if (isAlive1) {
                ke.update(this);
                ke.draw(g2, this);

                if (ke.getLife() == 0) {
                    this.isAlive1 = false;
                }
            }
            if (isAlive2) {
                ke2.update(this);

                ke2.draw(g2, this);

                if (ke2.getLife() == 0) {
                    isAlive2 = false;
                }

            }
            for (int i = 0; i < MyP1BulletList.size(); i++) {
                if (this.MyP1BulletList.get(i).getIsVisible()) {
                    this.MyP1BulletList.get(i).update(this);
                    this.MyP1BulletList.get(i).draw(g2, this);
                } else {
                    this.MyP1BulletList.remove(i);
                }
            }
            for (int i = 0; i < MyP2BulletList.size(); i++) {
                if (this.MyP2BulletList.get(i).getIsVisible()) {
                    this.MyP2BulletList.get(i).update(this);
                    this.MyP2BulletList.get(i).draw(g2, this);
                } else {
                    this.MyP2BulletList.remove(i);
                }
            }
            for (int i = 0; i < EnemyBulletList.size(); i++) {
                if (this.EnemyBulletList.get(i).getIsVisible()) {
                    this.EnemyBulletList.get(i).update(this);
                    this.EnemyBulletList.get(i).draw(g2, this);
                } else {
                    this.EnemyBulletList.remove(i);
                }
            }
            for (int i = 0; i < EnemyList.size(); i++) {
                if (this.EnemyList.get(i).getIsVisible()) {
                    this.EnemyList.get(i).update(this);
                    this.EnemyList.get(i).draw(g2, this);
                } else {
                    this.EnemyList.remove(i);
                }
            }
            if (ke.getHp() == 12 || ke.getHp() == 8 || ke.getHp() == 4) {
                g2.drawImage(table.get("Health0"), 2, 420, this);
            }

            if (ke.getHp() == 11 || ke.getHp() == 7 || ke.getHp() == 3) {
                g2.drawImage(table.get("Health1"), 2, 420, this);
            }

            if (ke.getHp() == 10 || ke.getHp() == 6 || ke.getHp() == 2) {
                g2.drawImage(table.get("Health2"), 2, 420, this);
            }
            if (ke.getHp() == 9 || ke.getHp() == 5 || ke.getHp() == 1) {
                g2.drawImage(table.get("Health3"), 2, 420, this);
            }
            if (ke.getLife() == 3) {
                g2.drawImage(table.get("Life"), 120, 415, this);
                g2.drawImage(table.get("Life"), 140, 415, this);
            }
            if (ke.getLife() == 2) {
                g2.drawImage(table.get("Life"), 120, 415, this);

            }
            if (ke2.getHp() == 12 || ke2.getHp() == 8 || ke2.getHp() == 4) {
                g2.drawImage(table.get("Health0"), 490, 420, this);
            }

            if (ke2.getHp() == 11 || ke2.getHp() == 7 || ke2.getHp() == 3) {
                g2.drawImage(table.get("Health1"), 490, 420, this);
            }

            if (ke2.getHp() == 10 || ke2.getHp() == 6 || ke2.getHp() == 2) {
                g2.drawImage(table.get("Health2"), 490, 420, this);
            }
            if (ke2.getHp() == 9 || ke2.getHp() == 5 || ke2.getHp() == 1) {
                g2.drawImage(table.get("Health3"), 490, 420, this);
            }
            if (ke2.getLife() == 3) {
                g2.drawImage(table.get("Life"), 460, 420, this);
                g2.drawImage(table.get("Life"), 440, 420, this);

            }
            if (ke2.getLife() == 2) {
                g2.drawImage(table.get("Life"), 460, 420, this);

            }
            if ((isAlive1 == false) && (isAlive2 == false)) {
                this.isGameover = true;
            }
            if (this.isBossDead) {
                this.isGameover = true;
            }
        }
        if (this.isBossDead) {
            g2.drawImage(table.get("WIN"), 220, 140, this);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(null, Font.BOLD, 20));
            g2.drawString("Player 1 score: " + String.valueOf(this.gamescoreP1), 250, 400);
            g2.drawString("Player 2 score: " + String.valueOf(this.gamescoreP2), 250, 420);
        } else if(this.isGameover){
            g2.drawImage(table.get("GameOver"), 200, 140, this);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(null, Font.BOLD, 20));
            g2.drawString("Player 1 score: " + String.valueOf(this.gamescoreP1), 250, 280);
            g2.drawString("Player 2 score: " + String.valueOf(this.gamescoreP2), 250, 300);
        }
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        Graphics2D g2 = createGraphics2D(d.width, d.height);
        backG.drawDemo(d.width, d.height, g2);
        drawGame(g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

    }
    

    public static void main(String[] args) {
        final Gm1942 demo = new Gm1942();
        demo.init();
        JFrame f = new JFrame("Scrolling Shooter");
        f.addWindowListener(new WindowAdapter() {
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.setFocusable(true);
        demo.start();
    }
        
    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
