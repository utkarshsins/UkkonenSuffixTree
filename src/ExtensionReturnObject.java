
public class ExtensionReturnObject {
	private Edge edgecur;
	private int offset;
	boolean EVENT3;
	public ExtensionReturnObject(Edge e,int offset, boolean event3){
		this.edgecur=e;
		this.offset=offset;
		this.EVENT3=event3;
	}
	public Edge getEdge(){
		return edgecur;
		
	}
	public int getOffset(){
		return offset; // 0 is not valid. In general offset means number of characters which have been seen.
	}
	public boolean isEvent3(){
		return this.EVENT3;
	}

}
