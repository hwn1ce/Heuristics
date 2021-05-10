package me.shsmith0206.heuristics;

import me.shsmith0206.heuristics.swing.HeuristicsPanel;
import me.shsmith0206.heuristics.swing.TextIcon;
import me.shsmith0206.heuristics.util.JarUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ResponseManager {
    public void begin(HeuristicsPanel panel, int rounds) {
        List<BufferedImage> imageList;
        try {
            imageList = JarUtil.getResources(JarUtil.JPG_FILTER.and(path -> path.startsWith("images")), JarUtil.IMAGE_FUNCTION);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < rounds; i++) {
            System.out.println(i);
            JLabel response0 = panel.getResponsePanel().getResponse0();
            JLabel response1 = panel.getResponsePanel().getResponse1();

            BufferedImage image0 = imageList.get(ThreadLocalRandom.current().nextInt(imageList.size()));
            BufferedImage image1 = imageList.get(ThreadLocalRandom.current().nextInt(imageList.size()));
            response0.setIcon(new ImageIcon(image0.getScaledInstance(response0.getWidth(), response0.getHeight(), Image.SCALE_SMOOTH)));
            response1.setIcon(new ImageIcon(image1.getScaledInstance(response0.getWidth(), response0.getHeight(), Image.SCALE_SMOOTH)));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
