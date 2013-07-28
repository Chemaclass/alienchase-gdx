package tk.makigas.chase;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Alien Chase";
		cfg.useGL20 = false;
		cfg.width = 640;
		cfg.height = 360;
		cfg.resizable = false;
		
		new LwjglApplication(new AlienChase(), cfg);
	}
}
