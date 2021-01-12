package pl.szymonwrobel.tms.enums;

public enum UserType {
    ADMIN("Administrator"),
    TRAINER("Trener"),
    STUDENT("Uczestnik");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
