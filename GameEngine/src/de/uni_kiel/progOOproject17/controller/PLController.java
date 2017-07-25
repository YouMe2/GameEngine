package de.uni_kiel.progOOproject17.controller;

import de.uni_kiel.progOOproject17.controller.abs.TickedController;
import de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;
import de.uni_kiel.progOOproject17.resources.GameProperties;
import de.uni_kiel.progOOproject17.view.PLDektopView;
import de.uni_kiel.progOOproject17.view.abs.InputActionQueueWorker;
import de.uni_kiel.progOOproject17.view.abs.InputConnector;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;

/**
 * This class represents the controller of this MVC structure and therefore the
 * access point to the whole program it self. This class is the most powerful in
 * the whole Program structure followed by the {@link KittenBaseModel}.
 * 
 * It is the intermediary between all the views, which are split into inputs and
 * outputs, and the model, which itself contains a massively branched structure.
 * The creation of an {@link PLController} requires a {@link PLDektopView}
 * and a {@link KittenBaseModel}. After creation the controller awaits a call of the
 * {@link #start(long)} method which will start the ticked gamecycle. It can
 * also be stooped with the {@link #stop()} method and then restarted with again
 * with the {@link #start(long)} method.
 *
 */
public class PLController extends TickedController {

	/**
	 * stores the casted model to prevent casting on every call of
	 * {@link #getModel()}
	 */
	private KittenBaseModel myModel;

	/**
	 * stores the gameTime for a (re-)start when stopped for debugging purposes
	 * 
	 * @see #stop()
	 * @see #start(long)
	 */
	private long gametimeSafe = 0;

	private final InputConnector inputConnector;

	private InputActionQueueWorker worker;
	
	/**
	 * Constructs a new {@link PLController} and sets the standard view and
	 * model. Initializes the InputActions in the standardIn as well as the
	 * debugging action: E to freeze the ticks, T to print FPS and TPS
	 * 
	 * 
	 * @param view
	 *            the {@link PLDektopView} serving as standartIn and standardOut
	 * @param model
	 *            the {@link KittenBaseModel} hosting the game.
	 */
	public PLController(PLDektopView view, KittenBaseModel model) {
		super(view, view, model, Integer.valueOf(GameProperties.getInstance().getProperty("tickLength")));

		// standard Game Actions:
		
		worker = new InputActionQueueWorker(getModel());
		
		inputConnector = new InputConnector(getStandardIn(), worker);
		
		HashMap<InputActionKey, String> keyBindings = new HashMap<>();
		keyBindings.put(InputActionKey.UP_P, "pressed W");
		keyBindings.put(InputActionKey.UP_R, "released W");
		
		keyBindings.put(InputActionKey.DOWN_P, "pressed S");
		keyBindings.put(InputActionKey.DOWN_R, "released S");

		keyBindings.put(InputActionKey.LEFT_P, "pressed A");
		keyBindings.put(InputActionKey.LEFT_R, "released A");
		
		keyBindings.put(InputActionKey.RIGHT_P, "pressed D");
		keyBindings.put(InputActionKey.RIGHT_R, "released D");
		
		keyBindings.put(InputActionKey.SELECT_P, "pressed SPACE");
		keyBindings.put(InputActionKey.SELECT_R, "released SPACE");
		
		inputConnector.useBindings(keyBindings);
		
		
		// debugging actions
		getStandardIn().addAction("pressed E", new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7423115611510814418L;

			@Override
			public void actionPerformed(ActionEvent e) {
				gametimeSafe = stop();
			}
		});

		getStandardIn().addAction("released E", new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4385435299522741604L;

			@Override
			public void actionPerformed(ActionEvent e) {
				start(gametimeSafe);
			}
		});

		getStandardIn().addAction("pressed R", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.toggelTextureDebuggRender();
				System.out.println("ResDebuggRender toggel");
				
			}
		});
		
//		getStandardIn().addAction("pressed H", new AbstractAction() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				view.toggelHitboxDebugRender();
//				System.out.println("HitboxDebugRender toggel");
//				
//			}
//		});
		
		getStandardIn().addAction("pressed 1", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.showDebugScreen();
				System.out.println("Showing DegubScreen");
			}
		});
		
		getStandardIn().addAction("pressed T", new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 305735706610084922L;

			@Override
			public void actionPerformed(ActionEvent e) {
				toggelEnableTpsFpsPrint();
			}
		});

	}
	
	@Override
	public void start(long timestamp) {
		worker.start();
		super.start(timestamp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_kiel.progOOproject17.controller.abs.TickedController#getModel()
	 */
	@Override
	public KittenBaseModel getModel() {
		if (myModel == null)
			myModel = (KittenBaseModel) model;
		return myModel;
	}

}
