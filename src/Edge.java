import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class Edge {

	private Node startNode;
	private Node endNode;
	private int startPosition;
	private Object endPosition;

	public Edge(Node startNode, Node endNode, int startPosition,
			Object endPosition) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	public void setEndNode(Node node) {
		endNode = node;
	}
	public Node traverseUp()
	{
		return this.startNode;
	}
	public int getstartIndex(){
		return this.startPosition;
	}
	public String getString(String original) throws Exception{
		return original.substring(getstartIndex(),getEdgelength()+getstartIndex()); 
	}
	
	public int getEdgelength() throws Exception
	{
		if (endPosition instanceof Integer)
				return (Integer)endPosition -startPosition;
		else if(endPosition instanceof GlobalUpdate) {
			GlobalUpdate a=(GlobalUpdate) endPosition;
			return a.getValue()-startPosition;
		}
		else throw new Exception();
			
	}

}
