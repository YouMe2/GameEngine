/**
 * 
 */
package de.uni_kiel.progOOproject17.model.screen;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.Block;
import de.uni_kiel.progOOproject17.model.SimpleEnvironment;
import de.uni_kiel.progOOproject17.model.abs.CreationHelper;
import de.uni_kiel.progOOproject17.model.abs.Destroyable;
import de.uni_kiel.progOOproject17.model.abs.Environment;
import de.uni_kiel.progOOproject17.model.abs.GameElement;
import de.uni_kiel.progOOproject17.model.abs.Hitbox;
import de.uni_kiel.progOOproject17.model.abs.MoveCommand;
import de.uni_kiel.progOOproject17.view.abs.ViewCompound;
import de.uni_kiel.progOOproject17.view.abs.Viewable;

/**
 * @author Yannik Eikmeier
 * @since 28.03.2017
 *
 */
public class DebugScreen extends Screen {

	private final LinkedList<GameElement> gameElements;
	private final LinkedList<GameElement> createdElements;
	private final LinkedList<Destroyable> destroyedElements;


	private final Environment environment;
	private final CreationHelper creationHelper = new CreationHelper() {
		/*
		 * /* (non-Javadoc)
		 * 
		 * @see de.uni_kiel.progOOproject17.model.abs.ElementCreator#create(de.
		 * uni_kiel.progOOproject17.model.abs.GameElement)
		 */
		@Override
		public void create(GameElement g) {

			// System.out.println("Created: " + g.getResourceKey());

			createdElements.add(g);
			compView.addViewable(g.getFullSimpleViewable());
//			if (g instanceof Collidable)
//				compView.addViewable(((Collidable) g).getHitbox().getDebugViewable());
			
			
			g.activate(environment, this);
		}

		@Override
		public void onDestruction(Destroyable d) {
			// System.out.println("Destroyed: " + ((GameElement)
			// d).getResourceKey());

			destroyedElements.add(d);
		}

	};

	private Block testBlock;
	private Block block;

	/**
	 * @param w
	 * @param h
	 */
	public DebugScreen(int w, int h, Action resumeAction) {
		super(w, h);
		gameElements = new LinkedList<>();
		destroyedElements = new LinkedList<>();
		createdElements = new LinkedList<>();
		environment = new SimpleEnvironment(gameElements);

//		testBlock = new Block(new Hitbox.PolygonHitbox(new Point(60, 60),
//				new Point[] { new Point(0, 0), new Point(30, 30), new Point(0, 30), new Point(-10, 10) }));

//		testBlock = new Block(new Hitbox.CircleHitbox(60, 60, 6));

		testBlock = new Block(new Hitbox.LineHitbox(60, 60, 80, 70));
		testBlock.getFullSimpleViewable().setClearTextKey("floor");
		testBlock.getFullSimpleViewable().setLocation(60, 60);
		testBlock.getFullSimpleViewable().setSize(4, 4);
		testBlock.getFullSimpleViewable().setPriority(Viewable.ENTITY_LAYER);
		testBlock.activate(environment, creationHelper);

		Block line = new Block(new Hitbox.LineHitbox(20, 100, 20, 200));
		creationHelper.create(line);
		System.out.println(line.getHitbox());

		creationHelper.create(new Block(new Hitbox.CircleHitbox(170, 170, 32)));

		creationHelper.create(new Block(new Hitbox.PointHitbox(50, 200)));

		// creationHelper.create(new Background("floor", 50, 20, 1, 1));

		block = new Block(new Hitbox.PolygonHitbox(new Point(100, 100),
				new Point[] { new Point(0, 0), new Point(100, -10), new Point(0, 30) }));
		block.getFullSimpleViewable().setClearTextKey("floor");
		block.getFullSimpleViewable().setLocation(100, 100);
		block.getFullSimpleViewable().setSize(100, 30);
		block.getFullSimpleViewable().setPriority(Viewable.ENTITY_LAYER);
		creationHelper.create(block);
		creationHelper.create(testBlock);

		putAction(InputActionKey.UP_P, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.UP);

			}
		});
		putAction(InputActionKey.DOWN_P, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.DOWN);

			}
		});
		putAction(InputActionKey.LEFT_P, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.LEFT);

			}
		});
		putAction(InputActionKey.RIGHT_P, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.RIGHT);

			}
		});

		putAction(InputActionKey.UP_R, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.NONE);

			}
		});
		putAction(InputActionKey.DOWN_R, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.NONE);

			}
		});
		putAction(InputActionKey.LEFT_R, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.NONE);

			}
		});
		putAction(InputActionKey.RIGHT_R, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testBlock.setCurrMoveCommand(MoveCommand.NONE);

			}
		});

		putAction(InputActionKey.SELECT_P, resumeAction);

		// Viewable zusammen setzten:

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {

		for (GameElement gameElement : gameElements) {
			gameElement.tick(timestamp);
		}
		//
		// System.out.println(testBlock.getHitbox());
		// System.out.println(block.getHitbox());

		// System.out.println(((Hitbox.PolygonHitbox)
		// testBlock.getHitbox()).isInside(new Point(50, 20)));

		gameElements.removeAll(destroyedElements);
		destroyedElements.clear();

		gameElements.addAll(createdElements);
		createdElements.clear();

	}


}
