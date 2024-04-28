package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import java.io.File;
import main.KeyHandler;
import main.Mappanel;

public class Player extends Entity {
	Mappanel mp;
	KeyHandler keyH;
	
	public Player(Mappanel mp, KeyHandler keyH) {
		super();
		this.mp = mp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x= 100;
		y=100;
		speed = mp.tilesize;
		direction = "left";
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
		if(keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		else if (keyH.downPressed == true) {
			direction = "down";
			y += speed;
		}
		else if (keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		else if (keyH.rightPressed == true) {
			direction = "right";
			x += speed;
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
//		g2.drawImage(image, x, y, x, y, 50, 50, 50, 50, null);
		g2.drawImage(image,x,y,mp.tilesize,mp.tilesize,null);
	}
}
