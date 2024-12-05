package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CustomBackground extends JPanel {
    private ImageIcon backgroundImage;

    public CustomBackground(String backgroundPath) {
        backgroundImage = new ImageIcon(backgroundPath);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
