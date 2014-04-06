public class Node {

	private Edge parentEdge;
	private Edge childEdges[];
	private Node suffixLink;
	private int depth;
	private int id;
	boolean leaf;

	public Node(Node root) {
		parentEdge = null;
		childEdges = new Edge[Utils.SYMBOL_SET_SIZE];
		suffixLink = root;
		if (root == null)
			suffixLink = this;
		depth = 0; // number of chars preceeding it.
		id = Utils.nextNodeId();
		leaf = true;
	}

	public Node(Edge parentEdge, Node root) throws Exception {
		this.parentEdge = parentEdge;
		childEdges = new Edge[Utils.SYMBOL_SET_SIZE];
		suffixLink = root;
		depth = this.parentEdge.traverseUp().depth
				+ this.parentEdge.getEdgelength();
		id = Utils.nextNodeId();
		leaf = true;
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

	public Edge[] getEdges() {
		return this.childEdges;
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

	public int getDepth() {
		return this.depth;
	}

	public int getID() {
		return this.id;
	}

	public void addOutgoingEdge(Edge e, char c) {
		int x = Utils.getSymbolIndex(c);
		this.childEdges[x] = e;
	}
}
