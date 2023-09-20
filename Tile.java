public class Tile {
    private Coordinate coordinate = new Coordinate();
    private int layer;
    private String character;

    public Tile(String tileChar) {
        character = tileChar;
    }

    public Coordinate getCoords() {
        return coordinate;
    }
}
