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
		
	public final AbstractScreen GAMEOVER, GAMEPLAY, LOADING, MAIN, SCREAMER;
		
	public AlienChase() {
		GAMEOVER = new GameOverScreen(this);
		GAMEPLAY = new GameplayScreen(this);
		LOADING = new LoadingScreen(this);
		MAIN = new MainScreen(this);
		SCREAMER = new ScreamerScreen(this);
	}

	@Override
	public void create() {
		SB = new SpriteBatch();
		
		// Cargamos todos los elementos externos que usará el juego.
		MANAGER.load("images/cargando.png", Texture.class);
		MANAGER.load("images/alien.gif", Texture.class);
		MANAGER.load("images/balaNave.png", Texture.class);
		MANAGER.load("images/balaAlien.png", Texture.class);
		MANAGER.load("images/nave.png", Texture.class);
		MANAGER.load("images/defensa.png", Texture.class);
		MANAGER.load("images/muro.png", Texture.class);
		MANAGER.load("images/fondo.png", Texture.class);
		MANAGER.load("images/pad.png", Texture.class);
		MANAGER.load("images/vida.png", Texture.class);		
		MANAGER.load("images/gameover.png", Texture.class);
		MANAGER.load("images/title.png", Texture.class);
		MANAGER.load("images/jugar.png", Texture.class);
		MANAGER.load("images/salir.png", Texture.class);
		MANAGER.load("images/sonidoOn.png", Texture.class);
		MANAGER.load("images/sonidoOff.png", Texture.class);
		MANAGER.load("images/txt.png", Texture.class);
		MANAGER.load("images/sangre.png", Texture.class);
		MANAGER.load("images/screamer.png", Texture.class);

		MANAGER.load("sound/hit.ogg", Sound.class);
		MANAGER.load("sound/explosion.ogg", Sound.class);
		MANAGER.load("sound/shoot.ogg", Sound.class);
		MANAGER.load("sound/corazon.ogg", Sound.class);
		MANAGER.load("sound/campana.ogg", Sound.class);
		MANAGER.load("sound/fondo.ogg", Sound.class);
		MANAGER.load("sound/siguienteNivel.ogg", Sound.class);
		MANAGER.load("sound/gameOver.ogg", Sound.class);
		MANAGER.load("sound/scream.wav", Sound.class);
		
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
