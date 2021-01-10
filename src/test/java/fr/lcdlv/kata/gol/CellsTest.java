package fr.lcdlv.kata.gol;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellsTest {

    @Test
    public void test() {
        boolean aliveCell = true;
        int aliveAdjacentCells = 0;

        boolean deadCell = update(aliveCell, aliveAdjacentCells);

        Assertions.assertThat(deadCell).isFalse();
    }

    private boolean update(boolean cell, int aliveAdjacentCells) {
        return false;
    }
}
