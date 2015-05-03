package menu;

import Levels.Level_Default;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.DeathDoor.Intro_scene;

public class Buttons {
	private TextureAtlas atlas;
	private Skin table_skin;
	private Table table;
	private Stage stage;
	private BitmapFont black;
	private Button play,controls;
	private Intro_scene intro;
	public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	
	public Buttons(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false);
		atlas = new TextureAtlas("button/Play Button.pack"); //defines regions of sprite image
		table_skin = new Skin(atlas);
		
		table = new Table(table_skin);
		table.setCenterPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		TextButtonStyle textbuttonstyle= new TextButtonStyle();
		textbuttonstyle.up = table_skin.getDrawable("button-menuP");
		textbuttonstyle.down = table_skin.getDrawable("button-menu");
		textbuttonstyle.pressedOffsetX = -1;
		textbuttonstyle.font = black;
		
		//above sets up button style and below handles putting in the new buttoncs
		
		play = new TextButton("Play", textbuttonstyle);
		play.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				intro.song.stop();
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level_Default());
			}
		});
		
		controls = new TextButton("Tilt On/Off", textbuttonstyle);
		controls.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("options");
			}
		});
		
		
		
		table.add(play);
		//table.add(controls).size(20, 20);
		stage.addActor(table);
		
	}
	
	public void draw(float delta){
		stage.act(delta);
		stage.draw();
	}
	
	public void dispose(){
		stage.dispose();
		table.clear();
		black.dispose();
		atlas.dispose();
		table_skin.dispose();
		System.gc();
	}

}
