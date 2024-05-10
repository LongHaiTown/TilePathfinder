package main;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera extends Player {
    public Player player1;
    public Camera(Mappanel mp, KeyHandler keyH) {
        super(mp, keyH);
        player1 = mp.player;
    }
    @Override
    public void setDefaultValues(){
        x= mp.tilesize*5;
        y= mp.tilesize*5;
        speed = mp.tilesize;
        speed1= 10;
    }
    @Override
    public void update() {
        if (keyH.upPressed1 == true) {
            direction = "up";
            y -= speed1;
            player1.y +=speed1;

        } else if (keyH.downPressed1 == true) {
            direction = "down";
            y += speed1;
            player1.y -= speed1;
        } else if (keyH.leftPressed1 == true) {
            direction = "left";
            x -= speed1;
            player1.x +=speed1;
        } else if (keyH.rightPressed1 == true) {
            direction = "right";
            x += speed1;
            player1.x -=speed1;
        }
    }
}

