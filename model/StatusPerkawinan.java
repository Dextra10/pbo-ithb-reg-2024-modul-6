package model;

public enum StatusPerkawinan {
    BELUM_MENIKAH("Belum Menikah"), 
    MENIKAH("Menikah"), 
    JANDA_DUDA("Janda/Duda");

    private final String displayName;

    StatusPerkawinan(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}