package fr.lcdlv.kata.gol;

public class Cell {
    static boolean update(boolean aliveCell, int aliveNeighbours) {
        if (aliveCell) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        }
        return aliveNeighbours == 3;
    }
}
