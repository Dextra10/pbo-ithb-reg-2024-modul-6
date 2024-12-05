package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.ActionController;
import controller.DatabaseController;
import model.AgamaIndonesia;
import model.DataPenduduk;
import model.GolonganDarah;
import model.JenisKelamin;
import model.JenisPekerjaan;
import model.Kewarganegaraan;
import model.StatusPerkawinan;

public class InputDataScreen {
    private JFrame inputFrame;
    private JButton submitButton;
    private JButton resetButton;
    private JButton updateButton;
    private JButton deleteButton;

    private File fotoSelectedFile;
    private File tandaTanganSelectedFile;

    public InputDataScreen() {
        initInputDataScreen(null);
    }

    public InputDataScreen(DataPenduduk dataPenduduk) {   
        initInputDataScreen(dataPenduduk);
    }

    private void initInputDataScreen(DataPenduduk dataPenduduk) {

        // FRAME
        
        inputFrame = new JFrame();
        CustomBackground customBackground = new CustomBackground("img/ktp-mono.jpg");
        new CustomBaseFrame(inputFrame, dataPenduduk == null ? "REGISTRASI" : "PERUBAHAN", customBackground);
    
        // NIK

        JTextField nikTextField = new JTextField();
        CustomNikFont customNikFont = new CustomNikFont("font/ocraextended.ttf");
        nikTextField.setFont(customNikFont.getBoldFont((float) 28));
        nikTextField.setBounds(230, 130, 400, 40);
        customBackground.add(nikTextField);

        // NAMA

        JTextField namaTextField = createTextField(0, 'F');
        customBackground.add(namaTextField);

        // TEMPAT LAHIR

        JTextField tempatLahirTextField = createTextField(1, 'L');
        customBackground.add(tempatLahirTextField);
        
        // TANGGAL LAHIR

        JDatePickerImpl tanggalLahirDatePicker = createDatePicker(1, 'R');
        customBackground.add(tanggalLahirDatePicker);

        // JENIS KELAMIN

        JRadioButton priaRadioButton = createRadioButton(2, 0, JenisKelamin.PRIA.toString());
        JRadioButton wanitaRadioButton = createRadioButton(2, 1, JenisKelamin.WANITA.toString());

        customBackground.add(priaRadioButton);
        customBackground.add(wanitaRadioButton);

        ButtonGroup jenisKelaminButtonGroup = new ButtonGroup();
        jenisKelaminButtonGroup.add(priaRadioButton);
        jenisKelaminButtonGroup.add(wanitaRadioButton);

        // GOLONGAN DARAH

        JComboBox<String> golonganDarahComboBox = createListComboBox(2, 310, GolonganDarah.class);
        customBackground.add(golonganDarahComboBox);

        // ALAMAT

        JTextField alamatTextField = createTextField(3, 'F');
        customBackground.add(alamatTextField);

        // RT/RW

        JTextField rtTextField = createTextField(4, 'L');
        JTextField rwTextField = createTextField(4, 'R');

        customBackground.add(rtTextField);
        customBackground.add(rwTextField);

        // KEL/DES

        JTextField kelDesTextField = createTextField(5, 'F');
        customBackground.add(kelDesTextField);

        // KECAMATAN

        JTextField kecamatanTextField = createTextField(6, 'F');
        customBackground.add(kecamatanTextField);

        // AGAMA

        JComboBox<String> agamaComboBox = createListComboBox(7, 0, AgamaIndonesia.class);
        customBackground.add(agamaComboBox);

        // STATUS PERKAWINAN

        JComboBox<String> statusPerkawinanComboBox = createListComboBox(8, 0, StatusPerkawinan.class);
        customBackground.add(statusPerkawinanComboBox);

        // PEKERJAAN

        JTextField pekerjaanTextField = createTextField(9, 'F');
        pekerjaanTextField.setEditable(false);
        customBackground.add(pekerjaanTextField);

        List<JCheckBox> pekerjaanListCheckBox = createListCheckBox(JenisPekerjaan.getOpsi());
        JPanel pekerjaanListCheckBoxPanel = createListCheckBoxPanel(pekerjaanListCheckBox);
        JPopupMenu pekerjaanPanelPopupMenu = createPanelPopupMenu(pekerjaanListCheckBoxPanel, pekerjaanTextField.getWidth() - 10);

        pekerjaanTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pekerjaanPanelPopupMenu.show(pekerjaanTextField, 6, pekerjaanTextField.getHeight() + 2);
            }
        });

        for (JCheckBox checkBox : pekerjaanListCheckBox) {
            checkBox.addItemListener(e -> updateTextField(pekerjaanTextField, pekerjaanListCheckBox));
        }

        JCheckBox pengangguranCheckBox = pekerjaanListCheckBox.get(JenisPekerjaan.indexOf("Pengangguran"));
        pengangguranCheckBox.addItemListener(e -> {
            boolean selected = pengangguranCheckBox.isSelected();
            for (JCheckBox pekerjaanCheckBox : pekerjaanListCheckBox) {
                if (pekerjaanCheckBox != pengangguranCheckBox) {
                    pekerjaanCheckBox.setEnabled(!selected);
                    if (selected) {
                         pekerjaanCheckBox.setSelected(false);
                    }
                }
            }
            updateTextField(pekerjaanTextField, pekerjaanListCheckBox);
        });
        
        // KEWARGANEGARAAN

        JRadioButton wniRadioButton = createRadioButton(10, 0, Kewarganegaraan.WNI.toString());
        JRadioButton wnaRadioButton = createRadioButton(10, 1, Kewarganegaraan.WNA.toString());

        customBackground.add(wniRadioButton);
        customBackground.add(wnaRadioButton);

        ButtonGroup kewarganegaraanButtonGroup = new ButtonGroup();
        kewarganegaraanButtonGroup.add(wniRadioButton);
        kewarganegaraanButtonGroup.add(wnaRadioButton);

        JTextField negaraTextField = createTextField(10, 'R');
        negaraTextField.setVisible(false);
        customBackground.add(negaraTextField);

        wnaRadioButton.addItemListener(e -> {
            boolean selected = wnaRadioButton.isSelected();
            if (selected) {
                negaraTextField.setVisible(true);
            } else {
                negaraTextField.setVisible(false);
            }
        });

        // BERLAKU HINGGA

        JDatePickerImpl berlakuHinggaDatePicker = createDatePicker(11, 'F');
        customBackground.add(berlakuHinggaDatePicker);

        // FOTO

        JLabel fotoLabel = createImageLabel(640, 160, 215, 250);
        customBackground.add(fotoLabel);

        JButton fotoButton = createImageButton(fotoLabel, "Upload Foto");
        new CustomButtonHover(fotoButton, 'M', 'H');
        customBackground.add(fotoButton);

        fotoButton.addActionListener(e -> {
            fotoSelectedFile = loadImageFile(fotoLabel, "Pilih Foto");
        });

        customBackground.setComponentZOrder(fotoButton, 0);
        customBackground.setComponentZOrder(fotoLabel, 1);

        // KOTA PEMBUATAN

        JTextField kotaPembuatanTextField = createTextField(0, 'F');
        

        int tempHeight = 25, tempWidth = 150;
        int tempX = fotoLabel.getX() + ((fotoLabel.getWidth() - tempWidth) / 2);
        int tempY = fotoLabel.getY() + fotoLabel.getHeight() + 2;
        kotaPembuatanTextField.setBounds(tempX, tempY, tempWidth, tempHeight);
        customBackground.add(kotaPembuatanTextField);

        // TANGGAL PEMBUATAN

        JDatePickerImpl tanggalPembuatanDatePicker = createDatePicker(11, 'F');

        tempX += 3; tempWidth -= 6; tempHeight -= 2; tempY += tempHeight + 3;
        tanggalPembuatanDatePicker.setBounds(tempX, tempY, tempWidth, tempHeight);
        customBackground.add(tanggalPembuatanDatePicker);

        // TANDA TANGAN

        tempY += tempHeight + 3; tempHeight *= 3;
        JLabel tandaTanganLabel = createImageLabel(tempX, tempY, tempWidth, tempHeight);
        customBackground.add(tandaTanganLabel);

        JButton tandaTanganButton = createImageButton(tandaTanganLabel, "Upload TTD");
        new CustomButtonHover(tandaTanganButton, 'M', 'H');
        customBackground.add(tandaTanganButton);

        tandaTanganButton.addActionListener(e -> {
            tandaTanganSelectedFile = loadImageFile(tandaTanganLabel, "Pilih TTD");
        });

        customBackground.setComponentZOrder(tandaTanganButton, 0);
        customBackground.setComponentZOrder(tandaTanganLabel, 1);

        int bottom = tempY + tempHeight;

        // RESET-SUBMIT & DELETE-UPDATE

        resetButton = createUserButton(bottom, 'L', "RESET");
        submitButton = createUserButton(bottom, 'R', "SUBMIT");
        deleteButton = createUserButton(bottom, 'L', "DELETE");
        updateButton = createUserButton(bottom, 'R', "UPDATE");

        new CustomButtonHover(resetButton, 'M', 'H');
        new CustomButtonHover(submitButton, 'M', 'H');
        new CustomButtonHover(deleteButton, 'M', 'H');
        new CustomButtonHover(updateButton, 'M', 'H');

        customBackground.add(resetButton);
        customBackground.add(submitButton);
        customBackground.add(updateButton);
        customBackground.add(deleteButton);

        if (dataPenduduk == null) {
            deleteButton.setVisible(false);
            updateButton.setVisible(false);
        } else {
            resetButton.setVisible(false);
            submitButton.setVisible(false);

            nikTextField.setText(dataPenduduk.getNik()); 
            namaTextField.setText(dataPenduduk.getNama());
            tempatLahirTextField.setText(dataPenduduk.getTempatLahir());
            setDatePickerDate(tanggalLahirDatePicker, dataPenduduk.getTanggalLahir());
            setSelectedRadioButton(jenisKelaminButtonGroup, dataPenduduk.getJenisKelamin().toString());
            golonganDarahComboBox.setSelectedItem(dataPenduduk.getGolonganDarah().toString());
            alamatTextField.setText(dataPenduduk.getAlamat());
            rtTextField.setText(dataPenduduk.getRt());
            rwTextField.setText(dataPenduduk.getRw());
            kelDesTextField.setText(dataPenduduk.getKelDes());
            kecamatanTextField.setText(dataPenduduk.getKecamatan());    
            agamaComboBox.setSelectedItem(dataPenduduk.getAgama().toString());
            statusPerkawinanComboBox.setSelectedItem(dataPenduduk.getStatusPerkawinan().toString()); 
            setSelectedListCheckBox(dataPenduduk.getPekerjaan(), pekerjaanListCheckBox, pekerjaanTextField);
            setSelectedRadioButton(kewarganegaraanButtonGroup, dataPenduduk.getKewarganegaraan().toString());
            negaraTextField.setText(dataPenduduk.getNegara());
            setDatePickerDate(berlakuHinggaDatePicker, dataPenduduk.getBerlakuHingga());
            displaySelectedImage(dataPenduduk.getFotoFile(), fotoLabel); 
            kotaPembuatanTextField.setText(dataPenduduk.getKotaPembuatan());
            setDatePickerDate(tanggalPembuatanDatePicker, dataPenduduk.getTanggalPembuatan());
            displaySelectedImage(dataPenduduk.getTandaTanganFile(), tandaTanganLabel);
        }
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActionController.clearInputs(nikTextField, namaTextField, tempatLahirTextField, tanggalLahirDatePicker,
                    jenisKelaminButtonGroup, golonganDarahComboBox, alamatTextField, rtTextField, rwTextField,
                    kelDesTextField, kecamatanTextField, agamaComboBox, statusPerkawinanComboBox, pekerjaanTextField,
                    kewarganegaraanButtonGroup, negaraTextField, berlakuHinggaDatePicker, fotoSelectedFile, fotoLabel,
                    kotaPembuatanTextField, tanggalPembuatanDatePicker, tandaTanganSelectedFile, tandaTanganLabel);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ActionController.isAllFilled(
                    nikTextField, namaTextField, tempatLahirTextField, tanggalLahirDatePicker,
                    jenisKelaminButtonGroup, golonganDarahComboBox, alamatTextField, rtTextField, rwTextField,
                    kelDesTextField, kecamatanTextField, agamaComboBox, statusPerkawinanComboBox, pekerjaanTextField,
                    kewarganegaraanButtonGroup, negaraTextField, berlakuHinggaDatePicker, fotoSelectedFile,
                    kotaPembuatanTextField, tanggalPembuatanDatePicker, tandaTanganSelectedFile)
                ) {
                    String nik = nikTextField.getText();
                    String nama = namaTextField.getText();
                    String tempatLahir = tempatLahirTextField.getText();
                    String tanggalLahir = datePickerToString(tanggalLahirDatePicker);
                    JenisKelamin jenisKelamin = priaRadioButton.isSelected() ? JenisKelamin.PRIA : JenisKelamin.WANITA;
                    GolonganDarah golonganDarah = ActionController.getGolonganDarah(String.valueOf(golonganDarahComboBox.getSelectedItem()));
                    String alamat = alamatTextField.getText();
                    String rt = rtTextField.getText();
                    String rw = rwTextField.getText();
                    String kelDes = kelDesTextField.getText();
                    String kecamatan = kecamatanTextField.getText();
                    AgamaIndonesia agama = ActionController.getAgamaIndonesia(String.valueOf(agamaComboBox.getSelectedItem()));
                    StatusPerkawinan statusPerkawinan = ActionController.getStatusPerkawinan(String.valueOf(statusPerkawinanComboBox.getSelectedItem()));
                    String pekerjaan = pekerjaanTextField.getText();
                    Kewarganegaraan kewarganegaraan = wniRadioButton.isSelected() ? Kewarganegaraan.WNI : Kewarganegaraan.WNA;
                    String negara = negaraTextField.isVisible() ? negaraTextField.getText() : "";
                    String berlakuHingga = datePickerToString(berlakuHinggaDatePicker);
                    String kotaPembuatan = kotaPembuatanTextField.getText();
                    String tanggalPembuatan = datePickerToString(tanggalPembuatanDatePicker);
                    // File fotoSelectedFile dan tandaTanganSelectedFile sudah diambil sebelumnya
        
                    DataPenduduk dataPenduduk = ActionController.createDataPenduduk(
                        nik, nama, tempatLahir, tanggalLahir, jenisKelamin, golonganDarah, alamat, rt, rw, kelDes, 
                        kecamatan, agama, statusPerkawinan, pekerjaan, kewarganegaraan, negara, berlakuHingga, 
                        fotoSelectedFile, kotaPembuatan, tanggalPembuatan, tandaTanganSelectedFile);
                
                    boolean status = DatabaseController.insertDataPenduduk(dataPenduduk);
    
                    if (status) {
                        JOptionPane.showMessageDialog(inputFrame, "Data Anda berhasil diregistrasi! \n\n" + dataPenduduk.toString());
                    } else {
                        JOptionPane.showMessageDialog(inputFrame, "Data Anda gagal diregistrasi!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                
                    inputFrame.dispose();
                    new PrintDataScreen(dataPenduduk);
                } else {
                    JOptionPane.showMessageDialog(inputFrame, "Mohon lengkapi data Anda!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean status = DatabaseController.deleteDataPenduduk(dataPenduduk.getNik());

                if (status) {
                    JOptionPane.showMessageDialog(inputFrame, "Data Anda berhasil dihapus! \n\n" + dataPenduduk.toString());
                } else {
                    JOptionPane.showMessageDialog(inputFrame, "Data Anda gagal dihapus!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                inputFrame.dispose();
                new MainMenuScreen();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = nikTextField.getText();
                String nama = namaTextField.getText();
                String tempatLahir = tempatLahirTextField.getText();
                String tanggalLahir = datePickerToString(tanggalLahirDatePicker);
                JenisKelamin jenisKelamin = priaRadioButton.isSelected() ? JenisKelamin.PRIA : JenisKelamin.WANITA;
                GolonganDarah golonganDarah = ActionController.getGolonganDarah(String.valueOf(golonganDarahComboBox.getSelectedItem()));
                String alamat = alamatTextField.getText();
                String rt = rtTextField.getText();
                String rw = rwTextField.getText();
                String kelDes = kelDesTextField.getText();
                String kecamatan = kecamatanTextField.getText();
                AgamaIndonesia agama = ActionController.getAgamaIndonesia(String.valueOf(agamaComboBox.getSelectedItem()));
                StatusPerkawinan statusPerkawinan = ActionController.getStatusPerkawinan(String.valueOf(statusPerkawinanComboBox.getSelectedItem()));
                String pekerjaan = pekerjaanTextField.getText();
                Kewarganegaraan kewarganegaraan = wniRadioButton.isSelected() ? Kewarganegaraan.WNI : Kewarganegaraan.WNA;
                String negara = negaraTextField.isVisible() ? negaraTextField.getText() : "";
                String berlakuHingga = datePickerToString(berlakuHinggaDatePicker);
                String kotaPembuatan = kotaPembuatanTextField.getText();
                String tanggalPembuatan = datePickerToString(tanggalPembuatanDatePicker);
                // File fotoSelectedFile dan tandaTanganSelectedFile sudah diambil sebelumnya
        
                DataPenduduk dataPenduduk = ActionController.createDataPenduduk(
                    nik, nama, tempatLahir, tanggalLahir, jenisKelamin, golonganDarah, alamat, rt, rw, kelDes, 
                    kecamatan, agama, statusPerkawinan, pekerjaan, kewarganegaraan, negara, berlakuHingga, 
                    fotoSelectedFile, kotaPembuatan, tanggalPembuatan, tandaTanganSelectedFile);
                
                boolean status = DatabaseController.updateDataPenduduk(dataPenduduk);

                if (status) {
                    JOptionPane.showMessageDialog(inputFrame, "Data Anda berhasil diubah! \n\n" + dataPenduduk.toString());
                } else {
                    JOptionPane.showMessageDialog(inputFrame, "Data Anda gagal diubah!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                inputFrame.dispose();
                new PrintDataScreen(dataPenduduk);
            }
        });

        inputFrame.setVisible(true);
    }

    private JTextField createTextField(int row, char position) {
        JTextField textField = new JTextField();
        textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        int x = (position == 'F' || position == 'L') ? 255 : 445;
        int width = (position == 'L' || position == 'R') ? 185 : 375;
        int height = 25, y = 175 + (height * row);
        textField.setBounds(x, y, width, height);
        
        return textField;
    }

    private JDatePickerImpl createDatePicker(int row, char position) {
        UtilDateModel dateModel = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        DateFormatter dateFormatter = new DateFormatter();
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, dateFormatter);

        int x = (position == 'F') ? 255 : 445;
        int width = (position == 'R') ? 185 : 375;
        int height = 25, y = 175 + (height * row);
        datePicker.setBounds(x + 3, y + 1, width - 6, height - 2);
        datePicker.setBackground(Color.WHITE);
        
        return datePicker;
    }

    private void setDatePickerDate(JDatePickerImpl datePicker, String dateString) {
        DateFormatter dateFormatter = new DateFormatter();
        try {
            Date date = (Date) dateFormatter.stringToValue(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePicker.getModel().setSelected(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    

    private String datePickerToString(JDatePickerImpl datePicker) {
        if (datePicker == null) {
            return "";
        }

        Date selectedDate = (Date) datePicker.getModel().getValue();
        if (selectedDate == null) {
            return "";
        }
        
        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDate.format(formatter);
    }
    

    private JRadioButton createRadioButton(int row, int col, String title) {
        JRadioButton radioButton = new JRadioButton(title);
        radioButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        
        int width = 90, x = 255 + (col * (width + 5));
        int height = 25, y = 175 + (height * row);
        radioButton.setBounds(x, y, width, height);

        return radioButton;
    }

    private void setSelectedRadioButton(ButtonGroup buttonGroup, String selected) {
        for (java.util.Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.getText().equals(selected)) {
                button.setSelected(true);
            }
        }
    }

    private JComboBox<String> createListComboBox(int row, int left, Class<? extends Enum<?>> enumClass) {
        JComboBox<String> listComboBox = new JComboBox<String>();
        listComboBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        int x = 255 + left, width = 375 - left;
        int height = 25, y = 175 + (height * row);
        listComboBox.setBounds(x - 2, y + 2, width + 5, height);

        listComboBox.addItem("   "); 

        for (Enum<?> e : enumClass.getEnumConstants()) {
            listComboBox.addItem(e.toString()); 
        }

        listComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) listComboBox.getSelectedItem();
                if ("   ".equals(selectedItem.trim())) {
                    listComboBox.setSelectedIndex(-1); 
                }
            }
        });

        return listComboBox;
    }

    private List<JCheckBox> createListCheckBox(String[] arrStr) {
        List<JCheckBox> listCheckBox = new ArrayList<JCheckBox>();
    
        for (String str : arrStr) {
            JCheckBox checkBox = new JCheckBox(str);
            checkBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            listCheckBox.add(checkBox);
        }
    
        return listCheckBox;
    }
    
    private JPanel createListCheckBoxPanel(List<JCheckBox> listCheckBox) {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
    
        for (JCheckBox checkBox : listCheckBox) {
            checkBoxPanel.add(checkBox);
        }
    
        return checkBoxPanel;
    }
    
    private JPopupMenu createPanelPopupMenu(JPanel panel, int width) {
        JPopupMenu panelPopupMenu = new JPopupMenu();
        panelPopupMenu.setLayout(new BorderLayout());
        panelPopupMenu.add(new JScrollPane(panel), BorderLayout.CENTER);
        panelPopupMenu.setPreferredSize(new Dimension(width, 145));
        return panelPopupMenu;
    }
    
    private void updateTextField(JTextField textField, List<JCheckBox> listCheckBox) {
        StringBuilder selectedText = new StringBuilder();
        for (JCheckBox checkBox : listCheckBox) {
            if (checkBox.isSelected()) {
                if (selectedText.length() > 0) {
                    selectedText.append(", ");
                }
                selectedText.append(checkBox.getText());
            }
        }
        textField.setText(selectedText.toString());
    }

    private void setSelectedListCheckBox(String multiSelected, List<JCheckBox> listCheckBox, JTextField textField) {
        String[] arrSelected = multiSelected.split(", ");
        for (JCheckBox checkBox : listCheckBox) {
            for (String selected : arrSelected) {
                if (checkBox.getText().equals(selected)) {
                    checkBox.setSelected(true);
                    break;
                }
            }
        }
        updateTextField(textField, listCheckBox);
    }

    private File loadImageFile(JLabel label, String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
        int result = fileChooser.showOpenDialog(inputFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            return selectedFile;
        }
        return null;
    }

    private void displaySelectedImage(File selectedFile, JLabel imageLabel) {
        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
        Image image = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }

    private JLabel createImageLabel(int x, int y, int width, int height) {
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(x, y, width, height);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.WHITE);
        return imageLabel;
    }

    private JButton createImageButton(JLabel label, String title) {
        JButton imageButton = new JButton(title);

        int height = 25;
        int width = imageButton.getPreferredSize().width;
        int x = label.getX() + (label.getWidth() - width) / 2;
        int y = label.getY() + label.getHeight() - height - 5;
        imageButton.setBounds(x, y, width, height);

        return imageButton;
    }

    private JButton createUserButton (int bottom, char position, String title) {
        JButton userButton = new JButton(title);
        userButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        int x = (position == 'L') ? 255 : 445;
        int width = 185, height = 25, y = bottom - height;
        userButton.setBounds(x + 2, y, width - 6, height);
        
        return userButton;
    }
}
