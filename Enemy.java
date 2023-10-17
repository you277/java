import java.util.ArrayList;

public class Enemy {
    private Tile tile = null;

    public Enemy(int x, int y) {
        tile = new Tile("🟥", x, y);
    }

    public void step(ArrayList<Enemy> otherEnemies, int playerX, int playerY) {
        Coordinate enemyCoordinates = tile.getCoordinates();
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

        int[] xPositions = {  x, newX };
        int[] yPositions = { newY, y };

        ArrayList<Integer> allowedMoves = new ArrayList<>();
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(1);

        for (Enemy otherEnemy: otherEnemies) {
            if (otherEnemy == this) {
                continue;
            }
            Coordinate otherCoordinate = otherEnemy.getTile().getCoordinates();
            int otherX = otherCoordinate.getX();
            int otherY = otherCoordinate.getY();
            for (Integer moveIdx: moves) {
                if (xPositions[moveIdx] != otherX || yPositions[moveIdx] != otherY) {
                    allowedMoves.add(moveIdx);
                }
            }
        }

        if (allowedMoves.size() == 0) {
            return;
        }
        if (allowedMoves.size() == 1) {
            enemyCoordinates.setCoordinates(xPositions[0], yPositions[0]);
        } else {
            int moveIdx = (int)Math.round(Math.random()*(allowedMoves.size() - 1));
            moveIdx = allowedMoves.get(moveIdx);
            enemyCoordinates.setCoordinates(xPositions[moveIdx], yPositions[moveIdx]);
        }
    }

    public Tile getTile() {
        return tile;
    }
}
