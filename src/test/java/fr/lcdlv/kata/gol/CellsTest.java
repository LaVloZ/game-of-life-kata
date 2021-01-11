package fr.lcdlv.kata.gol;

import org.junit.jupiter.api.Test;

import static fr.lcdlv.kata.gol.Cell.ALIVE;
import static fr.lcdlv.kata.gol.Cell.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

public class CellsTest {

    @Test
    public void nextGeneration() {
        Cell[][] cells = new Cell[][]{
                {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, DEAD,    ALIVE,  DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, ALIVE,   ALIVE,  DEAD, DEAD, DEAD,},
                {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
        };

        Cells updatedCells = new Cells(cells).nextGeneration();

        assertThat(updatedCells).isEqualTo(new Cells(
                new Cell[][]{
                    {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
                    {DEAD, DEAD, DEAD, ALIVE,   ALIVE,  DEAD, DEAD, DEAD,},
                    {DEAD, DEAD, DEAD, ALIVE,   ALIVE,  DEAD, DEAD, DEAD,},
                    {DEAD, DEAD, DEAD, DEAD,    DEAD,   DEAD, DEAD, DEAD,},
        }));
    }

}
