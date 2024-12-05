package model;

import java.io.File;

public class DataPenduduk {
    private String nik;
    private String nama;
    private String tempatLahir;
    private String tanggalLahir;
    private JenisKelamin jenisKelamin;
    private GolonganDarah golonganDarah;
    private String alamat;
    private String rt;
    private String rw;
    private String kelDes;
    private String kecamatan;
    private AgamaIndonesia agama;
    private StatusPerkawinan statusPerkawinan;
    private String pekerjaan;
    private Kewarganegaraan kewarganegaraan;
    private String negara;
    private String berlakuHingga;
    private File fotoFile;
    private String kotaPembuatan;
    private String tanggalPembuatan;
    private File tandaTanganFile;

    public DataPenduduk() {
        
    }
    
    public DataPenduduk(
        String nik, String nama, String tempatLahir, String tanggalLahir, 
        JenisKelamin jenisKelamin, GolonganDarah golonganDarah, String alamat, 
        String rt, String rw, String kelDes, String kecamatan,
        AgamaIndonesia agama, StatusPerkawinan statusPerkawinan, String pekerjaan, 
        Kewarganegaraan kewarganegaraan, String negara, String berlakuHingga, 
        File fotoFile, String kotaPembuatan, String tanggalPembuatan, File tandaTanganFile
    ) {
        this.nik = nik;
        this.nama = nama;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.golonganDarah = golonganDarah;
        this.alamat = alamat;
        this.rt = rt;
        this.rw = rw;
        this.kelDes = kelDes;
        this.kecamatan = kecamatan;
        this.agama = agama;
        this.statusPerkawinan = statusPerkawinan;
        this.pekerjaan = pekerjaan;
        this.kewarganegaraan = kewarganegaraan;
        this.negara = negara;
        this.berlakuHingga = berlakuHingga;
        this.fotoFile = fotoFile;
        this.kotaPembuatan = kotaPembuatan;
        this.tanggalPembuatan = tanggalPembuatan;
        this.tandaTanganFile = tandaTanganFile;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public GolonganDarah getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(GolonganDarah golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getKelDes() {
        return kelDes;
    }

    public void setKelDes(String kelDes) {
        this.kelDes = kelDes;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public AgamaIndonesia getAgama() {
        return agama;
    }

    public void setAgama(AgamaIndonesia agama) {
        this.agama = agama;
    }

    public StatusPerkawinan getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(StatusPerkawinan statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Kewarganegaraan getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(Kewarganegaraan kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    public String getBerlakuHingga() {
        return berlakuHingga;
    }

    public void setBerlakuHingga(String berlakuHingga) {
        this.berlakuHingga = berlakuHingga;
    }

    public File getFotoFile() {
        return fotoFile;
    }

    public void setFotoFile(File fotoFile) {
        this.fotoFile = fotoFile;
    }

    public String getKotaPembuatan() {
        return kotaPembuatan;
    }

    public void setKotaPembuatan(String kotaPembuatan) {
        this.kotaPembuatan = kotaPembuatan;
    }

    public String getTanggalPembuatan() {
        return tanggalPembuatan;
    }

    public void setTanggalPembuatan(String tanggalPembuatan) {
        this.tanggalPembuatan = tanggalPembuatan;
    }

    public File getTandaTanganFile() {
        return tandaTanganFile;
    }

    public void setTandaTanganFile(File tandaTanganFile) {
        this.tandaTanganFile = tandaTanganFile;
    }

    @Override
    public String toString() {
        String keteranganNegara = (kewarganegaraan.toString().equals("WNA")) ? " (" + negara + ")" : "";

        return "DATA PENDUDUK \nNIK: " + nik + "\nNama: " + nama + "\nTTL: " + tempatLahir + ", " + tanggalLahir 
            + "\nJenis Kelamin: " + jenisKelamin.toString() + "\nGolongan Darah: " + golonganDarah.toString() 
            + "\nAlamat: " + alamat + "\nRT/RW: " + rt + "/" + rw + "\nKel/Des: " + kelDes + "\nKecamatan: " + kecamatan 
            + "\nAgama: " + agama.toString() + "\nStatus Perkawinan: " + statusPerkawinan.toString() + "\nPekerjaan: " 
            + pekerjaan + "\nKewarganegaraan: " + kewarganegaraan.toString() + keteranganNegara + "\nBerlaku Hingga: " 
            + berlakuHingga + "\nPath File Foto: " + fotoFile.getAbsolutePath() + "\nKota Pembuatan: " + kotaPembuatan 
            + "\nTanggal Pembuatan: " + tanggalPembuatan + "\nPath File Tanda Tangan: " + tandaTanganFile.getAbsolutePath();
    }   
}