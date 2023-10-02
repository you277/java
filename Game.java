import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Game {
    private Player player = null;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private Grid grid = null;
    private boolean alive;
    private int currentStep;
    private int spawnPeriod; // how many steps it takes for another enemy to spawn
    private Scanner s;

    void render() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Enemy enemy: enemies) {
            tiles.add(enemy.getTile());
        }
        for (Projectile projectile: projectiles) {
            tiles.add(projectile.getTile());
        }
        if (alive) {
            tiles.add(player.getTile());
        }
        System.out.println(grid.getGridArt(tiles));
        System.out.println("score: " + currentStep);
        System.out.println("enemies: " + enemies.size());
    }

    void stepEnemies() {
        Coordinate playerCoordinates = player.getTile().getCoords();
        int x = playerCoordinates.getX();
        int y = playerCoordinates.getY();
        for (Enemy enemy: enemies) {
            enemy.step(enemies, x, y);
        }
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

    void processProjectileCollisions() {
        if (projectiles.size() == 0 || enemies.size() == 0) {
            return;
        }
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile: projectiles) {
            Coordinate projectileCoordinates = projectile.getTile().getCoords();
            int x = projectileCoordinates.getX();
            int y = projectileCoordinates.getY();
            for (Enemy enemy: enemies) {
                Coordinate enemyCoordinates = enemy.getTile().getCoords();
                if (enemyCoordinates.getX() == x && enemyCoordinates.getY() == y) {
                    enemiesToRemove.add(enemy);
                    projectilesToRemove.add(projectile);
                }
            }
        }
        if (enemiesToRemove.size() == 0) {
            return;
        }
        for (Projectile projectile: projectilesToRemove) {
            projectiles.remove(projectile);
        }
        for (Enemy enemy: enemiesToRemove) {
            enemies.remove(enemy);
        }
    }

    void processPlayerCollisions() {
        Coordinate playerCoordinates = player.getTile().getCoords();
        int x = playerCoordinates.getX();
        int y = playerCoordinates.getY();
        for (Enemy enemy: enemies) {
            Coordinate enemyCoordinates = enemy.getTile().getCoords();
            if (enemyCoordinates.getX() == x && enemyCoordinates.getY() == y) {
                alive = false;
                break;
            }
        }
    }

    void spawnEnemies() {
        if (currentStep % spawnPeriod != 0) {
            return;
        }
        Random rand = new Random();

        Coordinate playerCoordinates = player.getTile().getCoords();
        int x = playerCoordinates.getX();
        int y = playerCoordinates.getY();

        int xOffset = rand.nextInt(3, 10);
        int yOffset = rand.nextInt(3, 10);
        if (rand.nextInt(0, 1) == 0) {
            xOffset *= -1;
        }
        if (rand.nextInt(0, 1) == 0) {
            yOffset *= -1;
        }

        Enemy enemy = new Enemy(
                x + xOffset,
                y + yOffset
        );
        enemies.add(enemy);
    }

    void step() {
        stepProjectiles();
        processProjectileCollisions();
        player.step();
        stepEnemies();
        processPlayerCollisions();
        spawnEnemies();
        currentStep++;
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
                projectile.setBounds(-10, 10, -10, 10);
                projectiles.add(projectile);
            }
        }
    }

    boolean doIntro() {
        System.out.println(
                """

                                         _    ____ \s
                  ___ ____ ___ _  ___   (_)__/ / /__
                 / _ `/ _ `/  ' \\/ -_) / / _  /  '_/
                 \\_, /\\_,_/_/_/_/\\__/ /_/\\_,_/_/\\_\\\s
                /___/                              \s
                """
        );

        String input;
        while (true) {
            System.out.println("\nbegin (y/n)");
            input = s.nextLine();
            if (input.equals("y") || input.equals("n")) {
                break;
            } else {
                if (input.equals("y/n")) {
                    System.out.println("oh you think ur so funny");
                } else {
                    System.out.println("you FOOL provide CORRECT input or you will...");
                }
            }
        }
        return input.equals("y");
    }

    void onLose() {
        System.out.println("womp womp");
        System.out.println("final score: " + currentStep);
    }

    public void start() {
        if (alive) { return; }
        alive = true;

        s = new Scanner(System.in);
        boolean startGame = doIntro();
        if (!startGame) {
            alive = false;
            System.out.println("ok bye");
            return;
        }

        player = new Player();
        grid = new Grid();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        currentStep = 0;
        spawnPeriod = 3;

        player.setBounds(-10, 10, -10, 10);

        while (alive) {
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
