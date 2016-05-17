package org.websocket.server;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebServlet(urlPatterns="/jetty")
public class JettyServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.register(JettyWebSocket.class);
	}

}
