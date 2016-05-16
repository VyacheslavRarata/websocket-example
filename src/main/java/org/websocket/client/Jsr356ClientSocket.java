package org.websocket.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class Jsr356ClientSocket {

	CountDownLatch latch = new CountDownLatch(1);
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		latch.countDown();
		System.out.println("Connected to server");
	}

	@OnMessage
	public void send(String message, Session session) {
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		System.out.println("Closed due: " + reason.getReasonPhrase());
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void sendMessage(String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
