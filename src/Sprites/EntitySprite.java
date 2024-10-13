package Sprites;

public enum EntitySprite {
    WOLF("\uD83D\uDC3A"), GRASS("\uD83E\uDD6C"), ROCK("\uD83E\uDEA8"),
    TREE("\uD83C\uDF33"), GROUND("\uD83D\uDFEB"), HARE("\uD83D\uDC30"),
    COW("\uD83D\uDC04"), FOX("\uD83E\uDD8A");

    //secret TexturePack
//    WOLF("\uD83D\uDC6E\u200D♂\uFE0F"), GRASS("\uD83C\uDF57"), ROCK("\uD83D\uDE94"),
//    TREE("\uD83D\uDEB2"), GROUND("\uD83D\uDFEB"), HARE("\uD83E\uDDD1\uD83C\uDFFF\u200D\uD83E\uDDB2"),
//    COW("\uD83E\uDD64"), FOX("\uD83C\uDF49");

    private final String signature;

    EntitySprite(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return signature;
    }
}
