public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int initX, int initY) {
        x = initX;
        y = initY;
    }
    public Coordinate() {
        x = 0;
        y = 0;
    }

    public void setCoordinates(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
