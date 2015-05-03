package com.mygdx.DeathDoor;

import menu.Main_Menu;
import Levels.Level_Default;
import assests.Assest_Manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
//Game created by Jonathan Kumamoto
//for anyone smart to enough to look into the source code Here is the Hidden story of the game
//you are just a character, neither male nor female. There isn't earth I don't know what realm this is.
//Dying gets harder and harder as you progress in life (game) it gets harder to die
//this reflects on human beings, as you get older there are more options and more things to do to make 
//yourself happy.
//at the very end.... You are not really dead, you are simply... just You.
import com.badlogic.gdx.audio.Sound;

//Always remember this, Death is only a Door.


public class DeathDoorGame extends Game {
	//make important global variable (title usually)
	public static String LEVEL = "1";
	 public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	@Override
	public void create () {
		//create a new gamescreen
		//setScreen(new Loading_Screen());
		Assest_Manager.load();
		while(!Assest_Manager.manager.update()){
			//getting ready
		}
		Assest_Manager.create();

		setScreen(new Intro_scene());
		//setScreen(new Main_Menu());
		
		
	}

	@Override
	public void dispose () {
		super.dispose();
		//leave blank for now
	}
}
