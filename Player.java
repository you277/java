public class Player {
    private int ammo = 0;
    private String direction = "up";

    public void step() {
        // do a turn
    }

    public void setDirection(String dir) {
        direction = dir;
    }

    public int getAmmo() {
        return ammo;
    }

    public void addAmmo(int amt) {
        ammo += amt;
    }
}