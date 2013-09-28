/*
 * Alien Chase 2013 [SURVIVAL HORROR] Chema Edition 
 * -- a remake of Alien Chase 2013 Chema Edition
 * Copyright (C) 2013 José María Valera Reales <chemaclass@outlook.es> 
 * Twitter: @Chemaclass
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
package tk.makigas.chase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ScreamerScreen extends AbstractScreen {

	public ScreamerScreen(AlienChase game) {
		super(game);
	}

	private float timer;

	@Override
	public void render(float delta) {
		game.SB.begin();
		game.SB.draw(scream, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		game.SB.end();
		timer += delta;
		if (timer >= 4 && Gdx.input.isTouched()) {
			game.setScreen(game.MAIN);
		}
	}

	private Texture scream;

	@Override
	public void show() {
		timer = 0;
		AlienChase.MANAGER.get("sound/scream.wav", Sound.class).play();
		scream = AlienChase.MANAGER.get(
				"images/screamer.png", Texture.class);
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
