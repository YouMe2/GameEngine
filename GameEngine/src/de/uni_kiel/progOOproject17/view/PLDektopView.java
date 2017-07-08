package de.uni_kiel.progOOproject17.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import de.uni_kiel.progOOproject17.model.abs.Hitbox;
import de.uni_kiel.progOOproject17.model.kittenGame.KittenBaseModel;
import de.uni_kiel.progOOproject17.resources.ResourceManager;
import de.uni_kiel.progOOproject17.view.abs.FramedIOView;
import de.uni_kiel.progOOproject17.view.abs.InputView;
import de.uni_kiel.progOOproject17.view.abs.OutputView;
import de.uni_kiel.progOOproject17.view.abs.Viewable;
import de.uni_kiel.progOOproject17.view.abs.ViewCompound;
import de.uni_kiel.progOOproject17.view.abs.Viewable.ViewContentKey;

/**
 * This class represents the desktop-only-part of the view of this MVC
 * structure. It combines an {@link InputView} and an {@link OutputView}.
 * 
 */
public class PLDektopView extends FramedIOView {

	private static final long serialVersionUID = 2350129042863402099L;
	private BufferedImage img;

	private Canvas canvas;

	/**
	 * Constructs a new {@link PLDektopView}.
	 * 
	 * @param title
	 *            the title
	 */
	public PLDektopView(String title, int width, int height) {
		super(title, width, height, false, ResourceManager.getInstance());
		img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		canvas = new Canvas();
		canvas.setSize(width, height);
		
		contentPane.add(canvas);
		pack();
//		setResizable(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.OutputView#render(de.uni_kiel.
	 * progOOproject17.view.abs.Viewable[])
	 */
	@Override
	public void render(Viewable viewable) {

		final Graphics gr = img.getGraphics();
		gr.clearRect(0, 0, img.getWidth(), img.getHeight());

		renderTo(viewable, gr);
		gr.dispose();

		if (canvas.getBufferStrategy() == null) {
			canvas.createBufferStrategy(2);
		}
		Graphics gr2 = canvas.getBufferStrategy().getDrawGraphics();
		gr2.drawImage(img, 0, 0, KittenBaseModel.GAME_WIDTH, KittenBaseModel.GAME_HEIGHT, null);
		gr2.dispose();
		canvas.getBufferStrategy().show();

	}

}
