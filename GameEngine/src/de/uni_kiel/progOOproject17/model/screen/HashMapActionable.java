/**
 * 
 */
package de.uni_kiel.progOOproject17.model.screen;

import java.util.HashMap;

import javax.swing.Action;

/**
 * @author Yannik Eikmeier
 * @since 10.04.2017
 *
 */
public class HashMapActionable implements Actionable {

	private final HashMap<InputActionKey, Action> actions;
	
	/**
	 * 
	 */
	public HashMapActionable() {
		actions = new HashMap<>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#putAction(de.uni_kiel.
	 * progOOproject17.model.InputActionKey, javax.swing.Action)
	 */
	@Override
	public void putAction(InputActionKey iA, Action action) {
		actions.put(iA, action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#getAction(de.uni_kiel.
	 * progOOproject17.model.InputActionKey)
	 */
	@Override
	public Action getAction(InputActionKey iA) {
		return actions.get(iA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#forwardAllActions(de.
	 * uni_kiel.progOOproject17.model.Actionable)
	 */
	@Override
	public void forwardAllActionsToThis(Actionable a) {
		actions.clear();

		for (InputActionKey key : InputActionKey.values())
			putAction(key, a.getAction(key));
	}

}
