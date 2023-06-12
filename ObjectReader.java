package Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectReader extends Thread  {

	private Socket socket;
	private BufferedReader input;
	private ServerSocket server;
	private Hangman hang;

	//	Constructor
	public ObjectReader(BufferedReader input,Socket socket, ServerSocket server,Hangman hang) {
		this.input = input;
		this.socket = socket;
		this.server = server;	
		this.hang = hang;
	}

	public void run() {
			while(true) {
				try {
					String line = input.readLine();
					System.out.println("leo la letra");
					if(line != null) {
						System.out.println("Actualizo el programa");
						hang.actionPerformed(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
	
	}
}
