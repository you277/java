import java.util.ArrayList;

public class Grid {
    private String backgoundCharacter = "⬛";
    private int top = 10;
    private int bottom = 10;
    private int left = 10;
    private int right = 10;
    private int centerX = 0;
    private int centerY = 0;

    private String getTopTileCharacter(ArrayList tiles, int posX, int posY) {
        String currentChar = backgoundCharacter;
        int currentLayer = -1;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = (Tile)tiles.get(i);
            Coordinate coords = tile.getCoords();
            int x = coords.getX();
            int y = coords.getY();
            if (posX != x || posY != y) {
                continue;
            }
            int layer = tile.getLayer();
            if (layer > currentLayer) {
                currentLayer = layer;
                currentChar = tile.getCharacter();
            }
        }
        return currentChar;
    }

    public String getGridArt(ArrayList tiles) {
        String art = "";
        for (int x = centerX - left; x < centerX + right; x++) {
            for (int y = centerX - left; y < centerX + right; y++) {
                String tileChar = getTopTileCharacter(tiles, x, y);
                art += tileChar;
            }
            art += "\n";
        }
        return art;
    }

    public void setCenter(int x, int y) {
        centerX = x;
        centerY = y;
    }
}
