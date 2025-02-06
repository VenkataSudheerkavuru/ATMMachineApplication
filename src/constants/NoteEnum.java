package constants;

/**
 * Constant Note values
 */
public enum NoteEnum {
    NOTE_500(500), NOTE_200(200), NOTE_100(100);

    private final int value;

    NoteEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
