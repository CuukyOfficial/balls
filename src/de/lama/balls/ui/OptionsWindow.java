package de.lama.balls.ui;

import de.lama.balls.BallLauncher;
import de.lama.balls.Configuration;

import javax.swing.*;

public class OptionsWindow extends JFrame {

    public OptionsWindow(Configuration configuration, BallLauncher launcher) {
        super("Options");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        this.setLocation(800, 0);
        this.setContentPane(new OptionsMenu(configuration, launcher).getPanel1());
        this.pack();
    }
}