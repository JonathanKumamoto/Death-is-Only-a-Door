package Levels;

import java.util.ArrayList;
import objects.Block;
import objects.Entity;
import objects.EntityManager;
import objects.Jumper;
import objects.Saw;
import objects.Triangle;
import input_handler.DOD_Handler;
import render.RenderScreen;
import world_handler.GameWorld;
import world_handler.Restarter;
import world_handler.XmlLoader;
import assests.Assest_Manager;
import assests.Assets_Level;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Screen;
import com.mygdx.DeathDoor.DeathDoorGame;
import com.mygdx.DeathDoor.End_scene;
import com.mygdx.DeathDoor.Intro_scene;

/*
Copyright by Jonathan Simeon Kumamoto 2014

This file is the "core" file, bringing together all the parts and pieces to fully create this game

*/

public class Level_Default implements Screen {
	private GameWorld world;
	private RenderScreen level_render;
	private DeathDoorGame global;
	public static Orientation orientation;  //this is for the screen tilt when the user changes dimensions
	private Sound death_sound =  Assest_Manager.manager.get(Assest_Manager.death, Sound.class); //sound file for the character death
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	
	/*
	BELOW IS NON-SPECIFIC FOR LEVEL_"NAME"
	this is extremely important because it allows to reload this "level loader" and simply load objects from an .xml file
	*/
	private Intro_scene introduction;
	private Restarter reset;
	private EntityManager manager;
	private String[] args = {global.LEVEL};
	private Assets_Level levelAssets;
	public static ArrayList<String[]>loaded_images= new ArrayList<String[]>();
	public static int controls = 0; //0 = tilt & 1 = touch screen
	public static int death = 0; //0 = character alive, 1 means its dead NOW play animation+sound
	private int death_time = 75; //this is so the game does not change the screen right away
	

	
	public Level_Default(){
		//
		world = new GameWorld(21, 21, 19, 27); //change this input to make ME starting location change according to XML
						       //ME = character, GameWorld(ME width, ME height, ME x position, ME y position)
		manager = new EntityManager();
		Level_xml(manager);
		levelAssets = new Assets_Level(loaded_images);
		level_render = new RenderScreen(world, manager, levelAssets);
		reset = new Restarter(world, manager, levelAssets, level_render, this);
		int level = Integer.parseInt(global.LEVEL);
		
		//This switch control the music that plays based on what level the user is currently at
		switch(level){
		case 1:
			introduction.Welcome.play();
			break;
		case 5:
			introduction.Welcome.stop();
			introduction.intro_song.play();
			break;
		case 25:
			introduction.intro_song.stop();
			introduction.Level10.play();
			break;
		default:
			break;
		}
		
		
		
		/*
		***************************************BELOW IS IMPORTANT***************************************
		*************************************Below is where you add manager objects so say manager.addobject~~*
		manager.addObject == making platform
		manager.addS_Ojbect == making stairs
		manager.addK_Object == killer platform/object
		
		Take note that being specific pixel by pixel is important
		
		*/
		
		
		//this is the accelerametor
		orientation = Gdx.input.getNativeOrientation();                   //this SETS UP tilt
		//below is the input handler for dimensions ONLY
		Gdx.input.setInputProcessor(new DOD_Handler(world, reset));      //this SETS UP touchscreen
	}
	
	//Purpose is to CREATE OBJECTS based on the xml filed
	public void Level_xml(EntityManager manager){ 
																					// and its "key" for which level should be played...
		XmlLoader loader = new XmlLoader();
		
		for (String[] i : loader.main(args)){ //goes through all objects in game
			int OBJECT = Integer.parseInt(i[0]);
			
			//Initiates a game object based on the number the .xml gives and parses based on those given characteristics
			switch(OBJECT){
			case 1:
				manager.addObject(new Entity(Integer.parseInt(i[1]),new Vector2(Float.parseFloat(i[2]),Float.parseFloat(i[3]))
				,  Integer.parseInt(i[4]), Integer.parseInt(i[5])));
				break;
			case 2:
				//BELOW YOU NEED TO CREATE A FLOAT
				float[] verticez = {Float.parseFloat(i[4]), Float.parseFloat(i[5]),Float.parseFloat(i[6]), Float.parseFloat(i[7]), Float.parseFloat(i[8]), Float.parseFloat(i[9])};
				
				manager.addS_Object(new Triangle(Integer.parseInt(i[1]), new Vector2(Float.parseFloat(i[2]),Float.parseFloat(i[3])),
						verticez));
				break;
			case 3:
				manager.addK_Object(new Saw(Integer.parseInt(i[1]),new Vector2(Float.parseFloat(i[2]),Float.parseFloat(i[3]))
				,  Integer.parseInt(i[4]), Integer.parseInt(i[5])));
				break;
			case 4:
				loaded_images.add(i);
				break;
			case 5: //this means it is the character's coordinate's :D
				world.getMe().position.x = Float.parseFloat(i[1]);
				world.getMe().position.y = Float.parseFloat(i[2]);
				break;
			case 6:
				manager.addB_Object(new Block(Integer.parseInt(i[1]),new Vector2(Float.parseFloat(i[2]),Float.parseFloat(i[3]))
				,  Integer.parseInt(i[4]), Integer.parseInt(i[5])));
				break;
			case 7:
				manager.addJ_Object(new Jumper(Integer.parseInt(i[1]),new Vector2(Float.parseFloat(i[2]),Float.parseFloat(i[3]))
				,  Integer.parseInt(i[4]), Integer.parseInt(i[5])));
				break;
			default:
				System.out.println("Error)");
			}
		}
			
	}
	@Override
	public void render(float delta) {
		world.update(delta); //we want WORLD to stay away from manager because manager has to handle it's own things
		manager.update(world.DIMENSION, world.getMe());
		level_render.render(delta);
		
		if(world.getMe().dead){ //below doesn't take much memory because it won't proct unless character DIES
			death();
		}
		
	}
	//Called when character has died
	public void death(){
		switch(death_time){
			case 0:
				newGame();
				break;
			case 75:
				death_sound.play();
				death_time --;
				death =1;
				break;
			default:
				death_time --;
				break;
		}
	}
	//This resets the game or levels extremely quickly for a better user experience
	public void newGame(){
		int New_level = Integer.parseInt(global.LEVEL)+1;
		switch(New_level){
		case 26:
			//this means its ENDING scene, user has completed the game
			reset.RESTART();
			global.LEVEL = Integer.toString(1);
			introduction.Level10.stop();
			((Game)Gdx.app.getApplicationListener()).setScreen(new End_scene());
			break;
		default:
			global.LEVEL = Integer.toString(New_level); //ADDS level to ensure that the world is moving on
			reset.RESTART();
			((Game)Gdx.app.getApplicationListener()).setScreen(new Level_Default());
			break;
			
		}
		
		//REMEMBER THIS THAT THERE IS FREEDOM DUE TO GAME ENGINE made by myself ofc 
	}

	@Override
	public void resize(int width, int height) {
		//
		
	}

	@Override
	public void show() {
		//
		
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		//
		
	}

	@Override
	public void resume() {
		//
		
	}

	@Override
	public void dispose() {
		
	}

}
