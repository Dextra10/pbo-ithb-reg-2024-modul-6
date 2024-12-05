package model;

public class JenisPekerjaan {
    private static final String[] opsi = {
        "Karyawan Swasta",
        "PNS",
        "Wiraswasta",
        "Akademisi",
        "Pengangguran"
    };

    public static String[] getOpsi() {
        return opsi;
    }

    public static int indexOf(String pekerjaan) {
        for (int i = 0; i < opsi.length; i++) {
            if (opsi[i].equalsIgnoreCase(pekerjaan)) {
                return i;
            }
        }
        return -1;
    }
}
