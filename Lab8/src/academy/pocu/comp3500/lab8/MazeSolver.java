package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;

public final class MazeSolver {
    private static final int[][] MOVE_OFFSET = {
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

    private static final char BLOCKED_SIGN = 'B';
    private static final char VISITED_SIGN = 'V';

    public static List<Point> findPath(final char[][] maze, final Point start) {
        List<Point> result = new ArrayList<>();

        if (maze[start.getY()][start.getX()] == 'E') {
            result.add(start);

            return result;
        }

        findPathRecursive(maze, start.getX(), start.getY(), result);

        return result;
    }

    private static boolean findPathRecursive(final char[][]maze, int currentX, int currentY, List<Point> out) {
        if (maze[currentY][currentX] == 'E') {
            out.add(new Point(currentX, currentY));
            return true;
        }

        final int MAZE_WIDTH = maze[0].length;
        final int MAZE_HEIGHT = maze.length;

        for (int i = 0; i < MOVE_OFFSET.length; ++i) {
            int toX = currentX + MOVE_OFFSET[i][0];
            int toY = currentY + MOVE_OFFSET[i][1];

            if (toX < 0 || toX >= MAZE_WIDTH || toY < 0 || toY >= MAZE_HEIGHT) {
                continue;
            } else if (maze[toY][toX] == 'V' || maze[toY][toX] == 'B' || maze[toY][toX] == 'x') {
                continue;
            }

            maze[currentY][currentX] = 'V';

            if (findPathRecursive(maze, toX, toY, out)) {
                out.add(0, new Point(currentX, currentY));
                return true;
            }
        }

        maze[currentY][currentX] = 'B';
        return false;
    }
}