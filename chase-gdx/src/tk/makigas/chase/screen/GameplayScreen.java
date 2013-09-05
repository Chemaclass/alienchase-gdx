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
 * Pantalla de juego principal. Aquí es donde se desarrolla la trama.
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
	
	/** Lista de aliens cargados en el juego. */
	private List<AlienActor> aliens;
	
	/** Lista de balas cargadas en el juego. */
	private List<BulletActor> bullets;
	
	public GameplayScreen(AlienChase game) {
		super(game);
	}
	
	@Override
	public void show() {
		// (Re)inicializamos las listas de entidades cargadas en el juego.
		aliens = new ArrayList<AlienActor>();
		bullets = new ArrayList<BulletActor>();
		
		// Creamos un nuevo escenario y lo asociamos a la entrada.
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		initActors(); // inicializamos todos los actores en el escenario
		initInput(); // inicializamos el sistema de entrada

		// Finalmente inicializamos el contador de tiempo. Esto sólo
		// ajusta el tiempo que queda para que el primer alien haga spawn.
		timer = 2 + (float) Math.random();
	}

	/**
	 * Inicializa los actores. Se crean todas las naves y demás elementos
	 * que deban añadirse al escenario. El orden en el que se hacen es
	 * importante para que se muestren correctamente unos delante de otros.
	 */
	private void initActors() {
		// Crear fondo. Para crear el fondo se usa una imagen, que es un
		// elemento de scene2d-ui que permite crear imágenes simples.
		// Para lo que lo queremos, nos vale y nos sobra. Hay que añadirlo
		// en primer lugar para que quede en el fondo en todo momento.
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
		
		// La puntuación es un actor que muestra un string en pantalla.
		puntuacion = new PuntuacionActor(new BitmapFont());
		puntuacion.setPosition(10, stage.getHeight() - 10);
		puntuacion.puntuacion = 0;
		stage.addActor(puntuacion);
	}

	/**
	 * Inicializa los sistemas de entrada. En escritorio se usa un listener
	 * capaz de realizar todas las acciones a la primera. En cambio, en
	 * Android es necesario procesar estas acciones usando pads que habrá que
	 * agregar al escenario.
	 */
	private void initInput() {
		if(Gdx.app.getType() == ApplicationType.Desktop) {			// DESKTOP
			
			// Si estamos en escritorio, tenemos acceso a un teclado físico,
			// por lo que tiene sentido poder controlar la nave usando el
			// teclado. En este caso usamos un listener único que asociamos
			// a la nave (realmente valdría cualquier actor, ya que no
			// estamos usando el ratón para nada en este juego).
			
			stage.setKeyboardFocus(nave); // damos foco a nave.
			nave.addListener(new InputDesktopListener(nave, this));
		} else if(Gdx.app.getType() == ApplicationType.Android) { 	// ANDROID
			
			// Los móviles son táctiles por lo que no tenemos a nuestra
			// disposición en la mayoría de casos un teclado físico o al
			// menos un conjunto de teclas cuya posición sea fiable y
			// ergonómica. Así que creamos varios actores en la pantalla
			// a modo de pad virtual: son botones que al pulsarse controlan
			// la entrada del juego. En este caso necesitamos tres botones
			// INDEPENDIENTES entre sí, controlados por listeners igual
			// de INDEPENDIENTES.
			
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
			padShoot.addListener(new InputAndroidShootListener(stage, this));
		
			// Los añadimos al escenario.
			stage.addActor(padArriba);
			stage.addActor(padAbajo);
			stage.addActor(padShoot);
		}
	}

	@Override
	public void render(float delta) {
		// Limpieza de la pantalla.
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		// Actualizamos.
		stage.act();
		timer -= delta;
		if(timer < 0) // hora de hacer spawn	
			dispararAlien();
		comprobarListas();
		comprobarColisiones();
		stage.draw();
	}
	
	/**
	 * Atiende a cada una de las entidades de las listas. Este método
	 * comprobará si por ejemplo es necesario elimnar aliens o balas que
	 * hayan sobrepasado los límites de la pantalla.
	 * 
	 * A la hora de eliminar una entidad es necesario llamar a dos métodos
	 * remove. En primer lugar, es necesario eliminar el actor del escenario.
	 * Para ello se usa el método remove() de la clase Actor. Hecho eso,
	 * hace falta eliminar a la entidad de la lista dinámica en la que esté
	 * introducida.
	 */
	private void comprobarListas() {
		// Busca aliens que hayan salido por el lazo izquierdo de la pantalla
		for(int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).getRight() < 0) {
				aliens.get(i).remove(); // fuera del escenario
				aliens.remove(i); // fuera de la lista dinámica
				
				// Es necesario también quitarle vida al escudo.
				// Cada impacto resta un 40% de vida al escudo.
				if(escudo.getHealth() > 0.4f) {
					escudo.sumHealth(-0.4f);
					AlienChase.MANAGER.get("hit.ogg", Sound.class).play();
				} else {
					// Si el escudo ya tenía menos de un 40% de vida,
					// entonces el ataque a la tierra es inminente.
					game.setScreen(game.GAMEOVER);
				}
			}
		}
		
		// Busca balas que hayan salido por el lado derecho de la pantalla.
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).getX() > stage.getWidth()) {
				bullets.get(i).remove(); // fuera del escenario
				bullets.remove(i); // fuera de la lista dinámica
			}
		}
	}
	
	/**
	 * Comprueba el estado de las colisiones entre las distintas entidades
	 * del juego usando un sistema de bounding-boxes. Cada entidad tiene
	 * su bounding-box asociado y las colisiones se comprueban de forma
	 * geométrica.
	 */
	private void comprobarColisiones() {
		AlienActor alien;
		
		for(int i = 0; i < aliens.size(); i++) {
			alien = aliens.get(i);
			if(alien.bb.overlaps(nave.bb)) { // ¿este alien choca con nave?
				// Colisión alien-nave.
				aliens.get(i).remove(); // eliminar del escenario
				aliens.remove(i); // eliminar de la lista dinámica
				nave.sumHealth(-0.4f);
				AlienChase.MANAGER.get("hit.ogg", Sound.class).play();
				if(nave.getHealth() <= 0) {
					// si no hay vida en la nave, entonces se acabó la partida
					game.setScreen(game.GAMEOVER);
				}
			} else {	// ya sabemos que este alien no ha impactado con la
						// nave, ahora comprobamos si ha chocado con una bala.
						// probablemente este no es el sistema más eficiente...
				for(int j = 0; j < bullets.size(); j++) {
					if(bullets.get(j).bb.overlaps(alien.bb)) {
						// Colisión alien-bala.
						aliens.get(i).remove(); // fuera del escenario
						aliens.remove(i); // fuera de la lista dinámica
						bullets.get(j).remove(); // fuera del escenario
						bullets.remove(j); // fuera de la lista dinámica
						AlienChase.MANAGER.get("explosion.ogg", Sound.class).play();
						puntuacion.puntuacion++;
					}
				}
			}
		}
	}

	/**
	 * Genera un nuevo alien en el escenario. Este método es invocado de
	 * forma automática cuando el temporizador indica que es hora de disparar
	 * un nuevo alien.
	 */
	private void dispararAlien() {
		AlienActor alien = new AlienActor();
		
		// Le damos una posición en al pantalla a él y a su bounding box.
		alien.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 
				0.8f * stage.getHeight() * (float) Math.random());
		alien.bb.x = alien.getX();
		alien.bb.y = alien.getY();
		
		stage.addActor(alien);
		aliens.add(alien);
		
		// Hay que actualizar el contador. Es aquí donde realmente
		// se indica cada cuánto tiempo se disparará un alien nuevo.
		timer = 2 + (float) Math.random();
	}
	
	/**
	 * Spawns a net bullet right in front of the spaceship.
	 */
	public void spawnBullet() {
		BulletActor bullet = new BulletActor();
		bullet.setPosition(10 + nave.getWidth(),
				nave.getY() + 0.5f * (nave.getHeight() - bullet.getHeight()));
		stage.addActor(bullet);
		bullets.add(bullet);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null); // desbloqueamos la entrada
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	/**
	 * En un principio este método no deberá ser llamado en escritorio,
	 * puesto que el juego está configurado como no redimensionable.
	 * En teoría tampoco debería llamarse en Android pero nunca se sabe.
	 */
	@Override
	public void resize(int width, int height) {
		// Actualizamos el tamaño del escenario.
		stage.setViewport(640, 360, true);
		
		// Reposicionamos los actores cuya posición dependa del stage.
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		vidaEscudo.setPosition(stage.getWidth() - 150, stage.getHeight() - 28);
		puntuacion.setPosition(10, stage.getHeight() - 10);
		if(Gdx.app.getType() == ApplicationType.Android && padShoot != null)
			padShoot.setPosition(stage.getWidth() - 50, 10);
	}
}
