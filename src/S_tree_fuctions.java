public class S_tree_fuctions {

	public void Iteration(String treestring, int index,
			GlobalUpdate glblupdate, IterationPointer itpointer)
			throws Exception {

		glblupdate.increment();
		String currentprefix = treestring.substring(0, index);
		String len = currentprefix.substring(itpointer.getPointer().getDepth());
		Node startnode = TraverseDown(itpointer.getPointer(), len,
				currentprefix, itpointer, glblupdate);
		Node endnode;
		Edge appropriate_edge = startnode.getEdgeForSymbol(currentprefix
				.charAt(currentprefix.length() - itpointer.offset));

		while (startnode != null) {
			endnode = Extension(appropriate_edge, 0, itpointer, currentprefix,
					glblupdate);
			if (endnode != null) {
				startnode.setSuffixLink(endnode);
				startnode = endnode;
			}
		}

	}

	// stringtotravel is the string including the new character of iteration
	// which has to travel down
	public Node TraverseDown(Node node, String stringtotravel, String Original,
			IterationPointer itpointer, GlobalUpdate glblupdate)
			throws Exception {

		while (stringtotravel.length() != 1) {
			Edge traveledge = getEdge();
			String edgestString = traveledge.getString(Original);
			if (traveledge.getEdgelength() >= stringtotravel.length()) {
				if (stringtotravel.substring(stringtotravel.length() - 1)
						.equals(edgestString.substring(0,
								stringtotravel.length()))) {
					itpointer.setPointer(traveledge.traverseUp(),
							stringtotravel.length());
					return null; // event 3 has happened.
				}
				Edge routerToOldChild = traveledge.partitionEdge(stringtotravel
						.length() - 1);
				Node leaf = new Node();

				Edge routerToLeaf = new Edge(routerToOldChild.getstartNode(),
						leaf, Original.length() - 1, Original.length() - 1);
				leaf.setParentEdge(routerToLeaf);

			} else {
				node = traveledge.getendNode();
				stringtotravel = stringtotravel
						.substring(edgestString.length());
			}

		}

		Edge traveleEdge = getEdge();
		if (traveleEdge != null) {
			itpointer.setPointer(node, 1);
			return null;
		}

		if (node.getEdeEdges().size() == 0) // its a leaf, simply increment the
											// edge
		{
			traveleEdge.setStartPosition(Original.length()
					- traveleEdge.getEdgelength());
			traveleEdge.setEndPosition(glblupdate);

		}

		Node leaf = new Node();
		Edge routerToLeaf = new Edge(node, leaf, Original.length() - 2,
				glblupdate);
		leaf.setParentEdge(routerToLeaf);

		return node;
	}

	// index is number of characters preceeding cur position including cur
	// position
	// note that it includes the character which is added in this iteration.
	public Node Extension(Edge curedge, int index, IterationPointer itpointer,
			String iterationstring, GlobalUpdate glblupdate) throws Exception {
		Node startnode = curedge.traverseUp();
		Node getsuffixnode = startnode.getSuffixLink();
		String stringtotravel = curedge.getString(iterationstring).substring(0,
				index - 1);
		return TraverseDown(getsuffixnode, stringtotravel, iterationstring,
				itpointer, glblupdate);

	}
}
