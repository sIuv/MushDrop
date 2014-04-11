package com.badlogic.drop;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
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

public class MainScreenMenu implements Screen {
	final Drop game;
	Music rainMusic;
	OrthographicCamera camera;
	long lastDropTime;
	
	/*New Menu Add*/
	/*Skin skin;
	Stage stage;
    SpriteBatch batch;
	 */
	
    String[] myStringArray = {"New game", "Score board", "Instructions", "Exit"};
	int menu_choose = 0;
    
	public MainScreenMenu(final Drop gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/o-rally.mp3"));
		rainMusic.setLooping(true);
	}
	
	public void Create() {
		
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
		
		game.font.draw(game.batch, "~", 75, (125 - (25 * menu_choose)));
		
		game.batch.end();

		
		if (TimeUtils.nanoTime() - lastDropTime > 300000000) {
			/* Navigation menu handler for keyboard */
			if (Gdx.input.isKeyPressed(Keys.UP)) { //Keyevent.UP;
				lastDropTime = TimeUtils.nanoTime();
				if(menu_choose == 0) {
					menu_choose = myStringArray.length;
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
					case 0: game.setScreen(new GameScreen(game));
						break;
					case 1: game.setScreen(new ScoreBoard(game));
						break;
					case 2: game.setScreen(new Instructions(game));
						break;
					case 3: //game.setScreen(new Exit(game));
						break;						
				}
				dispose();
			}
		}
		
		/*if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}*/
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
}