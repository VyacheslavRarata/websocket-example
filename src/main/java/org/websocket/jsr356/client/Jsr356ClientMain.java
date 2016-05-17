package org.websocket.jsr356.client;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

public class Jsr356ClientMain {

	public static void main(String[] args) {

		try {
			String uri = "ws://localhost:8080/jsr";
			Jsr356ClientSocket client = new Jsr356ClientSocket();
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(client, new URI(uri));

			client.getLatch().await();
			client.sendMessage("hello");
			Thread.sleep(1000);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
