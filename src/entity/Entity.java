package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
	public double x;
    public double y;
	public double speed;
	public int speed1;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction;
	public Rectangle solidArea;
	public boolean collisionOn = false;
}
