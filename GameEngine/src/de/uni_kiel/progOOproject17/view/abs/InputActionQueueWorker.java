package de.uni_kiel.progOOproject17.view.abs;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.uni_kiel.progOOproject17.model.screen.Actionable;
import de.uni_kiel.progOOproject17.model.screen.InputActionKey;

public class InputActionQueueWorker implements Actionable{

	private Thread workerThread;
	private boolean running = false;
	
	private LinkedBlockingQueue<InputActionKey> inputs = new LinkedBlockingQueue<>(5);
	
	//the actionable to work on
	private Actionable actionable;	
	
	private Runnable worker = new Runnable() {
		
		@Override
		public void run() {
			while (running) {
				try {
					actionable.getAction(inputs.take()).actionPerformed(null);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
	};
	
	public InputActionQueueWorker(Actionable a) {
		forwardAllActionsFrom(a);
	}
	

	public void start() {
		if (workerThread == null) {
			workerThread = new Thread(worker);
			running = true;
			workerThread.start();
		}
	}
	
	public void stop() {
		running = false;
		workerThread = null;
	}
	

	@Override
	public Action getAction(InputActionKey iAkey) {
		//return an Action which adds the key to the queue
		return new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!inputs.offer(iAkey))
					System.err.println("Too many inputs!");
			}
		};
	}

	@Override
	public void putAction(InputActionKey iAkey, Action action) {
		// do nothing, cuz can only work on other already build actionables
		
	}

	@Override
	public void forwardAllActionsFrom(Actionable a) {
		Objects.requireNonNull(a);
		actionable = a;
		
	}
	
	
	
}
