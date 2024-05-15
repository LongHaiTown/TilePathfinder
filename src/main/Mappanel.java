package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

public class Mappanel extends JPanel implements Runnable {

	
	final int Originaltilesize = 16;
	final int scale = 3;
	
	public int tilesize = Originaltilesize*scale;

	public final int ScreeW = 1280;
	public final int ScreenH = 720;
	public final int maxScreenCol =  ScreeW/tilesize;
	public final int maxScreenRow = ScreenH/tilesize;

	public final int maxWorldCol =200;
	public final int maxWorldRow = 200;
	public final int worldWidth = tilesize *maxWorldCol;
	public final int worldHeight = tilesize* maxWorldRow;
	int FPS = 60;
	
	public TileManager TileM = new TileManager(this);
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker cChecker = new CollisionChecker((this));

	public Player camera = new Camera(this,keyH);
	public Player player = new Player(this,keyH);
	public Setter setter = new Setter(this);

	public Mappanel() {
		this.setPreferredSize(new Dimension(ScreeW,ScreenH));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void setupGame(){
		setter.setPosition();
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
	public void CenteringCamera(){
		double oldCameraXLocation = camera.x;
		camera.x= tilesize*player.getTileXLocationNum();
		double newCameraXLocation = camera.x;

		double oldCameraYLocation = camera.y;
		camera.y= tilesize*player.getTileYLocationNum();
		double newCameraYLocation = camera.y;

		double cameraXLocationChange = oldCameraXLocation - newCameraXLocation;
		double cameraYLocationChange = oldCameraYLocation - newCameraYLocation;

		if(cameraXLocationChange > 0) {
			player.x += Math.abs(oldCameraXLocation - newCameraXLocation);
		}else {
			player.x -= Math.abs(oldCameraXLocation - newCameraXLocation);
		}

		if(cameraYLocationChange > 0) {
			player.y += Math.abs(oldCameraYLocation - newCameraYLocation);
		}else {
			player.y -= Math.abs(oldCameraYLocation - newCameraYLocation);
		}
	}
	public void ZoomInOut(int i){
		CenteringCamera();
		int oldWorldWidth = tilesize *maxWorldCol;
		this.tilesize += i;
		int newWorldWidth = tilesize *maxWorldCol;

		double multiplier = (double) newWorldWidth /oldWorldWidth;

		double newPlayerWorldX = camera.x *multiplier;
		double newPlayerWorldY = camera.y *multiplier;

		player.speed =  newWorldWidth/(newWorldWidth/tilesize);
		camera.x = newPlayerWorldX ;
		camera.y = newPlayerWorldY ;

	}
	public void update() {
		camera.update();
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;

		TileM.draw(g2);
		player.draw(g2);
	}
	

}
