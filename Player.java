public class Player {
    private String direction = null;
    private Tile tile = null;
    private int lowerXBound;
    private int upperXBound;
    private int lowerYBound;
    private int upperYBound;

    public Player() {
        tile = new Tile("🟨");
        direction = "up";
    }

    public void setBounds(int lowerX, int upperX, int lowerY, int upperY) {
        lowerXBound = lowerX; // lower bound is weird
        upperXBound = upperX;
        lowerYBound = lowerY;
        upperYBound = upperY;
    }

    public void step() {
        Coordinate coordinates = tile.getCoordinates();
        int x = coordinates.getX();
        int y = coordinates.getY();
        switch (direction) {
            case "up" -> y--;
            case "left" -> x--;
            case "down" -> y++;
            case "right" -> x++;
        }
        if (x > upperYBound) {
            x = lowerXBound;
        } else if (lowerXBound > x) {
            x = upperXBound;
        }
        if (y > upperYBound) {
            y = lowerYBound;
        } else if (lowerYBound > y) {
            y = upperYBound;
        }
        coordinates.setCoordinates(x, y);
    }

    public void setDirection(String dir) {
        direction = dir;
    }
    public String getDirection() {
        return direction;
    }

    public Tile getTile() {
        return tile;
    }
}