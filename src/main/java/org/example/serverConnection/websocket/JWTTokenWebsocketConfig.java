package org.example.serverConnection.websocket;

import javax.websocket.ClientEndpointConfig;

/**
 * Config for Websocket to add auth header before connection to the server
 */
public class JWTTokenWebsocketConfig extends ClientEndpointConfig.Configurator {

    private final String token;

    public JWTTokenWebsocketConfig(String token) {
        this.token = token;
    }

    @Override
    public void beforeRequest(java.util.Map<String, java.util.List<String>> headers) {
        headers.put("Authorization", java.util.Collections.singletonList("Bearer " + token));
    }
}
