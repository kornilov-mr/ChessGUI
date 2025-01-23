package logic.pieceDisposition;

import java.io.File;

/**
 * Enum for positions presets
 * like basic position or 980 chess (in the future)
 */
public enum BoardDispositionEnum {
    BASIC_POSITION_OF_PIECES_8X8(new File("src/main/java/logic/pieceDisposition/Basic_Position_Of_pieces.sp"));
    private final File settingFile;

    BoardDispositionEnum(File settingFile) {
        this.settingFile = settingFile;
    }

    public BoardDispositionSetting getBoardDispositionSetting() {
        return new BoardDispositionSetting(settingFile);
    }
}
