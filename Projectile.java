public class Projectile {
    private Tile tile = null;
    private String direction = null;
    private int life;
    private int lowerXBound;
    private int upperXBound;
    private int lowerYBound;
    private int upperYBound;

    public Projectile(int x, int y, String direct) {
        tile = new Tile("⬜");
        direction = direct;
        life = 10;

        Coordinate coordinates = tile.getCoords();
        coordinates.setCoordinates(x, y);
    }

    public void setBounds(int lowerX, int upperX, int lowerY, int upperY) {
        lowerXBound = lowerX;
        upperXBound = upperX;
        lowerYBound = lowerY;
        upperYBound = upperY;
    }

    public void step() {
        Coordinate coordinates = tile.getCoords();
        int x = coordinates.getX();
        int y = coordinates.getY();
        switch (direction) {
            case "up": {
                y -= 2;
                break;
            }
            case "left": {
                x -= 2;
                break;
            }
            case "down": {
                y += 2;
                break;
            }
            case "right": {
                x += 2;
            }
        }
        if (x >= upperXBound) {
            x = lowerXBound + (x - upperXBound);
        } else if (lowerXBound >= x) {
            x = upperXBound + (x - lowerXBound);
        }
        if (y >= upperYBound) {
            y = lowerYBound + (y - upperYBound);
        } else if (lowerYBound >= y) {
            y = upperYBound + (y - lowerYBound);
        }
        coordinates.setCoordinates(x, y);
        life--;
    }

    public int getLife() {
        return life;
    }

    public Tile getTile() {
        return tile;
    }
}
