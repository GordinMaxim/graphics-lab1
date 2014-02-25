package ru.nsu.gordin;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class DrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        double x0 = 200, y0 = 100;
        double radius = 50;
        double x = 0, y = 0;
        for(double angle = 0; angle < 2 * Math.PI; angle += Math.toRadians(1))
        {
            x = radius * Math.cos(angle);
            y = radius * Math.sin(angle);
            g2.draw(new Line2D.Double(x0 + x, y0 + y, x0 + x, y0 + y));
        }
    }
}
