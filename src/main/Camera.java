package main;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera extends Player {
    public Player player1;
    public Camera(Mappanel mp, KeyHandler keyH) {
        super(mp, keyH);

    }
    @Override
    public void setDefaultValues(){
        x= mp.tilesize*20 ;
        y= mp.tilesize*12 ;
        speed = mp.tilesize;
        speed1= 10;
    }
    @Override
    public void update() {
        if (keyH.upPressed1) {
            direction = "up";
            y -= speed1;
            mp.player.y +=speed1;
        } else if (keyH.downPressed1) {
            direction = "down";
            y += speed1;
            mp.player.y -=speed1;
        } else if (keyH.leftPressed1) {
            direction = "left";
            x -= speed1;
            mp.player.x +=speed1;
        } else if (keyH.rightPressed1) {
            direction = "right";
            x += speed1;
            mp.player.x -=speed1;
        }
    }
}

