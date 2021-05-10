package me.shsmith0206.heuristics.data;

import java.util.ArrayList;
import java.util.List;

public class HeuristicsData {
    private final List<HeuristicsEntry> entries = new ArrayList<>();

    public void add(HeuristicsEntry entry) {
        entries.add(entry);
    }
}
