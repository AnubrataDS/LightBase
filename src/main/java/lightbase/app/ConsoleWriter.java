package lightbase.app;

public class ConsoleWriter {
	private static boolean caret = false ;
	protected static void uncaret()
	{
		caret=false ;
	}
	protected static void log(String log) {
		if(caret)
		{
			System.out.println(log);
			System.out.print("> ");
			caret = true ;
		}
		else
		{
			if(log.equals(""))
			{
				System.out.print("> ");
				caret = true;
			}
			else
			{
				System.out.println("> " + log);
				caret = false ;
			}
		}
	}

	protected static void error(String err) {
		log("ERROR : " + err);
		System.exit(1);
	}
	
	protected static void softError(String err) {
		log("ERROR : " + err);
	}
	
}
