package fr.lcdlv.kata.gol;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    @Nested
    public class Death {

        @Nested
        public class Underpopulation {
            @Test
            public void update_returns_dead_when_alive_cell_without_alive_neighbours() {
                boolean deadCell = Cell.update(ALIVE, 0);

                assertThat(deadCell).isEqualTo(DEAD);
            }

            @Test
            public void update_returns_dead_when_alive_cell_with_only_one_alive_neighbour() {
                boolean deadCell = Cell.update(ALIVE, 1);

                assertThat(deadCell).isEqualTo(DEAD);
            }
        }

        @Nested
        public class Overcrowding {
            @Test
            public void update_returns_dead_when_alive_cell_with_four_alive_neighbours() {
                boolean deadCell = Cell.update(ALIVE, 4);

                assertThat(deadCell).isEqualTo(DEAD);
            }
        }
    }

    @Nested
    public class Life {
        @Test
        public void update_return_alive_when_alive_cell_with_two_alive_neighbours() {
            boolean deadCell = Cell.update(ALIVE, 2);

            assertThat(deadCell).isEqualTo(ALIVE);
        }

        @Test
        public void update_return_alive_when_alive_cell_with_three_alive_neighbours() {
            boolean deadCell = Cell.update(ALIVE, 3);

            assertThat(deadCell).isEqualTo(ALIVE);
        }

        @Test
        public void update_return_alive_when_alive_cell_with_three_alive_neighbours2() {
            boolean deadCell = Cell.update(DEAD, 3);

            assertThat(deadCell).isEqualTo(ALIVE);
        }

        @Test
        public void update_return_alive_when_alive_cell_with_three_alive_neighbours3() {
            boolean deadCell = Cell.update(DEAD, 2);

            assertThat(deadCell).isEqualTo(DEAD);
        }
    }

}
