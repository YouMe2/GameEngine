package de.uni_kiel.progOOproject17.controller;

import de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel;
import de.uni_kiel.progOOproject17.resources.GameProperties;
import de.uni_kiel.progOOproject17.view.PLDektopView;

/**
 * This class represents the controller of this MVC structure and therefore the
 * access point to the whole program it self. This class is the most powerful in
 * the whole Program structure followed by the {@link KittenBaseModel}.
 * 
 * It is the intermediary between all the views, which are split into inputs and
 * outputs, and the model, which itself contains a massively branched structure.
 * The creation of an {@link KittenGameController} requires a
 * {@link PLDektopView} and a {@link KittenBaseModel}. After creation the
 * controller awaits a call of the {@link #start(long)} method which will start
 * the ticked gamecycle. It can also be stooped with the {@link #stop()} method
 * and then restarted with again with the {@link #start(long)} method.
 *
 */
public class KittenGameController extends GameController<KittenBaseModel> {

	/**
	 * Constructs a new {@link KittenGameController} and sets the standard view and
	 * model. Initializes the InputActions in the standardIn as well as the
	 * debugging action: E to freeze the ticks, T to print FPS and TPS
	 * 
	 * 
	 * @param view
	 *            the {@link PLDektopView} serving as standartIn and standardOut
	 * @param model
	 *            the {@link KittenBaseModel} hosting the game.
	 */
	public KittenGameController(PLDektopView view, KittenBaseModel model) {
		super(view, model, Integer.valueOf(GameProperties.getInstance().getProperty("tickLength")));
	}
}
