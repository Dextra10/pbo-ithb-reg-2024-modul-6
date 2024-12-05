package controller;

import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import model.AgamaIndonesia;
import model.DataPenduduk;
import model.GolonganDarah;
import model.JenisKelamin;
import model.Kewarganegaraan;
import model.StatusPerkawinan;

public class ActionController {
    public static boolean isAllFilled(
        JTextField nikTextField, 
        JTextField namaTextField, 
        JTextField tempatLahirTextField, 
        JDatePickerImpl tanggalLahirDatePicker, 
        ButtonGroup jenisKelaminButtonGroup, 
        JComboBox<String> golonganDarahComboBox, 
        JTextField alamatTextField, 
        JTextField rtTextField, 
        JTextField rwTextField, 
        JTextField kelDesTextField, 
        JTextField kecamatanTextField, 
        JComboBox<String> agamaComboBox, 
        JComboBox<String> statusPerkawinanComboBox, 
        JTextField pekerjaanTextField, 
        ButtonGroup kewarganegaraanButtonGroup, 
        JTextField negaraTextField, 
        JDatePickerImpl berlakuHinggaDatePicker, 
        File fotoSelectedFile, 
        JTextField kotaPembuatanTextField, 
        JDatePickerImpl tanggalPembuatanDatePicker, 
        File tandaTanganSelectedFile
    ) {
        if (
            nikTextField.getText().trim().isEmpty() 
            || namaTextField.getText().trim().isEmpty() 
            || tempatLahirTextField.getText().trim().isEmpty() 
            || tanggalLahirDatePicker.getModel().getValue() == null 
            || jenisKelaminButtonGroup.getSelection() == null 
            || golonganDarahComboBox.getSelectedIndex() == -1 
            || alamatTextField.getText().trim().isEmpty() 
            || rtTextField.getText().trim().isEmpty() 
            || rwTextField.getText().trim().isEmpty() 
            || kelDesTextField.getText().trim().isEmpty() 
            || kecamatanTextField.getText().trim().isEmpty() 
            || agamaComboBox.getSelectedIndex() == -1 
            || statusPerkawinanComboBox.getSelectedIndex() == -1 
            || pekerjaanTextField.getText().trim().isEmpty() 
            || kewarganegaraanButtonGroup.getSelection() == null 
            || (kewarganegaraanButtonGroup.getSelection() != null 
                && kewarganegaraanButtonGroup.getSelection().getActionCommand() != null 
                && kewarganegaraanButtonGroup.getSelection().getActionCommand().equals("WNA")
                && negaraTextField.getText().trim().isEmpty()) 
            || berlakuHinggaDatePicker.getModel().getValue() == null 
            || fotoSelectedFile == null 
            || kotaPembuatanTextField.getText().trim().isEmpty() 
            || tanggalPembuatanDatePicker.getModel().getValue() == null 
            || tandaTanganSelectedFile == null
        ) {
            return false;
        } else {
            return true; 
        }
    }

    public static void clearInputs(
        JTextField nikTextField, 
        JTextField namaTextField, 
        JTextField tempatLahirTextField, 
        JDatePickerImpl tanggalLahirDatePicker, 
        ButtonGroup jenisKelaminButtonGroup, 
        JComboBox<String> golonganDarahComboBox, 
        JTextField alamatTextField, 
        JTextField rtTextField, 
        JTextField rwTextField, 
        JTextField kelDesTextField, 
        JTextField kecamatanTextField, 
        JComboBox<String> agamaComboBox, 
        JComboBox<String> statusPerkawinanComboBox, 
        JTextField pekerjaanTextField, 
        ButtonGroup kewarganegaraanButtonGroup, 
        JTextField negaraTextField, 
        JDatePickerImpl berlakuHinggaDatePicker, 
        File fotoSelectedFile, 
        JLabel fotoLabel,
        JTextField kotaPembuatanTextField, 
        JDatePickerImpl tanggalPembuatanDatePicker, 
        File tandaTanganSelectedFile,
        JLabel tandaTanganLabel
    ) {
        nikTextField.setText("");
        namaTextField.setText("");
        tempatLahirTextField.setText("");
        tanggalLahirDatePicker.getModel().setValue(null);
        jenisKelaminButtonGroup.clearSelection();
        golonganDarahComboBox.setSelectedItem("   ");
        alamatTextField.setText("");
        rtTextField.setText("");
        rwTextField.setText("");
        kelDesTextField.setText("");
        kecamatanTextField.setText("");
        agamaComboBox.setSelectedItem("   ");
        statusPerkawinanComboBox.setSelectedItem("   ");
        pekerjaanTextField.setText("");
        kewarganegaraanButtonGroup.clearSelection();
        negaraTextField.setText("");
        berlakuHinggaDatePicker.getModel().setValue(null);
        fotoSelectedFile = null;
        fotoLabel.setIcon(null);
        kotaPembuatanTextField.setText("");
        tanggalPembuatanDatePicker.getModel().setValue(null);
        tandaTanganSelectedFile = null;
        tandaTanganLabel.setIcon(null);
    }

    public static DataPenduduk createDataPenduduk(
        String nik, String nama, String tempatLahir, String tanggalLahir, 
        JenisKelamin jenisKelamin, GolonganDarah golonganDarah, String alamat, 
        String rt, String rw, String kelDes, String kecamatan, 
        AgamaIndonesia agama, StatusPerkawinan statusPerkawinan, String pekerjaan, 
        Kewarganegaraan kewarganegaraan, String negara, String berlakuHingga, 
        File fotoFile, String kotaPembuatan, String tanggalPembuatan, File tandaTanganFile
    ) {
        DataPenduduk dataPenduduk = new DataPenduduk(
            nik, nama, tempatLahir, tanggalLahir, jenisKelamin, golonganDarah, 
            alamat, rt, rw, kelDes, kecamatan, agama, statusPerkawinan, 
            pekerjaan, kewarganegaraan, negara, berlakuHingga, fotoFile, 
            kotaPembuatan, tanggalPembuatan, tandaTanganFile
        );

        return dataPenduduk;
    }

    public static void clearDataPenduduk(DataPenduduk dataPenduduk) {
        dataPenduduk = new DataPenduduk(
            null, null, null, null, null, null, 
            null, null, null, null, null, null, 
            null, null, null, null, null, null, 
            null, null, null
        );
    }

    public static GolonganDarah getGolonganDarah(String golonganDarah) {
        for (GolonganDarah value : GolonganDarah.values()) {
            if (value.toString().equals(golonganDarah)) {
                return value;
            }
        }
        return null;
    }

    public static AgamaIndonesia getAgamaIndonesia(String agama) {
        for (AgamaIndonesia value : AgamaIndonesia.values()) {
            if (value.toString().equals(agama)) {
                return value;
            }
        }
        return null;
    }

    public static StatusPerkawinan getStatusPerkawinan(String statusPerkawinan) {
        for (StatusPerkawinan value : StatusPerkawinan.values()) {
            if (value.toString().equals(statusPerkawinan)) {
                return value;
            }
        }
        return null;
    }
}


