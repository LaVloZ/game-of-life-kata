package fr.lcdlv.kata.gol;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellsTest {

    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    @Nested
    public class Death {

        @Nested
        public class Underpopulation {
            @Test
            public void update_returns_false_when_only_one_alive_neighbour() {
                boolean deadCell = update(ALIVE, 1);

                assertThat(deadCell).isEqualTo(DEAD);
            }

            @Test
            public void update_returns_false_when_four_alive_neighbours() {
                boolean deadCell = update(ALIVE, 4);

                assertThat(deadCell).isEqualTo(DEAD);
            }
        }

        @Nested
        public class Overcrowding {
            @Test
            public void update_returns_false_when_no_alive_neighbours() {
                boolean deadCell = update(ALIVE, 0);

                assertThat(deadCell).isEqualTo(DEAD);
            }
        }
    }

    private boolean update(boolean cell, int aliveNeighbours) {
        return false;
    }
}
