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
package tk.makigas.chase;

import tk.makigas.chase.screen.AbstractScreen;
import tk.makigas.chase.screen.GameOverScreen;
import tk.makigas.chase.screen.GameplayScreen;
import tk.makigas.chase.screen.LoadingScreen;
import tk.makigas.chase.screen.MainScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Juego de aliens.
 * 
 * @author danirod
 */
public class AlienChase extends Game {
	
	/** Gestor de recursos usado por el juego. */
	public static final AssetManager MANAGER = new AssetManager();
	
	/**
	 * SpriteBatch compartido por los distintos estados del juego.
	 * Se declara aquí porque varios estados necesitan tener acceso a
	 * este recurso y no es recomendable crear múltiples SpriteBatches. 
	 */
	private SpriteBatch sb;
	
	/** Cámara compartida por los distintos estados del juego. */
	private OrthographicCamera camera;
		
	public final AbstractScreen GAMEOVER, GAMEPLAY, LOADING, MAIN;
	
	public AlienChase() {
		GAMEOVER = new GameOverScreen(this);
		GAMEPLAY = new GameplayScreen(this);
		LOADING = new LoadingScreen(this);
		MAIN = new MainScreen(this);
	}

	@Override
	public void create() {
		int width, height;
		
		sb = new SpriteBatch();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width, height);
		
		// Cargamos todos los elementos externos que usará el juego.
		MANAGER.load("cargando.png", Texture.class);
		MANAGER.load("alien.gif", Texture.class);
		MANAGER.load("bala.png", Texture.class);
		MANAGER.load("cohete.png", Texture.class);
		MANAGER.load("defensa.png", Texture.class);
		MANAGER.load("fondo.png", Texture.class);
		MANAGER.load("pad.png", Texture.class);
		MANAGER.load("vida.png", Texture.class);
		MANAGER.load("hit.ogg", Sound.class);
		MANAGER.load("explosion.ogg", Sound.class);
		MANAGER.load("shoot.ogg", Sound.class);
		MANAGER.load("gameover.png", Texture.class);
		MANAGER.load("title.png", Texture.class);
		MANAGER.load("jugar.png", Texture.class);
		MANAGER.load("salir.png", Texture.class);
		
		setScreen(LOADING);
	}
	
	/**
	 * Recupera la instancia compartida de SpriteBatch
	 * @return SpriteBatch en uso por el juego
	 */
	public SpriteBatch getSpriteBatch() {
		return sb;
	}

	/**
	 * Recupera la instancia compartida de OrthograhpicCamera
	 * @return cámara en uso por el juego.
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);	// llamada a super obligatoria
										// (no sabemos si el estado actual
										// podría necesitarlo)
		getCamera().setToOrtho(false, width, height);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
		getSpriteBatch().dispose();
	}
	
}
