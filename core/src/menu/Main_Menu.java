package menu;

import assests.Assest_Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.DeathDoor.Intro_scene;

public class Main_Menu implements Screen {
	private Buttons button;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private float timeState;
	private Intro_scene intro;
	 public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	
	public Main_Menu(){
		//create things inside this for class
		button = new Buttons();
		camera = new OrthographicCamera(544, 272); //this is the setup for the LANDSCAPE CAMERA
		camera.setToOrtho(true, 544, 272);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		intro.song.play();
		
	}

	@Override
	public void render(float delta) {
		timeState += delta;
		Gdx.gl.glClearColor(0, 0.2f, 0, 0);  /// this color is black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		batch.begin();
		batch.draw(Assest_Manager.Menu_anime.getKeyFrame(timeState),
				 0, 0, 544, 272);
		batch.end();
		button.draw(delta);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		button.dispose();

	}

}
