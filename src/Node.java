import java.util.ArrayList;

public class Node {

	private Edge parentEdge;
	private ArrayList<Edge> childEdges;
	private Node suffixLink;
	private int depth;

	public Node() {
		parentEdge = null;
		childEdges = new ArrayList<Edge>(Utils.SYMBOL_SET_SIZE);
		suffixLink = null;
		depth = 0; // number of chars preceeding it.
	}

	public Node(Edge parentEdge) throws Exception {
		this.parentEdge = parentEdge;
		childEdges = new ArrayList<Edge>(Utils.SYMBOL_SET_SIZE);
		suffixLink = null;
		depth = this.parentEdge.traverseUp().depth
				+ this.parentEdge.getEdgelength();
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

	public ArrayList<Edge> getEdeEdges() {
		return this.childEdges;
	}

	public void setSuffixLink(Node suffixLink) {
		this.suffixLink = suffixLink;
	}

	public Edge getEdgeForSymbol(char c) {
		try {
			return childEdges.get(Utils.getSymbolIndex(c));
		} catch (Exception e) {
			System.err.println("Invalid symbol - " + c);
			System.exit(-1);
		}

		return null;
	}

	public int getDepth() {
		return this.depth;
	}
}
