package com.evh98.helium;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Window extends JFrame{
	
	JLabel text;
	
	JButton minButton;
	JButton volButton;
	JButton playButton;
	JButton scanButton;
	JButton fbackButton;
	JButton backButton;
	JButton forwardButton;
	JButton exitButton;
	
	ImageIcon icon = new ImageIcon(this.getClass().getResource("assets/icon.png"));
	ImageIcon minButtonIcon = new ImageIcon(this.getClass().getResource("assets/min.png"));
	ImageIcon vol_offButtonIcon = new ImageIcon(this.getClass().getResource("assets/vol_off.png"));
	ImageIcon vol_onButtonIcon = new ImageIcon(this.getClass().getResource("assets/vol_on.png"));
	ImageIcon volButtonIcon = vol_onButtonIcon;
	ImageIcon play_offButtonIcon = new ImageIcon(this.getClass().getResource("assets/play_off.png"));
	ImageIcon play_onButtonIcon = new ImageIcon(this.getClass().getResource("assets/play_on.png"));
	ImageIcon playButtonIcon = play_offButtonIcon;
	ImageIcon scanButtonIcon = new ImageIcon(this.getClass().getResource("assets/scan.png"));
	ImageIcon fbackButtonIcon = new ImageIcon(this.getClass().getResource("assets/fback.png"));
	ImageIcon backButtonIcon = new ImageIcon(this.getClass().getResource("assets/back.png"));
	ImageIcon forwardButtonIcon = new ImageIcon(this.getClass().getResource("assets/forward.png"));
	ImageIcon exitButtonIcon = new ImageIcon(this.getClass().getResource("assets/exit.png"));
	
	public Window(){
		setTitle("Helium");
		setIconImage(icon.getImage());
		setBounds(0, 992, 1920, 48);
		setLayout(null);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		HandlerClass handler = new HandlerClass();
		
		text = new JLabel("Project Helium");
		text.setFont(new Font("HelveticaNeue LT 35 Thin", Font.PLAIN, 32));
		text.setLocation(0, 0);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setSize(1920,48);
		text.setHorizontalAlignment(0);
		
		minButton = new JButton(minButtonIcon);
		minButton.setBorder(null);
		minButton.setBounds(0, 0, 48, 48);
		minButton.addActionListener(handler);
		
		volButton = new JButton(volButtonIcon);
		volButton.setBorder(null);
		volButton.setBounds(48, 0, 48, 48);
		volButton.addActionListener(handler);
		
		playButton = new JButton(playButtonIcon);
		playButton.setBorder(null);
		playButton.setBounds(96, 0, 48, 48);
		playButton.addActionListener(handler);
		
		scanButton = new JButton(scanButtonIcon);
		scanButton.setBorder(null);
		scanButton.setBounds(144, 0, 48, 48);
		scanButton.addActionListener(handler);
		
		fbackButton = new JButton(fbackButtonIcon);
		fbackButton.setBorder(null);
		fbackButton.setBounds(1728, 0, 48, 48);
		fbackButton.addActionListener(handler);
		
		backButton = new JButton(backButtonIcon);
		backButton.setBorder(null);
		backButton.setBounds(1776, 0, 48, 48);
		backButton.addActionListener(handler);
		
		forwardButton = new JButton(forwardButtonIcon);
		forwardButton.setBorder(null);
		forwardButton.setBounds(1824, 0, 48, 48);
		forwardButton.addActionListener(handler);
		
		exitButton = new JButton(exitButtonIcon);
		exitButton.setBorder(null);
		exitButton.setBounds(1872, 0, 48, 48);
		exitButton.addActionListener(handler);
		
		add(text);
		add(minButton);
		add(volButton);
		add(playButton);
		add(scanButton);
		add(fbackButton);
		add(backButton);
		add(forwardButton);
		add(exitButton);
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == minButton){
				Actions.min();
			}
			else if(e.getSource() == volButton){
				Actions.vol();
			}
			else if(e.getSource() == playButton){
				Actions.play();
			}
			else if(e.getSource() == scanButton){
				Actions.scan();
			}
			else if(e.getSource() == fbackButton){
				Actions.fback();
			}
			else if(e.getSource() == backButton){
				Actions.back();
			}
			else if(e.getSource() == forwardButton){
				Actions.forward();
			}
			else if(e.getSource() == exitButton){
				Actions.exit();
			}
		}
	}
}