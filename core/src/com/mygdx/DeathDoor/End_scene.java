package com.mygdx.DeathDoor;

import input_handler.INTRO_Handler;
import menu.Main_Menu;
import assests.Assest_Manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class End_scene implements Screen{
	
	private float timeState;
	private float frames = 272;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Sprite sprite;
	private Music end_song =  Assest_Manager.manager.get(Assest_Manager.ending, Music.class);
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public End_scene(){
		end_song.play();
		
		
		camera = new OrthographicCamera(544, 272); //this is the setup for the LANDSCAPE CAMERA
		camera.setToOrtho(true, 544, 272);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		sprite = new Sprite(Assest_Manager.end_scene);
		sprite.flip(false, true);
		
		//do things here
	}
	
	public void clicked(){
		//do things here when screen is clicked during introduction
		((Game)Gdx.app.getApplicationListener()).setScreen(new Main_Menu());
	}

	@Override
	public void render(float delta) {
		timeState+= delta;
		System.out.println(frames);
		Gdx.gl.glClearColor(0, 0, 0, 0);  /// this color is black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(Assest_Manager.Menu_anime.getKeyFrame(timeState),
				 0, 0, 544, 272);
		batch.draw(sprite, 0, frames, 544, 816);
		batch.end();
		camera.update();
		if(frames > -816){
			frames -= 1;			
		}
		if(frames == -816){
			end_song.stop();
			clicked();
		}
		
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
		batch.dispose();
		System.gc();
		
	}

}
