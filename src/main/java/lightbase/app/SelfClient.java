package lightbase.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SelfClient {

	private int queryPortNo ;
	private int notificationPortNo ;
	private String root ;
	private Socket socket ;
	private PrintWriter out; 
	private BufferedReader in; 
	public SelfClient(int queryPortNo,String root )
	{
		this.queryPortNo = queryPortNo ;
		this.notificationPortNo = queryPortNo+1 ;
		this.root=root;
		try {
			socket = new Socket("localhost" , this.queryPortNo);
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			consoleLog("connected to query port");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			error("Unknown host exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void console()
	{
		ConsoleWriter.log("");
	}
	
	private static void consoleLog(String log) {
		ConsoleWriter.log(log);
	}

	private static void error(String err) {
		ConsoleWriter.error(err);
	}

	
	private void send(String str)
	{
		out.println(str);
		out.flush();
	}
	
	private String receive()
	{
		consoleLog("Waiting for reply...");
		String str;
		try {
			str = in.readLine();

			return str ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	protected void execute(String str)
	{
		if(str.equalsIgnoreCase("help"))
		{
			consoleLog("NOTE : Commands are not case-sensitive");
			consoleLog("List of commands : ");
			consoleLog("GET [CHILD_NAME] : returns the data in child with name CHILD_NAME ");
			consoleLog("PUT_CHILD [CHILD_NAME] : add a new child with name CHILD_NAME ");
			consoleLog("PUT_CHILD : add a new child with randomly generated name , returns name ");
			consoleLog("PUT_VALUE [CHILD_NAME] [VALUE] : sets/updates VALUE to child CHILDNAME");
			consoleLog("GO_IN [CHILD_NAME] : set the child CHILD_NAME as working root");
			consoleLog("COME_OUT : sets the parent of working root as working root");
			consoleLog("ROOT : return name of working root ");
			consoleLog("LIST : return list of names of all children under working root");
			consoleLog("LISTEN_CHILD [CHILD_NAME]: add child listener to CHILD_NAME");
			consoleLog("LISTEN_VALUE [CHILD_NAME]: add value listener to CHILD_NAME");
			consoleLog("UN_LISTEN_CHILD [CHILD_NAME]: remove child listener from CHILD_NAME");
			consoleLog("UN_LISTEN_VALUE [CHILD_NAME]: remove value listener from CHILD_NAME");
			consoleLog("TREE : lists all data under root");	
			consoleLog("EXIT : exit");
		}
		if(str.equalsIgnoreCase("test"))
		{
			send("test");
			System.out.println(receive());
		}
	}
}
