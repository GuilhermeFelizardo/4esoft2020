package pingpong;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
	private static final int PORT = 8080;
	//private List<ChatClientHandler> clientHandlers = new ArrayList<>();
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.listen();
	}

	private void listen() {
		try (ServerSocket socket = new ServerSocket(PORT)) {
			while (true) {
				ChatClientHandler client = new ChatClientHandler(socket.accept());
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
