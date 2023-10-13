import java.util.ArrayList;

public class Grid {
    private Tile backgroundTile = null;
    private final int top;
    private final int bottom;
    private final int left;
    private final int right;
    private int centerX = 0;
    private int centerY = 0;
    private static boolean respectSetCenter;

    static void setRespectCenter(boolean yeah) {
        respectSetCenter = yeah;
    }

    public Grid() {
        backgroundTile = new Tile("⬛");
        backgroundTile.setLayer(-1);
        top = 10;
        bottom = 10;
        left = 10;
        right = 10;
    }

    private Tile getTopTileCharacter(ArrayList<Tile> tiles, int posX, int posY) {
        Tile currentTile = backgroundTile;
        for (Tile tile: tiles) {
            Coordinate tileCoordinates = tile.getCoordinates();
            int x = tileCoordinates.getX();
            int y = tileCoordinates.getY();
            if (posX != x || posY != y) {
                continue;
            }
            int layer = tile.getLayer();
            if (layer > backgroundTile.getLayer()) {
                currentTile = tile;
            }
        }
        return currentTile;
    }

    public String getGridArt(ArrayList<Tile> tiles) {
        String art = "";
        int trueCenterX = 0;
        int trueCenterY = 0;
        if (respectSetCenter) {
            trueCenterX = centerX;
            trueCenterY = centerY;
        }
        for (int y = trueCenterY - top; y < trueCenterY + bottom + 1; y++) {
            for (int x = trueCenterX - left; x < trueCenterX + right + 1; x++) {
                Tile tile = getTopTileCharacter(tiles, x, y);
                art += tile;
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
