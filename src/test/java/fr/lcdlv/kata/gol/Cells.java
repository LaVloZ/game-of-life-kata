package fr.lcdlv.kata.gol;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Cells {
    private final Cell[][] grid;

    public Cells(Cell[][] grid) {
        this.grid = grid.clone();
    }

    public Cells nextGeneration() {
        Cell[][] updatedCells = new Cell[grid.length][];

        for (int row = 0; row < grid.length; row++) {
            updatedCells[row] = nextRowGeneration(row);
        }

        return new Cells(updatedCells);
    }

    private Cell[] nextRowGeneration(int row) {
        Cell[] rowCell = new Cell[grid[row].length];

        for (int column = 0; column < grid[row].length; column++) {
            rowCell[column] = nextCellGeneration(row, column);
        }

        return rowCell;
    }

    private Cell nextCellGeneration(int row, int column) {
        Neighbours neighbours = new Neighbours(row, column);
        int aliveNeighbours = neighbours.countAlive(0);
        Cell cell = grid[row][column];

        return cell.nextGeneration(aliveNeighbours);
    }

    private boolean isInsideTheGrid(int row, int column) {
        return row >= 0 && row < grid.length && column >= 0 && column < grid[row].length;
    }

    private class Neighbours {
        private final List<Cell> cells;

        public Neighbours(int row, int column) {
            cells = findNeighbours(row, column);
        }

        private List<Cell> findNeighbours(int row, int column) {
            return findNeighboursList(row, column)
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(toList());
        }

        private List<Optional<Cell>> findNeighboursList(int row, int column) {
            return List.of(
                    getCellFromIndex(row - 1, column - 1),
                    getCellFromIndex(row - 1, column),
                    getCellFromIndex(row - 1, column + 1),
                    getCellFromIndex(row, column - 1),
                    getCellFromIndex(row, column + 1),
                    getCellFromIndex(row + 1, column - 1),
                    getCellFromIndex(row + 1, column),
                    getCellFromIndex(row + 1, column + 1)
            );
        }

        private Optional<Cell> getCellFromIndex(int row, int column) {
            if (isInsideTheGrid(row, column)) {
                Cell cell = grid[row][column];
                return Optional.of(cell);
            }

            return Optional.empty();
        }

        public int countAlive(int conter) {
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
