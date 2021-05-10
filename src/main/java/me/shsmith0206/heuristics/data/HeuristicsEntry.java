package me.shsmith0206.heuristics.data;

public class HeuristicsEntry {
    private final boolean completed;

    private final String response;

    private final boolean side;

    public HeuristicsEntry(boolean completed, String response, boolean side) {
        this.completed = completed;
        this.response = response;
        this.side = side;
    }
}
