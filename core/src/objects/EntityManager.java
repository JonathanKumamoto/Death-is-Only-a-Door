package objects;

import main_character.Me;
import assests.Assest_Manager;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EntityManager { //Entity Manager needs to be specific for each level (maybe change this later????)
	                                           //changing this later means general manager to allow the screen to create objects
	
	private final Array<Entity> entities = new Array<Entity>(); //this allows a new entity array rather the an array
	private final Array<Triangle> stair_entities = new Array<Triangle>(); //this allows a new entity array rather the an array
	private final  Array<Saw> kill_entities = new Array <Saw>();
	private final  Array<Block> block_entities = new Array <Block>();
	private final  Array<WarpZone> warp_entities = new Array <WarpZone>();
	private final  Array<Jumper> jump_entities = new Array <Jumper>();
	private Sound jump =  Assest_Manager.manager.get(Assest_Manager.jump, Sound.class);
	 public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	private int timer = 0;
	
	public EntityManager(){
		//leave blank EACH level_Screen will handle adding objects :D! Easy right?
		//makes it so this is just a general object manager thus there is no need for creating anything here
		//thanks to the 3 "addObject/addS_Object/addK_Object" methods
	}
	
	public void update(int CURRENT_DIMENSION, Me character){ //CURRENT_DIEMNSION = state of game dimension
		boolean contact = false;
		boolean stairs_contact = false;
		boolean block_contact = false;
		for(Entity e : entities){
			e.update(CURRENT_DIMENSION);
			if(e.collides(character)){ //this means that character has collided  [works perfect as of "9/11 1:34pm"]
				character.falling = false;
				
				if(character.getY() +character.getHeight()<=e.getPosition().y+e.getHeight()){  //this is meant for FEET ABOVE PLATform
					contact = true;
				}
			}
			
		}// this is the end of the updating for entities and checking boolean for falling
		
		for(Triangle t : stair_entities){   //this controls stairs + whether character should go up+++
			t.update(CURRENT_DIMENSION);
			
			if(t.getDimension() == CURRENT_DIMENSION & t.inside(character)){
				
				contact = true;
				character.dead = true;
				
			}else if(t.collides(character)){ //this means that character has collided  [works perfect as of "9/11 1:34pm"]
				character.falling = false;
				contact = true;
				stairs_contact = true;
				if(character.getX() == t.getPosition().x){
					character.stair_facing_collide = 0;
				}else if(character.getX() < t.getPosition().x){
					character.stair_facing_collide = 1;
				}else if(character.getX() > t.getPosition().x){
					character.stair_facing_collide = 2;
				}
				
			}//end of if stairs collide with ME
		} //this is the end of updating all the stairs and checking boolean statement of falling
		
		for(Saw s: kill_entities){
			s.update(CURRENT_DIMENSION);
			if(s.collides(character)){ //this means the saw has hit the character
				contact = true;
				character.dead = true; // this makes it so the character has finally DIED (move on to next stage)
			}
		} //end of saw 
		
		for(Jumper J: jump_entities){
			J.update(CURRENT_DIMENSION);
			if(J.collides(character)){ //this means the saw has hit the character
				jump.play();
				character.velocity.y = -260; // this makes it so the character can jump
				character.position.y -=5;
				
				//guy must hit it with no ground or platform/stair contact!!!
			}
		} //end of jumper
		
		for(Block B : block_entities){
			B.update(CURRENT_DIMENSION);
			if(B.collides(character)){ //this means the character has contact with the block
				block_contact = true;
				contact = true;
				if(character.getX() == 	B.getPosition().x+B.width/2){
					character.block_facing_collide = 0;
				}else if(character.getX() < B.getPosition().x+B.width/2){
					character.block_facing_collide = 1;
				}else if(character.getX() > B.getPosition().x+B.width/2){
					character.block_facing_collide = 2;
				}
				
			}
		} //end of block
		
		
		if(contact == false){  //this means character is free from touching any objects & charater should fall
			if (character.getY() <224){
				character.falling= true;
			}
		}
		if(block_contact== false){
			character.block_facing_collide = 0;
		}
		if(stairs_contact == false){ //
			character.stair_facing_collide = 0;
		} //nearing the end of update method
		
	}//END OF UPDATE
	public Array<Entity> render(){ // supposed to return a list/array for the Level_"level" to render() (ENTITIES ONLY)
		return entities;
	}
	
	public Array<Triangle> render_stairs(){ // supposed to return a list/array for the Level_"level" to render() (STAIRS ONLY)
		return stair_entities;
	}
	
	public Array<Saw> render_saws(){
		return kill_entities;
	}
	
	public Array<Block> render_blocks(){
		return block_entities;
	}
	
	public Array<WarpZone> render_warps(){
		return warp_entities;
	}
	
	public Array <Jumper> render_jumps(){
		return jump_entities;
	}
	
	public void addObject(Entity object){ //adds object to the array of entities
		entities.add(object);
	}
	
	public void addS_Object(Triangle stair){  //adds object to the array of triangles/stairs
		stair_entities.add(stair);
	}
	
	public void addK_Object(Saw killer){  //adds object to the array of triangles/stairs
		kill_entities.add(killer);
	}
	
	public void addB_Object(Block block){
		block_entities.add(block);
	}
	
	public void addW_Object(WarpZone warp){
		warp_entities.add(warp);
	}
	
	public void addJ_Object(Jumper jump){
		jump_entities.add(jump);
	}
	
	public void reset(){
		entities.clear();
		stair_entities.clear();
		kill_entities.clear();
		block_entities.clear();
		warp_entities.clear();
		jump_entities.clear();
	}

}
