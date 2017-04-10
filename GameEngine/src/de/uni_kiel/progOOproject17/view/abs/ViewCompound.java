/**
 * 
 */
package de.uni_kiel.progOOproject17.view.abs;

import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import de.uni_kiel.progOOproject17.view.abs.Viewable.Key;

/**
 * @author Yannik Eikmeier
 * @since 30.03.2017
 *
 */
public class ViewCompound implements Key, Observer, Iterable<SimpleViewable>{

	public static final String LISTKEY_TEXT = "ViewablesList";
	
	private final SortedLinkedList<SimpleViewable> views;
	
	/**
	 * 
	 */
	public ViewCompound() {
		views = new SortedLinkedList<>();

		
	}
	

	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.view.abs.Viewable.Key#getText()
	 */
	@Override
	public String getText() {
		return LISTKEY_TEXT;
	}
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		//priority changed
		
		assert views.contains(o) : "updated by a non contained observable";		
		if (o instanceof SimpleViewable)
			views.updatePosition((SimpleViewable) o);
		else
			System.err.println("updated by a non SimpleViewable");
	}
	
	//listMethods:
	public void addViewable(SimpleViewable view) {
		view.addObserver(this);
		views.add(view);
	}
	
	public void addAllViewables( Collection<SimpleViewable> views) {
		if (views == null)
			return;
		if (views.isEmpty())
			return;
		
		for (SimpleViewable v : views) {
			addViewable(v);		
		}
	}
	
	public void removeViewable(SimpleViewable view) {
		view.deleteObserver(this);
		views.remove(view);
	}


	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<SimpleViewable> iterator() {
		return views.iterator();
	}
	
	
	
}
