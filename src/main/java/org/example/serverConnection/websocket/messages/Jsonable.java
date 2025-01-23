package org.example.serverConnection.websocket.messages;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * interface to serialize objects
 */
public interface Jsonable {
    ObjectMapper objectMapper = new ObjectMapper();
    String toJsonString();
}
