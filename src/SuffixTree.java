import java.io.PrintStream;

public class SuffixTree {

	private String text;

	private Node root;

	public static int node_count = 0;
	public static int edge_count = 0;

	public SuffixTree(String text) throws Exception {
		this.text = text;
		root = new Node(null);
		createSuffixTree();
		set_numberofleaves_startindex(root);

	}

	private void createSuffixTree() throws Exception {
		if (text != null) {
			S_tree_fuctions func = new S_tree_fuctions(this.root);

			System.out.println("Generating suffix tree for : "
					+ Utils.ellipsize(text));

			Utils.Timer createTreeTimer = new Utils.Timer();

			// Create Tree
			for (int i = 1; i <= this.text.length(); i++) {
				func.Iteration(this.text, i);
			}

			createTreeTimer.stopAndPrint("Suffix tree created");

		} else {
			System.err.println("Text is null. Cannot create tree.");
		}
	}

	public void print(PrintStream stream) {
		node_count = 0;
		edge_count = 0;
		printNode(root, stream);
	}

	private void printNode(Node node, PrintStream stream) {
		node_count++;

		stream.print(node.getID() + ":");

		Edge[] edges = node.getEdges();

		boolean first = true;
		for (int i = 0; i < edges.length; i++) {

			if (edges[i] == null)
				continue;

			if (!first)
				stream.print(";");
			else
				first = false;

			stream.print(edges[i].getStartPosition() + 1);
			stream.print(" ");
			stream.print(edges[i].getEndPosition() + 1);
			stream.print(" ");
			stream.print(edges[i].getendNode().getID());
		}
		stream.println();

		for (int i = 0; i < edges.length; i++) {
			if (edges[i] == null)
				continue;
			else {
				edge_count++;
			}

			printNode(edges[i].getendNode(), stream);
		}
	}

	public void search(String pattern) {
		if (pattern != null) {
			System.out.print("Searching pattern " + Utils.ellipsize(pattern)
					+ " : ");

			Utils.Timer searchPatternTimer = new Utils.Timer();

			// Search for pattern in tree
			int position = getPatternPosition(pattern);

			searchPatternTimer.stop();

			if (position < 0)
				searchPatternTimer.print("Pattern not found in text");
			else
				searchPatternTimer.print("Pattern found at position "
						+ position);

		} else {
			System.err.println("Pattern is null. Cannot search.");
		}
	}

	private int getPatternPosition(String pattern) {

		int length = pattern.length();
		int counter = 0;

		Node node = root;

		while (counter < length) {
			Edge edge = node.getEdgeForSymbol(pattern.charAt(counter));
			// If edge is not null only then further search is possible
			if (edge != null) {

				int edgePosition = edge.getStartPosition();
				int edgeEnd = edge.getEndPosition();

				// Iterate on each character on the edge
				while (edgePosition <= edgeEnd && counter < length) {
					if (text.charAt(edgePosition) == pattern.charAt(counter)) {
						edgePosition++;
						counter++;
					} else
						return -1;
				}
				if (counter == length)
					return edge.getendNode().getMinStartIndex();

			} else
				return -1;

			node = edge.getendNode();
		}
		return -1;
	}

	public void set_numberofleaves_startindex(Node node) throws Exception {

		if (node.leaf) {
			node.set_numleaves_minstart(1, this.text.length()
					- (node.getParentEdge().getEdgelength() + node
							.getParentEdge().getstartNode().getDepth()));
			return;
		}

		int limit = Utils.SYMBOL_SET_SIZE;
		int numleaves = 0;
		int min_start = this.text.length();
		Edge e;
		for (int i = 0; i < limit; i++) {
			e = node.getEdgeForIndex(i);
			if (e == null)
				continue;
			set_numberofleaves_startindex(e.getendNode());
			numleaves += e.getendNode().getNumLeaves();
			if (min_start > e.getendNode().getMinStartIndex())
				min_start = e.getendNode().getMinStartIndex();
		}
		node.set_numleaves_minstart(numleaves, min_start);
	}

}
