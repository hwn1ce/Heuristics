package me.shsmith0206.heuristics;

import me.shsmith0206.heuristics.config.QuestionsConfig;
import me.shsmith0206.heuristics.swing.HeuristicsPanel;

import javax.swing.*;

public final class Heuristics {
    public static void main(String... args) {
        HeuristicsPanel panel = new HeuristicsPanel();

        System.out.println("Initializing panel.");

        JFrame content = new JFrame();
        content.add(panel);

        System.out.println("Opening panel.");


        content.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content.setSize(480, 240);
        content.setVisible(true);

        new Thread(() -> {
            new ResponseManager().begin(panel, 10);
        }).start();
    }
}
