package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean upPressed1, downPressed1, leftPressed1, rightPressed1;
	public boolean sizeIncrease, sizeDecrease;
	Mappanel mp;

	public KeyHandler(Mappanel mp){
		this.mp = mp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_W) {
			upPressed1 = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed1 = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed1 = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed1 = true;
		}
		if (code == KeyEvent.VK_J){
			mp.ZoomInOut(2);
		}
		if (code == KeyEvent.VK_K){
			mp.ZoomInOut(-2);
		}
		if (code == KeyEvent.VK_SPACE){
			mp.CenteringCamera();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_W) {
			upPressed1 = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed1 = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed1 = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed1 = false;
		}
	}
	

}
