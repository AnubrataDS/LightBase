package lightbase.workers;

import lightbase.service.Scheduler;

public class QueryHandler {
	String uid ;
	public QueryHandler(String uid)
	{
		this.uid = uid ;
	}
	
	public String handle(String query)
	{
		query = query.toLowerCase();
		if(query.equals("test"))
			return query ;
		else
		{
			String blocks[] = query.split(" ");
			//AFTER DECIDING WHAT TO DO
			Scheduler.enqueue(uid);
			while(!Scheduler.currentToken().equals(uid));
			//DO WHATEVER
			
			//WHEN DONE
			Scheduler.dequeue();
			
		}
		return "invalid query";
	}
}
