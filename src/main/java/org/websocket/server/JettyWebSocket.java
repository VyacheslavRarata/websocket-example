package org.websocket.server;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class JettyWebSocket {

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.println(session.getRemoteAddress().getHostString() + " connected!");
	}

	@OnWebSocketClose
	public void onClose(Session session, int status, String reason) {
		System.out.println(session.getRemoteAddress().getHostString() + " closed!");
	}

	@OnWebSocketMessage
	public void onText(Session session, String message) throws IOException {
		if(session.isOpen()){
			session.getRemote().sendString(message); 
			System.out.println("Message received:" + message);
		}
	}

}
