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
import com.badlogic.gdx.graphics.Texture;

public class GameOverScreen extends AbstractScreen {

	public GameOverScreen(AlienChase game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		game.SB.begin();
		game.SB.draw(gameover, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.SB.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(game.MAIN);
		}
	}
	
	private Texture gameover;

	@Override
	public void show() {
		gameover = AlienChase.MANAGER.get("gameover.png", Texture.class);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
