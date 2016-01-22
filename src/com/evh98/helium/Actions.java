package com.evh98.helium;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;

public class Actions {

	public static void min(){
		if(Helium.isMin==0){
			Helium.w.setBounds(0, 992, 288, 48);
			Helium.isMin = 1;
		}else if(Helium.isMin==1){
			Helium.w.setBounds(0, 992, 48, 48);
			Helium.isMin = 2;
		}else if(Helium.isMin==2){
			Helium.w.setBounds(0, 992, 1920, 48);
			Helium.isMin = 0;
		}
		
		if(Helium.isMin==1){
			Helium.w.scanButton.setLocation(1900, 0);
			Helium.w.backButton.setLocation(144, 0);
			Helium.w.forwardButton.setLocation(192, 0);
			Helium.w.exitButton.setLocation(240, 0);
		}else{
			Helium.w.scanButton.setLocation(144, 0);
			Helium.w.backButton.setLocation(1776, 0);
			Helium.w.forwardButton.setLocation(1824, 0);
			Helium.w.exitButton.setLocation(1872, 0);
		}
	}
	
	public static void vol(){
		if(Helium.isVol){
			Helium.w.volButtonIcon = Helium.w.vol_offButtonIcon;
		}else{
			Helium.w.volButtonIcon = Helium.w.vol_onButtonIcon;
		}

		Helium.w.volButton.setIcon(Helium.w.volButtonIcon);
		Helium.isVol = !Helium.isVol;
	}
	
	public static void play(){
		if(Helium.isPlay){
			Helium.cp.pause();
			Helium.w.playButtonIcon = Helium.w.play_offButtonIcon;
		}else{
			try {
				Helium.cp.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
			Helium.w.playButtonIcon = Helium.w.play_onButtonIcon;
		}
		
		Helium.w.playButton.setIcon(Helium.w.playButtonIcon);
		Helium.isPlay = !Helium.isPlay;
	}
	
	public static void scan(){
		Music.library.clear();
		if(Helium.isScan){
			return;
		}else{
			Music.scan(Music.musicPath);
			Helium.isScan = true;
		}
	}
	
	public static void fback(){
		try {
	        Desktop.getDesktop().browse(new URL(Helium.docLink).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void back(){
		Helium.cp.stop();
		Music.changeTrack(-1);
		try {
			Helium.cp.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		Helium.w.playButtonIcon = Helium.w.play_onButtonIcon;
		Helium.w.playButton.setIcon(Helium.w.playButtonIcon);
	}
	
	public static void forward(){
		Helium.cp.stop();
		Music.changeTrack(1);
		try {
			Helium.cp.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		Helium.w.playButtonIcon = Helium.w.play_onButtonIcon;
		Helium.w.playButton.setIcon(Helium.w.playButtonIcon);
	}
	
	public static void exit(){
		try {
			FileHandler.savePreferences();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Helium.cp.close();
		System.exit(0);
	}
}