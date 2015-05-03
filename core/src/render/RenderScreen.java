package render;

import java.util.Random;

import objects.Block;
import objects.Entity;
import objects.EntityManager;
import objects.Jumper;
import objects.Saw;
import objects.Triangle;
import main_character.Me;
import world_handler.GameWorld;
import Levels.Level_Default;
import assests.Assest_Manager;
import assests.Assets_Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class RenderScreen {
	private OrthographicCamera camera; //this is so I don't have worry about the resize function
	private int gameWidth;                       // this is important for maybe the game width
	private ShapeRenderer shapeMaker;  //this is to create shapes for character to walk/move/hit
	private SpriteBatch batch;                   //needed for the camera mainly
	private GameWorld World;                  //needed for varius commands and getters/setters
	private Me character;
	/// Above are global setups needed for every level renderer

    private EntityManager objects;
    private Assets_Level Assests;
    
    private float timer; //this is what keeps track of SHAKE screen to ensure it shake for about 1/4 of a second.
    private int randomInt;
    
    private Sprite sprite, skip, restart;
	private float timeState;
	private float timeDeath;
	private Level_Default level;
	
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
    
	
    private void initGameObjects() {
    	character = World.getMe();
    }
	
	public RenderScreen(GameWorld world,EntityManager manager, Assets_Level asset ){
		//makes the render output for Level_1
		Assests = asset;
		World = world;
		
		camera = new OrthographicCamera(544, 272); //this is the setup for the LANDSCAPE CAMERA
		camera.setToOrtho(true, 544, 272);
		
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		shapeMaker = new ShapeRenderer();
		shapeMaker.setProjectionMatrix(camera.combined);
		
		initGameObjects();
		objects = manager; //this is global now that entity manager is global as well!!!!!!
		//this 
		sprite = new Sprite(Assest_Manager.menu);
		sprite.flip(false,  true);
		
		skip = new Sprite(Assest_Manager.skip);
		skip.flip(false, true);
		
		restart = new Sprite(Assest_Manager.restart);
		restart.flip(false, true);
		
		///////////////////////////Above is a global setup for all level renders
		
		
		
	}

	public void render(float delta) {
		timeState += delta;
		if(timeState > 0.17 & timeState < 0.7){
			if(World.ready == false){
				World.ready= true;
			}
		}
		//
		if(World.SHAKE_SCREEN >0 & World.SHAKE_SCREEN <5){
				timer -= delta;
				if(timer <= 0){
					ShakeScreen();
					World.SHAKE_SCREEN ++;
					timer+=0.05f;
				}
			
		}else if(World.SHAKE_SCREEN >=5){
			World.SHAKE_SCREEN = 0;
			camera.setToOrtho(true, 544, 272);
			shapeMaker.setProjectionMatrix(camera.combined);
			batch.setProjectionMatrix(camera.combined);
			
		}
		///
		switch(World.DIMENSION){
		case 0:
			Gdx.gl.glClearColor(0, 0, 0, 0);  /// this color is black
			break;
		case 1:
			Gdx.gl.glClearColor(0, 0.2f, 0, 0);  /// this color is black
			break;
		}
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		
		camera.update();
		// 
		//Below renders only the outline for game
		 shapeMaker.begin(ShapeType.Filled);
			
		 	shapeMaker.setColor(0, 0.5f, 0, 0); //this is white btw the black border = edge of map

			 
			 shapeMaker.rect(21, 21, 544-42, 272-42); //the minus 15/30 is important to keep & record the border length
			 shapeMaker.end();
			 
			 ////////////////////////////////////////	this RENDERS IMAGES FROM ASSETS_LEVEL
			 batch.begin();
			 Assests.draw(batch, World.DIMENSION);
			 batch.end();
			 
			 
		//Below renders collision only objects
		
		//Below render objects of the game
		 shapeMaker.begin(ShapeType.Filled);
		 //inside here we need to draw all the objects in a forloop
		 
		 //BELOW DRAWS BLOCKS
		 for(Block B : objects.render_blocks()){										//AS TESTED THIS FORLOOPS RETURNS ALL ENTITES!!!
				Rectangle gmObject = B.returnSpace();
				if(gmObject.x == 0 & gmObject.y==0){
					shapeMaker.setColor(0.5f,0.5f,0.5f,0.5f);								//draws objects not allowed in game
					shapeMaker.rect(B.getPosition().x, B.getPosition().y, B.getWidth(), B.getHeight());
				}else{
					if(World.DIMENSION == 0){
						shapeMaker.setColor(0,0,0,0);
					}else{
						shapeMaker.setColor(0,0.2f,0,0);
					}
					shapeMaker.rect(gmObject.x, gmObject.y, gmObject.width, gmObject.height);             //AS TESTED THIS DRAWS OBJECTS
					
				}
			}
		 
		 
		 
		 //below DRAWS PLATFORMS
		for(Entity e : objects.render()){										//AS TESTED THIS FORLOOPS RETURNS ALL ENTITES!!!
			Rectangle gmObject = e.returnSpace();
			if(gmObject.x == 0 & gmObject.y==0){
				shapeMaker.setColor(0.5f,0.5f,0.5f,0.5f);								//draws objects not allowed in game
				shapeMaker.rect(e.getPosition().x, e.getPosition().y, e.getWidth(), e.getHeight());
			}else{
				if(World.DIMENSION == 0){
					shapeMaker.setColor(0,0,0,0);
				}else{
					shapeMaker.setColor(0,0.2f,0,0);
				}
				shapeMaker.rect(gmObject.x, gmObject.y, gmObject.width, gmObject.height);             //AS TESTED THIS DRAWS OBJECTS
				
			}
		}
		 
		 //////////////////////////////////////
		 //BELOW DRAWS JUMPERS
		 for(Jumper J : objects.render_jumps()){										//AS TESTED THIS FORLOOPS RETURNS ALL ENTITES!!!
				Rectangle gmObject = J.returnSpace();
				if(gmObject.x == 0 & gmObject.y==0){
					shapeMaker.setColor(0.5f,0.5f,0.5f,0.5f);								//draws objects not allowed in game
					shapeMaker.rect(J.getPosition().x, J.getPosition().y, J.getWidth(), J.getHeight());
				}else{
					if(World.DIMENSION == 0){
						shapeMaker.setColor(0,0,1,0);
					}else{
						shapeMaker.setColor(0,0.2f,1,0);
					}
					shapeMaker.rect(gmObject.x, gmObject.y, gmObject.width, gmObject.height);             //AS TESTED THIS DRAWS OBJECTS
					
				}
			}
		 shapeMaker.end();
		 
		 ///////////////////////////////////////inside here we need to draw all stairs in a forloop
		 shapeMaker.begin(ShapeType.Line);                               //DO NOT DRAW LINE in game, triangle will
		                                                                                         //exist BUT cannot be seen, it's a hidden factor
		 																						 // that will be molden to textures (ex:
		 																						 //draw triangle on top of stairs image & delete)
		 //forloop starts
		 for(Triangle t : objects.render_stairs()){
			 if(t.getDimension() == World.DIMENSION || t.getDimension() == 2){ //means its currently matching
				 shapeMaker.setColor(0,0,0,0);
			 }else{
				 shapeMaker.setColor(1, 0, 0, 0);
			 }
			 float[] bmstairs = t.getVertices(); //this is why I need the vertices method LOL
			 shapeMaker.polygon(bmstairs);
		 }
		 shapeMaker.end();
		 // forloop ends here so end of stairs
		 
		 //forloop for saws starts here (BTW SAWS = ANY OBJECT I WANT TO WRAP AND MAKE DEADLY)
		 shapeMaker.begin(ShapeType.Filled);
		 batch.begin();
		 for(Saw s : objects.render_saws()){										//AS TESTED THIS FORLOOPS RETURNS ALL ENTITES!!!
				Rectangle gmObject = s.returnSpace();
				if(gmObject.x == 0 & gmObject.y==0){
					// leave blank
				}else{
					batch.draw(Assest_Manager.saw_anime.getKeyFrame(timeState),
							gmObject.x, gmObject.y, gmObject.width, gmObject.height);
				}
			}
		 batch.end();
		 shapeMaker.end();
		 /////////////////////////////////////////////////////////end of saws
		 //BELOW IS SUPPOSED TO HANDLE DRAWING SCREEN TOUCHES FOR EXIT BUTTON, ETC.
		 
		 batch.begin();
		 batch.draw(sprite,0,0,21,21);
		 batch.draw(restart, 42, 0, 21,21);
		 //batch.draw(skip, 84, 0, 21,21);
		 batch.end();
		
		 
		 batch.begin(); //DRAWS CAHRACTER
			////test
		 //BELOW IS JUST FOR CHARACTER AND REGION
		 switch(level.death){
		 case 1:
			 timeDeath += delta;
			 batch.draw(Assest_Manager.dying_anime.getKeyFrame(timeDeath),
					 character.getX()-27, character.getY()-25, 75, 75);	
			break;
		default:
			if(World.DIMENSION== 0){
				 if(character.falling){
					 if (character.face == 0){
					 batch.draw(Assest_Manager.char5,
							 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }else{
						 batch.draw(Assest_Manager.Lchar1,
								 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }
				 }else{
					 if (character.face == 0){
						 batch.draw(Assest_Manager.D1_CHAR_animation.getKeyFrame(timeState),
								 character.getX(), character.getY(), character.getWidth(), character.getHeight());					 
					 }else{
						 batch.draw(Assest_Manager.D1_LEFT.getKeyFrame(timeState),
								 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }
				 }
			 }else if(World.DIMENSION ==1){
				 if(character.falling){
					 if (character.face == 0){
					 batch.draw(Assest_Manager.Dchar5,
							 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }else{
						 batch.draw(Assest_Manager.LDchar1,
								 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }
				 }else{
					 if(character.face == 0){
						 batch.draw(Assest_Manager.D2_CHAR_animation.getKeyFrame(timeState),
								 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }else{
						 batch.draw(Assest_Manager.D2_LEFT.getKeyFrame(timeState),
								 character.getX(), character.getY(), character.getWidth(), character.getHeight());
					 }
				 }
			 }
		 }
		batch.end();
	}
	
	public void ShakeScreen(){
			Random random  = new Random();
			randomInt = random.nextInt(3); //counts from 0-3
			switch(randomInt){
			case 0:
				camera.translate(10,10);
				break;
			case 1:
				camera.translate(-10,10);
				break;
			case 2:
				camera.translate(10,-10);
				break;
			case 3:
				camera.translate(-10,-10);
				break;
			}
			shapeMaker.setProjectionMatrix(camera.combined);
			batch.setProjectionMatrix(camera.combined);
	}
	


}
