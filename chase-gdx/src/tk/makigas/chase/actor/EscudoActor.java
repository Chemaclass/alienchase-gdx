package tk.makigas.chase.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class EscudoActor extends Actor implements HealthActor {
	
	private float health;

	public EscudoActor() {
		health = 1;
	}

	public float getHealth() {
		return health;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public void sumHealth(float sum) {
		health += sum;
	}
	
	private void checkHealth() {
		if(health < 0) health = 0;
		if(health > 1) health = 1;
	}
	
	private float timer = 0;
	
	@Override
	public void act(float delta) {
		timer += delta;
		if(timer > 2 && health < 1)
			health++;
	}
}
