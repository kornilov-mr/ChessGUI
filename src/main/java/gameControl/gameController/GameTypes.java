package gameControl.gameController;

public enum GameTypes {
    OFFLINE("Offline"),
    ONLINE("Online");
    private final String nameForUser;

    GameTypes(String nameForUser) {
        this.nameForUser = nameForUser;
    }

    @Override
    public String toString() {
        return nameForUser;
    }
}
