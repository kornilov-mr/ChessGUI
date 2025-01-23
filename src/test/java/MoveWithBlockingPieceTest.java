import logic.Board;
import logic.Move;
import logic.chessPieces.*;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class MoveWithBlockingPieceTest {
    private static final Board blockingPawnBoard = new Board(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableWithBlockingPawns.sp")));
    private static final Board boardWithKings = new Board(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableWithBlockingPawnsWithKings.sp")));
    @Test
    public void testPawnMovesInFrontOfDifferentPawnInOneTile(){
        String movesExpected = "[Move{xStart=4, yStart=4, xEnd=3, yEnd=4}]";
        Pawn pawn = new Pawn(4,4, PieceColor.Black);
        ArrayList<Move> moves =  pawn.getAllMovesInBounds(blockingPawnBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testPawnMovesWithAttackPawnDiagonally(){
        String movesExpected = "[Move{xStart=3, yStart=4, xEnd=2, yEnd=3}]";
        Pawn pawn = new Pawn(3,4, PieceColor.Black);
        ArrayList<Move> moves =  pawn.getAllMovesInBounds(blockingPawnBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }

}