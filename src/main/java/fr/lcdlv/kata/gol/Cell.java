package fr.lcdlv.kata.gol;

public class Cell {

    private static final int TWO_ALIVE_NEIGHBOURS = 2;
    private static final int THREE_ALIVE_NEIGHBOURS = 3;

    public boolean update(boolean aliveCell, int aliveNeighbours) {
        if (aliveCell) {
            return aliveNeighbours == TWO_ALIVE_NEIGHBOURS || aliveNeighbours == THREE_ALIVE_NEIGHBOURS;
        }
        return aliveNeighbours == THREE_ALIVE_NEIGHBOURS;
    }
}
