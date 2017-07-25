/**
 * 
 */
package de.uni_kiel.progOOproject17.controller.abs;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;

import de.uni_kiel.progOOproject17.model.abs.ScreenControllerModel;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;
import de.uni_kiel.progOOproject17.view.abs.FramedIOView;

/**
 * @author Yannik Eikmeier
 * @since 11.04.2017
 *
 */
public class GameController<M extends ScreenControllerModel> extends TickedController {

	private M myModel;
	private long frozenticktime;
	
	private final InputConnector inputConnector;
	
	public GameController(FramedIOView ioView, M model) {
		super(ioView, ioView, model, 30);
		inputConnector = new InputConnector(getStandardIn(), getModel());
	
		HashMap<InputActionKey, String> keyBindings = new HashMap<>();
		keyBindings.put(InputActionKey.UP_P, "pressed W");
		keyBindings.put(InputActionKey.UP_R, "pressed W");
		
		keyBindings.put(InputActionKey.DOWN_P, "pressed S");
		keyBindings.put(InputActionKey.DOWN_R, "pressed S");

		keyBindings.put(InputActionKey.LEFT_P, "pressed A");
		keyBindings.put(InputActionKey.LEFT_R, "pressed A");
		
		keyBindings.put(InputActionKey.RIGHT_P, "pressed D");
		keyBindings.put(InputActionKey.RIGHT_R, "pressed D");
		
		keyBindings.put(InputActionKey.SELECT_P, "pressed SPACE");
		keyBindings.put(InputActionKey.SELECT_R, "pressed SPACE");
		
		inputConnector.useBindings(keyBindings);

		
		// debug actions
		//fps
		getStandardIn().addAction("pressed F1", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggelEnableTpsFpsPrint();
			}
		});
		//texture debug
		getStandardIn().addAction("pressed F2", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ioView.toggelTextureDebuggRender();
				System.out.println("TextureDebuggRender toggel");
				
			}
		});
		//freeze ticks
		getStandardIn().addAction("pressed F3", new AbstractAction() {

			private static final long serialVersionUID = -7423115611510814418L;

			@Override
			public void actionPerformed(ActionEvent e) {
				frozenticktime = stop();
			}
		});
		//release ticks
		getStandardIn().addAction("released F3", new AbstractAction() {

			private static final long serialVersionUID = 4385435299522741604L;

			@Override
			public void actionPerformed(ActionEvent e) {
				start(frozenticktime);
			}
		});
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.controller.abs.TickedController#getModel()
	 */
	@Override
	public M getModel() {
		if (myModel == null)
			myModel = (M) model;
		return myModel;
	}



}
