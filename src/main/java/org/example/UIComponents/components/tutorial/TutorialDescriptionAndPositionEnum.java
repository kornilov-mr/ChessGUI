package org.example.UIComponents.components.tutorial;

import gameControl.gameController.GameBuilder;
import gameControl.gameController.GameControllerType;
import gameControl.gameController.TutorialGameController;
import gameControl.timer.TimerEnum;
import logic.BoardController;
import logic.Move;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;

import gameControl.colorChooser.ColorChooserEnum;
import org.example.utils.visual.IconsEnum;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum for all Tutorials
 */
public enum TutorialDescriptionAndPositionEnum {
    SIMPLE_PAWN_TUTORIAL(
            IconsEnum.WHITE_PAWN.getImageIcon(80),
            "Pawn tutorial",
            new File("src/main/resources/tutorials/positionSettings/SimplePawnTutorial.txt"),
            "Pawns can walk on 1 tile forward or on 2 tiles from starting position. Now move Pawn from D3 to D5",
            new ArrayList<>(Arrays.asList(new Move(1,3,3,3), new Move(3,3,4,2))),
            new ArrayList<>(List.of(new Move(5, 2, 4, 2)))),
    SIMPLE_BISHOP_TUTORIAL(
            IconsEnum.WHITE_BISHOP.getImageIcon(80),
            "Bishop tutorial",
            new File("src/main/resources/tutorials/positionSettings/SimpleBishopTutorial.txt"),
            "Bishops can walk on diagonals. Now move bishop from D3 to G7 to give the enemy king a check",
            new ArrayList<>(List.of(new Move(2, 3, 5, 6))),
            new ArrayList<>()),
    SIMPLE_KNIGHT_TUTORIAL(IconsEnum.WHITE_KNIGHT.getImageIcon(80),
            "Knight tutorial",
            new File("src/main/resources/tutorials/positionSettings/SimpleKnightTutorial.txt"),
            "Knights can hop over other pieces on Г pattern, now eat the pawn on E4 with your knight",
            new ArrayList<>(List.of(new Move(1, 3, 3, 4))),
            new ArrayList<>()),
    SIMPLE_ROOK_TUTORIAL(IconsEnum.WHITE_ROOK.getImageIcon(80),
            "Rook tutorial",
            new File("src/main/resources/tutorials/positionSettings/SimpleRookTutorial.txt"),
            "Rook can only walk on straight lines. Now eat the pawn on E7",
            new ArrayList<>(List.of(new Move(2, 6, 6, 6))),
            new ArrayList<>()),
    SIMPLE_QUEEN_TUTORIAL(IconsEnum.WHITE_QUEEN.getImageIcon(80),
            "Queen tutorial",
            new File("src/main/resources/tutorials/positionSettings/SimpleQueenTutorial.txt"),
            "Queens can walk like bishops and rooks together, now eat the Pawn on C6",
            new ArrayList<>(List.of(new Move(2, 2, 5, 2))),
            new ArrayList<>()),
    SIMPLE_CHECKMATE(IconsEnum.BLACK_KING.getImageIcon(80),
            "Simple checkmate",
            new File("src/main/resources/tutorials/positionSettings/SimpleCheckMate.txt"),
            "If enemy king can't move and can't escape check, the opponent wins",
            new ArrayList<>(List.of(new Move(1, 4, 3, 4),new Move(0,5,3,2), new Move(0,3,4,7), new Move(4,7,6,5))),
            new ArrayList<>(List.of(new Move(6,4,4,4), new Move(7,1,5,2), new Move(7,6,5,5)))),
    PROMOTIONAL_MOVE(IconsEnum.BLACK_PAWN.getImageIcon(80),
            "Promotional tutorial",
            new File("src/main/resources/tutorials/positionSettings/PromotionalMoveTutorial.txt"),
            "If a pawn reaches the end of the board it can be promoted to any other piece",
            new ArrayList<>(Arrays.asList(new Move(5,7,6,7),new Move(6,7,7,7))),
            new ArrayList<>(List.of(new Move(5, 2, 4, 2)))),
    SIMPLE_CASTLE_TUTORIAL(IconsEnum.WHITE_KING.getImageIcon(80),
            "Castle tutorial",
            new File("src/main/resources/tutorials/positionSettings/SimpleCastleTutorial.txt"),
            "If a King didn't move and it not under check, it can perform the castle. Now use this opportunity by moving king to G1",
            new ArrayList<>(List.of(new Move(0, 4, 0, 6))),
            new ArrayList<>());


    private final ImageIcon imageIcon;
    private final String name;
    private final String description;
    private final File fileWithPiecePosition;
    private final ArrayList<Move> opponentsMoves;
    private final ArrayList<Move> playersExpectedMoves;

    TutorialDescriptionAndPositionEnum(ImageIcon imageIcon, String name, File fileWithPiecePosition, String description, ArrayList<Move> playersExpectedMoves, ArrayList<Move> opponentsMoves) {
        if(!fileWithPiecePosition.exists()) throw new RuntimeException("Setting file:"+fileWithPiecePosition.getAbsolutePath()+" does not exist");
        this.imageIcon = imageIcon;
        this.name = name;
        this.description = description;
        this.fileWithPiecePosition=fileWithPiecePosition;
        this.opponentsMoves=opponentsMoves;
        this.playersExpectedMoves=playersExpectedMoves;
    }

    public TutorialGameController getTutorialGameController() {
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.setGameTimer(TimerEnum.NO_TIMER.getTamer()).setGameName(name).setIsTheGameOnPoints(false)
                .setGameControllerType(GameControllerType.TUTORIAL_CHESS)
                .setBoardController(new BoardController(new BoardDispositionSetting(fileWithPiecePosition)))
                .setColorChooser(ColorChooserEnum.WHITE_CHOOSER.getColorChooser())
                .setFirstColorToMove(PieceColor.White)
                .setOpponentsMoves(opponentsMoves)
                .setPlayersExpectedMoves(playersExpectedMoves);

        return (TutorialGameController) gameBuilder.build();
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public String getName() {
        return name;
    }
}
