package me.shsmith0206.heuristics.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class JarUtil {
    public static <E> List<E> getResources(Predicate<String> filter, Function<InputStream, E> capture) throws IOException, URISyntaxException {
        List<E> results = new ArrayList<>();

        System.out.println("Scanning JAR...");
        JarFile jarFile;
        try {
            jarFile = getJarFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (filter.test(entry.getName())) {
                try (InputStream stream = jarFile.getInputStream(entry)) {
                    results.add(capture.apply(stream));
                }
            }
        }
        System.out.println("Scan complete. Found " + results.size() + " results");
        return results;
    }

    public static final Predicate<String> JPG_FILTER = name -> name.endsWith(".jpg");

    public static final Function<InputStream, BufferedImage> IMAGE_FUNCTION = inputStream -> {
        try {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public static JarFile getJarFile() throws URISyntaxException, IOException {
        return new JarFile(getFile());
    }

    public static File getFile() throws URISyntaxException {
        return new File(getJarURL().toURI());
    }

    public static URL getJarURL() {
        return JarUtil.class.getProtectionDomain().getCodeSource().getLocation();
    }
}
