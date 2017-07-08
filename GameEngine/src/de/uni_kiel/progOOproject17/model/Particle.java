package de.uni_kiel.progOOproject17.model;

import java.awt.Rectangle;

import de.uni_kiel.progOOproject17.model.abs.GameElement;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.Viewable;
import de.uni_kiel.progOOproject17.view.abs.Viewable.ViewContentKey;

/**
 * This class represents a {@link GameElement} that serves as a animated
 * {@link Particle}.
 */
public class Particle extends GameElement {

	private int counter;
	private final int dtime;
	private long lasttime;
	private final int max;
	private final String resKey;
	
	private ViewContentKey key = new ViewContentKey() {
		@Override
		public String getText() {
			return resKey + "_" + counter;
		}
		
	};

	/**
	 * Constructs a new {@link Particle}. That after its been activated will be
	 * shown for max*dtime.
	 * 
	 * @param resKey
	 *            the base resource key
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 * @param dtime
	 *            the time between the animations
	 * @param lenghth
	 *            the length of the counter
	 */
	public Particle(String resKey, int x, int y, int w, int h, int dtime, int lenghth) {
		super();
		this.resKey = resKey;
		this.max = lenghth;
		this.dtime = dtime;
		
		getViewable().setKey(key);
		getViewable().setLocation(x, y);
		getViewable().setSize(w, h);
		getViewable().setPriority(Viewable.PARTICLE_LAYER);
		getViewable().setVisable(true);
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {
		// init
		if (lasttime == 0)
			lasttime = timestamp;

		// next
		if (timestamp - lasttime > dtime)
			if (counter < max - 1) {
				lasttime = timestamp;
				counter++;

			} else if (counter == max - 1)
				destroy();

	}

}
