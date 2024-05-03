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
        mapTileNum = new int[map.maxScreenCol][map.maxScreenCol];

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
            tile[2].Collision = true;

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sprite_3.png"));
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
                    if (col >= map.maxScreenCol) break; // Exit the inner loop if reached maximum column
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                } while (col < numbers.length); // Continue until end of line
                row++;
            } while (row < map.maxScreenRow); // Continue until end of rows

            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void draw (Graphics2D g2) {
//        g2.drawImage(tile[0].image,0,0,map.tilesize,map.tilesize,null);
        int col = 0;
        int row = 0;
        int x =0;
        int y = 0;
        while (col < map.maxScreenCol && row < map.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, map.tilesize, map.tilesize, null);
            col++;
            x += map.tilesize;

            if (col == map.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += map.tilesize;
            }
        }
    }
}
