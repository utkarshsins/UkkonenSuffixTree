
public class S_tree_fuctions {

	public void Iteration(String treestring,int index, GlobalUpdate glblupdate,IterationPointer itpointer)
	{
		glblupdate.increment();
		String currentprefix=treestring.substring(1,index);
		String len=currentprefix.substring(itpointer.getPointer().getDepth());
		Node startnode=TraverseDown(itpointer.getPointer(),len);
		Edge appropriate_edge; // find an edge
		while(startnode!=null){
			startnode=Extension(appropriate_edge,0,itpointer,currentprefix);
		}
			
	}
	public Node TraverseDown(Node node, String stringtotravel){
		return null;
	}
	// index is number of characters preceeding cur position including cur position
	public Node Extension(Edge curedge,int index,IterationPointer itpointer,String iterationstring) throws Exception
	{
		Node startnode=curedge.traverseUp();
		Node getsuffixnode=startnode.getSuffixLink();
		String stringtotravel=curedge.getString(iterationstring).substring(0,index-1);
		return TraverseDown(getsuffixnode,stringtotravel);
		
	}
}
