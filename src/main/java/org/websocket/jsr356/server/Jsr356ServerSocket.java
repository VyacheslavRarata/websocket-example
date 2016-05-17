package org.websocket.jsr356.server;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/jsr")
public class Jsr356ServerSocket {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Socket is opened: " + session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		session.getBasicRemote().sendText(message);
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		System.out.println("Socket is closed due: " + reason.getReasonPhrase());
	}
}
