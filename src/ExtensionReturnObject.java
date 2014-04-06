
public class ExtensionReturnObject {
	private Edge edgecur;
	private int offset;
	private boolean EVENT3;
	private boolean InternalNodeCreated;
	public ExtensionReturnObject(Edge e,int offset, boolean event3,boolean internalnodecreated){
		this.edgecur=e;
		this.offset=offset;
		this.EVENT3=event3;
		this.InternalNodeCreated=internalnodecreated;
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
	public boolean isInternalNodeCreated(){
		return this.InternalNodeCreated;
	}

}
