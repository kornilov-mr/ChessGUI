package org.example.serverConnection.websocket;

import gameControl.gameController.OnlineGameController;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

/**
 * WebsocketEndpoint, which add MessageHandler
 */
public class WebsocketEndpoint extends Endpoint {
    private final GameWebSocketClient webSocketClient;
    private final OnlineGameController onlineGameController;

    public WebsocketEndpoint(GameWebSocketClient webSocketClient, OnlineGameController onlineGameController) {
        this.webSocketClient = webSocketClient;
        this.onlineGameController = onlineGameController;
    }

    @Override
    public synchronized void onOpen(Session session, EndpointConfig endpointConfig) {
        webSocketClient.session = session;
        session.addMessageHandler(new GameMessageHandler(onlineGameController));
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
    }

    @Override
    public void onError(Session session, Throwable thr) {
        super.onError(session, thr);
    }
}
