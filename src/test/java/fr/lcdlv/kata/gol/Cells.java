package fr.lcdlv.kata.gol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                if (isOnEdge(i, j)) {
                    updatedCells[i][j] = DEAD;
                    continue;
                }

                int aliveNeighbours = getAliveNeighbours(i, j);
                Cell cell = grid[i][j];
                updatedCells[i][j] = cell.update(aliveNeighbours);
            }
        }

        return new Cells(updatedCells);
    }

    private boolean isOnEdge(int i, int j) {
        return isOnTheTopEdge(i) || isOnTheBottomEdge(i) || isOnTheLeftEdge(j) || isOnTheRightEdge(i, j);
    }

    private boolean isOnTheRightEdge(int i, int j) {
        return grid[i].length - 1 == j;
    }

    private boolean isOnTheLeftEdge(int j) {
        return j == 0;
    }

    private boolean isOnTheBottomEdge(int i) {
        return i == grid.length - 1;
    }

    private boolean isOnTheTopEdge(int i) {
        return i == 0;
    }

    private int getAliveNeighbours(int i, int j) {
        return getNeighbours(i, j).stream()
                .mapToInt(cell -> cell.countAlive(0))
                .sum();
    }

    private List<Cell> getNeighbours(int row, int column) {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(grid[row + 1][column]);
        neighbours.add(grid[row-1][column]);
        neighbours.add(grid[row][column + 1]);
        neighbours.add(grid[row][column-1]);
        neighbours.add(grid[row+1][column-1]);
        neighbours.add(grid[row+1][column+1]);
        neighbours.add(grid[row-1][column+1]);
        neighbours.add(grid[row-1][column-1]);
        return neighbours;
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
