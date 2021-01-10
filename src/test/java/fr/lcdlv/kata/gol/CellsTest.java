package fr.lcdlv.kata.gol;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CellsTest {

    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    @Nested
    public class Death {

        @Test
        public void update_returns_false_when_no_alive_neighbours() {
            boolean deadCell = update(ALIVE, 0);

            assertThat(deadCell).isEqualTo(DEAD);
        }

        @Test
        public void update_returns_false_when_only_one_alive_neighbour() {
            boolean deadCell = update(ALIVE, 1);

            assertThat(deadCell).isEqualTo(DEAD);
        }
    }

    private boolean update(boolean cell, int aliveNeighbours) {
        return false;
    }
}
