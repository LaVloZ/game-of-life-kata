package fr.lcdlv.kata.gol;

import fr.lcdlv.kata.gol.Cells.Neighbours;

public enum Cell {

    ALIVE {
        @Override
        public Cell nextGeneration(int aliveNeighbours) {
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
        public Cell nextGeneration(int aliveNeighbours) {
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

    public Cell nextGeneration(Neighbours neighbours) {
        var aliveNeighbours = neighbours.countAlive(0);
        return nextGeneration(aliveNeighbours);
    }

    abstract Cell nextGeneration(int aliveNeighbours);
    public abstract int countAlive(int counter);
}
