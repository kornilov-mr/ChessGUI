package org.example.serverConnection.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameControl.gameController.OnlineGameController;
import logic.BoardController;
import logic.Move;
import logic.PromotionMove;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;
import org.example.UIComponents.components.playField.fieldUpdaters.IUpdateField;
import org.example.UIComponents.components.playField.observers.INotifyState;
import org.example.account.AppContext;
import org.example.serverConnection.websocket.messages.StartGame;
import org.example.serverConnection.websocket.messages.TableMessage;
import org.example.serverConnection.websocket.messages.WinLoseResult;

import javax.websocket.MessageHandler;
/**
 * MessageHandlers for receiving Messages form the server
 * And updating UI as well as executing moves
 */
public class GameMessageHandler implements MessageHandler.Whole<String> {
    private final OnlineGameController onlineGameController;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GameMessageHandler(OnlineGameController onlineGameController) {
        this.onlineGameController = onlineGameController;
    }

    @Override
    public synchronized void onMessage(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String type = jsonNode.get("type").asText();

            switch (type) {
                case "TableMessage":
                    TableMessage tableMessage = objectMapper.readValue(message, TableMessage.class);
                    BoardController boardController = new BoardController(new BoardDispositionSetting(tableMessage.getPieces()));
                    onlineGameController.setBoardController(boardController);
                    for(IUpdateField fieldUpdater : onlineGameController.getFieldUpdaters()) {
                        fieldUpdater.updateField();
                    }
                    break;
                case "WinLoseResult":
                    WinLoseResult winLoseResult = objectMapper.readValue(message, WinLoseResult.class);
                    for(INotifyState notifyResult: onlineGameController.getResultsNotifiers()) {
                        notifyResult.notifyWin(PieceColor.valueOf(winLoseResult.getWonColor()));
                    }
                    break;
                case "StartGame":
                    onlineGameController.startGame();
                    StartGame startGame = objectMapper.readValue(message, StartGame.class);

                    String enemyUsername = AppContext.getAccount().getUserName().equals(startGame.getFirstUserName())?
                                        startGame.getSecondUserName(): startGame.getFirstUserName();
                    int enemyElo = AppContext.getAccount().getUserName().equals(startGame.getFirstUserName())?
                            startGame.getSecondUserElo(): startGame.getFirstUserElo();
                    for(INotifyState notifyResult: onlineGameController.getResultsNotifiers()) {
                        notifyResult.notifyOnlineGameStart(enemyUsername,enemyElo);
                    }
                    break;
                case "DrawResult":
                    for(INotifyState notifyResult: onlineGameController.getResultsNotifiers()) {
                        notifyResult.notifyDraw();
                    }
                    break;
                case "Move":
                    Move move = objectMapper.readValue(message, Move.class);
                    onlineGameController.superMakeAMove(move);
                    for(IUpdateField fieldUpdater : onlineGameController.getFieldUpdaters()) {
                        fieldUpdater.updateField();
                    }
                    break;
                case "PromotionMove":
                    PromotionMove promotionMove = objectMapper.readValue(message, PromotionMove.class);
                    onlineGameController.superMakeAMove(promotionMove);
                    for(IUpdateField fieldUpdater : onlineGameController.getFieldUpdaters()) {
                        fieldUpdater.updateField();
                    }
                    break;

            }
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
