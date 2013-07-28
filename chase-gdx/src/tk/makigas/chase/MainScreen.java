package tk.makigas.chase;

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

		stage = new Stage(640, 360, true, game.SB);
		
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
