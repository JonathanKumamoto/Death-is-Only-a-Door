package assests;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assest_Manager { //THIS ASSEST MANAGER IS NOT supposed to handle xml, because this is
												// for the menu or just global fx and images
	public static final AssetManager manager = new AssetManager();
	public static final String changing_dimension2 = "sounds/Dimension2.wav";
	public static final String jump = "sounds/Jump.wav";
	public static final String death ="sounds/dead.wav";
	public static final String theme_song = "music/ThemeSong.mp3";
	public static final String tutorial = "music/Tutorial.mp3";
	public static final String beginnings = "music/Beginnings.mp3";
	public static final String realization = "music/Realization.mp3";
	public static final String ending = "music/End.mp3";
	public static final String CHARACTER = "sprites/MEspritesheet.png";
	public static final String CHARACTERLEFT = "sprites/MEspritesheet2.png";
	public static final String INTRO = "loading/INTRO.png";
	public static final String END = "loading/end_scene.png";
	public static final String MENU_animation = "loading/intro_sheet.png";
	public static final String Saw_animation = "sprites/SawAnimation.png";
	public static final String Death_animation = "sprites/DyingSheet.png";
	public static final String button_menu = "button/Menu.png";
	public static final String button_skip = "button/Skip.png";
	public static final String button_restart = "button/Restart.png";
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public static TextureRegion char1, char2, char3, char4, char5, 
	Dchar1, Dchar2, Dchar3, Dchar4, Dchar5,
	Lchar1, Lchar2, Lchar3, Lchar4, Lchar5,
	LDchar1, LDchar2, LDchar3, LDchar4, LDchar5;//must be public because
																	 // if character is falling and what not, use a region instead
	private static TextureRegion menu1, menu2, menu3, menu4, menu5,
							saw1, saw2, saw3, saw4, dead1, dead2, dead3, dead4, dead5,
							dead6, dead7, dead8, dead9, dead10, dead11;
	public static Texture intro_scene, end_scene, menu, skip, restart;
	
	private static Texture sheet, sheetLEFT, menuSheet, sawSheet, dyingSheet;
	public static Animation D1_CHAR_animation, D2_CHAR_animation, D1_LEFT, D2_LEFT, 
							Menu_anime, saw_anime, dying_anime;
	
	public static void load(){
																						//SOUND CLASS IS BETTER THE MUSIC.CLASS
																						//because sound class stores in file memory
																						//music reads in realtime
		manager.load(changing_dimension2, Sound.class);
		manager.load(jump, Sound.class);
		manager.load(death, Sound.class);
		manager.load(theme_song, Music.class);
		manager.load(tutorial, Music.class);
		manager.load(beginnings, Music.class);
		manager.load(ending, Music.class);
		manager.load(realization, Music.class);
		manager.load(CHARACTER, Texture.class);
		manager.load(CHARACTERLEFT, Texture.class);
		manager.load(INTRO, Texture.class);
		manager.load(END, Texture.class);
		manager.load(MENU_animation, Texture.class);
		manager.load(Saw_animation, Texture.class);
		manager.load(Death_animation, Texture.class);
		manager.load(button_menu, Texture.class);
		manager.load(button_skip, Texture.class);
		manager.load(button_restart, Texture.class);
	}
	
	public static void create(){
		//BELOW CREATES DEATH ANIMATION FOR GHOSTY
		dyingSheet = manager.get(Death_animation, Texture.class);
		dead1 =  new TextureRegion(dyingSheet, 0,0, 75, 75);
		dead1.flip(false, true);
		
		dead2 =  new TextureRegion(dyingSheet, 75,0, 75, 75);
		dead2.flip(false, true);
		
		dead3 =  new TextureRegion(dyingSheet, 150,0, 75, 75);
		dead3.flip(false, true);
		
		dead4 =  new TextureRegion(dyingSheet, 225,0, 75, 75);
		dead4.flip(false, true);
		
		dead5 =  new TextureRegion(dyingSheet, 300,0, 75, 75);
		dead5.flip(false, true);
		
		dead6 =  new TextureRegion(dyingSheet, 375,0, 75, 75);
		dead6.flip(false, true);
		
		dead7 =  new TextureRegion(dyingSheet, 450,0, 75, 75);
		dead7.flip(false, true);
		
		dead8 =  new TextureRegion(dyingSheet, 525,0, 75, 75);
		dead8.flip(false, true);
		
		dead9 =  new TextureRegion(dyingSheet, 600,0, 75, 75);
		dead9.flip(false, true);
		
		dead10 =  new TextureRegion(dyingSheet, 675,0, 75, 75);
		dead10.flip(false, true);
		
		dead11 =  new TextureRegion(dyingSheet, 750,0, 75, 75);
		dead11.flip(false, true);
		
		
		TextureRegion[] dying = { dead1, dead2, dead3, dead4, dead5,
				dead6, dead7, dead8, dead9, dead10, dead11};
		dying_anime= new Animation(0.15f, dying);
		dying_anime.setPlayMode(Animation.PlayMode.LOOP);
		//below is menu button images for in-game play
		skip =manager.get(button_skip, Texture.class);
		menu = manager.get(button_menu, Texture.class);
		restart = manager.get(button_restart, Texture.class);
		///BELOW IS MENU ANIMATION
		menuSheet = manager.get(MENU_animation, Texture.class);
		menu1 =  new TextureRegion(menuSheet, 0,0, 502, 230);
		menu1.flip(false, true);
		
		menu2 =  new TextureRegion(menuSheet, 0,230, 502, 230);
		menu2.flip(false, true);
		
		menu3 =  new TextureRegion(menuSheet, 0,460, 502, 230);
		menu3.flip(false, true);
		
		menu4 =  new TextureRegion(menuSheet, 0,690, 502, 230);
		menu4.flip(false, true);
		
		menu5 =  new TextureRegion(menuSheet, 0,920, 502, 230);
		menu5.flip(false, true);
		
		
		TextureRegion[] menus = { menu1, menu2, menu3, menu4, menu5 };
		Menu_anime= new Animation(0.15f, menus);
		Menu_anime.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
		////END OF MENU ANIMATION
		
		
		
		intro_scene = manager.get(INTRO, Texture.class);
		end_scene = manager.get(END, Texture.class);
		///below is the character sprite's
		sheet = manager.get(CHARACTER, Texture.class);
		
		char1 =  new TextureRegion (sheet, 0,0,19,27);
		char1.flip(false, true);
		
		char2 =  new TextureRegion (sheet, 19,0,19,27);
		char2.flip(false, true);
		
		char3 =  new TextureRegion (sheet, 38,0,19,27);
		char3.flip(false, true);
		
		char4=  new TextureRegion (sheet, 57,0,19,27);
		char4.flip(false, true);
		
		char5 =  new TextureRegion (sheet, 76,0,19,27);
		char5.flip(false, true);
		
		//now we have all the top parts to render the triangle in the first dimension
		TextureRegion[] D1_character = { char1, char2, char3, char4, char5 };
		D1_CHAR_animation = new Animation(0.15f, D1_character);
		D1_CHAR_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		///////////////////////////////////////////////////////////////////////below is for 2nd diemnsion
		Dchar1 =  new TextureRegion (sheet, 0,27,19,27);
		Dchar1.flip(false, true);
		
		Dchar2 =  new TextureRegion (sheet, 19,27,19,27);
		Dchar2.flip(false, true);
		
		Dchar3 =  new TextureRegion (sheet, 38,27,19,27);
		Dchar3.flip(false, true);
		
		Dchar4=  new TextureRegion (sheet, 57,27,19,27);
		Dchar4.flip(false, true);
		
		Dchar5 =  new TextureRegion (sheet, 76,27,19,27);
		Dchar5.flip(false, true);
		
		//now we have all the top parts to render the triangle in the first dimension
		TextureRegion[] D2_character = { Dchar1, Dchar2, Dchar3, Dchar4, Dchar5 };
		D2_CHAR_animation = new Animation(0.15f, D2_character);
		D2_CHAR_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		////////////////this is FOR LEFT
		sheetLEFT = manager.get(CHARACTERLEFT, Texture.class);
		
		Lchar1 =  new TextureRegion (sheetLEFT, 0,0,19,27);
		Lchar1.flip(false, true);
		
		Lchar2 =  new TextureRegion (sheetLEFT, 19,0,19,27);
		Lchar2.flip(false, true);
		
		Lchar3 =  new TextureRegion (sheetLEFT, 38,0,19,27);
		Lchar3.flip(false, true);
		
		Lchar4=  new TextureRegion (sheetLEFT, 57,0,19,27);
		Lchar4.flip(false, true);
		
		Lchar5 =  new TextureRegion (sheetLEFT, 76,0,19,27);
		Lchar5.flip(false, true);
		
		//now we have all the top parts to render the triangle in the first dimension
		TextureRegion[] D1 = { Lchar1, Lchar2, Lchar3, Lchar4, Lchar5 };
		D1_LEFT = new Animation(0.15f, D1);
		D1_LEFT.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		///////////////////////////////////////////////////////////////////////below is for 2nd diemnsion
		LDchar1 =  new TextureRegion (sheetLEFT, 0,27,19,27);
		LDchar1.flip(false, true);
		
		LDchar2 =  new TextureRegion (sheetLEFT, 19,27,19,27);
		LDchar2.flip(false, true);
		
		LDchar3 =  new TextureRegion (sheetLEFT, 38,27,19,27);
		LDchar3.flip(false, true);
		
		LDchar4=  new TextureRegion (sheetLEFT, 57,27,19,27);
		LDchar4.flip(false, true);
		
		LDchar5 =  new TextureRegion (sheetLEFT, 76,27,19,27);
		LDchar5.flip(false, true);
		
		//now we have all the top parts to render the triangle in the first dimension
		TextureRegion[] D2 = { LDchar1, LDchar2, LDchar3, LDchar4, LDchar5 };
		D2_LEFT = new Animation(0.15f, D2);
		D2_LEFT.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		////BELOW IS SAW animation
		sawSheet = manager.get(Saw_animation, Texture.class);
		
		saw1 =  new TextureRegion (sawSheet , 0,0,25,25);
		saw1.flip(false, true);
		
		saw2 =  new TextureRegion (sawSheet , 25,0,25,25);
		saw2.flip(false, true);
		
		saw3 =  new TextureRegion (sawSheet , 50,0,25,25);
		saw3.flip(false, true);
		
		saw4=  new TextureRegion (sawSheet , 75,0,25,25);
		saw4.flip(false, true);
		
		//now we have all the top parts to render the triangle in the first dimension
		TextureRegion[] saw = { saw1, saw2, saw3, saw4 };
		saw_anime = new Animation(0.3f, saw);
		saw_anime.setPlayMode(Animation.PlayMode.LOOP);
		
		
		
	}
	
	public static void dispose(){
		manager.dispose();
		System.gc();
	}

}
