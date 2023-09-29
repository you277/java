import java.util.ArrayList;

public class Player {
    //private int ammo;
    private String direction;
    private final Tile tile;
    //private boolean alive;

    public Player() {
        tile = new Tile("🟨");
        //ammo = 0;
        direction = "up";
        //alive = true;
    }

    public void step() {
        Coordinate coordinates = tile.getCoords();
        int x = coordinates.getX();
        int y = coordinates.getY();
        switch (direction) {
            case "up": {
                y--;
                break;
            }
            case "left" : {
                x--;
                break;
            }
            case "down": {
                y++;
                break;
            }
            case "right": {
                x++;
            }
        }
        coordinates.setCoordinates(x, y);
    }

    public void setDirection(String dir) {
        direction = dir;
    }
    public String getDirection() {
        return direction;
    }

//    public int getAmmo() {
//        return ammo;
//    }
//
//    public void addAmmo(int amt) {
//        ammo += amt;
//    }
//
//    public boolean getAlive() {
//        return alive;
//    }

    public Tile getTile() {
        return tile;
    }
}