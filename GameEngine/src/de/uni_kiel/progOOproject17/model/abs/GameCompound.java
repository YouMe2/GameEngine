package de.uni_kiel.progOOproject17.model.abs;

import java.awt.Rectangle;

import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.ViewCompound;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * This class represents a {@link GameComponent} that might be holding a number of {@link Viewable}s in specific area.
 */
public abstract class GameCompound extends GameComponent {

	private final SimpleViewable view;
	protected final ViewCompound compView = new ViewCompound();
	
	/**
	 * Constructs a new {@link GameCompound}.
	 * 
	 * @param x the x coord
	 * @param y the y coord
	 * @param w the width
	 * @param h the height
	 */
	public GameCompound(int x, int y, int w, int h) {
		super(x, y, w, h);
		view = new SimpleViewable(compView, new Rectangle(x, y, w, h));

	}

	/**
	 * Returns the {@link Viewable} this {@link GameCompound} holds.
	 *
	 * @return the {@link Viewable} this holds
	 */
	public Viewable getViewable() {
		return view;
	}
	
	public SimpleViewable getFullSimpleViewable() {
		return view;
	}
	
	public void setViewPriority(float priority) {
		view.setPriority(priority);
	}

}
