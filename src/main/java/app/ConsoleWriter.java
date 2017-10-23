package app;

public class ConsoleWriter {
	
	protected static void log(String log) {
		if(log.equals(""))
			System.out.print("> ");
		else
		System.out.println("> " + log);
	}

	protected static void error(String err) {
		log("ERROR : " + err);
		System.exit(1);
	}
	
	protected static void softError(String err) {
		log("ERROR : " + err);
	}
	
}
