import java.util.ArrayList;

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
        lowerXBound = lowerX + 1; // lower bound is weird
        upperXBound = upperX;
        lowerYBound = lowerY + 1;
        upperYBound = upperY;
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
        if (x >= upperYBound) {
            x = lowerXBound;
        } else if (lowerXBound >= x) {
            x = upperXBound;
        }
        if (y >= upperYBound) {
            y = lowerYBound;
        } else if (lowerYBound >= y) {
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