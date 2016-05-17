package org.websocket.jetty.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class JettyClientSocket {

	private Session session;
	CountDownLatch latch = new CountDownLatch(1);

	@OnWebSocketMessage
	public void onText(Session session, String message) {
		System.out.println("Message received: " + message);
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		this.session = session;
		latch.countDown();
		System.out.println(session.getRemoteAddress().getHostName() + " connected!");
	}

	@OnWebSocketClose
	public void onClose(Session session, int status, String reason) {
		System.out.println(session.getRemoteAddress().getHostString() + " closed!");
	}

	public void sendMessage(String message) {
		try {
			session.getRemote().sendString(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
