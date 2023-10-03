public class Tile {
    private final Coordinate coordinate;
    private int layer = 0;
    private String character;

    public Tile(String tileChar) {
        character = tileChar;
        coordinate = new Coordinate();
    }

    public Coordinate getCoords() {
        return coordinate;
    }

    public void setLayer(int newLayer) {
        layer = newLayer;
    }

    public int getLayer() {
        return layer;
    }

    public void setCharacter(String tileChar) {
        character = tileChar;
    }

//    public String getCharacter() {
//        return character;
//    }
    public String toString() {
        return character;
    }
}
