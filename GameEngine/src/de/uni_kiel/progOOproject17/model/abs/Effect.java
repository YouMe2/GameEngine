/**
 * 
 */
package de.uni_kiel.progOOproject17.model.abs;

/**
 * @author Yannik Eikmeier
 * @since 03.04.2017
 *
 */
public abstract class Effect<O extends Effectable> implements Ticked {
	// TODO

	protected O object;

	public void initEffect(O o) {
		object = o;
		onApplication(o);
	}

	public abstract void onApplication(O o);

	public abstract class GameEffect extends Effect<GameObject> {

	}

}
