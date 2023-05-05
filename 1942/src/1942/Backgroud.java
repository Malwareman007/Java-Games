package 1942;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.URL;
import java.util.Random;
import javax.sound.sampled.*;
import javax.swing.JApplet;

public class Backgroud extends JApplet {

    private int x = 0, move = 0, speed = 1;
    private boolean gameOver = false;
    private ImageTable table;
    private Island I1, I2, I3;
    private Random generator;

    public Backgroud() {
    }

    public Backgroud(ImageTable c, Random generator) {
        this.table = c;
        this.generator = generator;
    }

    public void init() {
        setBackground(Color.white);
        I1 = new Island(table.get("island1"), 100, 100, speed, generator);
        I2 = new Island(table.get("island2"), 200, 400, speed, generator);
        I3 = new Island(table.get("island3"), 300, 200, speed, generator);
        try {
            // Open an audio input stream.
            URL url = Backgroud.class.getResource("Resources/background.wav");

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void drawBackGroundWithTileImage(int w, int h, Graphics2D g2) {
        Image sea;
        sea = table.get("Sea");
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        Image Buffer = createImage(NumberX * TileWidth, NumberY * TileHeight);
        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth, i * TileHeight + (move % TileHeight), TileWidth, TileHeight, this);
            }
        }
        move += speed;


    }

    public void drawDemo(int w, int h, Graphics2D g2) {

        if (!gameOver) {
            drawBackGroundWithTileImage(w, h, g2);
            I1.update(w, h);
            I1.draw(g2, this);

            I2.update(w, h);
            I2.draw(g2, this);

            I3.update(w, h);
            I3.draw(g2, this);
        }

    }

    public class Island {

        Image img;
        int x, y, speed;
        Random gen;

        Island(Image img, int x, int y, int speed, Random gen) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.gen = gen;
        }

        public void update(int w, int h) {
            y += speed;
            if (y >= h) {
                y = -100;
                x = Math.abs(gen.nextInt() % (w - 30));
            }
        }

        public void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, x, y, obs);
        }
    }
}
