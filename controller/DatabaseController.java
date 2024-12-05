package controller;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.AgamaIndonesia;
import model.DataPenduduk;
import model.GolonganDarah;
import model.JenisKelamin;
import model.Kewarganegaraan;
import model.StatusPerkawinan;

public class DatabaseController {
    private static DatabaseHandler db = new DatabaseHandler();

    public static DataPenduduk getDataPenduduk(String nik) {
        DataPenduduk dataPenduduk = new DataPenduduk();

        try {
            db.connect();
            String query = "SELECT * FROM data_penduduk WHERE nik ='" + nik + "'";
            Statement stmt = db.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                do {
                    dataPenduduk.setNik(rs.getString("nik"));
                    dataPenduduk.setNama(rs.getString("nama"));
                    dataPenduduk.setTempatLahir(rs.getString("tempat_lahir"));
                    dataPenduduk.setTanggalLahir(rs.getString("tanggal_lahir"));
                    dataPenduduk.setJenisKelamin(JenisKelamin.valueOf(rs.getString("jenis_kelamin")));
                    dataPenduduk.setGolonganDarah(GolonganDarah.valueOf(rs.getString("golongan_darah")));
                    dataPenduduk.setAlamat(rs.getString("alamat"));
                    dataPenduduk.setRt(rs.getString("rt"));
                    dataPenduduk.setRw(rs.getString("rw"));
                    dataPenduduk.setKelDes(rs.getString("kel_des"));
                    dataPenduduk.setKecamatan(rs.getString("kecamatan"));
                    dataPenduduk.setAgama(AgamaIndonesia.valueOf(rs.getString("agama")));
                    dataPenduduk.setStatusPerkawinan(StatusPerkawinan.valueOf(rs.getString("status_perkawinan")));
                    dataPenduduk.setPekerjaan(rs.getString("pekerjaan"));
                    dataPenduduk.setKewarganegaraan(Kewarganegaraan.valueOf(rs.getString("kewarganegaraan")));
                    dataPenduduk.setNegara(rs.getString("negara"));
                    dataPenduduk.setBerlakuHingga(rs.getString("berlaku_hingga"));
                    dataPenduduk.setFotoFile(new File(rs.getString("foto_file_path")));
                    dataPenduduk.setKotaPembuatan(rs.getString("kota_pembuatan"));
                    dataPenduduk.setTanggalPembuatan(rs.getString("tanggal_pembuatan"));
                    dataPenduduk.setTandaTanganFile(new File(rs.getString("tanda_tangan_file_path")));
                } while (rs.next());
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.disconnect();
        return dataPenduduk;
    }

    public static boolean insertDataPenduduk(DataPenduduk dataPenduduk) {
        try {
            db.connect();
            String query = "INSERT INTO data_penduduk "
                   + "(nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, golongan_darah, "
                   + "alamat, rt, rw, kel_des, kecamatan, agama, status_perkawinan, "
                   + "pekerjaan, kewarganegaraan, negara, berlaku_hingga, foto_file_path, "
                   + "kota_pembuatan, tanggal_pembuatan, tanda_tangan_file_path) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = db.con.prepareStatement(query);

            pstmt.setString(1, dataPenduduk.getNik());
            pstmt.setString(2, dataPenduduk.getNama());
            pstmt.setString(3, dataPenduduk.getTempatLahir());
            pstmt.setString(4, dataPenduduk.getTanggalLahir());
            pstmt.setString(5, dataPenduduk.getJenisKelamin().name());
            pstmt.setString(6, dataPenduduk.getGolonganDarah().name());
            pstmt.setString(7, dataPenduduk.getAlamat());
            pstmt.setString(8, dataPenduduk.getRt());
            pstmt.setString(9, dataPenduduk.getRw());
            pstmt.setString(10, dataPenduduk.getKelDes());
            pstmt.setString(11, dataPenduduk.getKecamatan());
            pstmt.setString(12, dataPenduduk.getAgama().toString());
            pstmt.setString(13, dataPenduduk.getStatusPerkawinan().name());
            pstmt.setString(14, dataPenduduk.getPekerjaan());
            pstmt.setString(15, dataPenduduk.getKewarganegaraan().name());
            pstmt.setString(16, dataPenduduk.getNegara());
            pstmt.setString(17, dataPenduduk.getBerlakuHingga());
            pstmt.setString(18, dataPenduduk.getFotoFile().getPath());
            pstmt.setString(19, dataPenduduk.getKotaPembuatan());
            pstmt.setString(20, dataPenduduk.getTanggalPembuatan());
            pstmt.setString(21, dataPenduduk.getTandaTanganFile().getPath());

            int row = pstmt.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.disconnect();
        }
    }

    public static boolean updateDataPenduduk(DataPenduduk dataPenduduk) {
        try {
            db.connect();
            String query = "UPDATE data_penduduk SET "
                       + "nama=?, tempat_lahir=?, tanggal_lahir=?, jenis_kelamin=?, golongan_darah=?, " 
                       + "alamat=?, rt=?, rw=?, kel_des=?, kecamatan=?, agama=?, status_perkawinan=?, " 
                       + "pekerjaan=?, kewarganegaraan=?, negara=?, berlaku_hingga=?, foto_file_path=?, "
                       + "kota_pembuatan=?, tanggal_pembuatan=?, tanda_tangan_file_path=? "
                       + "WHERE nik=?";
            PreparedStatement pstmt = db.con.prepareStatement(query);
  
            pstmt.setString(1, dataPenduduk.getNama());
            pstmt.setString(2, dataPenduduk.getTempatLahir());
            pstmt.setString(3, dataPenduduk.getTanggalLahir());
            pstmt.setString(4, dataPenduduk.getJenisKelamin().toString());
            pstmt.setString(5, dataPenduduk.getGolonganDarah().toString());
            pstmt.setString(6, dataPenduduk.getAlamat());
            pstmt.setString(7, dataPenduduk.getRt());
            pstmt.setString(8, dataPenduduk.getRw());
            pstmt.setString(9, dataPenduduk.getKelDes());
            pstmt.setString(10, dataPenduduk.getKecamatan());
            pstmt.setString(11, dataPenduduk.getAgama().toString());
            pstmt.setString(12, dataPenduduk.getStatusPerkawinan().toString());
            pstmt.setString(13, dataPenduduk.getPekerjaan());
            pstmt.setString(14, dataPenduduk.getKewarganegaraan().toString());
            pstmt.setString(15, dataPenduduk.getNegara());
            pstmt.setString(16, dataPenduduk.getBerlakuHingga());
            pstmt.setString(17, dataPenduduk.getFotoFile().getPath());
            pstmt.setString(18, dataPenduduk.getKotaPembuatan());
            pstmt.setString(19, dataPenduduk.getTanggalPembuatan());
            pstmt.setString(20, dataPenduduk.getTandaTanganFile().getPath());
            
            pstmt.setString(21, dataPenduduk.getNik());
    
            int row = pstmt.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.disconnect();
        }
    }
    
    public static boolean deleteDataPenduduk(String nik) {
        try {
            db.connect(); 
            String query = "DELETE FROM data_penduduk WHERE nik = ?";
            PreparedStatement pstmt = db.con.prepareStatement(query);
            
            pstmt.setString(1, nik);
            
            int row = pstmt.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.disconnect();
        }
    }
    
}
