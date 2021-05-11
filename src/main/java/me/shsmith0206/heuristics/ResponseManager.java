package me.shsmith0206.heuristics;

import com.dfsek.tectonic.exception.ConfigException;
import com.dfsek.tectonic.loading.ConfigLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.shsmith0206.heuristics.config.QuestionsConfig;
import me.shsmith0206.heuristics.data.HeuristicsData;
import me.shsmith0206.heuristics.data.HeuristicsEntry;
import me.shsmith0206.heuristics.data.Question;
import me.shsmith0206.heuristics.data.Response;
import me.shsmith0206.heuristics.swing.HeuristicsPanel;
import me.shsmith0206.heuristics.swing.TextIcon;
import me.shsmith0206.heuristics.util.JarUtil;
import me.shsmith0206.heuristics.util.RandomUtil;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

public class ResponseManager {
    private volatile Response option;
    private volatile boolean status = false;
    private volatile String response;

    public synchronized void begin(HeuristicsPanel panel, int rounds) {
        HeuristicsData data = new HeuristicsData();

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


        List<String> names = new ArrayList<>();
        List<BufferedImage> images = new ArrayList<>();
        try {
            JarUtil.getResources(JarUtil.JPG_FILTER.and(path -> path.startsWith("images")), (path, inputStream) -> {
                images.add(JarUtil.IMAGE_FUNCTION.apply(inputStream));
                names.add(path);
            });
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

            int i0 = RandomUtil.threadLocalInt(images.size());
            int i1 = RandomUtil.threadLocalInt(images.size());

            BufferedImage image0 = images.get(i0);
            BufferedImage image1 = images.get(i1);



            response0.setIcon(new ImageIcon(image0.getScaledInstance(response0.getWidth(), response0.getHeight(), Image.SCALE_SMOOTH)));
            response1.setIcon(new ImageIcon(image1.getScaledInstance(response0.getWidth(), response0.getHeight(), Image.SCALE_SMOOTH)));

            JLabel question = panel.getQuestionPanel().getQuestion();

            String questionString = questions.get(RandomUtil.threadLocalInt(questions.size()));

            Question questionObj = new Question(questionString, names.get(10), names.get(i1));

            question.setIcon(new TextIcon(question, questionString));

            try {
                status = true; // We're waiting now.
                option = Response.NONE;
                response = null;
                wait(5000);
                status = false; // Done waiting.
                System.out.println("Options: " + option);
                if (option != Response.NONE) {
                    panel.getQuestionPanel().setHighlighted(true);
                    wait();
                    System.out.println(response);
                }
                HeuristicsEntry entry = new HeuristicsEntry(response, option, questionObj);
                data.add(entry);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String json = new GsonBuilder().setPrettyPrinting().create().toJsonTree(data).toString();
        File file = new File("./out", System.currentTimeMillis() + ".json");
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream os = new FileOutputStream(file)) {
            IOUtils.copy(new StringReader(json), os, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
