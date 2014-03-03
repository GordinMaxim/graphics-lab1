package ru.nsu.gordin;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {

    static final int RADIUS_MIN = 0;
    static final int RADIUS_MAX = 200;
    static final int RADIUS_INIT = 10;
    private int radius;
    private CanvasPanel canvasPanel;

    public DrawPanel() {
        super(new BorderLayout());
        radius = RADIUS_INIT;

        canvasPanel = new CanvasPanel();
        add(canvasPanel, BorderLayout.CENTER);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, RADIUS_MIN, RADIUS_MAX, RADIUS_INIT);
        slider.addChangeListener(canvasPanel);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider, BorderLayout.NORTH);
    }

    protected class CanvasPanel extends JPanel implements ChangeListener {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int height = getSize().height;
            int width = getSize().width;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            int x0 = width/2, y0 = height/2;
            int x, y;
            for(double angle = 0; angle < 2 * Math.PI; angle += Math.toRadians(0.1))
            {
                x = (int)(radius * Math.cos(angle)) + x0;
                y = (int)(radius * Math.sin(angle)) + y0;
                image.setRGB(x, y, Color.WHITE.getRGB());
            }
            g2.drawImage(image, 0, 0, null);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            radius = slider.getValue();
            repaint();
        }
    }
}
