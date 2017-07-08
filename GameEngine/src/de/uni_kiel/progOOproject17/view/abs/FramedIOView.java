package de.uni_kiel.progOOproject17.view.abs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Set;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.uni_kiel.progOOproject17.view.abs.Viewable.ViewContentKey;

/**
 * This class provides a {@link JFrame} which holds an {@link InputView} in as
 * well as a {@link MappedKeyInput} and a {@link OutputView}. This class builds
 * a basic Frame which can easily be modified by the subclass.
 *
 *
 *
 */
public abstract class FramedIOView extends JFrame implements InputView, OutputView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2996189965279903424L;

	private MappedKeyInput in;

	/**
	 * The {@link JPanel} being the contentPane of this frame.
	 */
	protected JPanel contentPane;

	private HashMap<String, JButton> buttons = new HashMap<>();

	private boolean texDebuggRender = false;

	private final TextureManager textureManager;

	/**
	 * Constructs a new {@link FramedIOView}.
	 * 
	 * @param title
	 *            the title
	 * @param w
	 *            the width of the centerPane
	 * @param h
	 *            the height of the centerPane
	 * @param resizeable
	 *            whether the frame should be resizeable
	 */
	public FramedIOView(String title, int w, int h, boolean resizeable, TextureManager texMen) {
		super(title);

		this.textureManager = (texMen != null) ? texMen : new TextureManager() {
			// dummy null manager
			@Override
			public Image getImage(String name) {
				return null;
			}
		};

		in = new MappedKeyInput();
		contentPane = new JPanel(true);
		contentPane.setPreferredSize(new Dimension(w, h));
		in.initMaps(contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW), contentPane.getActionMap());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setContentPane(contentPane);

		setResizable(resizeable);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Adds a {@link JButton} b to the {@link JComponent} comp with the given
	 * constraints. And makes this button available for the internal {@link Action}
	 * handling.
	 * 
	 * @param b
	 *            the {@link JButton}
	 * @param comp
	 *            the {@link JComponent}
	 * @param constraints
	 *            the constraints object
	 */
	public void addJButton(JButton b, JComponent comp, Object constraints) {
		if (b == null || comp == null)
			return;
		comp.add(b, constraints);
		buttons.put(b.getName(), b);

	}

	/**
	 * Adds the action to the Input view, so that it will be performed when an event
	 * occurs that need to be specified by the String key. For example a keystroke
	 * or the name of a button.
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.InputView#addAction(java.lang.String,
	 *      javax.swing.Action)
	 */
	@Override
	public void addAction(String key, Action action) {

		if (buttons.get(key) != null)
			buttons.get(key).addActionListener(action);
		else
			in.addAction(key, action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.InputView#addActionMap(javax.swing.
	 * ActionMap)
	 */
	@Override
	public void addActionMap(ActionMap actionMap) {
		in.addActionMap(actionMap);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.InputView#setEnabeled(boolean)
	 */
	@Override
	public void setEnabeled(boolean enabeled) {

		Set<String> keys = buttons.keySet();
		for (String key : keys)
			buttons.get(key).setEnabled(false);
		in.setEnabeled(enabeled);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.view.abs.InputView#setEnabeled(java.lang.
	 * String, boolean)
	 */
	@Override
	public void setEnabeled(String key, boolean enabeled) {

		if (buttons.get(key) != null)
			buttons.get(key).setEnabled(enabeled);
		else
			in.setEnabeled(key, enabeled);
	}

	/**
	 * 
	 */
	public void toggelTextureDebuggRender() {
		texDebuggRender = !texDebuggRender;
	}

	@Override
	public abstract void render(Viewable viewable);

	protected void renderTo(Viewable v, Graphics gr) {
		if (v == null)
			return;
		if (!v.isVisble())
			return;

		Rectangle rect = v.getViewRect();
		ViewContentKey key = v.getContentKey();
		String keyText = key != null ? key.getText() : null;

		if (keyText == null)
			keyText = "null";

		// COMPOUND RENDER
		if (keyText.equals(ViewCompound.COMPOUND_KEYTEXT) && key instanceof ViewCompound) {
			ViewCompound comp = (ViewCompound) key;

			if (texDebuggRender) {
				// resource debug mode render
				// show outline:
				gr.setColor(Color.WHITE);
				gr.drawRect(rect.x, rect.y, rect.width, rect.height);
				gr.drawString(keyText, rect.x + 2, rect.y + rect.height - 8);
			}

			// relative render:
			gr.translate(rect.x, rect.y);

			for (Viewable viewable : comp) {
				renderTo(viewable, gr);
			}

			gr.translate(-rect.x, -rect.y);

		} else if (keyText.startsWith(Viewable.STRING_KEYPREFIX)) {
			String text = keyText.replaceFirst(Viewable.STRING_KEYPREFIX, "");
			gr.setColor(Color.BLACK);
			gr.setFont(new Font(null, Font.PLAIN, rect.height));
			gr.drawString(text, rect.x, rect.y + rect.height);

		}
		// DEBUG RENDER
		else if (keyText.startsWith(Viewable.DEBUG_KEYPREFIX)) {
			// TODO debug render hitboxes and more
			// if (hitboxDebugRender) {
			// // hitbox debugging
			// gr.setColor(Color.CYAN);
			// String key2 = keyText.replaceFirst(Viewable.DEBUG_KEYPREFIX, "");
			// if (key2.equals(Hitbox.CIRCLE_KEY)) {
			// gr.drawOval(rect.x, rect.y, rect.width, rect.height);
			// }
			// if (key2.equals(Hitbox.LINE_KEY)) {
			// // gr.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y +
			// // rect.height);
			// gr.drawLine(rect.x, rect.y, rect.width, rect.height);
			// }
			//
			// }
		}
		// TEXTURE RENDER
		else if (keyText.startsWith(Viewable.TEXTURE_KEYPREFIX)) {
			if (texDebuggRender) {
				// resource debug mode render
				gr.setColor(Color.WHITE);
				gr.drawRect(rect.x, rect.y, rect.width, rect.height);
				gr.drawString(keyText, rect.x + 2, rect.y + 16);
			}
			// standard render
			// System.out.println("redered img: "+keyText);
			gr.drawImage(textureManager.getImage(keyText), rect.x, rect.y, rect.width, rect.height, null);

		}
	}

}
