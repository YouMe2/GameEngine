package de.uni_kiel.progOOproject17.model.kittenGame;

import de.uni_kiel.progOOproject17.model.abs.GameCompound;
import de.uni_kiel.progOOproject17.view.abs.SimpleViewable;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

import static de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel.GAME_WIDTH;
import static de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel.LHPIXEL_HEIGHT;
import static de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel.LHPIXEL_WIDTH;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a {@link GameCompound} that acts as a Scoreboard. The
 * {@link KittenScoreboard} can display the three values given by the {@link KittenStats}
 * interface.
 *
 */
public class KittenScoreboard extends GameCompound {

	/**
	 * The stats this {@link KittenScoreboard} shows.
	 */
	private final KittenStats stats;

	/**
	 * The {@link Viewable} that make up the display for the progress
	 */
	private SimpleViewable progressDisplay;

	/**
	 * The {@link Viewable}s that make up the display for the points
	 */
	private SimpleViewable[] pointsDisplay;
	private String pointsKey = "point";

	/**
	 * The {@link Viewable}s that make up the display for the lifes
	 */
	private SimpleViewable[] lifesDisplay;
	private String lifesKey = "life";

	/**
	 * Constructs a new {@link KittenScoreboard} which when being ticked will display the {@link KittenStats} in stats.
	 * 
	 * @param stats the {@link KittenStats} to display
	 */
	public KittenScoreboard(KittenStats stats) {
		super(0, 0, GAME_WIDTH, LHPIXEL_HEIGHT * 3);
		this.stats = stats;

		pointsDisplay = new SimpleViewable[GAME_WIDTH / (LHPIXEL_WIDTH * 3)];
		lifesDisplay = new SimpleViewable[GAME_WIDTH / (LHPIXEL_WIDTH * 3)];

		progressDisplay = new SimpleViewable("stepbar", 0, Math.round(LHPIXEL_HEIGHT * 0.1f), 0,
				Math.round(LHPIXEL_HEIGHT * 0.8f), Viewable.SB_LAYER);

		for (int i = 0; i < pointsDisplay.length; i++)
			pointsDisplay[i] = new SimpleViewable(null, LHPIXEL_WIDTH * (1 + 3 * i), LHPIXEL_HEIGHT * 2,
					LHPIXEL_WIDTH * 2, LHPIXEL_HEIGHT, Viewable.SB_LAYER);

		for (int i = 0; i < lifesDisplay.length; i++)
			lifesDisplay[i] = new SimpleViewable(null, LHPIXEL_WIDTH * (1 + 3 * i), LHPIXEL_HEIGHT, LHPIXEL_WIDTH * 2,
					LHPIXEL_HEIGHT, Viewable.SB_LAYER);

	}

	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {

		progressDisplay.setSize((int) (stats.getProgress() * KittenBaseModel.GAME_WIDTH), progressDisplay.getHeight());

		int p = stats.getPoints();
		if (p > pointsDisplay.length)
			p = pointsDisplay.length;
		for (int i = 0; i < p; i++)
			pointsDisplay[i].setTextKey(pointsKey);
		for (int i = p; i < pointsDisplay.length; i++)
			pointsDisplay[i].setTextKey(null);

		int l = stats.getLifes();
		if (l > lifesDisplay.length)
			l = lifesDisplay.length;

		for (int i = 0; i < l; i++)
			lifesDisplay[i].setTextKey(lifesKey);
		for (int i = l; i < lifesDisplay.length; i++)
			lifesDisplay[i].setTextKey(null);

	}

	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.model.abs.GameCompound#getViewables()
	 */
	@Override
	public Viewable[] getViewables() {

		ArrayList<Viewable> views = new ArrayList<>();
		views.add(progressDisplay);
		views.addAll(Arrays.asList(pointsDisplay));
		views.addAll(Arrays.asList(lifesDisplay));

		return views.toArray(new Viewable[views.size()]);
	}

}
