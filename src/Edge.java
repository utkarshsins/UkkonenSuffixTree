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
	public void setEndPosition(Object endp){
		this.endPosition=endp;
	}
	public void setStartPosition(int startp){
		this.startPosition=startp;
	}
	
	
	
	public Node traverseUp()
	{
		return this.startNode;
	}
	public int getstartIndex(){
		return this.startPosition;
	}
	public Node getstartNode(){
		return this.startNode;
	}
	public Node getendNode(){
		return this.endNode;
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
	//put new node after index [0,length-2]
	public Edge partitionEdge(int index) throws Exception{
		Node router=new Node();
		Edge routerToEndNode=new Edge(router, this.endNode, index+this.startPosition, this.endPosition);
		this.endNode=router;
		this.endPosition=this.startPosition+index;		
		router.setSuffixLink(this.startNode.getSuffixLink());		
		router.setParentEdge(this);// this has to be done after modifying edge, otherwise depth will be set wrong.
		return routerToEndNode;
	
	}
	

}
