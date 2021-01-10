package fr.lcdlv.kata.gol;

public class State {
    private final boolean alive;

    public State(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
