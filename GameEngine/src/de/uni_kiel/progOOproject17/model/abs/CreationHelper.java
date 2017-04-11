package de.uni_kiel.progOOproject17.model.abs;

/**
 * Serves as a wraper for the interfaces {@link ElementCreator} and
 * {@link DestroyListener}.
 *
 */
public interface CreationHelper extends ElementCreator, DestroyListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_kiel.progOOproject17.model.abs.DestroyListener#onDestruction(de.
	 * uni_kiel.progOOproject17.model.abs.Destroyable)
	 */
	@Override
	public void onDestruction(Destroyable d);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_kiel.progOOproject17.model.abs.ElementCreator#create(de.uni_kiel.
	 * progOOproject17.model.abs.GameElement)
	 */
	@Override
	public void create(GameElement g);
}
