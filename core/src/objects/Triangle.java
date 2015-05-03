package objects;

import main_character.Me;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Triangle{ //this does not allow player to go inside of it like a platform
	
	private Me character;  //THIS IS NEEDED TO CALCULATE EACH ENTITIES COLLISION BOX
	
	protected Polygon stairs; //this is going to be the inherited style for other similar classes (like upside-down stairs)
	protected Vector2 pos;
	protected int t_dimension; // 0 = first 1=2nd 2= both dimensions
	private float[] VERTICES;  //this is going to keep relevant information of constructing the triangles
	private float[] blank = {0,0,0,0,0,0};
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	

	public Triangle(int dimension, Vector2 position, float[] vertices) { //draw a triangle instead of a box
		this.VERTICES = vertices;
		this.pos = position;               //VERY IMPORTANT!!!! the TIP of the triangle must be this position
		stairs = new Polygon();
		this.t_dimension = dimension;
	}
	
	public float[] getVertices(){ //important to return position for collision
		return VERTICES; 
	}
	public Vector2 getPosition(){
		return pos;
	}
	
	public int getDimension(){
		return  t_dimension;
	}
	
	
	public void update(int current_dimension) { //stairs DO NOT MOVE!!! But may be needed to change dimensions OR allow for
										 //unique calculation in the future (think ahead of what possibilities this could offer)
		stairs.setVertices(VERTICES);
		if(t_dimension == current_dimension){
			stairs.setVertices(VERTICES);
		}else if(t_dimension == 2){
			stairs.setVertices(VERTICES);
		}else{
			stairs.setVertices(blank);
		}
	}
	
	public Polygon returnSpace(){//this is supposed to just return dimensions for specific entity (drawing it)
		return stairs; 
	}
	
	public boolean collides(Me c){ //CALCULATED WRAPPED POLYGON COUNTER CLOCKWISE IMPORTANT!!!
       Polygon character_poly = new Polygon();
       character_poly.setVertices(new float [] {c.getX(), c.getY(),c.getX(),c.getY()+c.getHeight(),c.getX()+c.getWidth(),c.getY()+c.getHeight(), c.getX()+c.getWidth(), c.getY()});

	   return Intersector.overlapConvexPolygons(stairs, character_poly);
	}
	
	public boolean inside(Me c){ //CALCULATED WRAPPED POLYGON COUNTER CLOCKWISE IMPORTANT!!!
													 //also important, decrease box hitbox by a few pixels to determine if character
		                                             //is TOO much inside a triangle to kill it (when triangle comes back into dimension)
													 //for now, the pixel are specific but will be changed to correct & direct math
	      
		   return Intersector.isPointInTriangle(c.getX()+c.getWidth()/2,c.getY()+c.getHeight()/2,VERTICES[0],
				   VERTICES[1], VERTICES[2], VERTICES[3], VERTICES[4],
				   VERTICES[5]);
		}
	
	public void restart(){
		//leave blank for now this is going to be needed for moving objects (ex: moving saw to kill Me.java)
	}

}
