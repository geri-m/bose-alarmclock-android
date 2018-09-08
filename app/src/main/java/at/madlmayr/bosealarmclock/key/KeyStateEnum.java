package at.madlmayr.bosealarmclock.key;

public enum KeyStateEnum {

    // the key states _MUST_ be lower case.
    PRESS("press"), RELEASE("release");

    private final String value;

    KeyStateEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
