package com.badlogic.drop;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.RemoteInput;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.TableLayout;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.InputProcessor;

import java.lang.StringBuilder.*;
import java.lang.reflect.*;

public class MainScreenMenu implements Screen, InputProcessor {
	final Drop game;
	Music rainMusic;
	OrthographicCamera camera;
	long lastDropTime;
	Field[] _Keys = Keys.class.getFields();
	char c_keyTyped = (char)2;
	int var_iter;

	String str_highscore = "Kraken";
	//var_iter = str_highscore.length();
	
	/*private InputMultiplexer multiPlexer;
	multiPlexer.addProcessor(keyboardProcessor);
	    multiPlexer.addProcessor(stage);
	    Gdx.input.setInputProcessor(multiPlexer);*/
	
	/*New Menu Add*/
	/*Skin skin;
	Stage stage;
    SpriteBatch batch;
	 */
	
    String[] myStringArray = {"New game", "Score board", "Instructions", "Exit"};
	int menu_choose = 0;
    
	public void create() {
		Gdx.input.setInputProcessor(this);
	}
	
	public MainScreenMenu(final Drop gam) {
		game = gam;
		Gdx.input.setOnscreenKeyboardVisible(true);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/o-rally.mp3"));
		rainMusic.setLooping(true);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		/*TODO 
		 * -Add interactive menu with:
		 * 		New game
		 * 		Score board
		 * 		Instructions
		 * 		Exit
		 * */

		game.batch.begin();
		game.font.draw(game.batch, myStringArray[0], 100, 125);
		game.font.draw(game.batch, myStringArray[1], 100, 100);
		game.font.draw(game.batch, myStringArray[2], 100, 75);
		game.font.draw(game.batch, myStringArray[3], 100, 50);
		
		//c_keyTyped = var_iter;
		//game.font.draw(game.batch, "Key typed: " + (char)var_iter, 0, 465);
		game.font.draw(game.batch, "Key typed: " + var_iter + str_highscore, 0, 465);
		
		
		game.font.draw(game.batch, "~", 75, (125 - (25 * menu_choose)));
		
		game.batch.end();

		var_iter = 0;
		if (TimeUtils.nanoTime() - lastDropTime > 300000000) {
			/*Read keyboard input for scoreboard*/
			for (int i = 29; i < 73; i++) { if (Gdx.input.isKeyPressed(i)) {var_iter = i; break;} }
			var_iter += 36; 
			if ((var_iter >= 65) && (var_iter <= 90)) {
				lastDropTime = TimeUtils.nanoTime();
				str_highscore += (char)var_iter;
			}
			if (var_iter == 103) {
				if(str_highscore.length() > 0) {
					lastDropTime = TimeUtils.nanoTime();
					str_highscore = str_highscore.substring(0, str_highscore.length() - 1);
				}
			}
			/*End reading input for scoreboard*/
			
			
			/* Navigation menu handler for keyboard */
			if (Gdx.input.isKeyPressed(Keys.UP)) { //Keyevent.UP;
				lastDropTime = TimeUtils.nanoTime();
				if(menu_choose == 0) {
					menu_choose = myStringArray.length-1; //Begins with 1
				} else {
					menu_choose -= 1;
				}
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				lastDropTime = TimeUtils.nanoTime();
				if(menu_choose == 3) {
					menu_choose = 0;
				} else {
					menu_choose += 1;
				}
			}
			/* Generate a new screen depending on the chosen  */
			if (Gdx.input.isKeyPressed(Keys.ENTER)) {
				lastDropTime = TimeUtils.nanoTime();
				switch(menu_choose) {
					case 0: game.setScreen(new GameScreenPre(game));
						break;
					case 1: game.setScreen(new ScoreBoard(game));
						break;
					case 2: game.setScreen(new Instructions(game));
						break;
					case 3: System.exit(0);
						break;						
				}
				dispose();
			}
			
			
			/* TODO Create a loop that controls which button that has been pressed
			 * Add field conv
			 * Connect field with 
			 * 
			 * */		

				



		}
	}
	
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
		rainMusic.play();
	}
 
	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
	}

	@Override
	public boolean keyDown(int keycode) {
		//Gdx.app.log("Input Test", "key down: " + keycode);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		//Gdx.app.log("Input Test", "key up: " + keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		//game.font.draw(game.batch, "Key typed: " + character, 0, 465);
		//Gdx.app.log("Input Test", "key typed: '" + character + "'");
		//c_keyTyped = character;
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}	
}