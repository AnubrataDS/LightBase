package lightbase.ds;

import java.util.HashMap;

public class Node {
	String root ;
	String value ;
	HashMap<String,Node> children ;
	
	public Node()
	{
		root = null ;
		value =null;
		children = null ;
	}
	
	public Node(String root)
	{
		this.root = root ;
		value=null;
		this.children = new HashMap<String, Node>();
	}
	
	public Node(String key , String value)
	{
		this.root = key ;
		this.value = value ;
		this.children = null ;
	}
	
	private void addChild(String child_name)
	{
		children.put(child_name , new Node(child_name));
	}
	
	private void addKeyValue(String key , String Value)
	{
		children.put(key, new Node(key,value));
	}
	
	private String flatten()
	{
		String result = "" ;
		return result ;
	}
	
	
}
