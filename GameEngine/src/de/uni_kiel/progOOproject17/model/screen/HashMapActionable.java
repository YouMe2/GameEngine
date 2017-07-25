/**
 * 
 */
package de.uni_kiel.progOOproject17.model.screen;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.AbstractAction;
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
		for (InputActionKey inKey : InputActionKey.values()) {
			putAction(inKey, new AbstractAction("nullAction") {			
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#putAction(de.uni_kiel.
	 * progOOproject17.model.InputActionKey, javax.swing.Action)
	 */
	@Override
	public void putAction(InputActionKey iA, Action action) {
		Objects.requireNonNull(iA);
		Objects.requireNonNull(action);
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
		Objects.requireNonNull(iA);
		return actions.get(iA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.Actionable#forwardAllActions(de.
	 * uni_kiel.progOOproject17.model.Actionable)
	 */
	@Override
	public void forwardAllActionsFrom(Actionable a) {
		Objects.requireNonNull(a);
		actions.clear();
		for (InputActionKey key : InputActionKey.values())
			putAction(key, a.getAction(key));
	}

}
