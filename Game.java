import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private Grid grid;
    private boolean active;
    private Scanner s;

    void render() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Enemy enemy: enemies) {
            tiles.add(enemy.getTile());
        }
        for (Projectile projectile: projectiles) {
            tiles.add(projectile.getTile());
        }
        tiles.add(player.getTile());
        System.out.println(grid.getGridArt(tiles));
    }

    void stepEnemies() {
        // nothing yet
    }
    void stepProjectiles() {
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile: projectiles) {
            if (projectile.getLife() == 0) {
                projectilesToRemove.add(projectile);
                continue;
            }
            projectile.step();
        }
        if (projectilesToRemove.size() != 0) {
            for (Projectile projectile: projectilesToRemove) {
                projectiles.remove(projectile);
            }
        }
    }

    void step() {
        stepEnemies();
        stepProjectiles();
        player.step(enemies);
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
        System.out.println(
                """

                                         _    ____ \s
                  ___ ____ ___ _  ___   (_)__/ / /__
                 / _ `/ _ `/  ' \\/ -_) / / _  /  '_/
                 \\_, /\\_,_/_/_/_/\\__/ /_/\\_,_/_/\\_\\\s
                /___/                              \s
                """
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
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        s = new Scanner(System.in);

        doIntro();
        while (active) {
            render();
            System.out.println("change direction (wasd) or shoot (1): ");
            processInput(s.nextLine());
            step();
            pointArrow();
        }
        render();
        onLose();
    }
}
