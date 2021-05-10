package me.shsmith0206.heuristics.swing;

import javax.swing.*;
import java.awt.*;

public class HeuristicsPanel extends JPanel {
    private final QuestionPanel questionPanel;
    private final ResponsePanel responsePanel;

    public HeuristicsPanel() {
        setLayout(new GridLayout(2, 1));
        this.questionPanel = new QuestionPanel();
        this.responsePanel = new ResponsePanel();
        add(responsePanel);
        add(questionPanel);
    }

    public QuestionPanel getQuestionPanel() {
        return questionPanel;
    }

    public ResponsePanel getResponsePanel() {
        return responsePanel;
    }
}
