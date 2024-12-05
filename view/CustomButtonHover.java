package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomButtonHover {
    public CustomButtonHover(JButton button, char exitChar, char enterChar) {
        addButtonHover(button, exitChar, enterChar);
    }

    private void addButtonHover(JButton button, char exitChar, char enterChar) {
        button.setBackground(getColorByCode(exitChar));
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(getColorByCode(enterChar));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(getColorByCode(exitChar));
            }
        });
    }

    private Color getColorByCode(char colorChar) {
        switch (colorChar) {
            case 'M':
                return new Color(200, 200, 200, 255);
            case 'C':
                // return new Color(140, 198, 239, 255);
                return new Color(120, 180, 222, 255);
            case 'H':
                return new Color(251, 152, 152, 255);
        }
        return null;
    }
}
