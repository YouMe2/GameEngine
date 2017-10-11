package de.uni_kiel.progOOproject17.view.abs;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.screen.Actionable;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;

public class InputConnector {

	private final InputView inputView;
	private final Actionable actionableModel;
	private boolean hasValidBinding = false;
//	private boolean isBound = true;

	private HashMap<InputActionKey, String> bindingMap = null;
//	private HashMap<InputActionKey, Action> actions = new HashMap<>();

	public InputConnector(InputView in, Actionable actionable) {
		this.inputView = in;
		this.actionableModel = actionable;
	}

	public void useBindings(HashMap<InputActionKey, String> bindingMap) {
		this.bindingMap = (HashMap<InputActionKey, String>) bindingMap.clone();
		hasValidBinding = true;
		for (InputActionKey key : InputActionKey.values()) {
			 inputView.addAction(bindingMap.get(key), actionableModel.getAction(key));

			// adds a wrapper action to ensure uptodateness once the binding is set
//			Action wrapperAction = new AbstractAction() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// should never be null
//					actionableModel.getAction(key).actionPerformed(e);
////					System.out.println("ACTION "+key);
//				}
//			};
//			wrapperAction.setEnabled(isBound);
//			actions.put(key, wrapperAction);
//			inputView.addAction(bindingMap.get(key), wrapperAction);
		}
	}

//	public void setBindingEnabeld(boolean enabled) {	
//		if(!hasValidBinding)
//			throw new IllegalStateException("No Binding set to be enabled or disabeld!");
//		
//		if (isBound == enabled)
//			return;
//		else if (!enabled)
//			for (InputActionKey key : InputActionKey.values()) {
//				actions.get(key).setEnabled(false);
//			}
//		else if(enabled)
//			for (InputActionKey key : InputActionKey.values()) {
//				actions.get(key).setEnabled(true);
//			}
//
//		isBound = enabled;
//	}

	public HashMap<InputActionKey, String> getBindings() {
		return bindingMap;
	}
	
}
