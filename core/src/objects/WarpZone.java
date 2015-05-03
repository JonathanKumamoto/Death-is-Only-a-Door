package objects;

import com.badlogic.gdx.math.Vector2;

public class WarpZone extends Entity{
	//point of warp zone is that there will only be two warp zones...
	//if character touches #1 warp zone, he goes to #2 & if he touched #2 then he goes to #1
	protected int zone;

	public WarpZone(int zone, int dimension, Vector2 position, int width, int height) {
		super(dimension, position, width, height);
		this.zone = zone;
	}

}
