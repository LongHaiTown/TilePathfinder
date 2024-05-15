package main;

import entity.Entity;

public class CollisionChecker {
    Mappanel mp;

    public CollisionChecker(Mappanel mp){
        this.mp = mp;
    }

    public void checkTile(Entity en){

        int entityLeftCol = mp.player.getTileXLocationNum()-1;
        int entityRightCol = mp.player.getTileXLocationNum()+1;
        int entityTopRow = mp.player.getTileYLocationNum()-1;
        int entityBotRow = mp.player.getTileYLocationNum()+1;
        int tileNum1;

        switch (en.direction){
            case "up":
                tileNum1 = mp.TileM.mapTileNum[mp.player.getTileXLocationNum()][entityTopRow];
                if (mp.TileM.tile[tileNum1].Collision  ){
                    en.collisionOn = true;
                }
                break;
            case "down":
                tileNum1 = mp.TileM.mapTileNum[mp.player.getTileXLocationNum()][entityBotRow];
                if (mp.TileM.tile[tileNum1].Collision){
                    en.collisionOn = true;
                }
                break;
            case "left":
                tileNum1 = mp.TileM.mapTileNum[entityLeftCol][mp.player.getTileYLocationNum()];
                if (mp.TileM.tile[tileNum1].Collision){
                    en.collisionOn = true;
                }
                break;
            case "right":
                tileNum1 = mp.TileM.mapTileNum[entityRightCol][mp.player.getTileYLocationNum()];
                if (mp.TileM.tile[tileNum1].Collision){
                    en.collisionOn = true;
                }
                break;
            default:
        }
    }
}
