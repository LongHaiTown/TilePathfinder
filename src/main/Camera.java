package main;

import entity.Player;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera extends Player {
    public Player player1;
    public Camera(Mappanel mp, KeyHandler keyH) {
        super(mp, keyH);

    }
    @Override
    public void setDefaultValues(){
        x= mp.tilesize*12 ;
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
    public void draw (Graphics2D g2){
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
        g2.drawImage(image,screenX,screenY,mp.tilesize,mp.tilesize,null);
    }

}

