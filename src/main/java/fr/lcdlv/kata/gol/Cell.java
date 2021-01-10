package fr.lcdlv.kata.gol;

public class Cell {
    static boolean update(boolean cell, int aliveNeighbours) {
        if(cell == false && aliveNeighbours == 3) {
            return true;
        }
        if(cell == false) {
            return false;
        }
        return aliveNeighbours == 2 || aliveNeighbours == 3;
    }
}
