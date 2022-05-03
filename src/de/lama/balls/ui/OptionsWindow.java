package de.lama.balls.ui;

import de.lama.balls.Configuration;

import javax.swing.*;

public class OptionsWindow extends JFrame {

    public OptionsWindow(Configuration configuration) {
        super("Options");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        this.setContentPane(new OptionsMenu(configuration).getPanel1());
        this.pack();
    }
}