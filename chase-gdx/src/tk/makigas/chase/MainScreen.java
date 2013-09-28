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

	private Texture btnEfectosOff, btnEfectosOn;

	private Texture btnFondoOff, btnFondoOn;

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
		btnEfectosOff = new TextureRegion(AlienChase.MANAGER.get(
				"images/efectosOff.png", Texture.class), 0, 0, 90, 32)
				.getTexture();
		btnEfectosOn = new TextureRegion(AlienChase.MANAGER.get(
				"images/efectosOn.png", Texture.class), 0, 0, 90, 32)
				.getTexture();
		btnFondoOff = new TextureRegion(AlienChase.MANAGER.get(
				"images/fondoOff.png", Texture.class), 0, 0, 90, 32)
				.getTexture();
		btnFondoOn = new TextureRegion(AlienChase.MANAGER.get(
				"images/fondoOn.png", Texture.class), 0, 0, 90, 32)
				.getTexture();

		Image imgFondo = new Image(titulo);
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);

		implementarJugarSalir();

		implementarbtnEfectosSonido();

		implementarbtnSonidoFondo();

		Gdx.input.setInputProcessor(stage);
	}

	private void implementarJugarSalir() {
		Image imgJugar = new Image(btnJugar);

		imgJugar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(game.GAMEPLAY);
				return true;
			}
		});

		Image imgSalir = new Image(btnSalir);

		imgSalir.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});

		imgJugar.setBounds(180, 150, 300, 75);
		imgSalir.setBounds(520, 150, 300, 75);

		stage.addActor(imgJugar);
		stage.addActor(imgSalir);
	}

	private void implementarbtnEfectosSonido() {
		final Image imgEfectosSonidoOff = new Image(btnEfectosOff);
		final Image imgEfectosSonidoOn = new Image(btnEfectosOn);

		imgEfectosSonidoOff.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AlienChase.MANAGER.get("sound/shoot.ogg", Sound.class).play();
				GameplayScreen.setSonidoEfectos(true);
				imgEfectosSonidoOn.setVisible(true);
				return true;
			}
		});
		imgEfectosSonidoOn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameplayScreen.setSonidoEfectos(false);
				imgEfectosSonidoOn.setVisible(false);
				return true;
			}
		});
		float x = 380, y = 100, w = 140, h = 30;

		imgEfectosSonidoOff.setBounds(x, y, w, h);
		imgEfectosSonidoOn.setBounds(x, y, w, h);

		stage.addActor(imgEfectosSonidoOff);
		stage.addActor(imgEfectosSonidoOn);
		// Jugaremos con la imagen del sonido ON que estará superpuesta
		// a la imagen del sonido OFF. Mostraremos ON cuando el sonido
		// sea true, y cuando sea OFF pondremos la imagen ON a visible=false
		// para que se vea la imagen que tiene detrás, que es la de OFF.
		imgEfectosSonidoOn.setVisible(GameplayScreen.isSonidoEfectos());
	}

	private void implementarbtnSonidoFondo() {
		final Image imgSonidoFondoOff = new Image(btnFondoOff);
		final Image imgSonidoFondoOn = new Image(btnFondoOn);

		imgSonidoFondoOff.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameplayScreen.setSonidoFondo(true);
				imgSonidoFondoOn.setVisible(true);
				AlienChase.MANAGER.get("sound/loop-game.mp3", Sound.class)
						.loop(0.6f);
				return true;
			}
		});
		imgSonidoFondoOn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameplayScreen.setSonidoFondo(false);
				imgSonidoFondoOn.setVisible(false);
				AlienChase.MANAGER.get("sound/loop-game.mp3", Sound.class)
						.stop();
				return true;
			}
		});
		float x = 180, y = 100, w = 140, h = 30;

		imgSonidoFondoOff.setBounds(x, y, w, h);
		imgSonidoFondoOn.setBounds(x, y, w, h);

		stage.addActor(imgSonidoFondoOff);
		stage.addActor(imgSonidoFondoOn);
		// Jugaremos con la imagen del sonido ON que estará superpuesta
		// a la imagen del sonido OFF. Mostraremos ON cuando el sonido
		// sea true, y cuando sea OFF pondremos la imagen ON a visible=false
		// para que se vea la imagen que tiene detrás, que es la de OFF.
		imgSonidoFondoOn.setVisible(GameplayScreen.isSonidoFondo());
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
