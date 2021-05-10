package me.shsmith0206.heuristics.swing;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private final JLabel question;
    private final TextField response;
    public QuestionPanel() {
        setLayout(new GridLayout(1, 2));
        this.question = new JLabel();
        this.response = new TextField();
        add(question);
        add(response);
    }

    public JLabel getQuestion() {
        return question;
    }

    public TextField getResponse() {
        return response;
    }
}
