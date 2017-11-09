package lightbase.workers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {

	private Socket client;
	private QueryHandler queryhandler ;
	private String uid ;
	
	public ClientHandler(Socket client,String uid) {
		this.client = client;
		this.queryhandler = new QueryHandler(uid);
		this.uid = uid ;
	}

	public void threadRunner() throws IOException {
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader((client.getInputStream())));
			String query = br.readLine();
			String response = queryhandler.handle(query);
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			pw.println(response);
			pw.flush();
		}
	}
}
