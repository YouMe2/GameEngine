package de.uni_kiel.progOOproject17.model.kittenGame.levelGen;

import static de.uni_kiel.progOOproject17.model.PLBaseModel.LHPIXEL_HEIGHT;
import static de.uni_kiel.progOOproject17.model.PLBaseModel.LHPIXEL_WIDTH;
import static de.uni_kiel.progOOproject17.model.kittenGame.levelGen.LevelGenerator.*;

import de.uni_kiel.progOOproject17.model.abs.GameElement;
import de.uni_kiel.progOOproject17.model.kittenGame.KittenEnemy;

import java.awt.Point;

/**
 * An obstacle is a list of <code>GameElement</code>s. To specify an obstacle,
 * it needs to know how much space it's going to take up and how to create the
 * list of <code>GameElement</code>s. For the latter, an {@link ObstacleCreator}
 * must be passed that defines how to instantiate the elements. This creator
 * will be used later to create the obstacle.
 *
 */
public enum Obstacle {

	SINGLE(LHPIXEL_WIDTH * 4, x -> {
		// creates a single enemy on floor level
		KittenEnemy e = new KittenEnemy(ENEMYRESKEY, new Point(LHPIXEL_WIDTH * 2 + x, FLOOR_POS));
		
		
		e.move(0, -e.getView().getHeight());
		e.setGravityActive(true);
		return new GameElement[] { e };
	}), DOUBLE(LHPIXEL_WIDTH * 6, x -> {
		// creates two enemies next to each other on floor level
		int distance = LHPIXEL_WIDTH;
		KittenEnemy e0 = new KittenEnemy(ENEMYRESKEY, new Point(LHPIXEL_WIDTH * 2 + x, FLOOR_POS));
		e0.move(0, -e0.getView().getHeight());
		e0.setGravityActive(true);
		KittenEnemy e1 = new KittenEnemy(ENEMYRESKEY, new Point(LHPIXEL_WIDTH * 2 + x + e0.getView().getWidth() + distance, FLOOR_POS));
		e1.move(0, -e1.getView().getHeight());
		e1.setGravityActive(true);
		return new GameElement[] { e0, e1 };
	}), HOVERING(LHPIXEL_WIDTH * 8, x -> {
		// creates a single enemy hovering above the floor
		int hoveringHeight = (int) (LHPIXEL_HEIGHT * 1.1);
		KittenEnemy e = new KittenEnemy(ENEMYRESKEY, new Point(LHPIXEL_WIDTH * 2 + x, FLOOR_POS));
		e.move(0, -e.getView().getHeight() - hoveringHeight);
		e.setGravityActive(false);
		return new GameElement[] { e };
	}), DOUBLE_HOVERING(LHPIXEL_WIDTH * 10, x -> {
		// creates two enemies next to each other hovering above the floor
		int distance = (int) (LHPIXEL_WIDTH * 1.0);
		int hoveringHeight = (int) (LHPIXEL_HEIGHT * 1.1);
		KittenEnemy e0 = new KittenEnemy(ENEMYRESKEY, new Point(LHPIXEL_WIDTH * 2 + x, FLOOR_POS));
		e0.move(0, -e0.getView().getHeight() - hoveringHeight);
		e0.setGravityActive(false);
		KittenEnemy e1 = new KittenEnemy(ENEMYRESKEY, new Point(LHPIXEL_WIDTH * 2 + x + e0.getView().getWidth() + distance, FLOOR_POS));
		e1.move(0, -e1.getView().getHeight() - hoveringHeight);
		e1.setGravityActive(false);
		return new GameElement[] { e0, e1 };
	}), TRIPLE_HOVERING(LHPIXEL_WIDTH * 20, x -> {
		// creates three enemies next to each other hovering above the floor
		int distance = (int) (LHPIXEL_WIDTH * 1.0);
		int hoveringHeight = (int) (LHPIXEL_HEIGHT * 1.1);
		KittenEnemy e0 = new KittenEnemy(ENEMYRESKEY, new Point(x, FLOOR_POS));
		e0.move(0, -e0.getView().getHeight() - hoveringHeight);
		e0.setGravityActive(false);
		KittenEnemy e1 = new KittenEnemy(ENEMYRESKEY, new Point(x + e0.getView().getWidth() + distance, FLOOR_POS));
		e1.move(0, -e1.getView().getHeight() - hoveringHeight);
		e1.setGravityActive(false);
		KittenEnemy e2 = new KittenEnemy(ENEMYRESKEY, new Point(e1.getView().getX() + e1.getView().getWidth() + distance, FLOOR_POS));
		e2.move(0, -e1.getView().getHeight() - hoveringHeight);
		e2.setGravityActive(false);
		return new GameElement[] { e0, e1, e2 };
	}), TWO_ON_TOP(LHPIXEL_WIDTH * 16, x -> {
		// Creates two enemies on top of each other, one on the floor, the other
		// hovering above. The player has to crouch in the air to master this
		// obstacle safely.
		int hoveringHeightUpper = LHPIXEL_HEIGHT * 2;
		int hoveringHeightLower = (int) (LHPIXEL_HEIGHT * 1.1);
		int spaceBefore = (int) (LHPIXEL_HEIGHT * 2.5);
		KittenEnemy e0 = new KittenEnemy(ENEMYRESKEY, new Point(x + spaceBefore, FLOOR_POS));
		e0.move(0, -e0.getView().getHeight() - hoveringHeightLower);
		e0.setGravityActive(false);
		KittenEnemy e1 = new KittenEnemy(ENEMYRESKEY, new Point(x + spaceBefore, FLOOR_POS));
		e1.move(0, -e1.getView().getHeight() - hoveringHeightUpper);
		e1.setGravityActive(false);
		return new GameElement[] { e0, e1 };
	}), TWO_ON_TOP_HIGH(LHPIXEL_WIDTH * 6, x -> {
		// Creates two hovering enemies on top of each other. This obstacle can
		// easily be confused with the one before, TWO_ON_TOP.
		int hoveringHeightUpper = LHPIXEL_HEIGHT * 3;
		int hoveringHeightLower = (int) (LHPIXEL_HEIGHT * 1.1);
		int spaceBefore = (int) (LHPIXEL_HEIGHT * 2.5);
		KittenEnemy e0 = new KittenEnemy(ENEMYRESKEY, new Point(x + spaceBefore, FLOOR_POS));
		e0.move(0, -e0.getView().getHeight() - hoveringHeightLower);
		e0.setGravityActive(false);
		KittenEnemy e1 = new KittenEnemy(ENEMYRESKEY, new Point(x + spaceBefore, FLOOR_POS));
		e1.move(0, -e1.getView().getHeight() - hoveringHeightUpper);
		e1.setGravityActive(false);
		return new GameElement[] { e0, e1 };
	}), CENTER(LHPIXEL_WIDTH * 9, x -> {
		// Creates an enemy hovering a little bit above the ground so the player
		// has to decide quickly whether to crouch or to jump.
		int spaceBefore = LHPIXEL_WIDTH * 1;
		int hoveringHeight = 15;
		KittenEnemy e = new KittenEnemy(ENEMYRESKEY, new Point(x + spaceBefore, FLOOR_POS));
		e.move(0, -e.getView().getHeight() - hoveringHeight);
		e.setGravityActive(false);
		return new GameElement[] { e };
	});

	/**
	 * The width of the obstacle.
	 */
	private int width;
	/**
	 * The <code>ObstacleCreator</code> used to define how to instantiate a new
	 * obstacle.
	 */
	private final ObstacleCreator creator;

	/**
	 * Creates a new obstacle with the given width and obstacle creator.
	 *
	 * @param width
	 *            the width of the obstacle
	 * @param c
	 *            the obstacle creator used to define how to instantiate a new
	 *            obstacle
	 */
	private Obstacle(int width, ObstacleCreator c) {
		this.width = width;
		creator = c;
	}

	/**
	 * Creates a new instance of this obstacle, represented as a list of
	 * <code>GameElement</code>s.
	 *
	 * @param obstacleStart
	 *            the starting position used to create the obstacle
	 * @return the obstacle as a list of <code>GameElement</code>s
	 */
	public GameElement[] createNew(int obstacleStart) {
		return creator.createNew(obstacleStart);
	}

	/**
	 * Gets the width of this obstacle.
	 *
	 * @return the width of this obstacle
	 */
	public int getWidth() {
		return width;
	}
}
