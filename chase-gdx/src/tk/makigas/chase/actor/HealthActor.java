package tk.makigas.chase.actor;

/**
 * Elemento que puede tener vida. La vida es un valor flotante comprendido
 * entre 0 y 1 que representa un porcentaje. 0 es un 0% de vida, 1 es un 100%
 * de vida y cualquier otro valor intermedio representa una vida intermedia.
 * Por ejemplo, 0.32 indica un 32% de vida.
 * 
 * @author danirod
 */
public interface HealthActor {

	/**
	 * Obtiene la vida actual de la entidad.
	 * @return vida actual.
	 */
	public float getHealth();
	
	/**
	 * Cambia la vida actual.
	 * @param health nuevo valor de la vida.
	 */
	public void setHealth(float health);
	
	/**
	 * Suma (o resta) vida a la nave.
	 * @param sum cantidad de vida sumada o restada.
	 */
	public void sumHealth(float sum);
	
}
