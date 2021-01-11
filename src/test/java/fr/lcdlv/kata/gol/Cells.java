package fr.lcdlv.kata.gol;

import java.util.Arrays;

import static fr.lcdlv.kata.gol.Cell.DEAD;

public class Cells {
    private final Cell[][] grid;

    public Cells(Cell[][] grid) {
        this.grid = grid;
    }

    public Cells nextGeneration() {
        Cell[][] updatedCells = new Cell[grid.length][];

        for (int i = 0; i < grid.length; i++) {
            updatedCells[i] = new Cell[grid[i].length];
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[i].length - 1) {
                    updatedCells[i][j] = DEAD;
                    continue;
                }

                Cell cell = grid[i][j];
                int aliveAdjacent = 0;
                aliveAdjacent = grid[i + 1][j].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i - 1][j].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i][j + 1].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i][j - 1].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i + 1][j - 1].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i + 1][j + 1].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i - 1][j + 1].countAlive(aliveAdjacent);
                aliveAdjacent = grid[i - 1][j - 1].countAlive(aliveAdjacent);

                updatedCells[i][j] = cell.update(aliveAdjacent);
            }
        }

        return new Cells(updatedCells);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Cells otherCell = (Cells) other;
        return Arrays.deepEquals(grid, otherCell.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(grid);
    }

    @Override
    public String toString() {
        String s = "";
        for (Cell[] row : grid) {
            for(Cell cell : row) {
                s += cell.toString();
            }
            s += "\n";
        }
        return s;
    }
}
