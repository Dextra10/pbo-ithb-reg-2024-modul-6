package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomBaseFrame {
    private JLabel titleLabel;

    public CustomBaseFrame(JFrame frame, String title, CustomBackground customBackground) {
        initBaseFrameComponents(frame, title, customBackground);
    }

    private void initBaseFrameComponents(JFrame frame, String title, CustomBackground customBackground) {
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setTitle(title);
        frame.setContentPane(customBackground);
        customBackground.setLayout(null);

        titleLabel = new JLabel("REPUBLIK HARAPAN BANGSA");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));

        int titleLabelWidth = titleLabel.getPreferredSize().width;
        int titleLabelHeight = titleLabel.getPreferredSize().height;
        int titleLabelX = (frame.getWidth() - titleLabelWidth) / 2;

        titleLabel.setBounds(titleLabelX, 50, titleLabelWidth, titleLabelHeight);
        customBackground.add(titleLabel);
    }

}
