package de.lama.balls;

import de.lama.balls.surface.RectangularSurface;
import de.lama.balls.surface.Surface;
import de.lama.balls.ui.OptionsWindow;
import de.lama.balls.ui.RenderedWindow;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BallLauncher {

    private Configuration configuration;

    BallLauncher() {
        System.setProperty("sun.java2d.opengl", "True");

        try {
            this.configuration = this.readConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Surface surface = new RectangularSurface(this.configuration);
        RenderedWindow window = new RenderedWindow("Deez Nuts", this.configuration);
        OptionsWindow optionsWindow = new OptionsWindow(this.configuration, this);

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        surface.start(window, pool);

        pool.execute(() -> {
            window.start(surface);
            window.setVisible(true);
        });

        pool.execute(() -> optionsWindow.setVisible(true));
    }

    private Configuration readConfig() throws FileNotFoundException {
        if (!new File("./config.xml").exists())
            this.saveConfig();
        XMLDecoder decoder = new XMLDecoder(new FileInputStream("./config.xml"));
        Object config = decoder.readObject();
        if (config == null) this.saveConfig();
        decoder.close();
        return config == null ? null : (Configuration) config;
    }

    public void saveConfig() {
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new FileOutputStream("./config.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        encoder.writeObject(this.configuration == null ? new Configuration(true) : this.configuration);
        encoder.flush();
        encoder.close();
    }
}