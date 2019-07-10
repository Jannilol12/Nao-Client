package nao.events;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonLongPress {
	private final Button button;
	private volatile boolean pressed;
	private Runnable runnable;
	private Thread thread;
	private int sleep = 1000;
	
	public ButtonLongPress(Button button) {
		this.button = button;
		init();
	}
	
	public ButtonLongPress(Button button, Runnable runnable) {
		this.button = button;
		this.runnable = runnable;
		init();
	}
	
	public ButtonLongPress(Button button, Runnable runnable, int sleep) {
		this.button = button;
		this.runnable = runnable;
		this.sleep = sleep;
		init();
	}
	
	private void init() {
		button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
			pressed = true;
			synchronized (thread) {
				thread.notify();
			}
		});
		
		button.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			pressed = false;
		});
		
		thread = new Thread() {
			@Override
			public void run() {
				while(!this.isInterrupted()) {
					while(pressed && !this.isInterrupted()) {
						runnable.run();
						try {
							sleep(sleep);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					if(!this.isInterrupted()) {
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
		
		thread.start();
	}
	
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
	
	public void destroy() {
		thread.interrupt();
	}
}
