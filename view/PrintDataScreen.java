package view;

import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.DataPenduduk;

public class PrintDataScreen {
    JFrame printFrame;

    JButton mainButton;

    public PrintDataScreen(DataPenduduk dataPenduduk) {
        initPrintDataScreen(dataPenduduk);
    }

    private void initPrintDataScreen(DataPenduduk dataPenduduk) {

        // FRAME

        printFrame = new JFrame();
        CustomBackground customBackground = new CustomBackground("img/ktp-color.jpg");
        new CustomBaseFrame(printFrame, "KARTU TANDA PENDUDUK", customBackground);

        // NIK

        JLabel nikLabel = new JLabel(dataPenduduk.getNik());
        CustomNikFont customNikFont = new CustomNikFont("font/ocraextended.ttf");
        nikLabel.setFont(customNikFont.getBoldFont((float) 28));
        nikLabel.setBounds(230, 130, 400, 40);
        customBackground.add(nikLabel);

        // NAMA

        JLabel namaLabel = createLeftLabel(0, dataPenduduk.getNama().toUpperCase());
        customBackground.add(namaLabel);

        // TEMPAT & TANGGAL LAHIR

        JLabel tempatTanggalLahirLabel = createLeftLabel(1, dataPenduduk.getTempatLahir().toUpperCase() + ", " + dataPenduduk.getTanggalLahir().toString());
        customBackground.add(tempatTanggalLahirLabel);

        // JENIS KELAMIN

        JLabel jenisKelaminLabel = createLeftLabel(2, dataPenduduk.getJenisKelamin().toString().toUpperCase());
        customBackground.add(jenisKelaminLabel);

        // GOLONGAN DARAH

        JLabel golonganDarahLabel = new JLabel(dataPenduduk.getGolonganDarah().toString());
        
        int tempY = jenisKelaminLabel.getY();
        int tempWidth = golonganDarahLabel.getPreferredSize().width;
        golonganDarahLabel.setBounds(570, tempY, tempWidth, 35);

        customBackground.add(golonganDarahLabel);

        // ALAMAT

        JLabel alamatLabel = createLeftLabel(3, dataPenduduk.getAlamat().toUpperCase());
        customBackground.add(alamatLabel);

        // RT & RW

        JLabel rtRWLabel = createLeftLabel(4, dataPenduduk.getRt() + " / " + dataPenduduk.getRw());
        customBackground.add(rtRWLabel);

        // KEL/DES

        JLabel kelDesLabel = createLeftLabel(5, dataPenduduk.getKelDes().toUpperCase());
        customBackground.add(kelDesLabel);

        // KECAMATAN

        JLabel kecamatanLabel = createLeftLabel(6, dataPenduduk.getKecamatan().toUpperCase());
        customBackground.add(kecamatanLabel);

        // AGAMA

        JLabel agamaLabel = createLeftLabel(7, dataPenduduk.getAgama().toString().toUpperCase());
        customBackground.add(agamaLabel);

        // STATUS PERKAWINAN

        JLabel statusPerkawinanLabel = createLeftLabel(8, dataPenduduk.getStatusPerkawinan().toString().toUpperCase());
        customBackground.add(statusPerkawinanLabel);
        
        // PEKERJAAN

        JLabel pekerjaanLabel = createLeftLabel(9, dataPenduduk.getPekerjaan().toUpperCase());
        customBackground.add(pekerjaanLabel);

        // KEWARGANEGARAAN & NEGARA

        String keteranganNegara = (dataPenduduk.getKewarganegaraan().toString().equalsIgnoreCase("WNA")) ? " (" + dataPenduduk.getNegara().toUpperCase() + ") " : ""; 
        JLabel kewarganegaraanNegaraLabel = createLeftLabel(10, dataPenduduk.getKewarganegaraan().toString() + keteranganNegara);
        customBackground.add(kewarganegaraanNegaraLabel);

        // BERLAKU HINGGA

        JLabel berlakuHinggaLabel = createLeftLabel(11, dataPenduduk.getBerlakuHingga());
        customBackground.add(berlakuHinggaLabel);

        // FOTO

        JLabel fotoLabel = createImageLabel(640, 160, 215, 250, dataPenduduk.getFotoFile());
        customBackground.add(fotoLabel);

        // KOTA PEMBUATAN
        
        JLabel kotaPembuatanLabel = createRightLabel(dataPenduduk.getKotaPembuatan().toUpperCase());
        
        int tempHeight = 25;
        tempWidth = kotaPembuatanLabel.getPreferredSize().width;
        int tempX = fotoLabel.getX() + ((fotoLabel.getWidth() - tempWidth) / 2);
        tempY = fotoLabel.getY() + fotoLabel.getHeight() + 2;
        kotaPembuatanLabel.setBounds(tempX, tempY, tempWidth, tempHeight);
        
        customBackground.add(kotaPembuatanLabel);

        // TANGGAL PEMBUATAN

        JLabel tanggalPembuatanLabel = createRightLabel(dataPenduduk.getTanggalPembuatan());

        tempWidth = tanggalPembuatanLabel.getPreferredSize().width;
        tempX = kotaPembuatanLabel.getX() + ((kotaPembuatanLabel.getWidth() - tempWidth) / 2);
        tempY = kotaPembuatanLabel.getY() + tempHeight + 5;
        tanggalPembuatanLabel.setBounds(tempX, tempY, tempWidth, tempHeight);

        customBackground.add(tanggalPembuatanLabel);

        // TANDA TANGAN

        tempY = tanggalPembuatanLabel.getY() + tempHeight + 5;
        JLabel tandaTanganLabel = createImageLabel(675, tempY, 140, 70, dataPenduduk.getTandaTanganFile());
        customBackground.add(tandaTanganLabel);

        // BACK TO MAIN

        tempY = tempY + tandaTanganLabel.getHeight() - 25;
        mainButton = new JButton("BACK TO MAIN MENU");
        mainButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        mainButton.setBounds(257, tempY, 369, 25);
        new CustomButtonHover(mainButton, 'C', 'H');
        customBackground.add(mainButton);

        mainButton.addActionListener(e -> {
            printFrame.dispose();
            new MainMenuScreen();
        });

        printFrame.setVisible(true);
    }

    private JLabel createLeftLabel(int row, String content) {
        JLabel label = new JLabel(content);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        int x = 255, width = label.getPreferredSize().width;
        int height = 25, y = 175 + (height * row);
        label.setBounds(x, y, width, height);

        return label;
    }

    private JLabel createRightLabel(String content) {
        JLabel label = new JLabel(content);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        return label;
    } 

    private JLabel createImageLabel(int x, int y, int width, int height, File content) {
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(x, y, width, height);

        ImageIcon imageIcon = new ImageIcon(content.getAbsolutePath());
        Image iamge = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(iamge));

        return imageLabel;
    }   
}
