package de.uni_kiel.progOOproject17.model.screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel;
import de.uni_kiel.progOOproject17.resources.ResourceManager;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.ViewCompound;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * This class serves as a abstract implementation of a {@link Screen} forming a
 * simple menu.
 * 
 */
public class MenuScreen extends Screen {
	
	private final SimpleViewable background;
	private final SimpleViewable selectionCursor;
	private final SimpleViewable[] entries;


	private int selction = 0;

	public static final int ENTRY_WIDTH = KittenBaseModel.LHPIXEL_WIDTH * 6;
	public static final int ENTRY_HEIGHT = KittenBaseModel.LHPIXEL_HEIGHT * 3;

	public static final int CURSOR_WIDTH = KittenBaseModel.LHPIXEL_WIDTH * 6;
	public static final int CURSOR_HEIGHT = KittenBaseModel.LHPIXEL_HEIGHT * 3;

	/**
	 * Constructs a new {@link MenuScreen} with entries specified by the resKeys
	 * and actions.
	 * 
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 * @param resKeys
	 *            the resource keys for the entries
	 * @param actions
	 *            the {@link Action}s for the entries
	 */
	public MenuScreen(int w, int h, String[] resKeys, Action[] actions) {
		super(w, h);
		background = new SimpleViewable(null, new Rectangle(0, 0, w, h), Viewable.BG_LAYER, false);
		entries = new SimpleViewable[resKeys.length];

		for (int i = 0; i < actions.length; i++) {
			entries[i] = new SimpleViewable(Viewable.TEXTURE_KEYPREFIX + resKeys[i], (getWidth() - ENTRY_WIDTH) / 2,
					4 * KittenBaseModel.LHPIXEL_HEIGHT + i * (ENTRY_HEIGHT + KittenBaseModel.LHPIXEL_HEIGHT),
					ENTRY_WIDTH, ENTRY_HEIGHT, Viewable.MENU_LAYER);
		}

		selectionCursor = new SimpleViewable(Viewable.TEXTURE_KEYPREFIX + "selection", (getWidth() - ENTRY_WIDTH) / 2,
				4 * KittenBaseModel.LHPIXEL_HEIGHT + selction * (ENTRY_HEIGHT + KittenBaseModel.LHPIXEL_HEIGHT),
				CURSOR_WIDTH, CURSOR_HEIGHT, Viewable.MENU2_LAYER);

		putAction(InputActionKey.UP_P, new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6301899874617795350L;

			@Override
			public void actionPerformed(ActionEvent e) {
				selction = (selction - 1 + entries.length) % entries.length;

			}
		});
		putAction(InputActionKey.DOWN_P, new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6205517745963454529L;

			@Override
			public void actionPerformed(ActionEvent e) {

				selction = (selction + 1) % entries.length;

			}
		});
		putAction(InputActionKey.SELECT_P, new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6503123616124181745L;

			@Override
			public void actionPerformed(ActionEvent e) {

				ResourceManager.getInstance().getSound("pickup").play();

				actions[selction].actionPerformed(e);

			}
		});

		// build up Viewables		
		compView.addViewable(background);
		compView.addViewable(selectionCursor);
		compView.addAllViewables(entries);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {

		selectionCursor.setLocation(KittenBaseModel.LHPIXEL_WIDTH * 2,
				4 * KittenBaseModel.LHPIXEL_HEIGHT + selction * (ENTRY_HEIGHT + KittenBaseModel.LHPIXEL_HEIGHT));

		selectionCursor.setLocation((getWidth() - ENTRY_WIDTH) / 2,
				4 * KittenBaseModel.LHPIXEL_HEIGHT + selction * (ENTRY_HEIGHT + KittenBaseModel.LHPIXEL_HEIGHT));

	}

	/**
	 * Sets a background.
	 * 
	 * @param resKey
	 *            the resource key of the bg (only the resKey!)
	 */
	public void setBackground(String resKey) {
		if (resKey == null) {
			background.setVisable(false);
		}
		
		background.setTextureKey(resKey);
		background.setVisable(true);
	}

	/**
	 * Adds a {@link Viewable} to the {@link MenuScreen}.
	 * 
	 * @param v
	 *            the {@link Viewable} to be added
	 */
	public void addViewable(SimpleViewable v) {
		compView.addViewable(v);
	}
}
