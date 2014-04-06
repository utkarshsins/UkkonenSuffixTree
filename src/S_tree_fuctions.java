public class S_tree_fuctions {
	GlobalUpdate globalupdate;

	IterationPointer iterationpointer;
	Node root; // this is the root node.

	public S_tree_fuctions(Node node) {
		globalupdate = new GlobalUpdate();
		iterationpointer = new IterationPointer(node);
		this.root = node;

	}

	public void Iteration(String treestring, int index) throws Exception {
		String len;
		GlobalUpdate glblupdate = this.globalupdate;
		IterationPointer itpointer = this.iterationpointer;
		glblupdate.increment();
		String currentprefix = treestring.substring(0, index);
		if (index != 1)
			len = currentprefix.substring(index - 1 - itpointer.getOffset());
		else
			len = currentprefix;
		if (itpointer.getPointer().equals(this.root) && len.length() == 1) {
			TraverseDown(itpointer.getPointer(), len, currentprefix, itpointer,
					glblupdate);
			return;
		}
		ExtensionReturnObject obj = TraverseDown(itpointer.getPointer(), len,
				currentprefix, itpointer, glblupdate);

		ExtensionReturnObject endobj;

		while (obj != null && index > 2 && !obj.isEvent3()) {
			endobj = Extension(obj.getEdge(), obj.getOffset(), itpointer,
					currentprefix, glblupdate,obj.isInternalNodeCreated());
			if (endobj != null && obj.isInternalNodeCreated()) {				
				obj.getEdge().getstartNode()
						.setSuffixLink(endobj.getEdge().getstartNode());
				
			}
			obj = endobj;

		}

	}

	// stringtotravel is the string including the new character of iteration
	// which has to travel down
	public ExtensionReturnObject TraverseDown(Node node, String stringtotravel,
			String Original, IterationPointer itpointer, GlobalUpdate glblupdate)
			throws Exception {
		Edge parentedge = null;
		ExtensionReturnObject obj;

		while (stringtotravel.length() != 1) {
			Edge traveledge = node.getEdgeForSymbol(stringtotravel.charAt(0));
			String edgestString = traveledge.getString(Original);

			if (traveledge.getEdgelength() >= stringtotravel.length()) {
				if (stringtotravel.charAt(stringtotravel.length() - 1) == (edgestString
						.charAt(stringtotravel.length() - 1))) {
					itpointer.setPointer(traveledge.traverseUp(),
							stringtotravel.length());
					return new ExtensionReturnObject(traveledge, 0, true,false); // event
																			// 3
																			// has
																			// happened.,
																			// offset
																			// is
																			// irrelevant.
				}

				Edge routerToOldChild = traveledge.partitionEdge(
						stringtotravel.length() - 2, this.root,
						edgestString.charAt(stringtotravel.length() - 1)); // only
																			// last
																			// char
																			// will
																			// go
																			// in
																			// new
																			// edge
			//	makeSuffixLink(routerToOldChild.getstartNode(), Original);
				Node leaf = new Node(this.root);

				Edge routerToLeaf = new Edge(routerToOldChild.getstartNode(),
						leaf, Original.length() - 1, glblupdate); // assumes
																	// that when
																	// adding
																	// new
																	// edges,
																	// endpoint
																	// will be
																	// global
				routerToOldChild.getstartNode().addOutgoingEdge(routerToLeaf,
						stringtotravel.charAt(stringtotravel.length() - 1));
				leaf.setParentEdge(routerToLeaf);
				itpointer.setPointer(this.root, 0); // it can be done as event 3
													// will occur only after
													// this. If it doesn't
													// happen then also we are
													// good.
				return new ExtensionReturnObject(routerToLeaf,
						routerToLeaf.getEdgelength(), false,true);

			} else {
				node = traveledge.getendNode();
				stringtotravel = stringtotravel
						.substring(edgestString.length());
				parentedge = traveledge;
			}

		}

		Edge traveleEdge = node.getEdgeForSymbol(stringtotravel
				.charAt(stringtotravel.length() - 1));
		if (traveleEdge != null) {
			itpointer.setPointer(node, 1);
			return new ExtensionReturnObject(traveleEdge, 0, true,false); // event 3
																	// has
																	// happened,
																	// offset is
																	// irrelvant
		}
		itpointer.setPointer(this.root,0);
		traveleEdge = parentedge;
		if (node.leaf) // its a leaf, simply increment the
						// edge
		{
			if (traveleEdge != null) {
				traveleEdge.setStartPosition(Original.length()
						- traveleEdge.getEdgelength());
				traveleEdge.setEndPosition(glblupdate);
				traveleEdge.getendNode().setParentEdge(traveleEdge); // setting
																		// the
																		// depth
			} else { // in 1st iteration alone this will come
				Node endnode = new Node(this.root);
				traveleEdge = new Edge(node, endnode, 0, glblupdate);
				node.addOutgoingEdge(traveleEdge, stringtotravel.charAt(0));
				endnode.setParentEdge(traveleEdge);
			}
			obj = new ExtensionReturnObject(traveleEdge,
					traveleEdge.getEdgelength(), false,false);
			return obj;

		}

		Node leaf = new Node(this.root);
		Edge routerToLeaf = new Edge(node, leaf, Original.length() - 1,
				glblupdate);
		node.addOutgoingEdge(routerToLeaf, stringtotravel.charAt(0));
		leaf.setParentEdge(routerToLeaf);

		return new ExtensionReturnObject(routerToLeaf, 1, false,false);
	}

	// index is number of characters preceeding cur position including cur
	// position
	// note that it includes the character which is added in this iteration.
	public ExtensionReturnObject Extension(Edge curedge, int index,
			IterationPointer itpointer, String iterationstring,
			GlobalUpdate glblupdate,boolean prevNodeCreated) throws Exception {
		Node startnode,getsuffixnode;
		String stringtotravel;
		startnode = curedge.traverseUp();		
		if(!prevNodeCreated){		
			getsuffixnode = startnode.getSuffixLink();		
		}
		else{
			index+=startnode.getParentEdge().getEdgelength();
			startnode=startnode.getParentEdge().getstartNode();
			getsuffixnode=startnode.getSuffixLink();
					
		}
		//String stringtotravel = edgestring.substring(0, index);
		int differ = startnode.getDepth() - getsuffixnode.getDepth();
		stringtotravel = iterationstring.substring(iterationstring.length() - 1
				- (differ - 1) - (index - 1));
		if (stringtotravel.length() == 1 && getsuffixnode.equals(this.root)) { // we
																				// have
																				// to
																				// add
																				// only
																				// last
																				// char
																				// of
																				// iteration.

			TraverseDown(getsuffixnode, stringtotravel, iterationstring,
					itpointer, glblupdate);
			return null;
		}
		return TraverseDown(getsuffixnode, stringtotravel, iterationstring,
				itpointer, glblupdate);

	}
/*
	public void makeSuffixLink(Node node, String iterationstring)
			throws Exception {
		Node parent = node.getParentEdge().getstartNode();
		int diff;
		int depthpar = parent.getDepth();
		String strtotravel;
		Node suffpar = parent.getSuffixLink();
		if (!parent.equals(this.root)) {
			int depthsuffpar = suffpar.getDepth();
			diff = depthpar - depthsuffpar - 1;
			strtotravel = iterationstring.substring(iterationstring.length()
					- 1 - (+node.getParentEdge().getEdgelength() + diff),
					iterationstring.length() - 1);
		} else {
			diff = 0;
			strtotravel = node.getParentEdge().getString(iterationstring)
					.substring(1);
		}
		if (strtotravel.length() > 0)
			node.setSuffixLink(reachNode(suffpar, strtotravel));

	}

	public Node reachNode(Node node, String stringtotravel) throws Exception {

		Edge appropriate_edge = node.getEdgeForSymbol(stringtotravel.charAt(0));
		while (stringtotravel.length() > appropriate_edge.getEdgelength()) {
			node = appropriate_edge.getendNode();

			stringtotravel = stringtotravel.substring(appropriate_edge
					.getEdgelength());
		}
		return node;
	}
	*/

}
