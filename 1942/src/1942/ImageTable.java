
package 1942;

import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JApplet;

public class ImageTable extends JApplet{
    private static HashMap<String, Image> data = new java.util.HashMap<String, Image>();

    public Image get(String code) {
        Image c = data.get(code);
        return c;
    }

    public ImageTable() {
    }
    public void init(){
        data.put("island1", getSprite("Resources/island1.png"));
        data.put("island2", getSprite("Resources/island2.png"));
        data.put("island3", getSprite("Resources/island3.png"));
        
        data.put("Sea", getSprite("Resources/water.png"));
        
        data.put("Myplane1", getSprite("Resources/myplane_1.png"));
        data.put("Myplane2", getSprite("Resources/myplane_2.png"));
        data.put("Myplane3", getSprite("Resources/myplane_3.png"));
        
        data.put("Enemy1_1", getSprite("Resources/enemy1_1.png"));
        data.put("Enemy1_2", getSprite("Resources/enemy1_2.png"));
        data.put("Enemy1_3", getSprite("Resources/enemy1_3.png"));
        
        data.put("Enemy2_1", getSprite("Resources/enemy2_1.png"));
        data.put("Enemy2_2", getSprite("Resources/enemy2_2.png"));
        data.put("Enemy2_3", getSprite("Resources/enemy2_3.png"));
        
        data.put("Enemy3_1", getSprite("Resources/enemy3_1.png"));
        data.put("Enemy3_2", getSprite("Resources/enemy3_2.png"));
        data.put("Enemy3_3", getSprite("Resources/enemy3_3.png"));
        
        data.put("Enemy4_1", getSprite("Resources/enemy4_1.png"));
        data.put("Enemy4_2", getSprite("Resources/enemy4_2.png"));
        data.put("Enemy4_3", getSprite("Resources/enemy4_3.png"));
        
        data.put("Bullet_1", getSprite("Resources/bullet.png"));
        data.put("Bullet_2", getSprite("Resources/bulletLeft.png"));
        data.put("Bullet_3", getSprite("Resources/bulletRight.png"));
        
        data.put("Health0", getSprite("Resources/health.png"));
        data.put("Health1", getSprite("Resources/health1.png"));
        data.put("Health2", getSprite("Resources/health2.png"));
        data.put("Health3", getSprite("Resources/health3.png"));
        
        data.put("Life", getSprite("Resources/life.png"));
        data.put("GameOver", getSprite("Resources/gameOver.png"));
        data.put("WIN", getSprite("Resources/youWin.png"));
        
        
        data.put("EnemyBullet_1", getSprite("Resources/enemybullet1.png"));
        data.put("EnemyBullet_2", getSprite("Resources/enemybullet2.png"));
        data.put("EnemyBullet_3", getSprite("Resources/bigBullet.png"));
        
        data.put("Powerup", getSprite("Resources/powerup.png"));
        data.put("Enemy5_1", getSprite("Resources/boss.png"));
        
        data.put("Explosion1_1", getSprite("Resources/explosion1_1.png"));
        data.put("Explosion1_2", getSprite("Resources/explosion1_2.png"));
        data.put("Explosion1_3", getSprite("Resources/explosion1_3.png"));
        data.put("Explosion1_4", getSprite("Resources/explosion1_4.png"));
        data.put("Explosion1_5", getSprite("Resources/explosion1_5.png"));
        data.put("Explosion1_6", getSprite("Resources/explosion1_6.png"));
        
        data.put("Explosion2_1", getSprite("Resources/explosion2_1.png"));
        data.put("Explosion2_2", getSprite("Resources/explosion2_2.png"));
        data.put("Explosion2_3", getSprite("Resources/explosion2_3.png"));
        data.put("Explosion2_4", getSprite("Resources/explosion2_4.png"));
        data.put("Explosion2_5", getSprite("Resources/explosion2_5.png"));
        data.put("Explosion2_6", getSprite("Resources/explosion2_6.png"));
    }
    public Image getSprite(String name) {
        URL url = ImageTable.class.getResource(name);
        Image img = this.getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
        }
        return img;
    }
}
