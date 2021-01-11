package fr.lcdlv.kata.gol;

import org.junit.jupiter.api.Test;

import static fr.lcdlv.kata.gol.Cell.ALIVE;
import static fr.lcdlv.kata.gol.Cell.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

public class CellsTest {

    @Test
    public void toto() {
        Cell[][] cells = new Cell[][]{
                {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, DEAD,    ALIVE,  DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, ALIVE,   ALIVE,  DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
        };

        Cell[][] updatedCells = update(cells);

        assertThat(updatedCells).isEqualTo(new Cell[][]{
                {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, ALIVE,   ALIVE,  DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, ALIVE,   ALIVE,  DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
        });
    }

    private Cell[][] update(Cell[][] cells) {
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
                aliveAdjacent += cells[i+1][j] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i-1][j] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i][j+1] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i][j-1] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i+1][j-1] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i+1][j+1] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i-1][j+1] == ALIVE ? 1 : 0;
                aliveAdjacent += cells[i-1][j-1] == ALIVE ? 1 : 0;

                updatedCells[i][j] = cell.update(aliveAdjacent);
            }
        }

        return updatedCells;
    }
}
