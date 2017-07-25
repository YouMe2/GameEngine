package de.uni_kiel.progOOproject17.view.abs;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

/**
 * This class provides the useful functionality of combining an {@link InputMap}
 * and and {@link ActionMap} for key inputs into an {@link InputView}.
 *
 * @see KeyStroke#getKeyStroke(String)
 *
 *
 */
public class MappedKeyInput implements InputView {

	private InputMap inMap;
	private ActionMap aMap;
	private boolean inited = false;

	/**
	 * Creates a new {@link MappedKeyInput} with the specified
	 * {@link InputMap} and {@link ActionMap}.
	 *
	 * @param inMap
	 *            the {@link InputMap}
	 * @param aMap
	 *            the {@link ActionMap}
	 */
	public void initMaps(InputMap inMap, ActionMap aMap) {
		this.inMap = inMap;
		this.aMap = aMap;
		inited = true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uni_kiel.progOOproject17.view.abs.InputView#addAction(java.lang.
	 * String, javax.swing.Action)
	 */
	@Override
	public void addAction(String actionKeyText, Action action) {
		if(!inited)
			throw new IllegalStateException("Input- and Action Map not inited yet! See the init() method.");
		KeyStroke key = KeyStroke.getKeyStroke(actionKeyText);
		if (key != null)
			addKeyAction(key, action);
		else
			throw new IllegalArgumentException("Invalid KeyStrokeString: "+actionKeyText);
	}

	/**
	 * Adds a Keyaction to this {@link InputView}.
	 *
	 * @param key
	 *            The {@link KeyStroke} for the action
	 * @param action
	 *            The Action
	 */
	public void addKeyAction(KeyStroke key, Action action) {
		if(!inited)
			throw new IllegalStateException("Input- and Action Map not inited yet! See the init() method.");		
		inMap.put(key, key);
		aMap.put(key, action);
	}


//	@Override
//	public void setEnabeled(boolean enabeled) {
//		this.enabeled = enabeled;
//
//		for (KeyStroke key : inMap.keys())
//			if (enabeled)
//				inMap.put(key, key);
//			else
//				inMap.put(key, "none");
//
//	}
//
//	@Override
//	public void setEnabeled(String actionKey, boolean enabeled) {
//		KeyStroke key = KeyStroke.getKeyStroke(actionKey);
//		if (enabeled)
//			inMap.put(key, key);
//		else
//			inMap.put(key, "none");
//
//	}
//
//
//	@Override
//	public void addActionMap(ActionMap actionMap) {
//
//		for (Object o : actionMap.keys())
//			addAction((String) o, actionMap.get(o));
//	}

}
