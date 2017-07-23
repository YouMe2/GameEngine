package de.uni_kiel.progOOproject17.model;

import de.uni_kiel.progOOproject17.model.abs.GameElement;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * This class represents a {@link GameElement} that serves as a simple
 * Background.
 *
 */
public class Background extends GameElement {

	/**
	 * Constructs a new {@link Background}.
	 * Which will not be active until it is activated by the {@link #activate(Environment, CreationHelper)} method.
	 * 
	 * @param resKey the resource Key for this Background
	 * @param x the x coord 
	 * @param y the y coord 
	 * @param w the width
	 * @param h the height
	 */
	public Background(String resKey, int x, int y, int w, int h) {
		super();;
		
		getFullSimpleViewable().setTextureKey(resKey);
		getFullSimpleViewable().setLocation(x, y);
		getFullSimpleViewable().setSize(w, h);
		getFullSimpleViewable().setPriority(Viewable.BG_LAYER);
		getFullSimpleViewable().setVisable(true);
		
	}

	@Override
	public void tick(long timestamp) {
	}

}
