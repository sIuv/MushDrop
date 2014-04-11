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

public class Instructions implements Screen {
	  final Drop game;
	  OrthographicCamera camera;
	  
	public Instructions(final Drop gam) {
	
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		//rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/o-rally.mp3"));
		//rainMusic.setLooping(true);
	}

	@Override
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
		
		game.font.draw(game.batch, "Instructions", 200, 350);
		game.font.draw(game.batch, "Keep it cool", 200, 325);
		game.font.draw(game.batch, "...", 200, 300);
		game.font.draw(game.batch, "...", 200, 275);
		
		//game.font.draw(game.batch, "~", 75, (125 - (25 * menu_choose)));
		
		game.batch.end();

		
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			game.setScreen(new MainScreenMenu(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}