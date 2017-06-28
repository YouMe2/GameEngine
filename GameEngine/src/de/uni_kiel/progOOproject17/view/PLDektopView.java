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
import de.uni_kiel.progOOproject17.view.abs.Viewable.Key;

/**
 * This class represents the desktop-only-part of the view of this MVC
 * structure. It combines an {@link InputView} and an {@link OutputView}.
 * 
 */
public class PLDektopView extends FramedIOView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350129042863402099L;
	private BufferedImage img;
	private ResourceManager res = ResourceManager.getInstance();

	private boolean resDebuggRender = false;
	private boolean hitboxDebugRender = false;

	private Canvas canvas;

	/**
	 * Constructs a new {@link PLDektopView}.
	 * 
	 * @param title
	 *            the title
	 */
	public PLDektopView(String title) {
		super(title, KittenBaseModel.GAME_WIDTH, KittenBaseModel.GAME_HEIGHT, false);
		img = new BufferedImage(KittenBaseModel.GAME_WIDTH, KittenBaseModel.GAME_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		res = ResourceManager.getInstance();
		canvas = new Canvas();

		canvas.setSize(contentPane.getWidth(), contentPane.getHeight());
		
		canvas.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("moved!");
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
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

		render(viewable, gr);
		gr.dispose();

		if (canvas.getBufferStrategy() == null) {
			canvas.createBufferStrategy(2);
		}
		Graphics gr2 = canvas.getBufferStrategy().getDrawGraphics();
		gr2.drawImage(img, 0, 0, KittenBaseModel.GAME_WIDTH, KittenBaseModel.GAME_HEIGHT, null);
		gr2.dispose();
		canvas.getBufferStrategy().show();

	}

	private void render(Viewable v, Graphics gr) {
		if (v == null)
			return;
		if (!v.isVisble())
			return;
		
		

		Rectangle rect = v.getViewRect();
		Key key = v.getContentKey();
		String keyText = key != null ? key.getText() : null;

		if (keyText == null)
			keyText = "null";
		
		// COMPUND RENDER
		if (keyText.equals(ViewCompound.COMPOUND_KEYTEXT) && key instanceof ViewCompound) {
			ViewCompound comp = (ViewCompound) key;

			if (resDebuggRender) {
				// resource debug mode render
				gr.setColor(Color.WHITE);
				gr.drawRect(rect.x, rect.y, rect.width, rect.height);
				gr.drawString(keyText, rect.x + 2, rect.y + rect.height - 8);
			}
			
			gr.translate(rect.x, rect.y);
			
			
			for (Viewable viewable : comp) {
				render(viewable, gr);
			}
			
			gr.translate(-rect.x, -rect.y);

		}
		else if (keyText.startsWith(Viewable.STRINGKEY_PREFIX)) {
			String text = keyText.replaceFirst(Viewable.STRINGKEY_PREFIX, "");
			gr.setColor(Color.BLACK);
			gr.setFont(new Font(null, Font.PLAIN, rect.height));
			gr.drawString(text, rect.x, rect.y + rect.height);
			
		}
		// DEBUG RENDER HIBOXES
		else if (keyText.startsWith(Viewable.DEBUGKEY_PREFIX)) {
			if (hitboxDebugRender) {
				// hitbox debugging
				gr.setColor(Color.CYAN);
				String key2 = keyText.replaceFirst(Viewable.DEBUGKEY_PREFIX, "");
				if (key2.equals(Hitbox.CIRCLE_KEY)) {
					gr.drawOval(rect.x, rect.y, rect.width, rect.height);
				}
				if (key2.equals(Hitbox.LINE_KEY)) {
					// gr.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y +
					// rect.height);
					gr.drawLine(rect.x, rect.y, rect.width, rect.height);
				}

			}
		} 
		// STANDARD RENDER text -> img
		else {
			if (resDebuggRender) {
				// resource debug mode render
				gr.setColor(Color.WHITE);
				gr.drawRect(rect.x, rect.y, rect.width, rect.height);
				gr.drawString(keyText, rect.x + 2, rect.y + 16);
			} else if (!keyText.equals("null")) {
				// standard render
				// System.out.println("redered img: "+keyText);
				gr.drawImage(res.getImage(keyText), rect.x, rect.y, rect.width, rect.height, null);
			}

		}
	}

	/**
	 * @param resDebuggRender
	 *            the resDebuggRender to set
	 */
	public void toggelResDebuggRender() {
		resDebuggRender = !resDebuggRender;
	}

	/**
	 * @param hitboxDebugRender
	 *            the hitboxDebugRender to set
	 */
	public void toggelHitboxDebugRender() {
		hitboxDebugRender = !hitboxDebugRender;
	}

}
