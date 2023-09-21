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
                System.out.println("step up");
                y--;
                arrowCoord.setCoordinates(x, y - 1);
                break;
            case "left" :
                System.out.println("step left");
                x--;
                arrowCoord.setCoordinates(x - 1, y);
                break;
            case "down":
                System.out.println("step down");
                y++;
                arrowCoord.setCoordinates(x, y + 1);
                break;
            case "right":
                System.out.println("step right");
                x++;
                arrowCoord.setCoordinates(x - 1, y);
        }
        coord.setCoordinates(x, y);
        grid.setCenter(x, y);
        render();
    }

    void processInput(String input) {
        switch (input) {
            case "w":
                System.out.println("move up");
                player.setDirection("up");
                arrowTile.setCharacter("🔼");
                break;
            case "a":
                System.out.println("move a");
                player.setDirection("left");
                arrowTile.setCharacter("◀ ");
                break;
            case "s":
                System.out.println("move s");
                player.setDirection("down");
                arrowTile.setCharacter("🔽");
                break;
            case "d":
                System.out.println("move right");
                player.setDirection("right");
                arrowTile.setCharacter("▶ ");
                break;
            case "1":
                System.out.println("attack");
                // shoot
        }
    }

    void doLoop() {
        System.out.println("change direction (wasd) or shoot (1): ");
        String input = s.nextLine();
        processInput(input);
        step();
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
        render();
        while (active) {
            doLoop();
        }
        onLose();
    }
}
