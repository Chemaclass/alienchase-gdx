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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Juego de aliens.
 * 
 * @author danirod
 * @author chemaclass
 */
public class AlienChase extends Game {
	
	/** Gestor de recursos usado por el juego. */
	public static final AssetManager MANAGER = new AssetManager();
	
	public SpriteBatch SB;
		
	public final AbstractScreen GAMEOVER, GAMEPLAY, LOADING, MAIN;
		
	public AlienChase() {
		GAMEOVER = new GameOverScreen(this);
		GAMEPLAY = new GameplayScreen(this);
		LOADING = new LoadingScreen(this);
		MAIN = new MainScreen(this);
	}

	@Override
	public void create() {
		SB = new SpriteBatch();
		
		// Cargamos todos los elementos externos que usará el juego.
		MANAGER.load("cargando.png", Texture.class);
		MANAGER.load("alien.gif", Texture.class);
		MANAGER.load("balaNave.png", Texture.class);
		MANAGER.load("balaAlien.png", Texture.class);
		MANAGER.load("cohete.png", Texture.class);
		MANAGER.load("defensa.png", Texture.class);
		MANAGER.load("muro.png", Texture.class);
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
		MANAGER.load("sonidoOn.png", Texture.class);
		MANAGER.load("sonidoOff.png", Texture.class);
		MANAGER.load("txt.png", Texture.class);
		
		setScreen(LOADING);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
		SB.dispose();
	}
	
	public static int random(int inf, int sup) {
        int posibilidades = sup - inf;
        double a = Math.random() * posibilidades;
        return (int) Math.round(a) + inf;
    }
	
}
