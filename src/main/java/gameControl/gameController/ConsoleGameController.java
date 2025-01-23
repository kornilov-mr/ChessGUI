package gameControl.gameController;

import gameControl.colorChooser.ColorChooser;
import gameControl.timer.GameTimer;
import logic.BoardController;
import logic.Move;
import logic.chessPieces.PieceColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class ConsoleGameController extends AbstractGameController {
    protected ConsoleGameController(ColorChooser colorChooser, BoardController boardController,GameTimer gameTimer) {
        super(PieceColor.White, boardController, gameTimer);
    }

    @Override
    protected void start() {
        System.out.println(boardController.getBoardAsString());
        BufferedReader scanner;
        try {
            scanner = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String moveString = scanner.readLine();
                String[] moveArgs = moveString.split(" ");
                Move move = new Move(Integer.parseInt(moveArgs[0]),Integer.parseInt(moveArgs[1]),Integer.parseInt(moveArgs[2]),Integer.parseInt(moveArgs[3]));
                makeAMove(move);
                System.out.println(boardController.getBoardAsString());
                if (isGameInDraw()){
                    out.println("Draw");
                    return;
                }
                PieceColor colorOfWinning = whoWonTheGame();
                if(colorOfWinning!=null){
                    out.println(colorOfWinning +" Won");
                    return;
                }
                System.out.println(getWhiteTime()+"/"+getBlackTime());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
