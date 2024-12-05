package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ActionController;
import model.DataPenduduk;

public class MainMenuScreen {
    private DataPenduduk dataPenduduk;
    
    public MainMenuScreen() {
        initComponents();
    }
    
    private void initComponents() {
        ActionController.clearDataPenduduk(dataPenduduk);
        
        JFrame mainFrame = new JFrame("MAIN MENU");

        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(300, 150);
        mainFrame.setLocationRelativeTo(null); 
        
        JButton registerButton = new JButton("REGISTRASI");
        JButton findButton = new JButton("CARI DATA");
        JButton exitButton = new JButton("KELUAR");
        
        registerButton.addActionListener(e -> {
            mainFrame.dispose();
            new InputDataScreen();
        });

        findButton.addActionListener(e -> {
            mainFrame.dispose();
            new FindDataScreen();
        });

        exitButton.addActionListener(e -> {
            mainFrame.dispose();
        });
        
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(registerButton);
        mainPanel.add(findButton);
        mainPanel.add(exitButton);
        
        mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
