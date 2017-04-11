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
	private boolean applied = false;
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
	

	public void apply(Stats stats) {
		if (!isApplied()) {
			this.stats = stats;
			onApplication(stats);
			setApplied(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {
		if (isApplied()) {
			onTick(stats, timestamp);
		}
	}

	public void end() {
		if (isApplied()) {
			setApplied(false);
			onEnd(stats);
		}
	}

	protected abstract void onApplication(Stats stats);

	protected abstract void onTick(Stats stats, long timestamp);

	protected abstract void onEnd(Stats stats);

	/**
	 * @return the applied
	 */
	public boolean isApplied() {
		return applied;
	}

	/**
	 * @param applied
	 *            the applied to set
	 */
	private void setApplied(boolean applied) {
		this.applied = applied;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
