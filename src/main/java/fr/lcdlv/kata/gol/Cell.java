package fr.lcdlv.kata.gol;

public enum Cell {

    ALIVE {
        @Override
        public Cell update(int aliveNeighbours) {
            return aliveNeighbours == TWO_ALIVE_NEIGHBOURS || aliveNeighbours == THREE_ALIVE_NEIGHBOURS ? ALIVE : DEAD;
        }
    }, DEAD {
        @Override
        public Cell update(int aliveNeighbours) {
            return aliveNeighbours == THREE_ALIVE_NEIGHBOURS ? ALIVE : DEAD;
        }
    };

    private static final int TWO_ALIVE_NEIGHBOURS = 2;
    private static final int THREE_ALIVE_NEIGHBOURS = 3;

    public abstract Cell update(int aliveNeighbours);
}
