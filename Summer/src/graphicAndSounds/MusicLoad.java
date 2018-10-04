package graphicAndSounds;

import gameInterface.Settings;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicLoad {
 
    private boolean released = false;
    private Clip clip = null;
    private static FloatControl volumeC = null;
    private boolean playing = false;
    
    public MusicLoad(URL f) {
    	try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            
			clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(new Listener());
    	} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
            volumeC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(Settings.getVol());
//            System.out.println(Settings.getVol());
            released = true;
    }
 
    //true ���� ���� ������� ��������, false ���� ��������� ������
    public boolean isReleased() {
        return released;
    }
    
    //������������� �� ���� � ������ ������
    public boolean isPlaying() {
        return playing;
    }
 
    //������
    /*
      breakOld ���������� ���������, ���� ���� ��� ��������
      ���� reakOld==true, � ���� ����� ������� � ������� ������
      ����� ������ �� ���������
    */
    public void play(boolean breakOld) {
        if (released) {
            if (breakOld) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            } else if (!isPlaying()) {
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            }
        }
    }
    
    //�� �� �����, ��� � play(true)
    public void play() {
    	new Thread(){
    		public void run(){
    			 play(true);
    		}
    	}.start();
       
    }
    
    //������������� ���������������
    public void stop() {
        if (playing) {
            clip.stop();
        }
    }
 
    //��������� ���������
    /*
      x ����� ���� � �������� �� 0 �� 1 (�� ������ ������ � ������ ��������)
    */
    public static void setVolume(float x) {
        if (x<0) x = 0;
        if (x>1) x = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((float)(max-min)*x+min);
    }
    
    //���������� ������� ��������� (����� �� 0 �� 1)
    public float getVolume() {
        float v = volumeC.getValue();
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        return (v-min)/(max-min);
    }
    public void join() {
        if (!released) return;
        synchronized(clip) {
            try {
                while (playing) clip.wait();
            } catch (InterruptedException exc) {}
        }
    }
    public static MusicLoad playSound(String s){
        MusicLoad snd = new MusicLoad(MusicLoad.class.getResource(s));
        snd.play();
        return snd;
    }
    private class Listener implements LineListener {
        public void update(LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                playing = false;
                synchronized(clip) {
                    clip.notify();
                }
            }
        }
    }
    
}