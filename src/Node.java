import java.util.ArrayList;

public class Node {

	private Edge parentEdge;
	private Edge childEdges[];
	private Node suffixLink;
	private int depth;
	boolean leaf;
	private int start_min; // this will be index of string from where suffix will cover this node.
	private int num_leaves; // number of leaves

	public Node(Node root) {
		parentEdge = null;
		childEdges = new Edge[Utils.SYMBOL_SET_SIZE];
		suffixLink = root;
		if(root==null)
			suffixLink=this;
		depth = 0; // number of chars preceeding it.
		leaf=true;
	}
	

	public Node(Edge parentEdge, Node root) throws Exception {
		this.parentEdge = parentEdge;
		childEdges = new Edge[Utils.SYMBOL_SET_SIZE];
		suffixLink = root;
		depth = this.parentEdge.traverseUp().depth
				+ this.parentEdge.getEdgelength();
		leaf=true;
	}

	public void setParentEdge(Edge parentEdge) throws Exception {
		this.parentEdge = parentEdge;
		parentEdge.setEndNode(this);
		depth = this.parentEdge.traverseUp().depth
				+ this.parentEdge.getEdgelength();
	}

	public Node getSuffixLink() {
		return suffixLink;
	}

	public Edge[] getEdeEdges() {
		return this.childEdges;
	}
	public Edge getParentEdge() {
		return this.parentEdge;
	}

	public void setSuffixLink(Node suffixLink) {
		this.suffixLink = suffixLink;
	}

	public Edge getEdgeForSymbol(char c) {
		try {
			return childEdges[Utils.getSymbolIndex(c)];
		} catch (Exception e) {
			System.err.println("Invalid symbol - " + c);
			System.exit(-1);
		}

		return null;
	}
	public Edge getEdgeForIndex(int i){
		return childEdges[i];
	}

	public int getDepth() {
		return this.depth;
	}
	public void addOutgoingEdge(Edge e,char c){
		int x=Utils.getSymbolIndex(c);
		this.childEdges[x]=e;
	}
	public void set_numleaves_minstart(int numleaves,int minstart){
		this.start_min=minstart;
		this.num_leaves=numleaves;
	}
	public int getNumLeaves(){
		return this.num_leaves;
	}
	public int getMinStartIndex(){
		return this.start_min;
	}
}
