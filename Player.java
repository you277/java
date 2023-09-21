public class Player {
    private int ammo = 0;
    private String direction = "up";
    private Tile tile = new Tile("🟨");

    public void step() {
        // do a turn
    }

    public void setDirection(String dir) {
        direction = dir;
    }
    public String getDirection() {
        return direction;
    }

    public int getAmmo() {
        return ammo;
    }

    public void addAmmo(int amt) {
        ammo += amt;
    }

    public Tile getTile() {
        return tile;
    }
}