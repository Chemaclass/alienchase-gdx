/*
 * Alien Chase 2013 Chema Edition -- a remake of Alien Chase 2013 by danirod
 * Copyright (C) 2013 Dani Rodríguez <danirod@outlook.com> Twitter: @danirod93
 * & José María Valera Reales <chemaclass@outlook.es> Twitter: @Chemaclass
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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * @author danirod
 * @author chemaclass
 * 
 */
public class MainScreen extends AbstractScreen {

	private Texture titulo;

	private Texture btnJugar;

	private Texture btnSalir;

	private Texture btnEfectosSonidoOff, btnEfectosSonidoOn;

	private Stage stage;
	
	public MainScreen(AlienChase game) {
		super(game);
	}

	@Override
	public void show() {
		stage = new Stage(1024, 512, true, game.SB);

		titulo = AlienChase.MANAGER.get("images/title.png", Texture.class);
		btnJugar = AlienChase.MANAGER.get("images/jugar.png", Texture.class);
		btnSalir = AlienChase.MANAGER.get("images/salir.png", Texture.class);
		btnEfectosSonidoOff = new TextureRegion(AlienChase.MANAGER.get(
				"images/sonidoOff.png", Texture.class), 0, 0, 90, 32)
				.getTexture();
		btnEfectosSonidoOn = new TextureRegion(AlienChase.MANAGER.get(
				"images/sonidoOn.png", Texture.class), 0, 0, 90, 32)
				.getTexture();
		
		implementarFondo();	
		
		implementarJugarSalir();
		
		implementarSonido();

		Gdx.input.setInputProcessor(stage);
	}

	private void implementarFondo() {
		Image imgFondo = new Image(titulo);
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);		
	}

	private void implementarJugarSalir() {
		Image imgJugar = new Image(btnJugar);
		imgJugar.setBounds(200, 150, 350, 75);
		imgJugar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AlienChase.MANAGER.get("sound/campana.ogg", Sound.class).play();			
				game.setScreen(game.GAMEPLAY);
				return true;
			}
		});
		stage.addActor(imgJugar);

		Image imgSalir = new Image(btnSalir);
		imgSalir.setBounds(500, 100, 300, 75);
		imgSalir.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});
		stage.addActor(imgSalir);
	}

	private void implementarSonido() {
		final Image imgEfectosSonidoOff = new Image(btnEfectosSonidoOff);
		final Image imgEfectosSonidoOn = new Image(btnEfectosSonidoOn);

		imgEfectosSonidoOff.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AlienChase.MANAGER.get("sound/shoot.ogg", Sound.class).play(0.3f);
				GameplayScreen.setSoundEffects(true);
				imgEfectosSonidoOn.setVisible(true);
				return true;
			}
		});
		imgEfectosSonidoOn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AlienChase.MANAGER.get("sound/campana.ogg", Sound.class).play();
				GameplayScreen.setSoundEffects(false);
				imgEfectosSonidoOn.setVisible(false);
				return true;
			}
		});
		imgEfectosSonidoOff.setBounds(360, 100, 140, 30);
		imgEfectosSonidoOn.setBounds(360, 100, 140, 30);
		stage.addActor(imgEfectosSonidoOff);
		stage.addActor(imgEfectosSonidoOn);
		// Jugaremos con la imagen del sonido ON que estará superpuesta
		// a la imagen del sonido OFF. Mostraremos ON cuando el sonido
		// sea true, y cuando sea OFF pondremos la imagen ON a visible=false
		// para que se vea la imagen que tiene detrás, que es la de OFF.
		imgEfectosSonidoOn.setVisible(GameplayScreen.isSoundEffects());
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

	}

}
