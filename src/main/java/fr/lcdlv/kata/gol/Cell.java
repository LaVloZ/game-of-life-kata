package fr.lcdlv.kata.gol;

public class Cell {

    private static final int TWO_ALIVE_NEIGHBOURS = 2;
    private static final int THREE_ALIVE_NEIGHBOURS = 3;

    public abstract Cell update(int aliveNeighbours);
}
