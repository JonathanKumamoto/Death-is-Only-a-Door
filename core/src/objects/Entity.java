package objects;

import main_character.Me;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity { //this class is designed for inheritance for game objects 
	                            //EXAMPLE: death spike, platform to stand on, stairs
	
	private Me character;  //THIS IS NEEDED TO CALCULATE EACH ENTITIES COLLISION BOX
	
	protected Vector2 pos;//reason for proctection is for other inherited class to have access
	protected Rectangle box; //this is needed to create the HITBOX
	protected int e_dimension; // 0 = first 1=2nd 2= both dimensions
	protected int height;
	protected int width;
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public Entity(int dimension, Vector2 position, int width, int height){
		this.pos = position; //vector 2 takes x and y so it's two variables inside a ()
		this.width = width; //width for box
        this.height = height; //height for box
		box = new Rectangle();  //this is the hitBOX for the rectangle
		this.e_dimension = dimension;  //this is to keep track of its dimension
	}
	
	public Vector2 getPosition(){ //important to return position for collision
		return pos; 
		
	}
	public int getDimension(){
		return  e_dimension;
	}

	public void update(int current_dimension) { //this just resets the position if needed to change or stabilize the hit box/collision GG
		if(e_dimension == current_dimension){
			box.set(pos.x, pos.y, width, height);
		}else if(e_dimension == 2){
			box.set(pos.x, pos.y, width, height);
		}else{
			box.set(0,0,0,0);
		}
	}
	
	public boolean collides(Me character){
		 return (Intersector.overlaps(character.getBoundingBox(), box));
	}
	
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Rectangle returnSpace(){//this is supposed to just return dimensions for specific entity
		return box; 
	}

	public void restart(){
		//leave blank for now this is going to be needed for moving objects (ex: moving saw to kill Me.java)
	}

}
