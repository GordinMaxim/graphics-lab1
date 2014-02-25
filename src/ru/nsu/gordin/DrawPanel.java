package ru.nsu.gordin;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_3BYTE_BGR);

        int x0 = 150, y0 = 150;
        int radius = 50;
        int x = 0, y = 0;
        for(double angle = 0; angle < 2 * Math.PI; angle += Math.toRadians(1))
        {
            x = (int)(radius * Math.cos(angle)) + x0;
            y = (int)(radius * Math.sin(angle)) + y0;
            image.setRGB(x, y, Color.WHITE.getRGB());
        }
        g2.drawImage(image, 0, 0, null);
    }
}
