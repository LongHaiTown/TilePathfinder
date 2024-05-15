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
    public Tiles[] tile;
    public int[][] mapTileNum;
    public TileManager(Mappanel map){
        this.map = map;

        tile = new Tiles[15];
        mapTileNum = new int[map.maxWorldCol][map.maxWorldRow];

        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_0.png"));
            tile[0].Collision = true;

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_1.png"));
            tile[1].Collision = false;

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_2.png"));
            tile[2].Collision = true;

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_4.png"));
            tile[3].Collision = true;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/tiles/matrix.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            do {
                String line = reader.readLine();
                if (line == null) break;
                String[] numbers = line.split(" ");
                int col = 0;
                do {
                    if (col >= map.maxWorldCol) break; // Exit the inner loop if reached maximum column
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                } while (col < numbers.length); // Continue until end of line
                row++;
            } while (row < map.maxWorldRow); // Continue until end of rows

            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;

        while (col < map.maxWorldCol && row < map.maxWorldRow) {
            int tileNum = mapTileNum[col][row];

            int worldX = col * map.tilesize;
            int worldY = row * map.tilesize;
            double screenX = worldX - map.camera.x + map.camera.screenX;
            double screenY = worldY - map.camera.y + map.camera.screenY;
            if (worldX + map.tilesize *2  > map.camera.x - map.camera.screenX &&
                    worldX  < map.camera.x + map.camera.screenX &&
                    worldY + map.tilesize *2 > map.camera.y - map.camera.screenY &&
                    worldY  < map.camera.y + map.camera.screenY  ) {

                g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, map.tilesize, map.tilesize, null);
            }
            col++;

            if (col == map.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
}
