package com.evh98.helium;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ClientPlayer {

	private final static int NOTSTARTED = 0;
	private final static int PLAYING = 1;
	private final static int PAUSED = 2;
	private final static int FINISHED = 3;
	
	private Player player = null;
	private final Object playerLock = new Object();
	private int playerStatus = NOTSTARTED;
	
	public ClientPlayer(final InputStream is) throws JavaLayerException{
			this.player = new Player(is);
	}
	
	public void play() throws JavaLayerException{
		synchronized(playerLock){
			switch(playerStatus){
			case NOTSTARTED:
				final Runnable r = new Runnable(){
					public void run(){
						playInternal();
					}
				};
				final Thread t = new Thread(r);
				t.setDaemon(true);
				t.setPriority(Thread.MAX_PRIORITY);
				playerStatus = PLAYING;
				t.start();
				break;
			case PAUSED:
				resume();
				break;
			default:
				break;
			}
		}
	}
	
	public boolean pause(){
		synchronized(playerLock){
			if(playerStatus == PLAYING){
				playerStatus = PAUSED;
			}
			return playerStatus == PAUSED;
		}
	}
	
	public boolean resume(){
		synchronized(playerLock){
			if(playerStatus == PAUSED){
				playerStatus = PLAYING;
				playerLock.notifyAll();
			}
			return playerStatus == PLAYING;
		}
	}
	
	public void stop(){
		synchronized(playerLock){
			playerStatus = FINISHED;
			playerLock.notifyAll();
		}
	}
	
	private void playInternal(){
		while(playerStatus != FINISHED){
			try{
				if(!player.play(1)){
					break;
				}
			}catch(final JavaLayerException e){
				break;
			}
			synchronized(playerLock){
				while(playerStatus == PAUSED){
					try{
						playerLock.wait();
					}catch(final InterruptedException e){
						break;
					}
				}
			}
		}
		close();
	}
	
	public void close(){
		synchronized(playerLock){
			playerStatus = FINISHED;
		}
		try{
			player.close();
		}catch(final Exception e){
			
		}
	}
}