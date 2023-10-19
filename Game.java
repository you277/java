import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Player player = null;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private Grid grid = null;
    private boolean alive;
    private int currentStep;
    private int score;
    private boolean scoreExceededIntMax;
    private int enemySpawnPeriod; // how many steps it takes for another enemy to spawn
    private int enemyMovePeriod; // how many steps it takes for enemies to move
    private final Scanner s;

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
        Coordinate playerCoordinates = player.getTile().getCoordinates();
        int x = playerCoordinates.getX();
        int y = playerCoordinates.getY();
        grid.setCenter(x, y);
        System.out.println(grid.getGridArt(tiles));
        if (scoreExceededIntMax) {
            System.out.println("score: how");
        } else {
            System.out.println("score: " + score);
        }
        System.out.println("enemies: " + enemies.size());
    }

    void stepEnemies() {
        if (currentStep % enemyMovePeriod != 0) return;
        Coordinate playerCoordinates = player.getTile().getCoordinates();
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
        if (projectiles.size() == 0 || enemies.size() == 0) return;
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile: projectiles) {
            Coordinate projectileCoordinates = projectile.getTile().getCoordinates();
            int x = projectileCoordinates.getX();
            int y = projectileCoordinates.getY();
            for (Enemy enemy: enemies) {
                Coordinate enemyCoordinates = enemy.getTile().getCoordinates();
                if (enemyCoordinates.getX() == x && enemyCoordinates.getY() == y) {
                    enemiesToRemove.add(enemy);
                    projectilesToRemove.add(projectile);
                    score += 2;
                }
            }
        }
        if (enemiesToRemove.size() == 0) return;
        for (Projectile projectile: projectilesToRemove) {
            projectiles.remove(projectile);
        }
        for (Enemy enemy: enemiesToRemove) {
            enemies.remove(enemy);
        }
    }

    void processPlayerCollisions() {
        Coordinate playerCoordinates = player.getTile().getCoordinates();
        int x = playerCoordinates.getX();
        int y = playerCoordinates.getY();
        for (Enemy enemy: enemies) {
            Coordinate enemyCoordinates = enemy.getTile().getCoordinates();
            if (enemyCoordinates.getX() == x && enemyCoordinates.getY() == y) {
                alive = false;
                break;
            }
        }
    }

    void spawnEnemies() {
        if (currentStep % enemySpawnPeriod != 0) return;

        Coordinate playerCoordinates = player.getTile().getCoordinates();
        int x = playerCoordinates.getX();
        int y = playerCoordinates.getY();

        int xOffset = 3 +(int)Math.round(Math.random()*7);
        int yOffset = 3 + (int)Math.round(Math.random()*7);
        if (Math.random() > 0.5) {
            xOffset *= -1;
        }
        if (Math.random() > 0.5) {
            yOffset *= -1;
        }

        Enemy enemy = new Enemy(x + xOffset, y + yOffset);
        enemies.add(enemy);
    }

    void step() {
        stepProjectiles();
        processProjectileCollisions();
        player.step();
        stepEnemies();
        processPlayerCollisions();
        spawnEnemies();
        if (score == Integer.MAX_VALUE) { // should never happen but whatever
            scoreExceededIntMax = true;
        }
        currentStep++;
        score++;
    }

    void pointArrow() {
        String direction = player.getDirection();
        Tile playerTile = player.getTile();

        switch (direction) {
            case "up" -> playerTile.setCharacter("⏫");
            case "left" -> playerTile.setCharacter("⏪");
            case "down" -> playerTile.setCharacter("⏬");
            case "right" -> playerTile.setCharacter("⏩");
        }
    }

    void processInput(String input) {
        switch (input) {
            case "w" -> player.setDirection("up");
            case "a" -> player.setDirection("left");
            case "s" -> player.setDirection("down");
            case "d" -> player.setDirection("right");
            case "1" -> {
                Coordinate playerCoordinate = player.getTile().getCoordinates();
                Projectile projectile = new Projectile(playerCoordinate.getX(), playerCoordinate.getY(), player.getDirection());
                projectile.setBounds(-10, 10, -10, 10);
                projectiles.add(projectile);
            }
        }
    }

    boolean doIntro() {
        System.out.println(
                """
                        
                        
                            ██████╗ ███████╗██╗    ██╗    ██████╗ ███████╗██╗    ██╗     ██████╗  █████╗ ███╗   ███╗███████╗
                            ██╔══██╗██╔════╝██║    ██║    ██╔══██╗██╔════╝██║    ██║    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝
                            ██████╔╝█████╗  ██║ █╗ ██║    ██████╔╝█████╗  ██║ █╗ ██║    ██║  ███╗███████║██╔████╔██║█████╗ \s
                            ██╔═══╝ ██╔══╝  ██║███╗██║    ██╔═══╝ ██╔══╝  ██║███╗██║    ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝ \s
                            ██║     ███████╗╚███╔███╔╝    ██║     ███████╗╚███╔███╔╝    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗
                            ╚═╝     ╚══════╝ ╚══╝╚══╝     ╚═╝     ╚══════╝ ╚══╝╚══╝      ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
                                                                                                                           \s
                        
                        """
        );
        while (true) {
            System.out.println("\nbegin (y/n)");
            String input = s.nextLine();
            if (input.equals("y") || input.equals("n")) {
                return input.equals("y");
            } else {
                if (input.equals("y/n")) {
                    System.out.println("oh you think ur so funny");
                } else {
                    System.out.println("you FOOL provide CORRECT input or you will...");
                }
            }
        }
    }

    void onLose() {
        System.out.println("\nwomp womp");
        System.out.println("\nfinal score: " + score);
    }

    public void doGame() {
        if (alive) return;
        alive = true;

        System.out.println("do centered mode? (y/n):");
        String doCenteredMode = s.nextLine();

        player = new Player();
        grid = new Grid();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        score = 0;
        currentStep = 0;
        enemySpawnPeriod = 3;
        enemyMovePeriod = 2;

        Grid.setRespectCenter(doCenteredMode.equals("y"));
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

    boolean getRestartInput() {
        while (true) {
            System.out.println("\nplay again? (y/n)");
            String input = s.nextLine();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")){
                return false;
            } else {
                System.out.println("you fool.");
            }
        }
    }

    public void start() {
        boolean startGame = doIntro();
        if (!startGame) {
            alive = false;
            System.out.println("ok bye");
            return;
        }
        doGame();
        while (true) {
            if (getRestartInput()) {
                doGame();
            } else {
                break;
            }
        }
    }

    public Game() {
        s = new Scanner(System.in);
    }
}
