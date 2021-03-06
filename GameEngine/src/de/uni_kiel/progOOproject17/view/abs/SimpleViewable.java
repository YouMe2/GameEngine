package de.uni_kiel.progOOproject17.view.abs;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;

import de.uni_kiel.progOOproject17.model.abs.Distance;

/**
 * This class serves as a simple implementation on the {@link Viewable}
 * interface.
 *
 */
public class SimpleViewable extends Observable implements Viewable {

	private ViewContentKey key;
	private Rectangle rect;
	private Rectangle relativeAnchor = new Rectangle(0, 0, 0, 0);
	private boolean visable;
	private float priority;

	/**
	 * Constructs a new {@link SimpleViewable}.
	 * 
	 * @param resKey
	 *            the resource key
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 * @param layer
	 *            the layer
	 */
	public SimpleViewable(String keyText, int x, int y, int w, int h) {
		this(keyText, x, y, w, h, 0);
	}
	
	
	public SimpleViewable(String keyText, int x, int y, int w, int h, float priority) {
		this(keyText, x, y, w, h, priority, true);
	}

	public SimpleViewable(String keyText, int x, int y, int w, int h, float priority, boolean visable) {
		this(new ViewContentKey() {
			
			@Override
			public String getText() {
				return keyText;
			}
			
		}, new Rectangle(x, y, w, h), priority, visable);
	}
	
	public SimpleViewable(String keyText, Rectangle rect, float priority) {
		this(new ViewContentKey() {

			@Override
			public String getText() {
				return keyText;
			}

		}, rect, priority);
	}

	public SimpleViewable(ViewContentKey key, Rectangle rect) {
		this(key, rect, 0, true);
	}

	public SimpleViewable(String keyText, Rectangle rect) {
		this(new ViewContentKey() {

			@Override
			public String getText() {
				return keyText;
			}
		}, rect);
	}

	public SimpleViewable(ViewContentKey key, Rectangle rect, float priority) {
		this(key, rect, priority, true);
	}

	public SimpleViewable(ViewContentKey key, Rectangle rect, boolean visable) {
		this(key, rect, 0, visable);
	}

	public SimpleViewable(ViewContentKey key, Rectangle rect, float priority, boolean visable) {
		this.key = key;
		this.rect = rect;
		this.priority = priority;
		this.visable = visable;

	}

	

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uni_kiel.progOOproject17.view.abs.Viewable#getResourceKey()
	 */
	@Override
	public ViewContentKey getContentKey() {
		return key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_kiel.progOOproject17.view.abs.Viewable#getViewRect(java.awt.Point)
	 */
	@Override
	public Rectangle getViewRect() {
		Rectangle r = new Rectangle(rect);
		r.translate(-relativeAnchor.x, -relativeAnchor.y);
		return r;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.Viewable#isVisble()
	 */
	@Override
	public boolean isVisble() {
		return visable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.Viewable#getPriority()
	 */
	@Override
	public float getPriority() {
		return priority;
	}

	public void setRelativeAnchor(Rectangle relativeAnchor) {
		this.relativeAnchor = relativeAnchor;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(float priority) {
		this.priority = priority;
		setChanged();
		notifyObservers();
	}

	/**
	 * @param visable
	 *            the visable to set
	 */
	public void setVisable(boolean visable) {
		this.visable = visable;
	}

	/**
	 * sets the location
	 * 
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		rect.setLocation(x, y);
	}

	/**
	 * sets the location
	 * 
	 * @param p
	 */
	public void setLocation(Point p) {
		setLocation(p.x, p.y);
	}

	/**
	 * translates the location
	 * 
	 * @param dx
	 * @param dy
	 */
	public void translate(int dx, int dy) {
		rect.translate(dx, dy);
	}

	/**
	 * translates the location
	 * 
	 * @param dis
	 *            the {@link Distance}
	 */
	public void translate(Distance dis) {
		translate(dis.x, dis.y);
	}

	/**
	 * sets the size
	 * 
	 * @param w
	 * @param h
	 */
	public void setSize(int w, int h) {
		setSize(new Dimension(w, h));
	}

	/**
	 * sets the size
	 * 
	 * @param d
	 *            the {@link Dimension}
	 */
	public void setSize(Dimension d) {
		rect.setSize(d);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return rect.x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return rect.y;
	}

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return rect.getLocation();
	}

	/**
	 * @return the w
	 */
	public int getWidth() {
		return rect.width;
	}

	/**
	 * @return the h
	 */
	public int getHeight() {
		return rect.height;
	}

	/**
	 * @param pointsKey
	 */
	public void setClearTextKey(String keyText) {
		setContentKey(new ViewContentKey() {

			@Override
			public String getText() {
				
				
				return keyText;
			}
		});

	}
	
	public void setStringKey(String text) {
		setClearTextKey(Viewable.STRING_KEYPREFIX + text);
	}
	
	public void setTextureKey(String textureKey) {
		setClearTextKey(Viewable.TEXTURE_KEYPREFIX + textureKey);
	}
	
	public void setDebugKey(String text) {
		setClearTextKey(Viewable.DEBUG_KEYPREFIX + text);
	}
	

	/**
	 * @param key
	 *            the key to set
	 */
	public void setContentKey(ViewContentKey key) {
		this.key = key;
	}
	
	public void removeInAllComps() {
		
		setChanged();
		notifyObservers(ViewCompound.REMOVE_ME_FLAG);
		
		
	}
	
	

}
