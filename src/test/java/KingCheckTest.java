import logic.Board;
import logic.Move;
import logic.chessPieces.King;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class KingCheckTest {
    private final static Board boardWithKings = new Board(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableWithKingAndAttack.sp")));
    @Test
    public void kingUnderAttackMovesTest(){
        String movesExpected = "[Move{xStart=4, yStart=7, xEnd=3, yEnd=6}]";
        King king = new King(4,7, PieceColor.Black);
        ArrayList<Move> moves =  king.getAllMovesInBounds(boardWithKings);
        Assert.assertEquals(movesExpected, moves.toString());
    }

}
