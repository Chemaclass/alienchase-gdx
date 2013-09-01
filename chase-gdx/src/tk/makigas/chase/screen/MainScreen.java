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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainScreen extends AbstractScreen {

	private Texture titulo;

	private Texture btnJugar;

	private Texture btnSalir;

	private Stage stage;

	public MainScreen(AlienChase game) {
		super(game);
	}

	@Override
	public void show() {
		titulo = AlienChase.MANAGER.get("title.png", Texture.class);
		btnJugar = AlienChase.MANAGER.get("jugar.png", Texture.class);
		btnSalir = AlienChase.MANAGER.get("salir.png", Texture.class);

		stage = new Stage(640, 360, true, game.getSpriteBatch());
		
		Image imgFondo = new Image(titulo);
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		
		Image imgJugar = new Image(btnJugar);
		imgJugar.setBounds(10, 100, 300, 75);
		imgJugar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(game.GAMEPLAY);
				return true;
			}
		});
		stage.addActor(imgJugar);
		
		Image imgSalir = new Image(btnSalir);
		imgSalir.setBounds(330, 100, 300, 75);
		imgSalir.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});
		stage.addActor(imgSalir);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}
	
	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}
