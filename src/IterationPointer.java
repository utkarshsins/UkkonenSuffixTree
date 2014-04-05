public class IterationPointer {

	Node pointer;
	int offset;

	IterationPointer(Node node) {
		pointer = node;
		offset = 0;
	}

	public void setPointer(Node node, int offset) {
		pointer = node;
		this.offset = offset;
	}

	public Node getPointer() {
		return pointer;
	}

	public int getOffset() {
		return offset;
	}

}
