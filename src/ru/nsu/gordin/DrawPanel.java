package ru.nsu.gordin;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JPanel settingPanel = new JPanel(new BorderLayout());
        settingPanel.add(slider, BorderLayout.CENTER);

        JButton button = new JButton("Hide");
        button.setPreferredSize(new Dimension(100, 25));
        button.addActionListener(canvasPanel);
        settingPanel.add(button, BorderLayout.EAST);
        add(settingPanel, BorderLayout.NORTH);
    }

    protected class CanvasPanel extends JPanel implements ChangeListener, ActionListener {

        private boolean show = true;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(show) {
                drawCircle(g);
            }
        }

        public void drawCircle(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            int height = getHeight();
            int width = getWidth();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            int x0 = width/2, y0 = height/2;
            int x = 0, y = radius;
            int delta = 1 - 2 * radius;
            int error = 0;
            while(0 <= y) {
                image.setRGB(x0 + x, y0 + y, Color.WHITE.getRGB());
                image.setRGB(x0 - x, y0 + y, Color.WHITE.getRGB());
                image.setRGB(x0 + x, y0 - y, Color.WHITE.getRGB());
                image.setRGB(x0 - x, y0 - y, Color.WHITE.getRGB());
                error = 2 * (delta + y) - 1;
                if(delta < 0 && error <= 0) {
                    x++;
                    delta += 2 * x + 1;
                    continue;
                }
                error = 2 * (delta - x) - 1;
                if(delta > 0 && error > 0) {
                    y--;
                    delta += 1 - 2 * y;
                    continue;
                }
                x++;
                delta += 2 * (x - y);
                y--;
            }
            
            g2.drawImage(image, 0, 0, null);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            radius = slider.getValue();
            repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if(show) {
                button.setText("Show");
            }
            else {
                button.setText("Hide");
            }
            show = !show;
            repaint();
        }
    }
}
