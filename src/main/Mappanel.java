package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

public class Mappanel extends JPanel implements Runnable {

	
	final int Originaltilesize = 25;
	final int scale = 3;
	
	public int tilesize = Originaltilesize*scale;

	public final int ScreeW = 1600;
	public final int ScreenH = 900;
	public final int maxScreenCol =  ScreeW/tilesize;
	public final int maxScreenRow = ScreenH/tilesize;

	public final int maxWorldCol =100;
	public final int maxWorldRow = 100;

	int FPS = 60;
	
	public TileManager TileM = new TileManager(this);
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	public CollisionChecker cChecker = new CollisionChecker((this));

	public Player camera = new Camera(this,keyH);

	public Mappanel() {
		this.setPreferredSize(new Dimension(ScreeW,ScreenH));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
/*
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() *drawInterval;
		
		while (gameThread!= null) {
			
			update();
			
			repaint();
			

			try {
				double remainingTime = nextDrawTime -System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime =0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread!= null) {
			currentTime = System.nanoTime();
			delta += (currentTime- lastTime)/ drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();	
				repaint();
				delta--;
			}
			 

		}
	}
	public void update() {
		camera.update();
		if (keyH.sizeIncrease){
			this.tilesize ++;
			repaint();
			keyH.sizeIncrease = false;
		}
		if (keyH.sizeDecrease){
			this.tilesize --;
			repaint();
			keyH.sizeDecrease = false;
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;

		TileM.draw(g2);

	}
	

}
