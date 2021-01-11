package fr.lcdlv.kata.gol;

public enum Cell {

    ALIVE {
        @Override
        public Cell update(int aliveNeighbours) {
            return aliveNeighbours == TWO_ALIVE_NEIGHBOURS || aliveNeighbours == THREE_ALIVE_NEIGHBOURS ? ALIVE : DEAD;
        }

        @Override
        public int countAlive(int counter) {
            return counter + 1;
        }

        @Override
        public String toString() {
            return "*";
        }
    }, DEAD {
        @Override
        public Cell update(int aliveNeighbours) {
            return aliveNeighbours == THREE_ALIVE_NEIGHBOURS ? ALIVE : DEAD;
        }

        @Override
        public int countAlive(int counter) {
            return counter;
        }

        @Override
        public String toString() {
            return ".";
        }
    };

    private static final int TWO_ALIVE_NEIGHBOURS = 2;
    private static final int THREE_ALIVE_NEIGHBOURS = 3;

    public abstract Cell update(int aliveNeighbours);
    public abstract int countAlive(int counter);
}
