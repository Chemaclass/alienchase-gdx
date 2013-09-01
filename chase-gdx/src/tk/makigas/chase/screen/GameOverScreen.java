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

/**
 * Pantalla de Game Over. Cuando el usuario pierde la partida se le redirige
 * a esta pantalla donde puede sufrir pensando en lo que ha hecho hasta que
 * decida pulsar el botón para volver al menú principal.
 * 
 * @author danirod
 */
public class GameOverScreen extends AbstractScreen {

	/** Textura con la imagen que se mostrará a modo de Game Over. */
	private Texture gameover;

	/**
	 * Guardan el ancho y el alto de la pantalla para que la imagen que se
	 * renderiza en este estado sepa cómo de ancha y alta debe mostrarse.
	 */
	private int width, height;
	
	public GameOverScreen(AlienChase game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		// Para asegurarnos de que el SpriteBatch y la cámara están
		// siempre sincronizados, los ajustamos en cada fotograma.
		game.getCamera().update();			// recalcula las matrices de la cámara
		game.getCamera().apply(Gdx.gl10);	// reajusta las matrices de la cámara
		// lo que viene a continuación es super necesario: le indica al
		// SpriteBatch que use la matriz de proyección de la cámara en sus
		// cálculos. De este modo, se usarán las coordenadas de la cámara.
		game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
		
		game.getSpriteBatch().begin();
		game.getSpriteBatch().draw(gameover, 0, 0, width, height);
		game.getSpriteBatch().end();
		
		if(Gdx.input.isTouched()) {
			// Volvemos al menú inicio en cuanto se toque la pantalla.
			game.setScreen(game.MAIN);
		}
	}
	
	@Override
	public void show() {
		gameover = AlienChase.MANAGER.get("gameover.png", Texture.class);
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
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
	}

}