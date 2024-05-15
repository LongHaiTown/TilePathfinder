package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import java.io.File;
import main.KeyHandler;
import main.Mappanel;

public class Player extends Entity {
	protected Mappanel mp;
	protected KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	public int TileXLocationNum=5;
	public int TileYLocationNum=5;
	public Player(Mappanel mp, KeyHandler keyH) {
		super();
		this.mp = mp;
		this.keyH = keyH;

		screenX =mp.ScreeW/2;
		screenY = mp.ScreenH/2;

		solidArea =new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.height =(mp.tilesize);
		solidArea.width = (mp.tilesize);

		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x= mp.tilesize*TileXLocationNum - mp.camera.x + mp.camera.screenX;
		y= mp.tilesize*TileYLocationNum - mp.camera.y + mp.camera.screenY;
//		speed = mp.tilesize;
		speed = mp.worldWidth/(mp.worldWidth/mp.tilesize);
		speed1= 10;
		direction = "left";
	}

	public int getTileXLocationNum() {
		return (int) ((x+ mp.camera.x - mp.camera.screenX)/mp.tilesize);
	}
	public int getTileYLocationNum() {
		return (int) ((y+ mp.camera.y - mp.camera.screenY)/mp.tilesize);
	}
	public void getPlayerImage() {
		try {
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_0.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_1.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_2.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_3.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_4.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_5.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_6.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_7.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed) {
				direction = "up";
//				y -= speed;
				keyH.upPressed = false;
			} else if (keyH.downPressed) {
				direction = "down";
//				y += speed;
				keyH.downPressed = false;
			} else if (keyH.leftPressed) {
				direction = "left";
//				x -= speed;
				keyH.leftPressed = false;
			} else if (keyH.rightPressed) {
				direction = "right";
//				x += speed;
				keyH.rightPressed = false;
			}
//			else if (keyH.upPressed1 == true) {
//				direction = "up";
//			} else if (keyH.downPressed1 == true) {
//				direction = "down";
//			} else if (keyH.leftPressed1 == true) {
//				direction = "left";
//			} else if (keyH.rightPressed1 == true) {
//				direction = "right";
//			}

			collisionOn = false;
			mp.cChecker.checkTile(this);

					if (!collisionOn){
						switch (direction){
							case "up":
								y -= speed;
								break;
							case "down":
								y += speed;
								break;
							case "left":
								x -= speed;
								break;
							case "right":
								direction = "right";
								x += speed;
								break;

						}
						if (keyH.isCentered){
							mp.CenteringCamera();
						}
					}
			System.out.println("Player position is [" + this.getTileXLocationNum() + "][" + this.getTileYLocationNum() + "] ");
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;

            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
		g2.drawImage(image,(int)x,(int)y,mp.tilesize,mp.tilesize,null);
	}
}
