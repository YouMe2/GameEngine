/**
 * 
 */
package de.uni_kiel.progOOproject17.model.abs;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.screen.Actionable;
import de.uni_kiel.progOOproject17.model.screen.HashMapActionable;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;
import de.uni_kiel.progOOproject17.model.screen.Screen;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * @author Yannik Eikmeier
 * @since 28.03.2017
 *
 */
public abstract class ScreenControllerModel extends TickedBaseModel implements Actionable {

	private Screen previousScreen;
	private Screen activeScreeen;

	private final HashMapActionable actions;

	/**
	 * serves as a switch between multiple models aka Screens
	 */
	public ScreenControllerModel() {
		actions = new HashMapActionable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.TickedBaseModel#tick(long)
	 */
	public void tick(long timestamp) {
		activeScreeen.tick(timestamp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.TickedBaseModel#getViewables()
	 */
	@Override
	public Viewable getViewable() {
		return getActiveScreeen().getViewable();
	}

	/**
	 * Sets the currently active {@link Screen} to s. And pauses the last
	 * {@link Screen}.
	 * 
	 * @see ScreenControllerModel#activeScreeen
	 * 
	 * @param s
	 *            the new {@link Screen}
	 */
	public void showScreen(Screen s) {

		previousScreen = activeScreeen;
		activeScreeen = s;
		forwardAllActionsFrom(getActiveScreeen());
	}

	/**
	 * @return the activeScreeen
	 */
	public Screen getActiveScreeen() {
		return activeScreeen;
	}

	/**
	 * Resumes the previous Screen and pauses the active {@link Screen}.
	 * 
	 */
	public void resumePreviousScreen() {
		showScreen(previousScreen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#putAction(de.uni_kiel.
	 * progOOproject17.model.InputActionKey, javax.swing.Action)
	 */
	@Override
	public void putAction(InputActionKey iA, Action action) {
		actions.putAction(iA, action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#getAction(de.uni_kiel.
	 * progOOproject17.model.InputActionKey)
	 */
	@Override
	public Action getAction(InputActionKey iA) {
		/*
		 * Returns only a wrapper action which on being called then will get the
		 * corresponding action and call it. This is done so that the returned
		 * action will allways be up to date and never as to be refreshed.
		 */
//		return new AbstractAction() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// should never be null
//				actions.getAction(iA).actionPerformed(e);
//			}
//		};
		return actions.getAction(iA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#forwardAllActions(de.
	 * uni_kiel.progOOproject17.model.Actionable)
	 */
	@Override
	public void forwardAllActionsFrom(Actionable a) {
		actions.forwardAllActionsFrom(a);
	}

}
