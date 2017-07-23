package de.uni_kiel.progOOproject17.model.kittenGame;

import de.uni_kiel.progOOproject17.model.screen.MenuScreen;
import de.uni_kiel.progOOproject17.resources.GameProperties;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.Viewable;
import javax.swing.Action;

/**
 * This class represents a {@link MenuScreen} which acts as the
 * {@link KittenStartMenu} at the beginning of the game.
 * 
 */
public class KittenStartMenu extends MenuScreen {

	/**
	 * Constructs a new {@link KittenStartMenu}.
	 * 
	 * @param w the width
	 * @param h the height
	 */
	public KittenStartMenu(int w, int h, Action kittenGameAction, Action exitAction) {
		super(w, h, new String[] { "play_kitten", "exit" }, new Action[] { kittenGameAction, exitAction });
		setBackground("black");

		addViewable(new SimpleViewable(Viewable.TEXTURE_KEYPREFIX + GameProperties.getInstance().getProperty("titleResKey"), KittenBaseModel.lhToGam(8, 0, 12, 3), Viewable.FLOOR_LAYER));

	}

}
