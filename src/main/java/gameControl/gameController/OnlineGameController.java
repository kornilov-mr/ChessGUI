package gameControl.gameController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameControl.timer.GameTimer;
import logic.BoardController;
import logic.Move;
import logic.MoveIsNotValidException;
import logic.chessPieces.PieceColor;
import org.example.serverConnection.websocket.GameWebSocketClient;

import java.util.Objects;

/**
 * Implementation for Online play, which takes all information from UI
 * and sends in to the server, using websockets to hang to connection,
 * receiving answers from server, about which move have been played
 */
public class OnlineGameController extends AbstractGameController {
    private final Long gameId;
    private final GameWebSocketClient webSocketClient;
    private final PieceColor ownerColor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OnlineGameController(PieceColor ownerColor, BoardController boardController, GameTimer gameTimer, Long gameId) {
        super(PieceColor.White, boardController, gameTimer);
        this.ownerColor = ownerColor;
        this.gameId = gameId;
        this.webSocketClient = new GameWebSocketClient(gameId,this);
    }

    /**
     * this Implementation disregards previous logic, sending move to server
     * @param move object with info about the move
     */
    @Override
    public void makeAMove(Move move) throws MoveIsNotValidException {
        if(Objects.equals(boardController.getBoard().getPieceColorOnMoveStart(move), ownerColor)){
            try {
                webSocketClient.sendMessage(objectMapper.writeValueAsString(move));
                throw new MoveIsNotValidException();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new MoveIsNotValidException();
        }
    }

    /**
     * Used in websocket when receiving a move from the server
     * to execute the move
     * @param move object with info about the move
     */
    public void superMakeAMove(Move move) throws MoveIsNotValidException {
        super.makeAMove(move);
    }

    @Override
    protected void start() {

    }

    public Long getGameId() {
        return gameId;
    }
}
