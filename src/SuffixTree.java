public class SuffixTree {

	private String text;

	private Node root;

	public SuffixTree(String text) {
		this.text = text;
		createSuffixTree(text);
	}

	private void createSuffixTree(String text) {
		if (text != null) {
			System.out.println("Generating suffix tree for : "
					+ Utils.ellipsize(text));

			Utils.Timer createTreeTimer = new Utils.Timer();

			// Create Tree

			createTreeTimer.stopAndPrint("Suffix tree created");

		} else {
			System.err.println("Text is null. Cannot create tree.");
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
		int position = -1;

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
						break;
				}

				if (edgePosition > edgeEnd)
					counter++;
				else
					break;

			} else
				break;
		}

		return position;
	}
}
