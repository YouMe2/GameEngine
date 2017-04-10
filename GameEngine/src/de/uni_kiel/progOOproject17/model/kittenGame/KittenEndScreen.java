package de.uni_kiel.progOOproject17.model.kittenGame;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.screen.MenuScreen;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * This class represents a {@link MenuScreen} which will show after the game
 * ended.
 */
public class KittenEndScreen extends MenuScreen {

	private KittenScoreboard scoreboard;

	/**
	 * Constructs a new {@link KittenEndScreen} that will show the given {@link KittenStats}
	 * and a simple {@link MenuScreen} with a retry action and a exit action.
	 * 
	 * @param w the width
	 * @param h the height
	 * @param stats the {@link KittenStats} to show
	 * @param newGameAction the retry action
	 * @param exitAction the exit action
	 */
	public KittenEndScreen(int w, int h, KittenStats stats, Action newGameAction, Action exitAction) {
		super(w, h, new String[] { "play", "exit" }, new Action[] { newGameAction, exitAction });

		scoreboard = new KittenScoreboard(stats);

		addViewable(scoreboard.getViewable());
		
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uni_kiel.progOOproject17.model.MenuScreen#tick(long)
	 */
	@Override
	public void tick(long timestamp) {
		scoreboard.tick(timestamp);
		super.tick(timestamp);
	}

	/*
//	 * (non-Javadoc)
//	 *
//	 * @see de.uni_kiel.progOOproject17.model.MenuScreen#getViewables()
//	 */
//	@Override
//	public Viewable[] getViewables() {
//
//		ArrayList<Viewable> views = new ArrayList<>();
//		views.addAll(Arrays.asList(scoreboard.getViewables()));
//		views.addAll(Arrays.asList(super.getViewables()));
//		return views.toArray(new Viewable[views.size()]);
//
//	}

}
