package assests;

import java.util.ArrayList;

import world_handler.GameWorld;
import Levels.Level_Default;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Assets_Level { 
	//the GOAL of this class is to be able to load/unload level-specific items
    // which will be images (background, stairs)
	private Sprite temp_sprite;
	private SpriteBatch batcher;
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	private static ArrayList<Sprite>Sprite_Assets= new ArrayList<Sprite>(); //dimension #2 (stays in both)
	private static ArrayList<Sprite>D0_Sprites = new ArrayList<Sprite>();
	private static ArrayList<Sprite>D1_Sprites = new ArrayList<Sprite>();
	private static AssetManager level_specific_asset_mananger = new AssetManager(); 
	private static Array<String> loaded_images = new Array<String>();
	
	
	public Assets_Level(ArrayList<String[]> images) {
		for(String[] list : images){
			level_specific_asset_mananger.load(list[1], Texture.class);
			loaded_images.add(list[1]);
			}
		//DONE LOADING ALL IMAGES\
		
		//
		while(!level_specific_asset_mananger.update()){
			//just wait till loading is finished
		}
		
		//DONE UPDATING ALL LOADED QUES
		for(String[] list : images){
			temp_sprite = new Sprite(level_specific_asset_mananger.get(list[1], Texture.class));
			temp_sprite.flip(false, true);
			temp_sprite.setPosition(Float.parseFloat(list[2]), Float.parseFloat(list[3]));
			temp_sprite.setSize(Float.parseFloat(list[4]), Float.parseFloat(list[5]));
			int OBJECT = Integer.parseInt(list[6]);
			switch(OBJECT){
			case 0: //means only exists in first dimension
				D0_Sprites.add(temp_sprite);
				break;
			case 1: //only exists in 2nd dimension
				D1_Sprites.add(temp_sprite);
				break; 
			case 2: //exists in both dimension
				Sprite_Assets.add(temp_sprite);
				break;
			default:
				System.out.println("error in asset_levels");
				break;
			
			}
			


			}//done making sprites
		
		
		}
	
	//now that textures are set make sprites for render to DRAW
	
	
	public void draw(SpriteBatch batch, Integer dimension){
		//this is so the render method can call on this
		for(Sprite spr: Sprite_Assets){
			spr.draw(batch);
		}
		switch(dimension){
		case 0:  //MEANS THAT ONLY D0 OBJECTS EXISTS
			for(Sprite spr0: D0_Sprites){
				spr0.draw(batch);
			}
			for(Sprite spr1: D1_Sprites){	
				spr1.setAlpha(0.3f);
				spr1.draw(batch);
				spr1.setAlpha(1);
			}
			break;
		case 1:
			for(Sprite spr0: D0_Sprites){
				spr0.setAlpha(0.3f);
				spr0.draw(batch);
				spr0.setAlpha(1);
			}
			for(Sprite spr1: D1_Sprites){
				spr1.draw(batch);
			}
			break;
		default:
			System.out.println("error in the asset_level.draw()");
			break;
			
		} // end of switch
		
	}
	
	public static void destroy(){ //best to NOT dispose manager because we want to use it over and over again.
		for(String references : loaded_images){
			level_specific_asset_mananger.unload(references);
		}
		Sprite_Assets.clear();
		D0_Sprites.clear();
		D1_Sprites.clear();
		level_specific_asset_mananger.clear();
		loaded_images.clear();
		
		System.gc();
	}

}
