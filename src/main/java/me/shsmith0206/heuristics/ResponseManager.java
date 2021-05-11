package me.shsmith0206.heuristics;

import com.dfsek.tectonic.exception.ConfigException;
import com.dfsek.tectonic.loading.ConfigLoader;
import me.shsmith0206.heuristics.config.QuestionsConfig;
import me.shsmith0206.heuristics.data.Response;
import me.shsmith0206.heuristics.swing.HeuristicsPanel;
import me.shsmith0206.heuristics.swing.TextIcon;
import me.shsmith0206.heuristics.util.JarUtil;
import me.shsmith0206.heuristics.util.RandomUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ResponseManager {
    private volatile Response option;
    private volatile boolean status = false;
    private volatile String response;

    public synchronized void begin(HeuristicsPanel panel, int rounds) {
        panel.getQuestionPanel().setHighlighted(false);
        panel.registerKeyboardAction(e -> {
            if (!status) return;
            synchronized (this) {
                this.option = Response.LEFT;
                this.notifyAll();
            }
        }, KeyStroke.getKeyStroke('z'), JComponent.WHEN_IN_FOCUSED_WINDOW);

        panel.registerKeyboardAction(e -> {
            if (!status) return;
            synchronized (this) {
                this.option = Response.RIGHT;
                this.notifyAll();
            }
        }, KeyStroke.getKeyStroke('m'), JComponent.WHEN_IN_FOCUSED_WINDOW);

        panel.getQuestionPanel().getResponse().addActionListener((e) -> {
            if (response != null) return;
            synchronized (this) {
                this.response = panel.getQuestionPanel().getResponse().getText();
                panel.getQuestionPanel().setHighlighted(false);
                panel.getQuestionPanel().getResponse().setText("");
                this.notifyAll();
            }
        });

        List<BufferedImage> imageList;
        try {
            imageList = JarUtil.getResources(JarUtil.JPG_FILTER.and(path -> path.startsWith("images")), JarUtil.IMAGE_FUNCTION);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        QuestionsConfig config = new QuestionsConfig();
        ConfigLoader loader = new ConfigLoader();

        try {
            loader.load(config, ResponseManager.class.getResourceAsStream("/questions.yml"));
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }

        List<String> questions = config.getQuestions();

        for (int i = 0; i < rounds; i++) {
            System.out.println(i);
            JLabel response0 = panel.getResponsePanel().getResponse0();
            JLabel response1 = panel.getResponsePanel().getResponse1();

            BufferedImage image0 = imageList.get(RandomUtil.threadLocalInt(imageList.size()));
            BufferedImage image1 = imageList.get(RandomUtil.threadLocalInt(imageList.size()));
            response0.setIcon(new ImageIcon(image0.getScaledInstance(response0.getWidth(), response0.getHeight(), Image.SCALE_SMOOTH)));
            response1.setIcon(new ImageIcon(image1.getScaledInstance(response0.getWidth(), response0.getHeight(), Image.SCALE_SMOOTH)));

            JLabel question = panel.getQuestionPanel().getQuestion();
            question.setIcon(new TextIcon(question, questions.get(RandomUtil.threadLocalInt(questions.size()))));

            try {
                status = true; // We're waiting now.
                option = Response.NONE;
                wait(5000);
                status = false; // Done waiting.
                System.out.println("Options: " + option);

                if (option != Response.NONE) {
                    response = null;
                    panel.getQuestionPanel().setHighlighted(true);
                    wait();
                    System.out.println(response);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
