package gameControl.gameController;

public enum IsGameOnPointsEnum {
    GAME_ON_POINTS("Yes", true),
    GAME_WITHOUT_POINTS("No",false);
    private final String nameForUser;
    private final boolean value;
    IsGameOnPointsEnum(String nameForUser, boolean value) {
        this.nameForUser = nameForUser;
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  nameForUser;
    }
}
