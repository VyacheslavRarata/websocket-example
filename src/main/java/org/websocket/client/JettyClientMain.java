package org.websocket.client;

import java.net.URI;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class JettyClientMain {

	public static void main(String[] args) {
		String uri = "ws://localhost:8080/jetty";
		WebSocketClient client = new WebSocketClient();
		try {
			JettyClientSocket socket = new JettyClientSocket();
			client.start();
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			client.connect(socket, new URI(uri), request);
			socket.getLatch().await();
			socket.sendMessage("hello jetty");
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
