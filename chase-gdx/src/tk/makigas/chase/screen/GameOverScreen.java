/*
 * Alien Chase 2013 -- a remake of Alien Chase 2012 (also developed by me)
 * Copyright (C) 2012, 2013 Dani Rodríguez <danirod@outlook.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tk.makigas.chase.screen;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameOverScreen extends AbstractScreen {

	private int width, height;
	
	private OrthographicCamera cam;
	
	public GameOverScreen(AlienChase game) {
		super(game);
		cam = new OrthographicCamera();
	}

	@Override
	public void render(float delta) {
		cam.update();
		cam.apply(Gdx.gl10);
		game.sb.setProjectionMatrix(cam.combined);
		game.sb.begin();
		game.sb.draw(gameover, 0, 0, width, height);
		game.sb.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(game.MAIN);
		}
	}
	
	private Texture gameover;

	@Override
	public void show() {
		gameover = AlienChase.MANAGER.get("gameover.png", Texture.class);
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		cam.setToOrtho(false, width, height);
	}
}
