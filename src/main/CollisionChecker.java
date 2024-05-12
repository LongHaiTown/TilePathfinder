package main;

import entity.Entity;

public class CollisionChecker {
    Mappanel mp;

    public CollisionChecker(Mappanel mp){
        this.mp = mp;
    }

    public void checkTile(Entity en){
        int entityLeftX = (int)en.x + en.solidArea.x;
        int entityRightX = (int)en.x + en.solidArea.y + en.solidArea.width;
        int entityTopY = (int)en.y + en.solidArea.y;
        int entityBotY = (int)en.y + en.solidArea.y + en.solidArea.height;

        int entityLeftCol = entityLeftX/mp.tilesize;
        int entityRightCol = entityRightX /mp.tilesize;
        int entityTopRow = entityTopY/mp.tilesize;
        int entityBotRow = entityBotY/mp.tilesize;

        int tileNum1, tileNum2;

        switch (en.direction){
            case "up":
                entityTopRow = (entityTopY - (int)en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.TileM.mapTileNum[entityRightCol][entityTopRow];
                if (mp.TileM.tile[tileNum1].Collision || mp.TileM.tile[tileNum2].Collision){
                    en.collisionOn = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotY + (int)en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = mp.TileM.mapTileNum[entityRightCol][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision || mp.TileM.tile[tileNum2].Collision){
                    en.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - (int)en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.TileM.mapTileNum[entityLeftCol][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision || mp.TileM.tile[tileNum2].Collision){
                    en.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + (int)en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = mp.TileM.mapTileNum[entityRightCol][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision || mp.TileM.tile[tileNum2].Collision){
                    en.collisionOn = true;
                }
                break;
            default:
        }
    }
}
