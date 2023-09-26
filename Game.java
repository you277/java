import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Player player;
    private Tile arrowTile;
    private ArrayList enemies;
    private Grid grid;
    private boolean active = true;
    private boolean started = false;
    private Scanner s;

    void render() {
        ArrayList tiles = new ArrayList<Tile>();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = (Enemy)enemies.get(i);
            tiles.add(enemy.getTile());
        }
        tiles.add(player.getTile());
        tiles.add(arrowTile);
        System.out.println(grid.getGridArt(tiles));
    }

    void step() {
        String direction = player.getDirection();
        Coordinate coord = player.getTile().getCoords();
        Coordinate arrowCoord = arrowTile.getCoords();
        int x = coord.getX();
        int y = coord.getY();

        switch (direction) {
            case "up":
                y--;
                arrowCoord.setCoordinates(x, y - 1);
                break;
            case "left" :
                x--;
                arrowCoord.setCoordinates(x - 1, y);
                break;
            case "down":
                y++;
                arrowCoord.setCoordinates(x, y + 1);
                break;
            case "right":
                x++;
                arrowCoord.setCoordinates(x + 1, y);
        }
        coord.setCoordinates(x, y);
        //grid.setCenter(x, y);
    }

    void processInput(String input) {
        switch (input) {
            case "w":
                player.setDirection("up");
                arrowTile.setCharacter("⏫");
                break;
            case "a":
                player.setDirection("left");
                arrowTile.setCharacter("⏪");
                break;
            case "s":
                player.setDirection("down");
                arrowTile.setCharacter("⏬");
                break;
            case "d":
                player.setDirection("right");
                arrowTile.setCharacter("⏩");
                break;
            case "1":
                System.out.println("attack");
                // shoot
        }
    }

    void doIntro() {
        System.out.println("any input to begin");
        s.nextLine();
    }

    void onLose() {
        System.out.println("womp womp");
    }

    public void start() {
        if (started) { return; }
        started = true;

        player = new Player();
        arrowTile = new Tile("🔼");
        grid = new Grid();
        enemies = new ArrayList<Enemy>();
        s = new Scanner(System.in);

        doIntro();
        while (active) {
            render();
            System.out.println("change direction (wasd) or shoot (1): ");
            processInput(s.nextLine());
            step();
        }
        onLose();
    }
}
