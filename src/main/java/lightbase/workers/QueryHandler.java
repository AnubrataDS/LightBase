package lightbase.workers;

public class QueryHandler {

	public String handle(String query)
	{
		
		if(query.equals("test"))
			return query ;
		
		return "invalid query";
	}
}
