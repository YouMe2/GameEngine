package de.uni_kiel.progOOproject17.model.screen;

import java.awt.Rectangle;
import java.util.HashMap;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.abs.GameCompound;
import de.uni_kiel.progOOproject17.model.abs.ScreenControllerModel;
import de.uni_kiel.progOOproject17.model.abs.TickedBaseModel;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.ViewCompound;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * This class represents a subModel in a {@link ScreenControllerModel} which specifly is {@link Actionable}.
 *
 */
public abstract class Screen extends TickedBaseModel implements Actionable {


	private final SimpleViewable view;
	protected final ViewCompound compView = new ViewCompound();
	
	private final HashMapActionable actions;
	
	private final int w;
	private final int h;
	
	
	/**
	 * Constructs a new Screen with no action preset!
	 * 
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 */
	public Screen(int w, int h) {
		this.w = w;
		this.h = h;
		actions = new HashMapActionable();
		view = new SimpleViewable(compView, new Rectangle(0,0,w,h));
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
	
	@Override
	public abstract void tick(long timestamp);

	@Override
	public Viewable getViewable() {
		return view;
	}
	
	public SimpleViewable getFullSimpleViewable() {
		return view;
	}
	
	public int getWidth(){
		return w;
	}
	
	public int getHeight() {
		return h;
	}
}
