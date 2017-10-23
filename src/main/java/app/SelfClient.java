package app;

public class SelfClient {

	private int queryPortNo ;
	private int notificationPortNo ;
	private String root ;
	public SelfClient(int queryPortNo )
	{
		this.queryPortNo = queryPortNo ;
		this.notificationPortNo = queryPortNo+1 ;
		root="\\";
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

	
	protected void execute(String str)
	{
		if(str.equals("help"))
		{
			consoleLog("NOTE : Commands are not case-sensitive");
			consoleLog("List of commands : ");
			consoleLog("GET [CHILD_NAME] : returns the data in child with name CHILD_NAME ");
			consoleLog("PUT_CHILD [CHILD_NAME] : add a new child with name CHILD_NAME ");
			consoleLog("PUT_CHILD : add a new child with randomly generated name , returns name ");
			consoleLog("PUT_VALUE [CHILD_NAME] [VALUE] : sets VALUE to child CHILDNAME");
			consoleLog("GO_IN [CHILD_NAME] : set the child CHILD_NAME as root");
			consoleLog("COME_OUT : sets the parent of current root as root");
			consoleLog("ROOT : return name of root");
			consoleLog("LIST : return list of names of all children under current root");
			consoleLog("LISTEN_CHILD [CHILD_NAME]: add child listener to CHILD_NAME");
			consoleLog("LISTEN_VALUE [CHILD_NAME]: add value listener to CHILD_NAME");
			consoleLog("UN_LISTEN_CHILD [CHILD_NAME]: remove child listener from CHILD_NAME");
			consoleLog("UN_LISTEN_VALUE [CHILD_NAME]: remove value listener from CHILD_NAME");
			consoleLog("TREE : lists all data under root");
			consoleLog("EXIT : exit");
		}
	}
}
