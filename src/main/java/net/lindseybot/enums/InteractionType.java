package net.lindseybot.enums;

public enum InteractionType {

    UNKNOWN(-1),
    PING(1),
    SLASH_COMMAND(2),
    COMPONENT(3);

    private final int key;

    InteractionType(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
