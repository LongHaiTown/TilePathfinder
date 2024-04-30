package tile;

import main.Mappanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    Mappanel map;
    Tiles[] tile;
    int mapTileNum[][];
    public TileManager(Mappanel map){
        this.map = map;

        tile = new Tiles[15];
        mapTileNum = new int[map.maxWorldCols][map.maxWorldRows];

        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_0.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_1.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_2.png"));

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_3.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/tiles/matrix.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
/*
            int row = 0;
            do {
                String line = reader.readLine();
                if (line == null) break;
                String[] numbers = line.split(" ");
                int col = 0;
                do {
//                    if (col >= map.maxWorldCols) break; // Exit the inner loop if reached maximum column
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                } while (col < map.maxWorldCols); // Continue until end of line
                row++;
            } while (row < map.maxWorldRows); // Continue until end of rows

            reader.close();

 */
            int col = 0;
            int row = 0;
            while (col<map.maxWorldCols && row<map.maxWorldRows){
                String line = reader.readLine();
                while (col< map.maxWorldCols){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == map.maxWorldCols){
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void draw (Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;
        while (worldcol < map.maxWorldCols && worldrow < map.maxWorldRows) {
            int tileNum = mapTileNum[worldcol][worldrow];

            int worldX = worldcol*map.tilesize;
            int worldY = worldrow*map.tilesize;
            int screenX= worldX - map.player.worldX +map.player.screenX;
            int screenY= worldY - map.player.worldY +map.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, map.tilesize, map.tilesize, null);
            worldcol++;

            if (worldcol == map.maxWorldCols) {
                worldcol = 0;
                worldrow++;
            }
        }
    }
}
