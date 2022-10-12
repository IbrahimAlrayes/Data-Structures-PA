
public class TreeLocator<T> implements Locator<T> {
	TNode<T> root;
	
	public TreeLocator(){		// CONSTRUCTOR
		root = null;
	}
	
	
	@Override
	public int add(T e, Location loc) {// needs another visit
		// TODO Auto-generated method stub
		int count = 0;			// does root count??? Because i didn't count root
								// we count the root by making the counter starts on one
		TNode<T> p = root;
		TNode<T> q = null;
		TNode<T> newNode = new TNode<T>(loc);
		
		while(p != null && (p.loc.x != loc.x || p.loc.y != loc.y)) {
			// q is the parent node
			q = p;
			if(loc.x < p.loc.x && loc.y <= p.loc.y) {
				count++;
				p = p.c1;
			}
			else if(loc.x <= p.loc.x && loc.y > p.loc.y) {
				count++;
				p = p.c2;
			}
			else if(loc.x > p.loc.x && loc.y >= p.loc.y) {
				count++;
				p = p.c3;
			}
			else if(loc.x >= p.loc.x && loc.y < p.loc.y) {
				count++;
				p = p.c4;
			}
		}
		
		if(q == null) {
			root = newNode;
			newNode.data.insert(e);
			return count;
		}
		
		if(p == null) {
			if(loc.x < q.loc.x && loc.y <= q.loc.y) {
				q.c1 = newNode;
				newNode.data.insert(e);
				return count;
			}
		
			else if(loc.x <= q.loc.x && loc.y > q.loc.y) {
				q.c2 = newNode;
				newNode.data.insert(e);
				return count;
			}
			else if(loc.x > q.loc.x && loc.y >= q.loc.y) {
				q.c3 = newNode;
				newNode.data.insert(e);
				return count;
			}
			else if(loc.x >= q.loc.x && loc.y < q.loc.y) {
				q.c4 = newNode;
				newNode.data.insert(e);
				return count;
			}
			
		
		}
		else {
			p.data.insert(e);
		}
		return count;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) { // need to be REVIEWED
		// TODO Auto-generated method stub
		Integer count = 0;
		List<T> loclist = new LinkedList<T>();
		Pair<List<T>, Integer> pair = new Pair<List<T>, Integer>(loclist,count);
		
		TNode<T> p = root;
		
		while(p != null && (p.loc.x != loc.x || p.loc.y != loc.y)) {
			
			if(loc.x < p.loc.x && loc.y <= p.loc.y) {
				count++;
				p = p.c1;
			}
			else if(loc.x <= p.loc.x && loc.y > p.loc.y) {
				count++;
				p = p.c2;
			}
			else if(loc.x > p.loc.x && loc.y >= p.loc.y) {
				count++;
				p = p.c3;
			}
			else if(loc.x >= p.loc.x && loc.y < p.loc.y) {
				count++;
				p = p.c4;
			}
		}
		
		if(p==null) {
			pair.second = count;
			return pair;
		}
		else {
			pair.first = p.data;
			pair.second = count;
			return pair;
			}
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		// TODO Auto-generated method stub
		//need to be visited
		
		Boolean result = false;
		Integer count = 0;
		Pair<Boolean, Integer> pair = new Pair<Boolean, Integer>(result,count);
		
		TNode<T> p = root;
		while(p != null && (p.loc.x != loc.x || p.loc.y != loc.y)) {
			if(loc.x < p.loc.x && loc.y <= p.loc.y) {
				pair.second++;
				p = p.c1;
				System.out.println("Entered 1");
			}
			else if(loc.x <= p.loc.x && loc.y > p.loc.y) {
				pair.second++;
				p = p.c2;
				System.out.println("entered 2");
			}
			else if(loc.x > p.loc.x && loc.y >= p.loc.y) {
				pair.second++;
				p = p.c3;
				System.out.println("entered 3");
			}
			else if(loc.x >= p.loc.x && loc.y < p.loc.y) {
				pair.second++;
				p = p.c4;
				System.out.println("entered 4");
			}
		}
		if(p == null) {
			return pair;
		}
		else {
			p.data.findFirst();
			while(!p.data.last()) {
				
				if(p.data.retrieve() == e) {
					
					p.data.remove();
					pair.first = true;
					
				}
				p.data.findNext();
			}
			if(p.data.retrieve() == e) {
				
				p.data.remove();
				pair.first = true;
			}
			
			return pair;
		}
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		// TODO Auto-generated method stub
		List<Pair<Location, List<T>>> List = new LinkedList<Pair<Location, List<T>>>();
		return getAll_rec(root , List);
	}
	
	private List<Pair<Location, List<T>>> getAll_rec(TNode<T> Node , List<Pair<Location, List<T>>> l ) {
		// TODO Auto-generated method stub
		if(Node == null) {
			return l;
		}
		getAll_rec(Node.c1, l);
		getAll_rec(Node.c2, l);
		getAll_rec(Node.c3, l);
		getAll_rec(Node.c4, l);
		Pair<Location, List<T>> pair = new Pair<Location, List<T>>(Node.loc , Node.data);
		l.insert(pair);
		return l;
	
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		
		//Pair<List<Pair<Location, List<T>>>, Integer> pair = new Pair<List<Pair<Location, List<T>>>, Integer>(pairlist,count);
		return null;
	}
	private Pair<List<Pair<Location, List<T>>>, Integer> rec_inRange(Location low, Location high ){
		return null;
	}
	private Integer countRange(TNode<T> Node , Location low, Location high) {
		if(Node == null) {
			return 0;
		}
		if((Node.loc.x >= low.x && Node.loc.y >= low.y)&&(Node.loc.x <= high.x && Node.loc.y <= high.y)) {
			return 1+ 
					this.countRange(Node.c1, low, high) + this.countRange(Node.c2, low, high)
					+ this.countRange(Node.c3, low, high) + this.countRange(Node.c4, low, high);
		}
		else {
			return 0+ 
					this.countRange(Node.c1, low, high) + this.countRange(Node.c2, low, high)
					+ this.countRange(Node.c3, low, high) + this.countRange(Node.c4, low, high);
		}
	}
}
