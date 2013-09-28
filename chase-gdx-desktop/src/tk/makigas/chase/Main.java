package tk.makigas.chase;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Alien Chase - Chema Version";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 512;
		cfg.resizable = false;
		
		new LwjglApplication(new AlienChase(), cfg);
	}
}
