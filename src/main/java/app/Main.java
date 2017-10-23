package app;

import java.util.Scanner;

public class Main {
	private static int queryPortNo;
	private static int notificationPortNo;
	private static String fileName;
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

	private static boolean IsInt(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}

	private static void handleCLI(String arg) {
		// avoiding switch-case because switch with strings is not available
		// in jdk versions lower than 1.7

		if (arg.equalsIgnoreCase("--version")) {
			consoleLog("lightbase v0.1");
		} else if (arg.equalsIgnoreCase("--help")) {
			consoleLog("sample help message. not really helpful");
		} else if (arg.equalsIgnoreCase("--usage")) {
			consoleLog("usage :- ");
			consoleLog("1. lightbase [FILE_NAME] [QUERY_PORT_NO]");
			consoleLog("2. lightbase [FILE_NAME] ");
			consoleLog("3. lightbase [QUERY_PORT_NO] ");
			consoleLog("4 lightbase --[OPTION] ");
			consoleLog("OPTIONS : ");
			consoleLog("1. --version");
			consoleLog("2. --help");
			consoleLog("3. --usage");
		} else {
			error("Invalid arguments , use --usage to see usage or --help for help");
		}
		System.exit(0);
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		// CLI args format :-
		// Max 2 args
		// 1. Filename , default : db , converted to db.json
		// 2. Query port no. , default : 8888
		// Notification port no. = query port+1 , default : 8889

		// Special args :-
		// --version : version
		// --help : help
		// --usage : usage

		if (args.length > 2) {
			error("Too many arguments , use --usage to see usage or --help for help");
		}

		else if (args.length == 2) {
			try {
				fileName = args[0];
				queryPortNo = Integer.parseInt(args[1]);
				notificationPortNo = queryPortNo + 1;
			} catch (NumberFormatException e) {
				error("Invalid arguments , use --usage to see usage or --help for help");
			}
		}

		else if (args.length == 1) {
			if (args[0].startsWith("--")) {
				handleCLI(args[0]);
			} else {
				if (IsInt(args[0])) {
					queryPortNo = Integer.parseInt(args[0]);
					if (queryPortNo < 1024)
						error("Invalid port no. , please provide a port no. > 1024");
					else {
						notificationPortNo = queryPortNo + 1;
						fileName = "db.json";
					}
				} else {
					fileName = args[0];
					queryPortNo = 8888;
					notificationPortNo = 8889;
				}
			}
		}
		else
		{
			fileName = "db.json";
			queryPortNo = 8888;
			notificationPortNo = 8889;
		}
		// One thread runs the db service
		// This thread runs self client for CL Args
		Thread dbservice = new Thread(){
			
		};
		dbservice.run();
		consoleLog("Lightbase started using "+fileName+" at query port "+queryPortNo+" and notification port "+notificationPortNo);
		//sending the console to selfclient as it will write the output in case of notification
		SelfClient selfclient = new SelfClient(queryPortNo);
		consoleLog("Lightbase self-client started");
		consoleLog("Type help to get command help");
		consoleLog("Type exit to exit");
		console();
		while(true)
		{
			String str = sc.nextLine() ;
			if(str.equals("exit"))
			{
				consoleLog("Lightbase stopped");
				break;
			}
			
			else
			{
				//perform query on str
				selfclient.execute(str);
				console();
			}
		}
		sc.close();
	}
}
