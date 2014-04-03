import java.util.ArrayList;

public class Node {

	private Edge parentEdge;
	private ArrayList<Edge> childEdges;
	private Node suffixLink;

	public Node() {
		parentEdge = null;
		childEdges = new ArrayList<Edge>(Utils.SYMBOL_SET_SIZE);
		suffixLink = null;
	}

	public Node(Edge parentEdge) {
		this.parentEdge = parentEdge;
		childEdges = new ArrayList<Edge>(Utils.SYMBOL_SET_SIZE);
		suffixLink = null;
	}

	public void setParentEdge(Edge parentEdge) {
		this.parentEdge = parentEdge;
		parentEdge.setEndNode(this);
	}

	public Node getSuffixLink() {
		return suffixLink;
	}

	public void setSuffixLink(Node suffixLink) {
		this.suffixLink = suffixLink;
	}
}
