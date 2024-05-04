package main;

import entity.Entity;

public class CollisionChecker {
    Mappanel mp;

    public CollisionChecker(Mappanel mp){
        this.mp = mp;
    }

    public void checkTile(Entity en){
        int entityLeftX = en.x + en.solidArea.x;
        int entityRightX = en.x + en.solidArea.y + en.solidArea.width;
        int entityTopY = en.y + en.solidArea.y;
        int entityBotY = en.y + en.solidArea.y + en.solidArea.height;

        int entityLeftCol = entityLeftX/mp.tilesize;
        int entityRightCol = entityRightX /mp.tilesize;
        int entityTopRow = entityTopY/mp.tilesize;
        int entityBotRow = entityBotY/mp.tilesize;

        int tileNum1, tileNum2;

        switch (en.direction){
            case "up":
                entityTopRow = (entityTopY - en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.TileM.mapTileNum[entityRightCol][entityTopRow];
                if (mp.TileM.tile[tileNum1].Collision == true || mp.TileM.tile[tileNum2].Collision == true){
                    en.collisionOn = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotY + en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = mp.TileM.mapTileNum[entityRightCol][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision == true || mp.TileM.tile[tileNum2].Collision == true){
                    en.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.TileM.mapTileNum[entityLeftCol][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision == true || mp.TileM.tile[tileNum2].Collision == true){
                    en.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + en.speed)/ mp.tilesize;
                tileNum1 = mp.TileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = mp.TileM.mapTileNum[entityRightCol][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision == true || mp.TileM.tile[tileNum2].Collision == true){
                    en.collisionOn = true;
                }
                break;
            default:
        }
    }
}
