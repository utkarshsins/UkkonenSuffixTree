
public class ExtensionReturnObject {
	private Edge edgecur;
	private int offset;
	public ExtensionReturnObject(Edge e,int offset){
		this.edgecur=e;
		this.offset=offset;
	}
	public Edge getEdge(){
		return edgecur;
		
	}
	public int getOffset(){
		return offset; // 0 is not valid. In general offset means number of characters which have been seen.
	}

}
