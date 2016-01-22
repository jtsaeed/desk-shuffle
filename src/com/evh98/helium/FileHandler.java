package com.evh98.helium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileHandler {

	public static void saveLibrary() throws IOException{
		FileOutputStream fos = new FileOutputStream(Helium.path + "library" + Helium.ext);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(Music.library);
		fos.close();
	}
	
	public static void readLibrary() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(Helium.path + "library" + Helium.ext);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Music.library = (ArrayList<String>) ois.readObject();
		fis.close();
	}
	
	public static void savePreferences() throws IOException{
		FileWriter fw = new FileWriter(Helium.path + "prefs" + Helium.ext);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("<prefs>\n");
		bw.write("\t<musicPath>"+Music.musicPath+"</musicPath>\n");
		bw.write("\t<trackNumber>"+Music.trackNumber+"</trackNumber>\n");
		bw.write("</prefs>");
		bw.close();
	}
	
	public static void readPreferences() throws ParserConfigurationException, SAXException, IOException{
		File f = new File(Helium.path + "prefs" + Helium.ext);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.parse(f);
		d.normalize();
		
		NodeList nl = d.getElementsByTagName("prefs");
		
		for(int i=0; i < nl.getLength(); i++){
            Element element = (Element) nl.item(i);
            
            Music.musicPath = element.getElementsByTagName("musicPath").item(0).getChildNodes().item(0).getNodeValue();
            Music.trackNumber = Integer.parseInt(element.getElementsByTagName("trackNumber").item(0).getChildNodes().item(0).getNodeValue());
		}
	}
}