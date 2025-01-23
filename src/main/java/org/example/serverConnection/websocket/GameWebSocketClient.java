package org.example.serverConnection.websocket;


import gameControl.gameController.OnlineGameController;
import org.example.account.AppContext;
import org.example.utils.logical.DotEnv;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * WebSocket client, which sends Messages to the server
 */
public class GameWebSocketClient {
    private static final String SERVER_ROOT = DotEnv.getValue("SERVER_ROOT");
    protected Session session;

    public GameWebSocketClient(Long gameId, OnlineGameController gameController) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            ClientEndpointConfig config = createConfigWithJwtToken(AppContext.getAccount().getJWTToken());

            container.connectToServer( new WebsocketEndpoint(this, gameController),
                    config,
                    URI.create("ws://"+SERVER_ROOT+DotEnv.getValue("GAME_WEBSOCKET_PATH")+gameId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ClientEndpointConfig createConfigWithJwtToken(String token) {
        return ClientEndpointConfig.Builder.create()
                .configurator(new JWTTokenWebsocketConfig(token))
                .build();
    }
}
