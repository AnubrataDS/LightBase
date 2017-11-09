package lightbase.service;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	private static Queue<String> clients ;
	public Scheduler()
	{
		clients = new LinkedList<>();
	}
	
	public static void enqueue(String uid)
	{
		clients.add(uid);
	}
	
	public static String currentToken()
	{
		return clients.peek();
	}
	public static void dequeue()
	{
		clients.remove();
	}
	
}
