package model;

public enum AgamaIndonesia {
    ISLAM("Islam"),
    KRISTEN_PROTESTAN("Kristen Protestan"),
    KRISTEN_KATOLIK("Kristen Katolik"),
    HINDU("Hindu"),
    BUDDHA("Buddha"),
    KHONGHUCU("Khonghucu"),
    LAINNYA("Lainnya");

    private final String displayName;

    AgamaIndonesia(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}