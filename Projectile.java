public class Projectile {
    private final Tile tile;
    private final String direction;
    private int life;

    public Projectile(int x, int y, String direct) {
        tile = new Tile("⬜");
        direction = direct;
        life = 10;

        Coordinate coordinates = tile.getCoords();
        coordinates.setCoordinates(x, y);
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
