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

	public int getEndPosition() {
		if (endPosition instanceof Integer)
			return ((Integer) endPosition).intValue();
		else
			return ((GlobalUpdate) endPosition).getValue();
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getEdgeLength() {
		return getEndPosition() - getStartPosition() + 1;
	}
}
