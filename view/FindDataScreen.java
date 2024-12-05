package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DatabaseController;

public class FindDataScreen {
    public FindDataScreen() {
        initFindDataScree();
    }

    private void initFindDataScree() {
        JFrame findFrame = new JFrame("MAIN MENU");

        findFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        findFrame.setSize(300, 150);
        findFrame.setLocationRelativeTo(null); 

        JLabel nikLabel = new JLabel("MASUKKAN NIK ANDA:");
        JTextField nikTextField = new JTextField(20);
        JButton searchButton = new JButton("CARI");

        searchButton.addActionListener(e -> {
            if (nikTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(findFrame, "Mohon masukkan NIK Anda!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                findFrame.dispose();

                if (DatabaseController.getDataPenduduk(nikTextField.getText()) == null) {
                    JOptionPane.showMessageDialog(findFrame, "Data Anda tidak ditemukan!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    new MainMenuScreen();
                } else {
                    new InputDataScreen(DatabaseController.getDataPenduduk(nikTextField.getText()));
                }
            }
        });

        JPanel findPanel = new JPanel(new GridLayout(3, 1, 10, 5));
        findPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        findPanel.add(nikLabel);
        findPanel.add(nikTextField);
        findPanel.add(searchButton);

        findFrame.getContentPane().add(findPanel, BorderLayout.CENTER);
        findFrame.setVisible(true);
    }
}
