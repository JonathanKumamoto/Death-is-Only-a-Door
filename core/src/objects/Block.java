package objects;

import com.badlogic.gdx.math.Vector2;

public class Block extends Entity{
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	//the point of block is once touched, character cannot move into it AT all
	//falling becomes false as well
	//but character cannot move INTO block that is important

	public Block(int dimension, Vector2 position, int width, int height) {
		super(dimension, position, width, height);
	}

}
