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

public class Intro_scene implements Screen{
	
	private float countdown;
	private float frames = -272;
	private SpriteBatch batch;
	public static Music song =  Assest_Manager.manager.get(Assest_Manager.theme_song, Music.class);
	private OrthographicCamera camera;
	private Sprite sprite;
	private Sound D2 =  Assest_Manager.manager.get(Assest_Manager.changing_dimension2, Sound.class);
	public static Music intro_song =  Assest_Manager.manager.get(Assest_Manager.beginnings, Music.class);
	public static Music Level10 = Assest_Manager.manager.get(Assest_Manager.realization, Music.class);
	public static Music Welcome = Assest_Manager.manager.get(Assest_Manager.tutorial, Music.class);
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public Intro_scene(){
		song.play();
		
		
		camera = new OrthographicCamera(544, 272); //this is the setup for the LANDSCAPE CAMERA
		camera.setToOrtho(true, 544, 272);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		sprite = new Sprite(Assest_Manager.intro_scene);
		sprite.flip(false, true);
		
		
		Gdx.input.setInputProcessor(new INTRO_Handler(this));
		//do things here
	}
	
	public void clicked(){
		//do things here when screen is clicked during introduction
		D2.play();
		((Game)Gdx.app.getApplicationListener()).setScreen(new Main_Menu());
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 0);  /// this color is black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sprite, 0, frames, 544, 272);
		batch.end();
		camera.update();
		if(frames < 0){
			frames += 0.5f;			
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
		Assest_Manager.manager.unload(Assest_Manager.INTRO);
		System.gc();
		
	}

}
