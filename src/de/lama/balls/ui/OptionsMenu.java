package de.lama.balls.ui;

import de.lama.balls.BallLauncher;
import de.lama.balls.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class OptionsMenu {

    private enum OptionTransformer {

        CONNECTION_DISTANCE(100, Configuration::getConnectionDistance, Configuration::setConnectionDistance),
        BALL_SPEED(10000, Configuration::getBallSpeed, Configuration::setBallSpeed),
        BALL_SIZE(2000, Configuration::getBallRadius, Configuration::setBallRadius);

        private final float scale;
        private final Function<Configuration, Float> getter;
        private final BiConsumer<Configuration, Float> configAction;

        OptionTransformer(float scale, Function<Configuration, Float> getter, BiConsumer<Configuration, Float> configAction) {
            this.scale = scale;
            this.getter = getter;
            this.configAction = configAction;
        }

        public void doStuff(JSlider slider, Configuration configuration) {
            slider.setValue((int) (this.getter.apply(configuration) * this.scale));
            slider.addChangeListener(e -> this.configAction.accept(configuration, slider.getValue() / this.scale));
        }
    }

    private final Configuration configuration;

    private JSlider sliderConnectionDistance;
    private JPanel panel1;
    private JSlider sliderRenderQuality;
    private JLabel lblBallAmount;
    private JSlider sliderBallAmount;
    private JSlider sliderBallSpeed;
    private JSlider sliderBallRadius;
    private JLabel lblConnectionDistance;
    private JLabel LblBallSpeed;
    private JLabel LblBallRadius;
    private JButton btnBackgroundColor;
    private JLabel lblBackgroundColor;
    private JButton btnBallColor;
    private JLabel lblBallColor;
    private JLabel lblRenderQuality;
    private JButton btnSave;

    public OptionsMenu(Configuration configuration, BallLauncher launcher) {
        this.configuration = configuration;

        this.sliderBallAmount.setValue(configuration.getBallAmount());
        this.sliderBallAmount.addChangeListener(e -> this.configuration.setBallAmount(this.sliderBallAmount.getValue()));
        btnBackgroundColor.addActionListener(e -> this.openColorPicker(this.configuration.getBackground(), configuration::setBackground));
        btnBallColor.addActionListener(e -> this.openColorPicker(this.configuration.getCircleColor(), configuration::setCircleColor));

        OptionTransformer.CONNECTION_DISTANCE.doStuff(this.sliderConnectionDistance, configuration);
        OptionTransformer.BALL_SPEED.doStuff(this.sliderBallSpeed, configuration);
        OptionTransformer.BALL_SIZE.doStuff(this.sliderBallRadius, configuration);

        btnSave.addActionListener(e -> launcher.saveConfig());

        this.sliderRenderQuality.setValue(configuration.getRenderQuality().toIndex());
        this.sliderRenderQuality.addChangeListener(e -> this.configuration.setRenderQuality(RenderQuality.fromIndex(this.sliderRenderQuality.getValue() - 1)));
    }

    private void openColorPicker(Color color, Consumer<Color> setter) {
        JFrame frame = new JFrame("Pick color");
        JColorChooser colorChooser = new JColorChooser(color);
        colorChooser.getSelectionModel().addChangeListener(e -> setter.accept(colorChooser.getColor()));
        frame.getContentPane().add(colorChooser);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanel1() {
        return this.panel1;
    }
}
