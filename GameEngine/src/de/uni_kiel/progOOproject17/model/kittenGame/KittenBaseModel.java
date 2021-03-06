package de.uni_kiel.progOOproject17.model.kittenGame;

import de.uni_kiel.progOOproject17.controller.KittenGameController;
import de.uni_kiel.progOOproject17.model.abs.ScreenControllerModel;
import de.uni_kiel.progOOproject17.model.abs.TickedBaseModel;
import de.uni_kiel.progOOproject17.model.screen.DebugScreen;
import de.uni_kiel.progOOproject17.model.screen.PauseMenu;
import de.uni_kiel.progOOproject17.model.screen.Screen;
import de.uni_kiel.progOOproject17.view.abs.OutputView;
import de.uni_kiel.progOOproject17.view.abs.Viewable;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This class represents the very base of the model structure of this MVC
 * structure. This class is the 2nd most powerful after the {@link KittenGameController}
 * and the most powerful in the model branch. It controls all the
 * {@link Screen}s and redirects the input actions to the one which is currently
 * active ({@link #currentScreen}).
 * 
 * This also is the first class where it gets some what specific in terms of
 * this being a game made to be viewable on the Lighthouse!
 * 
 */
public class KittenBaseModel extends ScreenControllerModel {

	/**
	 * The width of the Lighthouse in pixels
	 */
	public static final int LH_WIDTH = 28;
	/**
	 * The height of the Lighthouse in pixels
	 */
	public static final int LH_HEIGHT = 14;

	/**
	 * The scaling factor from LightHouse pixels width to in game width
	 */
	public static final int LHPIXEL_WIDTH = 16;
	/**
	 * The scaling factor from LightHouse pixels height to in game height
	 */
	public static final int LHPIXEL_HEIGHT = 32;

	/**
	 * The width of the game logic
	 */
	public static final int GAME_WIDTH = LH_WIDTH * LHPIXEL_WIDTH; // = 420
	/**
	 * The heigt of the game logic
	 */
	public static final int GAME_HEIGHT = LH_HEIGHT * LHPIXEL_HEIGHT; // = 490

	
	/**
	 * The {@link Action} which starts a new {@link KittenGameScreen}.
	 */
	public AbstractAction newGame = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -42763394711236754L;

		@Override
		public void actionPerformed(ActionEvent e) {

			showScreen(new KittenGameScreen(GAME_WIDTH, GAME_HEIGHT, pauseGame, endGame));
		}
	};

	/**
	 * The {@link Action} which pauses an active {@link KittenGameScreen} and displays
	 * the {@link PauseMenu} as an overlay to the paused {@link KittenGameScreen}
	 */
	public AbstractAction pauseGame = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5887420044511791287L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			PauseMenu pm = new PauseMenu(GAME_WIDTH, GAME_HEIGHT, resumeAction, exitGame);
			pm.addViewable(getActiveScreeen().getFullSimpleViewable());
			showScreen(pm);
		}
	};

	/**
	 * The {@link Action} which resumes a previously paused Game
	 */
	public AbstractAction resumeAction = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8495282146520468156L;

		@Override
		public void actionPerformed(ActionEvent e) {

			resumePreviousScreen();
		}
	};

	/**
	 * The {@link Action} which displays the {@link KittenEndScreen} after a game has
	 * ended.
	 */
	public AbstractAction endGame = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8224197960021665769L;

		@Override
		public void actionPerformed(ActionEvent e) {
			showScreen(new KittenEndScreen(GAME_WIDTH, GAME_HEIGHT, ((KittenGameScreen) getActiveScreeen()).getPlayerStats(),
					newGame, exitGame));
		}
	};

	/**
	 * The {@link Action} which exits the whole game.
	 */
	public AbstractAction exitGame = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7904181445578765754L;

		@Override
		public void actionPerformed(ActionEvent e) {

			System.exit(0);

		}
	};

	/**
	 * Constructs a new {@link KittenBaseModel} which starts the
	 * {@link KittenStartMenu}.
	 */
	public KittenBaseModel() {
		showScreen(new KittenStartMenu(GAME_WIDTH, GAME_HEIGHT, newGame, exitGame));
	}
	
	public void showDebugScreen(){
		showScreen( new DebugScreen(GAME_WIDTH, GAME_HEIGHT, resumeAction));
	}
	

	/**
	 * Converts the given Coords from Lighthouse scaling to ingame scaling
	 * 
	 * @param x
	 *            the x coord in LHPixles
	 * @param y
	 *            the y coord in LHPixels
	 * @return the {@link Point} in ingame Coords
	 */
	public static Point lhToGame(float x, float y) {
		return new Point(Math.round(x * LHPIXEL_WIDTH), Math.round(y * LHPIXEL_HEIGHT));
	}

	/**
	 * Converts the given bounds from Lighthouse scaling to ingame scaling
	 * 
	 * @param x
	 *            the x coord on the lH
	 * @param y
	 *            the y coord on the lH
	 * @param w
	 *            the width on the lH
	 * @param h
	 *            the height on the lH
	 * @return the bounds in ingame sclaing
	 */
	public static Rectangle lhToGam(float x, float y, float w, float h) {
		return new Rectangle(Math.round(x * LHPIXEL_WIDTH), Math.round(y * LHPIXEL_HEIGHT),
				Math.round(w * LHPIXEL_WIDTH), Math.round(h * LHPIXEL_HEIGHT));
	}
}
