import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Player player;
    private ArrayList enemies;
    private ArrayList projectiles;
    private Grid grid;
    private boolean active;
    private Scanner s;

    void render() {
        ArrayList tiles = new ArrayList<Tile>();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = (Enemy)enemies.get(i);
            tiles.add(enemy.getTile());
        }
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = (Projectile)projectiles.get(i);
            tiles.add(projectile.getTile());
        }
        tiles.add(player.getTile());
        System.out.println(grid.getGridArt(tiles));
    }

    void stepEnemies() {
        // nothing yet
    }
    void stepProjectiles() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = (Projectile)projectiles.get(i);
            if (projectile.getLife() == 0) {
                projectiles.remove(projectile);
                continue;
            }
            projectile.step();
        }
    }

    void step() {
        stepEnemies();
        stepProjectiles();
        player.step();
    }

    void pointArrow() {
        String direction = player.getDirection();
        Tile playerTile = player.getTile();

        switch (direction) {
            case "up": {
                playerTile.setCharacter("⏫");
                break;
            }
            case "left" : {
                playerTile.setCharacter("⏪");
                break;
            }
            case "down": {
                playerTile.setCharacter("⏬");
                break;
            }
            case "right": {
                playerTile.setCharacter("⏩");
            }
        }
    }

    void processInput(String input) {
        switch (input) {
            case "w": {
                player.setDirection("up");
                break;
            }
            case "a": {
                player.setDirection("left");
                break;
            }
            case "s": {
                player.setDirection("down");
                break;
            }
            case "d": {
                player.setDirection("right");
                break;
            }
            case "1": {
                Coordinate playerCoordinate = player.getTile().getCoords();
                Projectile projectile = new Projectile(playerCoordinate.getX(), playerCoordinate.getY(), player.getDirection());
                projectiles.add(projectile);
            }
        }
    }

    void doIntro() {
        System.out.println("\n"
                + "                         _    ____  \n"
                + "  ___ ____ ___ _  ___   (_)__/ / /__\n"
                + " / _ `/ _ `/  ' \\/ -_) / / _  /  '_/\n"
                + " \\_, /\\_,_/_/_/_/\\__/ /_/\\_,_/_/\\_\\ \n"
                + "/___/                               \n"
        );

        System.out.println("any input to begin");
        s.nextLine();
    }

    void onLose() {
        System.out.println("womp womp");
    }

    public void start() {
        if (active) { return; }
        active = true;

        player = new Player();
        grid = new Grid();
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        s = new Scanner(System.in);

        doIntro();
        while (active) {
            render();
            System.out.println("change direction (wasd) or shoot (1): ");
            processInput(s.nextLine());
            step();
            pointArrow();
        }
        active = false;
        onLose();
    }
}
