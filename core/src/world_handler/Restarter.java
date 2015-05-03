package world_handler;

import com.mygdx.DeathDoor.DeathDoorGame;

import Levels.Level_Default;
import assests.Assets_Level;
import objects.EntityManager;
import render.RenderScreen;

public class Restarter {
	private GameWorld WORLD;
	private EntityManager ENTITIES;
	private Assets_Level ASSETS;
	private Level_Default LEVEL;
	private RenderScreen RENDER;
	private DeathDoorGame global;
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public Restarter(GameWorld w, EntityManager e, Assets_Level a, RenderScreen R, Level_Default l){
		WORLD = w;
		ENTITIES = e;
		ASSETS = a;
		LEVEL = l;
		RENDER = R; //hehe
	}
	
	public void RESTART(){
		System.out.println("wipey time");
		WORLD.DIMENSION = 0; //sets it back to the original dimension
		ENTITIES.reset();
		ENTITIES = null;
		WORLD = null;
		RENDER = null;
		WORLD.ready = false;
		LEVEL.loaded_images.clear();
		LEVEL.death = 0;
		ASSETS.destroy();
		System.gc();//calls THE GARABGE COLLECTOR to allocate (wipe) unused variables
		//this doesn't PROMISE that they will ALL be wiped, java will do it's best to claim back memory
		//what java claims back as FREE MEMORY is unknown...
	}

}
