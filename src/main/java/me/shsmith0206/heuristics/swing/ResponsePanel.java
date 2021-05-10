package me.shsmith0206.heuristics.swing;

import javax.swing.*;
import java.awt.*;

public class ResponsePanel extends JPanel {
    private final JLabel response0;
    private final JLabel response1;
    public ResponsePanel() {
        setLayout(new GridLayout(1, 2));
        this.response0 = new JLabel();
        this.response1 = new JLabel();
        add(response0);
        add(response1);
    }

    public JLabel getResponse0() {
        return response0;
    }

    public JLabel getResponse1() {
        return response1;
    }
}
