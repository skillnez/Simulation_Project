package Sprites;

public enum EntitySprite {
    WOLF("\uD83D\uDC3A"), GRASS("\uD83E\uDD6C"), ROCK("⛰"),
    TREE("\uD83C\uDF33"), GROUND("\uD83D\uDFEB"), HARE("\uD83D\uDC30"),
    COW("\uD83D\uDC04"), FOX("\uD83E\uDD8A");

    private final String signature;

    EntitySprite(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return signature;
    }
}
