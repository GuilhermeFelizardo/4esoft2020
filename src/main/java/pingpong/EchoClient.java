package pingpong;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class EchoClient {
	
	private static final String SERVER_ADDRESS = "localhost";
	private static final int PORT = 8080;

	public static void main(String[] args) throws Exception {
		EchoClient client = new EchoClient();
		client.doIt();
	}

	private void doIt() throws Exception {
		InputStream consoleInput = System.in;
		OutputStream consoleOutput = System.out;
		List<Integer> valores = new ArrayList<>();
		String comando = "";
		double media = 0;
		int count = 0;
		while (count < 1000) {
			long start = System.currentTimeMillis();

			comando = "ping";
			String response = handleServerCommunication(comando);
			long end = System.currentTimeMillis();

			System.out.println(response);
			System.out.println(end - start);
			valores.add((int) (end - start));
			count ++;
		}
		Collections.sort(valores);
		System.out.println("Menor valor: " + valores.get(0));
		System.out.println("Maior valor: " + valores.get(valores.size() - 1));

		for (Integer tempo: valores) {
			media += tempo;
		}

		media /= 1000;

		System.out.println("Media dos valores: " + media);
		System.out.println("Saiu.");
	}

	private String handleServerCommunication(String comando) throws UnknownHostException, IOException {
		Socket connection = new Socket(SERVER_ADDRESS, PORT);			
		Scanner serverInput = new Scanner(connection.getInputStream());
		PrintWriter serverOutput = new PrintWriter(connection.getOutputStream());
		serverOutput.println(comando);
		serverOutput.flush();			
		String response = serverInput.nextLine();
		connection.close();
		return response;
	}

}
