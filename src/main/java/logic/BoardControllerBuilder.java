package logic;

import logic.pieceDisposition.BoardDispositionSetting;

public class BoardControllerBuilder {
    private BoardDispositionSetting boardDispositionSetting;

    public BoardControllerBuilder setBoardDispositionSetting(BoardDispositionSetting boardDispositionSetting) {
        this.boardDispositionSetting = boardDispositionSetting;
        return this;
    }
    public BoardController build() {
        if(boardDispositionSetting==null) throw new IllegalArgumentException("boardDispositionSetting must not be null");
        return new BoardController(boardDispositionSetting);
    }
}
