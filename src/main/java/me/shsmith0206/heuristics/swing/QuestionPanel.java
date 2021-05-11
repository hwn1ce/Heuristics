package me.shsmith0206.heuristics.swing;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionPanel extends JPanel {
    private final JLabel question;
    private final JTextField response;
    public QuestionPanel() {
        setLayout(new GridLayout(2, 1));
        this.question = new JLabel();
        this.response = new JTextField();
        add(question);
        add(response);
    }

    public void setHighlighted(boolean highlighted) {
        response.setVisible(highlighted);
    }

    public JLabel getQuestion() {
        return question;
    }

    public JTextField getResponse() {
        return response;
    }
}
