package websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import thread.SocketThread;

@ServerEndpoint("/socket")
public class Websocket {
	//Start the polling thread in the onopen method
	@OnOpen
	public void onOpen(Session session){
		System.out.println("Named"+session.getId()+"The link is open");
		SocketThread thread = null;
		thread = new SocketThread(session);
		thread.start();
	}
	
	@OnClose
	public void closeSocket(Session session){
		System.out.println("Named"+session.getId()+"The link is closed");
	}
}
