/**
 * 
 */
package de.uni_kiel.progOOproject17.model.testgame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.uni_kiel.progOOproject17.controller.abs.TickedController;
import de.uni_kiel.progOOproject17.model.abs.TickedBaseModel;
import de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel;
import de.uni_kiel.progOOproject17.view.abs.FramedIOView;

/**
 * @author Yannik Eikmeier
 * @since 11.04.2017
 *
 */
public class GameController<M extends TickedBaseModel> extends TickedController {

	private M myModel;
	
	/**
	 * 
	 */
	public GameController(FramedIOView ioView, M model) {
		super(ioView, ioView, model, 30);
		
		getStandardIn().addAction("pressed F1", new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 305735706610084922L;

			@Override
			public void actionPerformed(ActionEvent e) {
				toggelEnableTpsFpsPrint();
			}
		});
		getStandardIn().addAction("pressed F2", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ioView.toggelResDebuggRender();
				System.out.println("ResDebuggRender toggel");
				
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
