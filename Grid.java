import java.util.ArrayList;

public class Grid {
    private final String backgroundCharacter;
    private final int top;
    private final int bottom;
    private final int left;
    private final int right;
    private int centerX = 0;
    private int centerY = 0;

    public Grid() {
        backgroundCharacter = "⬛";
        top = 10;
        bottom = 10;
        left = 10;
        right = 10;
    }

    private String getTopTileCharacter(ArrayList<Tile> tiles, int posX, int posY) {
        String currentChar = backgroundCharacter;
        int currentLayer = -1;
        for (Tile tile: tiles) {
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

    public String getGridArt(ArrayList<Tile> tiles) {
        String art = "";
        for (int y = centerY - top; y < centerY + bottom; y++) {
            for (int x = centerX - left; x < centerX + right; x++) {
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
