
public class TNode<T> {
	List<T> data;
	Location loc;
	TNode<T> c1,c2,c3,c4;
	
	public TNode(Location loc) {
		data = new LinkedList<T>(); //// should we Initialize?
		this.loc = loc;
		c1=c2=c3=c4=null;
	}

	public TNode(List<T> data, Location loc, TNode<T> c1, TNode<T> c2, TNode<T> c3, TNode<T> c4) {
		this.data = data;
		this.loc = loc;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
	}
	
}
