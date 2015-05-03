package input_handler;

import menu.Main_Menu;
import world_handler.GameWorld;
import world_handler.Restarter;
import Levels.Level_Default;
import assests.Assets_Level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.DeathDoor.Intro_scene;

public class DOD_Handler implements InputProcessor {
	private GameWorld world; //the only thing handler affects is WORLD and that's IT no other classes
	private Restarter reset;
	private Intro_scene level;
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public DOD_Handler(GameWorld theworld, Restarter R){
		world = theworld;
		reset = R;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(screenX < (Gdx.graphics.getWidth()*0.0386f) & screenY <(Gdx.graphics.getHeight()*0.0772f)){
			//Whats the point of the weird math????
			///When screen is UNCHANGED a (15,15) sized box is fixed for reading pixel cursor placement
			//but when is CHANGED it expands so 15 divided 272 = ~~0.0551f to be fitted into the box
			reset.RESTART();
			level.intro_song.stop();
			level.Level10.stop();
			level.Welcome.stop();
			((Game)Gdx.app.getApplicationListener()).setScreen(new Main_Menu());
		//}else if(screenX < (Gdx.graphics.getWidth()*0.0386f*5) & 
			//screenX > (Gdx.graphics.getWidth()*0.0386f*4) 
			//	& screenY <(Gdx.graphics.getHeight()*0.0772f)){ //this is just the height box which is easy
			//	world.getMe().dead=true;
			}else if(screenX < (Gdx.graphics.getWidth()*0.0386f*3) & 
					screenX > (Gdx.graphics.getWidth()*0.0386f*2) 
					& screenY <(Gdx.graphics.getHeight()*0.0772f)){
				reset.RESTART();
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level_Default());
			}else{
				world.onclick();
		}
		return true;
	}
	@Override
	public boolean keyDown(int keycode) { //for now the only important variable to change dimension
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
