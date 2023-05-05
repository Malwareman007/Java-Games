
package 1942;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.util.Observable;
import javax.sound.sampled.*;

public class GameEvents extends Observable {

    int type;
    Object event;

    public int getType() {
        return type;
    }

    public Object getEvent() {
        return event;
    }

    public void setValue(KeyEvent e) {
        type = 1; // let's assume this mean key input. Should use CONSTANT value for this
        event = e;
        setChanged();
        // trigger notification
        notifyObservers(this);
    }

    public void setValue(String msg) {
        if(msg.equals("explosionP1")){
            type=2;
            event=msg;
            addExplosionSound(1);
        }
        if(msg.equals("explosionP2")){
            type=2;
            event=msg;
            addExplosionSound(1);
        }
        if(msg.equals("explosionE1")){
            type=3;
            event=msg;
        }
        if(msg.equals("explosionE2")){
            type=3;
            event=msg;
        }
        if(msg.equals("explosionE3")){
            type=3;
            event=msg;
            addExplosionSound(1);
        }
        if(msg.equals("explosionE4")){
            type=3;
            event=msg;
            addExplosionSound(1);
        }
        if(msg.equals("WIN")){
            type=4;
            event=msg;
            addExplosionSound(1);
        }
        setChanged();
        
        // trigger notification
        notifyObservers(this);
    }
    public void addExplosionSound(int type){
        try {
            // Open an audio input stream.
            URL url=null;
            if(type == 2){
             url= Backgroud.class.getResource("Resources/snd_explosion2.wav");
            }else{
                url= Backgroud.class.getResource("Resources/snd_explosion1.wav");
            }
            
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
