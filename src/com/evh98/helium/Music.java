package com.evh98.helium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javazoom.jl.decoder.JavaLayerException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Music {

	public static ArrayList<String> library = new ArrayList<String>();
	
	public static String musicPath;
	public static int trackNumber;
	public static String currentArtist;
	public static String currentSong;
	public static String currentAlbum;
	
	public static void scan(String path){
		System.out.println(path);
		File root = new File(path);
		File[] list = root.listFiles();
		
		for(File f : list){
			if(f.isDirectory()){
				scan(f.getAbsolutePath());
			}else{
				if(f.getAbsolutePath().contains(".mp3")){
					library.add(f.getAbsolutePath());
				}
			}
		}
		
		Collections.shuffle(library);
		
		try {
			FileHandler.saveLibrary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		trackNumber = 0;

		changeTrack(0);
	}
	
	public static void changeTrack(int steps){
		trackNumber += steps;
		
		if(trackNumber >= library.size()){
			trackNumber = 0;
			Collections.shuffle(library);
			return;
		}
		
		FileInputStream input;
		try {
			input = new FileInputStream(library.get(trackNumber));
			getMetadata(new File(library.get(trackNumber)));
			Helium.cp = new ClientPlayer(input);
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public static void getMetadata(File f){
		Tag t = null;
		try {
			AudioFile af = AudioFileIO.read(f);
			t = af.getTag();
		} catch (CannotReadException | IOException | TagException
				| ReadOnlyFileException | InvalidAudioFrameException e) {
			e.printStackTrace();
		}
		
		currentArtist = t.getFirst(FieldKey.ARTIST);
		currentSong = t.getFirst(FieldKey.TITLE);
		currentAlbum = t.getFirst(FieldKey.ALBUM);
		
		Helium.w.text.setText(currentArtist + "     " + currentSong + "     " + currentAlbum);
	}
}