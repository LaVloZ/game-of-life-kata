package fr.lcdlv.kata.gol;

public class Cell {
    static boolean update(boolean cell, int aliveNeighbours) {
        if (cell) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        }
        return aliveNeighbours == 3;
    }
}
