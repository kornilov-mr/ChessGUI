import logic.BoardController;
import logic.BoardControllerBuilder;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class CheckMateTest {
    @Test
    public void simpleCheckMateTest(){
        BoardControllerBuilder builder = new BoardControllerBuilder();
        builder.setBoardDispositionSetting(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableWithCheckmate.sp")));
        BoardController boardController = builder.build();
        Assert.assertEquals(boardController.whoWonTheGame(), PieceColor.White);
    }
    @Test
    public void almostCheckMateTest(){
        BoardControllerBuilder builder = new BoardControllerBuilder();
        builder.setBoardDispositionSetting(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableWithAlmostCheckMate.sp")));
        BoardController boardController = builder.build();
        Assert.assertNull(boardController.whoWonTheGame());
    }
}
