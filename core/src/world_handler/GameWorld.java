package world_handler;

import objects.EntityManager;
import render.RenderScreen;
import Levels.Level_Default;
import assests.Assest_Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import main_character.Me;

public class GameWorld {
	private Me character; 						//importing character is important because we need to get position + extra
	private Level_Default level;
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	///// Below create the borders for the game world to prevent character from getting out
	public static final float left_edge = 22;
	public static final float top_edge = 21;
	public static final float right_edge = 522;//136-13; //CHARGER CABLE TOUCHES THIS
	public static final float bottom_edge = 230-21;
	///
	
	public static int DIMENSION = 0; // 0 Means the first dimension & 1 = the 2nd dimension
	public static int SHAKE_SCREEN = 0; //0 means camera does not nee to shakeif > 0, do it in render
	public static Boolean ready= false;
	private Sound D2 =  Assest_Manager.manager.get(Assest_Manager.changing_dimension2, Sound.class);
	
	
	public GameWorld(float x, float y, int width, int height){
		//create the initial world
		character = new Me(x, y, width, height);			
	}

	public void update(float delta) {
		// update level for render options
		if(ready != false){
			switch(level.death){
			case 1:
				//leave blank
				break;
			default:
				character.update(delta);
				break;
			}
		}
	}
	
	public Me getMe(){
		return character;
	}

	public void onclick() {
		if(DIMENSION ==0){ // when it WAS first dimension
			DIMENSION = 1;
			SHAKE_SCREEN ++;
			D2.play();
		}else if(DIMENSION == 1){
			DIMENSION = 0;
			SHAKE_SCREEN ++;
			D2.play();
		}
		
	}

}
