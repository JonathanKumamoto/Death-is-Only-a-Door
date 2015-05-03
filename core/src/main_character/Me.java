package main_character;

import world_handler.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Me {
	public Vector2 position;         //needed to determine position math
	public Vector2 velocity;         // need for move speed maybe? may have to TRASH IDEA!!!!!!!!!!!!!!!!!1
	private Vector2 acceleration; // when character fall he/she must fall faster as time goes on
	private Rectangle hitBox;       //creates a hitbox so the intersector can mathematically recognize collision
	
	//THERE IS GONNA BE JUMPING not from user input but from game levels ("JUMP PAD")
	
	public static Boolean falling = true;          //mainly needed for falling animation
	private float height;              // needed for height collision calculation
	private float width;               // needed for width collision calculation
	public static Boolean dead;           // needed for making sure game ends once dead (IMPORTANT!!!)
	public static int face =0 ;    // needed for checking whether to make character to face forward or back
	                                              // 0 = face right (towards phone charger [samsung galaxy s3] 1 =  face left 
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	
	//this variable is VERY IMPORTANT to climb stairs
	public static int stair_facing_collide = 0;  // 0 = no stair collides, 1 = right face collide, 2= left face collide
	public static int block_facing_collide = 0;  // 0 = no block collides, 1 = right face collide, 2= left face collide
	private GameWorld world;
	
	public Me(float x, float y, int width,  int height){ //only x/y need to have input because for each new level a new coordinate = given
		                                         // the width and height are important for the collision math calculation
		this.width = width;
		this.height = height;
		position = new Vector2(x,y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		dead = false;
		hitBox = new Rectangle();
		
		
	}
	
	public void update(float delta){
		if(position.y < 224){
			velocity.add(acceleration.cpy().scl(delta)); //.cpy() is IMPORTANT FOR SPECIAL milliseconds to prevent 			
			// the garbage collector from cleaning out "ram" memory 
			///////////////////////////////////////////////////////////////
			if(falling){//if falling is true do this
				if (velocity.y > 200) {
					velocity.y = 200;
				}/////////////////This is for causing object to fall downwards
			}else{ //if falling is false do this (nothing lol)
				velocity.y = 0;	
			}
			position.add(velocity.cpy().scl(delta));
		}else{
			falling = false;
			velocity.y = 0;
			position.y = 224;
		}

        //Above is for falling
        calculateTilt();
        
        hitBox.set(position.x, position.y, width, height); // x/y position + width + height
        //^ THIS SETS THE HITBOX FOR COLLISION
        
	}//END OF UPDATE FOR ME!!!!!!!!!!
	
	
	
	//make void for accelelametor
	public void calculateTilt(){
		//calculate whether it's negative or positive
		if (Gdx.input.getAccelerometerY() > 1){
			face = 0;
			if(position.x+width< GameWorld.right_edge){
				if(block_facing_collide != 1){
					if(stair_facing_collide == 1){
						position.x +=0.5f;
						position.y -=0.7;
					}else{
						position.x += 1.7f;
					}//end of if/else
				}//end of block_facing_collide
			}
		}else if (Gdx.input.getAccelerometerY() < -1){
			face = 1;
			if(position.x > GameWorld.left_edge){
				if(block_facing_collide != 2){
					if(stair_facing_collide == 2){
						position.x -=0.5f;
						position.y -=0.7;
					}else{
						position.x -= 1.7f;  //means no collide btw
					}//end of if/else
				}//end of block_facing_collide if/else
			}
		}
		///below stop it and make it to 0 if its about to go offscren
		
	}
	
	public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
    
    public Rectangle getBoundingBox() {
        return hitBox;
    }
    
    public void reset(float x, float y, int width,  int height){ //this happens once character diessss Good Game LOL
    	this.width = width;
		this.height = height;
		position = new Vector2(x,y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		dead = false;
		hitBox = new Rectangle();
    }

}
