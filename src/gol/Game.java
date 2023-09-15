package gol;

import java.util.Arrays;

public class Game
{
    private boolean[][] points = new boolean[15][20];

    public void markAlive(int x, int y)
    {
        this.points[x][y] = true;
    }

    public boolean isAlive(int x, int y)
    {
        return this.points[x][y];
    }

    public void toggle(int x, int y)
    {
        this.points[x][y] = !this.points[x][y];
    }

    public Integer getNeighbourCount(int x, int y)
    {
        var neighbours = 0;

        for (var row = x - 1; row <= x + 1; row++)
        {
            if (row < 0 || row > 14) {
                continue;
            }

            for (var column = y - 1; column <= y + 1; column++)
            {
                if (column < 0 || column > 19) {
                    continue;
                }

                if (row == x && column == y) {
                    continue;
                }

                if (isAlive(row, column)) {
                    neighbours++;
                }
            }
        }

        return neighbours;
    }

    public void nextFrame()
    {
        var clone = Arrays.stream(this.points).map(boolean[]::clone).toArray(boolean[][]::new);

        for (var x = 0; x < 15; x++)
        {
            for (var y = 0; y < 20; y++)
            {
                var isLiving = isAlive(x, y);
                var neighborCount = getNeighbourCount(x, y);

                clone[x][y] = nextState(isLiving, neighborCount);
            }
        }

        this.points = clone;
    }

    public void clear()
    {
        this.points = new boolean[15][20];
    }

    public boolean nextState(boolean isLiving, int neighborCount)
    {
        if (neighborCount == 2) {
            return isLiving;
        }

        return neighborCount == 3;
    }
}