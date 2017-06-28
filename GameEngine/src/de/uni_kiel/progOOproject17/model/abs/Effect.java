/**
 * 
 */
package de.uni_kiel.progOOproject17.model.abs;

/**
 * @author Yannik Eikmeier
 * @since 03.04.2017
 *
 */
public abstract class Effect implements Ticked {

	/**
	 * wheather this {@link Effect} already is applied or not
	 */
	private boolean active = false;
	/**
	 * The {@link Stats} this {@link Effect} effects
	 */
	private Stats stats;

	private final String name;

	/**
	 * 
	 */
	public Effect(String name) {
		this.name = name;
	}
	

	public void applyTo(Stats stats) {
		if (!isActive()) {
			this.stats = stats;
			onApplication(stats);
			setActive(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {
		if (isActive()) {
			onTick(stats, timestamp);
		}
	}

	//a effect may end itself during a tick!
	public void end() {
		if (isActive()) {
			setActive(false);
			onEnd(stats);
		}
	}

	protected abstract void onApplication(Stats stats);

	protected abstract void onTick(Stats stats, long timestamp);

	protected abstract void onEnd(Stats stats);

	/**
	 * @return the applied
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param applied
	 *            the applied to set
	 */
	private void setActive(boolean applied) {
		this.active = applied;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
