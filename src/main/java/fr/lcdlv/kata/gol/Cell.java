package fr.lcdlv.kata.gol;

public class Cell {
    static boolean update(boolean cell, int aliveNeighbours) {
        return aliveNeighbours == 2 || aliveNeighbours == 3;
    }
}
