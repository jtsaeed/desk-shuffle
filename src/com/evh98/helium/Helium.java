package com.evh98.helium;

import java.awt.Font;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Helium{

	public static Window w;
	public static ClientPlayer cp;
	
	public static Font font;
	
	public static String path = System.getProperty("user.home") + "/AppData/Roaming/EVH98/DeskShuffle/";
	public static String ext = ".dsks";
	public static String docLink = "https://docs.google.com/forms/d/1joQsVHn20yVNcUq0E3xRNHKrMxKwNZJyCOfst2RWvgk/viewform";
	
	public static int isMin;
	public static boolean isVol;
	public static boolean isPlay;
	public static boolean isScan;
	public static boolean isLoop;
	
	public static void main(String[] args){
		w = new Window();
		
		isMin = 0;
		isVol = true;
		isPlay = false;
		isScan = false;
		isLoop = false;
		
		
		try {
			FileHandler.readPreferences();
			FileHandler.readLibrary();
		} catch (IOException | 
				ParserConfigurationException | SAXException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Music.changeTrack(0);
	}
}