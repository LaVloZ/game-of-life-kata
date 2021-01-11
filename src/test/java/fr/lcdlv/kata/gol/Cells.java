package fr.lcdlv.kata.gol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.lcdlv.kata.gol.Cell.DEAD;

public class Cells {
    private final Cell[][] grid;

    public Cells(Cell[][] grid) {
        this.grid = grid.clone();
    }

    public Cells nextGeneration() {
        Cell[][] updatedCells = new Cell[grid.length][];

        for (int row = 0; row < grid.length; row++) {
            updateRow(updatedCells, row);
        }

        return new Cells(updatedCells);
    }

    private void updateRow(Cell[][] updatedCells, int row) {
        updatedCells[row] = new Cell[grid[row].length];
        for (int column = 0; column < grid[row].length; column++) {
            updateCell(updatedCells, row, column);
        }
    }

    private void updateCell(Cell[][] updatedCells, int row, int column) {
        Neighbours aliveNeighbours = getAliveNeighbours(row, column);
        int aliveNeighboursCount = aliveNeighbours.count(0);
        Cell cell = grid[row][column];
        updatedCells[row][column] = cell.update(aliveNeighboursCount);
    }

    private void updateEdgedCell(Cell[][] updatedCells, int row, int column) {
        updatedCells[row][column] = DEAD;
    }

    private boolean isOnEdge(int row, int column) {
        return isOnTheTopEdge(row)
                || isOnTheBottomEdge(row)
                || isOnTheLeftEdge(column)
                || isOnTheRightEdge(row, column)
                ;
    }

    private boolean isOnTheTopEdge(int row) {
        return row == 0;
    }

    private boolean isOnTheBottomEdge(int row) {
        return row == grid.length - 1;
    }

    private boolean isOnTheLeftEdge(int column) {
        return column == 0;
    }

    private boolean isOnTheRightEdge(int row, int column) {
        return column == grid[row].length - 1;
    }

    private Neighbours getAliveNeighbours(int row, int column) {
        List<Cell> neighbours = new ArrayList<>();
        if(row + 1 < grid.length) {
            neighbours.add(grid[row + 1][column]);
            if(column - 1 >= 0) {
                neighbours.add(grid[row + 1][column - 1]);
            }
            if(column + 1 < grid[row].length) {
                neighbours.add(grid[row + 1][column + 1]);
            }
        }
        if(row - 1 >= 0) {
            neighbours.add(grid[row - 1][column]);
            if(column + 1 < grid[row].length) {
                neighbours.add(grid[row - 1][column + 1]);
            }
            if(column - 1 >= 0) {
                neighbours.add(grid[row - 1][column - 1]);
            }
        }
        if(column + 1 < grid[row].length) {
            neighbours.add(grid[row][column + 1]);
        }
        if(column - 1 >= 0) {
            neighbours.add(grid[row][column - 1]);
        }
        return new Neighbours(neighbours);
    }

    private static class Neighbours {
        private final List<Cell> cells;

        public Neighbours(List<Cell> cells) {
            this.cells = cells;
        }

        public int count(int conter) {
            return cells.stream()
                    .mapToInt(cell -> cell.countAlive(conter))
                    .sum();
        }
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
            for (Cell cell : row) {
                s += cell.toString();
            }
            s += "\n";
        }
        return s;
    }
}
