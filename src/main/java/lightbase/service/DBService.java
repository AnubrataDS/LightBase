package lightbase.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import lightbase.workers.ClientHandler;

public class DBService {
	private static int queryPortNo;
	private static int notificationPortNo;
	private static String fileName;
	private ServerSocket socket;
	private Notifier notifier ;
	private FileManager fileManager ;
	public DBService(int queryPortNo, int notificationPortNo, String fileName) {
		this.queryPortNo = queryPortNo;
		this.notificationPortNo = notificationPortNo;
		this.fileName = fileName;
		try {
			this.socket = new ServerSocket(this.queryPortNo);
			this.notifier = new Notifier(this.notificationPortNo);
			this.fileManager = new FileManager(this.fileName);
			threadRunner();
			// listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void threadRunner()
	{
		listen() ; 
	}
	
	private void newClient(Socket client)
	{
		ClientHandler clientHandler = new ClientHandler(client);
		Thread newClientThread = new Thread(new Runnable(){
			public void run()
			{
				try {
					clientHandler.threadRunner();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		newClientThread.start();
		
	}
	
	private void listen() {
		while (true) {
			try {
				Socket client = socket.accept();
				newClient(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
