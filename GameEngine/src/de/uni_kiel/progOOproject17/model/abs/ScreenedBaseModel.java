/**
 * 
 */
package de.uni_kiel.progOOproject17.model.abs;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.screen.Actionable;
import de.uni_kiel.progOOproject17.model.screen.HashMapActionable;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;
import de.uni_kiel.progOOproject17.model.screen.Screen;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.ViewCompound;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * @author Yannik Eikmeier
 * @since 28.03.2017
 *
 */
public abstract class ScreenedBaseModel extends TickedBaseModel implements Actionable {

	private Screen pausedScreen;
	private Screen activeScreeen;

	private HashMapActionable actions;
	
	private final SimpleViewable view;
	private final ViewCompound compView;

	
	public ScreenedBaseModel(int w, int h) {
		actions = new HashMapActionable();
		compView = new ViewCompound();		
		view = new SimpleViewable(compView, new Rectangle(0, 0, w, h));
		
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
		return view;
	}

	/**
	 * Sets the currently active {@link Screen} to s. And pauses the last
	 * {@link Screen}.
	 * 
	 * @see ScreenedBaseModel#activeScreeen
	 * 
	 * @param s
	 *            the new {@link Screen}
	 */
	public void showScreen(Screen s) {
		compView.clear();
		
		pausedScreen = activeScreeen;
		activeScreeen = s;
		
		forwardAllActionsToThis(getActiveScreeen());
		compView.addViewable(getActiveScreeen().getViewable());
	}

	/**
	 * @return the activeScreeen
	 */
	public Screen getActiveScreeen() {
		return activeScreeen;
	}

	/**
	 * Resumes the paused Screen and pauses the active {@link Screen}.
	 * 
	 */
	public void resumeScreen() {
		showScreen(pausedScreen);
	}

	// /**
	// * Returns a allways up-to-date {@link Action} that performes the
	// * {@link Action} corresponding to the key.
	// *
	// * @param key
	// * the {@link InputActionKey} which action will be returned
	// * @return the action for the key
	// */
	// public Action getAction(InputActionKey key) {
	//
	//
	//// /*
	//// * Returns only a wrapper action which on being called then will get the
	//// * corresponding action and call it. This is done so that the returned
	//// * action will allways be up to date and never as to be refreshed.
	//// */
	//// return new AbstractAction() {
	////
	////
	//// @Override
	//// public void actionPerformed(ActionEvent e) {
	//// Action a = activeScreeen.getAction(key);
	//// if (a != null)
	//// a.actionPerformed(e);
	////
	//// }
	//// };
	// }


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
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Action a = actions.getAction(iA);
				if (a != null)
					a.actionPerformed(e);
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#forwardAllActions(de.
	 * uni_kiel.progOOproject17.model.Actionable)
	 */
	@Override
	public void forwardAllActionsToThis(Actionable a) {
		actions.forwardAllActionsToThis(a);
	}

}
