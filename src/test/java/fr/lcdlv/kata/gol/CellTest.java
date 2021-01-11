package fr.lcdlv.kata.gol;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static fr.lcdlv.kata.gol.Cell.ALIVE;
import static fr.lcdlv.kata.gol.Cell.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    @Nested
    public class Death {

        @Nested
        public class Underpopulation {
            @Test
            public void update_returns_dead_when_alive_cell_without_alive_neighbours() {
                Cell deadCell = ALIVE.update(0);

                assertThat(deadCell).isEqualTo(DEAD);
            }

            @Test
            public void update_returns_dead_when_alive_cell_with_only_one_alive_neighbour() {
                Cell deadCell = ALIVE.update(1);

                assertThat(deadCell).isEqualTo(DEAD);
            }
        }

        @Nested
        public class Overcrowding {
            @Test
            public void update_returns_dead_when_alive_cell_with_four_alive_neighbours() {
                Cell deadCell = ALIVE.update(4);

                assertThat(deadCell).isEqualTo(DEAD);
            }
        }
    }

    @Nested
    public class Life {
        @Test
        public void update_return_alive_when_alive_cell_with_two_alive_neighbours() {
            Cell deadCell = ALIVE.update(2);

            assertThat(deadCell).isEqualTo(ALIVE);
        }

        @Test
        public void update_return_alive_when_alive_cell_with_three_alive_neighbours() {
            Cell deadCell = ALIVE.update(3);

            assertThat(deadCell).isEqualTo(ALIVE);
        }

        @Test
        public void update_return_alive_when_dead_cell_with_three_alive_neighbours() {
            Cell deadCell = DEAD.update(3);

            assertThat(deadCell).isEqualTo(ALIVE);
        }

        @Test
        public void update_return_dead_when_dead_cell_with_two_alive_neighbours() {
            Cell deadCell = DEAD.update(2);

            assertThat(deadCell).isEqualTo(DEAD);
        }
    }

}
