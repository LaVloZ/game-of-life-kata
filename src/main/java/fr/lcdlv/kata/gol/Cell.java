package fr.lcdlv.kata.gol;

public class Cell {

    private static final int TWO_ALIVE_NEIGHBOURS = 2;
    private static final int THREE_ALIVE_NEIGHBOURS = 3;

    private final boolean alive;

    public Cell(State state) {
        this.alive = state.isAlive();
    }

    public boolean update(int aliveNeighbours) {
        if (alive) {
            return aliveNeighbours == TWO_ALIVE_NEIGHBOURS || aliveNeighbours == THREE_ALIVE_NEIGHBOURS;
        }
        return aliveNeighbours == THREE_ALIVE_NEIGHBOURS;
    }
}
