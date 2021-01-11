package fr.lcdlv.kata.gol;

import static fr.lcdlv.kata.gol.Cell.DEAD;

public class Cells {
    private final Cell[][] cells;

    public Cells(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[][] nextGeneration() {
        Cell[][] updatedCells = new Cell[cells.length][];

        for (int i = 0; i < cells.length; i++) {
            updatedCells[i] = new Cell[cells[i].length];
            for (int j = 0; j < cells[i].length; j++) {
                if(i == 0 || i == cells.length -1 || j == 0 || j == cells[i].length -1) {
                    updatedCells[i][j] = DEAD;
                    continue;
                }

                Cell cell = cells[i][j];
                int aliveAdjacent = 0;
                aliveAdjacent = cells[i+1][j].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i-1][j].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i][j+1].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i][j-1].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i+1][j-1].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i+1][j+1].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i-1][j+1].countAlive(aliveAdjacent);
                aliveAdjacent = cells[i-1][j-1].countAlive(aliveAdjacent);

                updatedCells[i][j] = cell.update(aliveAdjacent);
            }
        }

        return updatedCells;
    }

}