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

import java.util.ArrayList;
import java.util.List;

import tk.makigas.chase.AlienChase;
import tk.makigas.chase.actor.*;
import tk.makigas.chase.listeners.InputAndroidMoveListener;
import tk.makigas.chase.listeners.InputAndroidShootListener;
import tk.makigas.chase.listeners.InputDesktopListener;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Pantalla de juego principal.
 * 
 * @author danirod
 */
public class GameplayScreen extends AbstractScreen {

	/** Escenario usado por el juego. */
	private Stage stage;
	
	/** Nave usada por el jugador. */
	private NaveActor nave;
	
	/** Escudo de la Tierra. */
	private EscudoActor escudo;
	
	/** HUDs usados para mostrar la vida de escudo y de nave. */
	private BarraActor vidaNave, vidaEscudo;
	
	/** Pads usados para controlar el juego en Android. */
	private PadActor padArriba, padAbajo, padShoot;
	
	/** Contador de tiempo usado para sincronizar algunos eventos. */
	private float timer;
	
	/** Puntuación */
	private PuntuacionActor puntuacion;
	
	private List<AlienActor> aliens;
	
	private List<BulletActor> bullets;
	
	public GameplayScreen(AlienChase game) {
		super(game);
	}
	
	@Override
	public void show() {
		aliens = new ArrayList<AlienActor>();
		bullets = new ArrayList<BulletActor>();
		
		// Creamos un nuevo escenario y lo asociamos a la entrada.
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, game.sb);
		Gdx.input.setInputProcessor(stage);
		
		// Crear fondo.
		Image imgFondo = new Image(AlienChase.MANAGER.get("fondo.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		
		// Creamos una nave.
		nave = new NaveActor();
		nave.setPosition(10, 10);
		stage.addActor(nave);

		// Creamos un escudo.
		escudo = new EscudoActor();
		escudo.setBounds(-5, 0, 5, stage.getHeight());
		stage.addActor(escudo);
		
		// Creamos los HUD de las naves.
		vidaNave = new BarraActor(nave);
		vidaEscudo = new BarraActor(escudo);		
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		vidaEscudo.setPosition(stage.getWidth() - 150, stage.getHeight() - 28);
		stage.addActor(vidaNave);
		stage.addActor(vidaEscudo);
		
		// Creamos los sistemas de entrada. En escritorio tendremos que usar
		// un listener que lo hace todo, mientras que para Android tendremos
		// que usar tres botones asociados cada uno a algo.
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			stage.setKeyboardFocus(nave); // damos foco a nave.
			nave.addListener(new InputDesktopListener(nave, stage, bullets));
		} else if(Gdx.app.getType() == ApplicationType.Android) {
			// Creamos los pads.
			padArriba = new PadActor(0, 0);
			padAbajo = new PadActor(1, 0);
			padShoot = new PadActor(0, 1);
		
			// Los colocamos.
			padArriba.setPosition(10, 50);
			padAbajo.setPosition(10, 10);
			padShoot.setPosition(stage.getWidth() - 50, 10);
		
			// Añadimos los listeners.
			padArriba.addListener(new InputAndroidMoveListener(nave, 250f));
			padAbajo.addListener(new InputAndroidMoveListener(nave, 250f));
			padShoot.addListener(new InputAndroidShootListener(stage, nave, bullets));
		
			// Los añadimos al escenario.
			stage.addActor(padArriba);
			stage.addActor(padAbajo);
			stage.addActor(padShoot);
		}
		
		puntuacion = new PuntuacionActor(new BitmapFont());
		puntuacion.setPosition(10, stage.getHeight() - 10);
		puntuacion.puntuacion = 0;
		stage.addActor(puntuacion);
		
		// Finalmente inicializamos el contador de tiempo.
		timer = 2 + (float) Math.random();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		timer -= delta;
		if(timer < 0)		
			dispararAlien();
		comprobarListas();
		comprobarColisiones();
		stage.draw();
	}
	
	private void comprobarListas() {
		for(int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).getRight() < 0) {
				aliens.get(i).remove();
				aliens.remove(i);
				if(escudo.getHealth() > 0.4f) {
					escudo.sumHealth(-0.4f);
					AlienChase.MANAGER.get("hit.ogg", Sound.class).play();
				} else {
					game.setScreen(game.GAMEOVER);
				}
			}
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).getX() > stage.getWidth()) {
				bullets.get(i).remove();
				bullets.remove(i);
			}
		}
	}
	
	private void comprobarColisiones() {
		AlienActor alien;
		for(int i = 0; i < aliens.size(); i++) {
			alien = aliens.get(i);
			if(alien.bb.overlaps(nave.bb)) {
				// Colisión alien-nave.
				aliens.get(i).remove();
				aliens.remove(i);
				nave.sumHealth(-0.4f);
				AlienChase.MANAGER.get("hit.ogg", Sound.class).play();
				if(nave.getHealth() <= 0) {
					game.setScreen(game.GAMEOVER);
				}
			} else {
				for(int j = 0; j < bullets.size(); j++) {
					if(bullets.get(j).bb.overlaps(alien.bb)) {
						// Colisión alien-bala.
						aliens.get(i).remove();
						aliens.remove(i);
						bullets.get(j).remove();
						bullets.remove(j);
						AlienChase.MANAGER.get("explosion.ogg", Sound.class).play();
						puntuacion.puntuacion++;
					}
				}
			}
		}
	}

	private void dispararAlien() {
		AlienActor alien = new AlienActor();
		alien.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 
				0.8f * stage.getHeight() * (float) Math.random());
		alien.bb.x = alien.getX();
		alien.bb.y = alien.getY();
		stage.addActor(alien);
		aliens.add(alien);
		timer = 2 + (float) Math.random();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(640, 360, true);
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		vidaEscudo.setPosition(stage.getWidth() - 150, stage.getHeight() - 28);
		puntuacion.setPosition(10, stage.getHeight() - 10);
		if(Gdx.app.getType() == ApplicationType.Android && padShoot != null)
			padShoot.setPosition(stage.getWidth() - 50, 10);
	}
}
