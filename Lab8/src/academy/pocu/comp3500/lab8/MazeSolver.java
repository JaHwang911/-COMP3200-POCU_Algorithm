package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MazeSolver {
    private static final int[][] MOVE_OFFSET = {
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

    public static List<Point> findPath(final char[][] maze, final Point start) {
        List<Point> out = new ArrayList<>();

        if (maze[start.getY()][start.getX()] == 'E') {
            out.add(start);

            return out;
        }

        final int MAZE_WIDTH = maze[0].length;
        final int MAZE_HEIGHT = maze.length;

        for (int i = 0; i < MOVE_OFFSET.length; ++i) {
            int toX = start.getX() + MOVE_OFFSET[i][0];
            int toY = start.getY() + MOVE_OFFSET[i][1];

            if (toX < 0 || toX >= MAZE_WIDTH || toY < 0 || toY >= MAZE_HEIGHT) {
                continue;
            }

            if (maze[toY][toX] != 'x') {
                if (findPathRecursive(maze, start.getX(), start.getY(), toX, toY, out)) {
                    out.add(new Point(start.getX(), start.getY()));
                    break;
                }
            }
        }

        Collections.reverse(out);
        return out;
    }

    private static boolean findPathRecursive(final char[][]maze, int prevX, int prevY, int currentX, int currentY, final List<Point>out) {
        if (maze[currentY][currentX] == 'E') {
            out.add(new Point(currentX, currentY));
            return true;
        }

        final int MAZE_WIDTH = maze[0].length;
        final int MAZE_HEIGHT = maze.length;

        for (int i = 0; i < MOVE_OFFSET.length; ++i) {
            int toX = currentX + MOVE_OFFSET[i][0];
            int toY = currentY + MOVE_OFFSET[i][1];

            if (prevX == toX && prevY == toY) {
                continue;
            } else if (toX < 0 || toX >= MAZE_WIDTH || toY < 0 || toY >= MAZE_HEIGHT) {
                continue;
            }

            if (maze[toY][toX] != 'x') {
                if (findPathRecursive(maze, currentX, currentY, toX, toY, out)) {
                    out.add(new Point(currentX, currentY));
                    return true;
                }
            }
        }

        return false;
    }
}