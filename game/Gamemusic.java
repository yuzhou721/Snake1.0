package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Gamemusic {
	static InputStream music;
	public static final String  backgroundmuisc="musics/backdrop.wav";
	public static final String  foodmuisc="musics/eatfood.wav";
	public static final String  doublekill="musics/doublekill.wav";
	public static final String  triblekill="musics/triblekill.wav";
	public static final String  quadrkill="musics/quadrkill.wav";
	public static final String  pentakill="musics/pentakill.wav";
	public static void palymusic(String a){
		try {
			music = new FileInputStream(new File(a));
			AudioStream as = new AudioStream(music);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void backgroundpaly(){
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						music = new FileInputStream(new File(backgroundmuisc));
						AudioStream as = new AudioStream(music);
						AudioPlayer.player.start(as);
						Thread.sleep(16000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				}
		});
		t1.start(); 
	}
	
	public static void main(String[] args) {
		palymusic(foodmuisc);
	}
}
