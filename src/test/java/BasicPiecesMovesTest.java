import logic.Board;
import logic.Move;
import logic.chessPieces.*;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class BasicPiecesMovesTest {
    private static final Board emptyBoard = new Board(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/EmptyTableForTesting.sp")));
    private static final Board boardWithKings = new Board(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableOnlyWithKingsForTesting.sp")));
    @Test
    public void testPawnMovesWhiteNotMoved(){
        String movesExpected = "[Move{xStart=6, yStart=0, xEnd=5, yEnd=0}, Move{xStart=6, yStart=0, xEnd=4, yEnd=0}]";
        Pawn pawn = new Pawn(6,0, PieceColor.Black);
        ArrayList<Move> moves =  pawn.getAllMovesInBounds(emptyBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testPawnMovesWhiteMoved(){
        String movesExpected = "[Move{xStart=6, yStart=0, xEnd=5, yEnd=0}]";
        Pawn pawn = new Pawn(6,0, PieceColor.Black);
        pawn.setMovedAsTrue();
        ArrayList<Move> moves =  pawn.getAllMovesInBounds(emptyBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testKnightMoves(){
        String movesExpected = "[Move{xStart=3, yStart=6, xEnd=5, yEnd=7}, Move{xStart=3, yStart=6, xEnd=1, yEnd=5}, Move{xStart=3, yStart=6, xEnd=2, yEnd=4}, Move{xStart=3, yStart=6, xEnd=1, yEnd=7}, Move{xStart=3, yStart=6, xEnd=5, yEnd=5}, Move{xStart=3, yStart=6, xEnd=4, yEnd=4}]";
        Knight knight = new Knight(3,6,PieceColor.Black);
        ArrayList<Move> moves =  knight.getAllMovesInBounds(emptyBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testBishopMoves(){
        String movesExpected = "[Move{xStart=3, yStart=6, xEnd=4, yEnd=7}, Move{xStart=3, yStart=6, xEnd=2, yEnd=7}, Move{xStart=3, yStart=6, xEnd=4, yEnd=5}, Move{xStart=3, yStart=6, xEnd=5, yEnd=4}, Move{xStart=3, yStart=6, xEnd=6, yEnd=3}, Move{xStart=3, yStart=6, xEnd=7, yEnd=2}, Move{xStart=3, yStart=6, xEnd=2, yEnd=5}, Move{xStart=3, yStart=6, xEnd=1, yEnd=4}, Move{xStart=3, yStart=6, xEnd=0, yEnd=3}]";
        Bishop bishop = new Bishop(3,6,PieceColor.Black);
        ArrayList<Move> moves =  bishop.getAllMovesInBounds(emptyBoard);
        System.out.println(moves);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testKingMoves(){
        String movesExpected = "[Move{xStart=4, yStart=7, xEnd=5, yEnd=6}, Move{xStart=4, yStart=7, xEnd=3, yEnd=6}, Move{xStart=4, yStart=7, xEnd=4, yEnd=6}, Move{xStart=4, yStart=7, xEnd=3, yEnd=7}, Move{xStart=4, yStart=7, xEnd=5, yEnd=7}]";
        King king = new King(4,7,PieceColor.Black);
        ArrayList<Move> moves =  king.getAllMovesInBounds(boardWithKings);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testRookMoves(){
        String movesExpected = "[Move{xStart=3, yStart=6, xEnd=3, yEnd=7}, Move{xStart=3, yStart=6, xEnd=2, yEnd=6}, Move{xStart=3, yStart=6, xEnd=1, yEnd=6}, Move{xStart=3, yStart=6, xEnd=0, yEnd=6}, Move{xStart=3, yStart=6, xEnd=3, yEnd=5}, Move{xStart=3, yStart=6, xEnd=3, yEnd=4}, Move{xStart=3, yStart=6, xEnd=3, yEnd=3}, Move{xStart=3, yStart=6, xEnd=3, yEnd=2}, Move{xStart=3, yStart=6, xEnd=3, yEnd=1}, Move{xStart=3, yStart=6, xEnd=3, yEnd=0}, Move{xStart=3, yStart=6, xEnd=4, yEnd=6}, Move{xStart=3, yStart=6, xEnd=5, yEnd=6}, Move{xStart=3, yStart=6, xEnd=6, yEnd=6}, Move{xStart=3, yStart=6, xEnd=7, yEnd=6}]";
        Rook rook = new Rook(3,6,PieceColor.Black);
        ArrayList<Move> moves =  rook.getAllMovesInBounds(emptyBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testQueenMoves(){
        String movesExpected = "[Move{xStart=3, yStart=6, xEnd=3, yEnd=7}, Move{xStart=3, yStart=6, xEnd=2, yEnd=6}, Move{xStart=3, yStart=6, xEnd=1, yEnd=6}, Move{xStart=3, yStart=6, xEnd=0, yEnd=6}, Move{xStart=3, yStart=6, xEnd=3, yEnd=5}, Move{xStart=3, yStart=6, xEnd=3, yEnd=4}, Move{xStart=3, yStart=6, xEnd=3, yEnd=3}, Move{xStart=3, yStart=6, xEnd=3, yEnd=2}, Move{xStart=3, yStart=6, xEnd=3, yEnd=1}, Move{xStart=3, yStart=6, xEnd=3, yEnd=0}, Move{xStart=3, yStart=6, xEnd=4, yEnd=6}, Move{xStart=3, yStart=6, xEnd=5, yEnd=6}, Move{xStart=3, yStart=6, xEnd=6, yEnd=6}, Move{xStart=3, yStart=6, xEnd=7, yEnd=6}, Move{xStart=3, yStart=6, xEnd=4, yEnd=7}, Move{xStart=3, yStart=6, xEnd=2, yEnd=7}, Move{xStart=3, yStart=6, xEnd=4, yEnd=5}, Move{xStart=3, yStart=6, xEnd=5, yEnd=4}, Move{xStart=3, yStart=6, xEnd=6, yEnd=3}, Move{xStart=3, yStart=6, xEnd=7, yEnd=2}, Move{xStart=3, yStart=6, xEnd=2, yEnd=5}, Move{xStart=3, yStart=6, xEnd=1, yEnd=4}, Move{xStart=3, yStart=6, xEnd=0, yEnd=3}]";
        Queen queen = new Queen(3,6,PieceColor.Black);
        ArrayList<Move> moves =  queen.getAllMovesInBounds(emptyBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
}
