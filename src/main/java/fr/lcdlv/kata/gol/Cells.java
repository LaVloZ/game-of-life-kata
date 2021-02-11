package fr.lcdlv.kata.gol;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class Cells {
    private final Cell[][] grid;

    public Cells(Cell[][] grid) {
        this.grid = clone(grid);
    }


    public Cells nextGeneration() {
        var updatedCells = new Cell[grid.length][];

        for (int row = 0; row < grid.length; row++) {
            updatedCells[row] = nextRowGeneration(row);
        }

        return new Cells(updatedCells);
    }

    private Cell[] nextRowGeneration(int row) {
        var rowCell = new Cell[grid[row].length];

        for (var column = 0; column < grid[row].length; column++) {
            rowCell[column] = nextCellGeneration(row, column);
        }

        return rowCell;
    }
    private Cell nextCellGeneration(int row, int column) {
        Cell cell = grid[row][column];
        var neighbours = new Neighbours(row, column);

        return neighbours.nextGeneration(cell);
    }

    class Neighbours {

        private final List<Optional<Cell>> cells;
        public Neighbours(int row, int column) {
            cells = findNeighbours(row, column);
        }

        private List<Optional<Cell>> findNeighbours(int row, int column) {
            return List.of(
                    cellFromIndex(row - 1, column - 1),
                    cellFromIndex(row - 1, column),
                    cellFromIndex(row - 1, column + 1),
                    cellFromIndex(row, column - 1),
                    cellFromIndex(row, column + 1),
                    cellFromIndex(row + 1, column - 1),
                    cellFromIndex(row + 1, column),
                    cellFromIndex(row + 1, column + 1)
            );
        }

        private Optional<Cell> cellFromIndex(int row, int column) {
            if (isInsideTheGrid(row, column)) {
                Cell cell = grid[row][column];
                return Optional.of(cell);
            }

            return Optional.empty();
        }

        private boolean isInsideTheGrid(int row, int column) {
            return row >= 0 && row < grid.length && column >= 0 && column < grid[row].length;
        }

        public Cell nextGeneration(Cell cell) {
            var aliveNeighbours = countAlive();
            return cell.nextGeneration(aliveNeighbours);
        }

        private int countAlive() {
            return cells.stream()
                    .map(maybeACell -> maybeACell.map(cell -> cell.countAlive(0))
                            .orElse(0))
                    .mapToInt(value -> value)
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

    private Cell[][] clone(Cell[][] grid) {
        return Arrays.stream(grid)
                .map(Cell[]::clone)
                .toArray(Cell[][]::new);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(grid);
    }

    @Override
    public String toString() {
        return Arrays.stream(grid)
                .map(cells -> Arrays.stream(cells)
                    .map(Enum::toString)
                    .collect(joining()))
                .collect(joining("\n"));
    }
}
