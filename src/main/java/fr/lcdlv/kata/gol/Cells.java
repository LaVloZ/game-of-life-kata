package fr.lcdlv.kata.gol;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class Cells {
    private final Cell[][] grid;

    public Cells(Cell[][] grid) {
        this.grid = clone(grid);
    }

    public Cells nextGeneration() {
        var end = grid.length - 1;
        var nextGeneration = IntStream.rangeClosed(0, end)
                .mapToObj(this::nextRowGeneration)
                .toArray(Cell[][]::new);
        return new Cells(nextGeneration);
    }

    private Cell[] nextRowGeneration(int row) {
        var end = grid[row].length - 1;
        return IntStream.rangeClosed(0, end)
                .mapToObj(c -> nextCellGeneration(row, c))
                .toArray(Cell[]::new);
    }

    private Cell nextCellGeneration(int row, int column) {
        Cell cell = grid[row][column];
        var neighbours = new Neighbours(row, column);

        return cell.nextGeneration(neighbours);
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
            if (indexIsInsideTheGrid(row, column)) {
                Cell cell = grid[row][column];
                return Optional.of(cell);
            }

            return Optional.empty();
        }

        private boolean indexIsInsideTheGrid(int row, int column) {
            return row >= 0 && row < grid.length && column >= 0 && column < grid[row].length;
        }

        int countAlive() {
            return cells.stream()
                    .map(maybeANeighbour -> maybeANeighbour.map(neighbour -> neighbour.countAlive(0))
                            .orElse(0))
                    .mapToInt(value -> value)
                    .sum();
        }
    }

    private Cell[][] clone(Cell[][] grid) {
        return Arrays.stream(grid)
                .map(Cell[]::clone)
                .toArray(Cell[][]::new);
    }

    @Override
    public boolean equals(Object anOther) {
        if (this == anOther) return true;
        if (anOther == null || getClass() != anOther.getClass()) return false;
        Cells anOtherCell = (Cells) anOther;

        return Arrays.deepEquals(grid, anOtherCell.grid);
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
