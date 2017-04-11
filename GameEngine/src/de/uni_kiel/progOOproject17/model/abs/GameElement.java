package de.uni_kiel.progOOproject17.model.abs;

import java.awt.Rectangle;

import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;

public abstract class GameElement implements Destroyable, Ticked {

	private boolean alive = false;
	private final SimpleViewable  view;

	/**
	 * stores the {@link Environment} after this {@link GameElement} was
	 * {@link #activate(Environment, CreationHelper)}d
	 */
	protected Environment environment;
	/**
	 * stores the {@link CreationHelper} after this {@link GameElement} was
	 * {@link #activate(Environment, CreationHelper)}d
	 */
	protected CreationHelper creationHelper;

	public GameElement() {
		view = new SimpleViewable(null, new Rectangle(0, 0, 0, 0), false);
	}

	public SimpleViewable getViewable() {
		return view;
	}
	
	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_kiel.progOOproject17.model.abs.Destroyable#activate(de.uni_kiel.
	 * progOOproject17.model.abs.Environment,
	 * de.uni_kiel.progOOproject17.model.CreationHelper)
	 */
	@Override
	public void activate(Environment environment, CreationHelper creationHelper) {
		alive = true;
		this.environment = environment;
		this.creationHelper = creationHelper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Destroyable#destroy()
	 */
	@Override
	public void destroy() {
		if (isAlive()) {
			alive = false;
//			getViewable().setVisable(false);
			getViewable().removeInAllComps();
			creationHelper.onDestruction(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Destroyable#isAlive()
	 */
	@Override
	public boolean isAlive() {
		return alive;
	}

}
