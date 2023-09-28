import java.util.ArrayList;

public class Enemy {
    private Tile tile = new Tile("🟥");

    public void step(ArrayList<Enemy> otherEnemies, int playerX, int playerY) {
        Coordinate enemyCoordinates = tile.getCoords();
        int x = enemyCoordinates.getX();
        int y = enemyCoordinates.getY();
        int newX = x;
        int newY = y;

        if (playerX > x) {
            newX++;
        } else if (playerX < x) {
            newX--;
        }
        if (playerY > y) {
            newY++;
        } else if (playerY < y) {
            newY--;
        }

        int[] allowedX = { newX, x, newX };
        int[] allowedY = { newY, newY, y };

        ArrayList<Integer> allowedMoves = new ArrayList<>();
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(1);
        moves.add(2);

        for (Enemy otherEnemy: otherEnemies) {
            if (otherEnemy == this) {
                continue;
            }
            Coordinate otherCoordinate = otherEnemy.getTile().getCoords();
            int otherX = otherCoordinate.getX();
            int otherY = otherCoordinate.getY();
            for(Integer moveIdx: moves) {
                if (allowedX[moveIdx] == otherX && allowedY[moveIdx] == otherY) {

                }
            }
        }
    }

    public Tile getTile() {
        return tile;
    }
}
