/**
 * 
 */
package de.uni_kiel.progOOproject17.model.abs;

/**
 * @author Yannik Eikmeier
 * @since 03.04.2017
 *
 */
public interface Effectable {
	
	public void applyEffect(Effect e);

	public void endEffect(Effect e);

}
