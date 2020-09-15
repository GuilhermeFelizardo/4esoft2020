package pingpong;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class ChatClientHandler extends Thread {
	private Socket socket;
	private PrintWriter output;
	private Logger logger = Logger.getLogger(ChatClientHandler.class.getName());

	public ChatClientHandler(Socket socket) {
		logger.info("New user connecting...");
		this.socket = socket;
	}

	public void run() {
		try  {
			output = new PrintWriter(socket.getOutputStream());
			//send("conectado");
			Scanner input = new Scanner(socket.getInputStream());
			String command = "";
			while (!command.contains("end")) {
				command = input.nextLine();
				handleMessage(command);
			}
			if(command.contains("end")){
				send("fechando conexao");
				socket.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleMessage(String command) {
		if (command.equalsIgnoreCase("ping")) {
			send("pong");
		}
	}

	private void send(String message) {
		try {
			output.println(message);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
