/**
 * 
 */
package de.uni_kiel.progOOproject17.model.testgame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.uni_kiel.progOOproject17.controller.abs.TickedController;
import de.uni_kiel.progOOproject17.model.abs.ScreenedBaseModel;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;
import de.uni_kiel.progOOproject17.view.abs.FramedIOView;

/**
 * @author Yannik Eikmeier
 * @since 11.04.2017
 *
 */
public class GameController<M extends ScreenedBaseModel> extends TickedController {

	private M myModel;
	private long frozenticktime;
	
	public GameController(FramedIOView ioView, M model) {
		super(ioView, ioView, model, 30);
		
	
		// standard Game Actions:
		getStandardIn().addAction("pressed W", model.getAction(InputActionKey.UP_P));
		getStandardIn().addAction("released W", model.getAction(InputActionKey.UP_R));
		getStandardIn().addAction("pressed S", model.getAction(InputActionKey.DOWN_P));
		getStandardIn().addAction("released S", model.getAction(InputActionKey.DOWN_R));
		
		getStandardIn().addAction("pressed D", model.getAction(InputActionKey.RIGHT_P));
		getStandardIn().addAction("released D", model.getAction(InputActionKey.RIGHT_R));
		getStandardIn().addAction("pressed A", model.getAction(InputActionKey.LEFT_P));
		getStandardIn().addAction("released A", model.getAction(InputActionKey.LEFT_R));
		
		getStandardIn().addAction("pressed SPACE", model.getAction(InputActionKey.SELECT_P));
		getStandardIn().addAction("released SPACE", model.getAction(InputActionKey.SELECT_R));

		
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
		getStandardIn().addAction("pressed E", new AbstractAction() {

			private static final long serialVersionUID = -7423115611510814418L;

			@Override
			public void actionPerformed(ActionEvent e) {
				frozenticktime = stop();
			}
		});
		//release ticks
		getStandardIn().addAction("released E", new AbstractAction() {

			private static final long serialVersionUID = 4385435299522741604L;

			@Override
			public void actionPerformed(ActionEvent e) {
				start(frozenticktime);
			}
		});
		
		//a
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
