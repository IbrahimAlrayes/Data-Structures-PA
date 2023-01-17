
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
		
		if(loc == null) {
			return count;
		}
		TNode<T> newNode = new TNode<T>(loc);
		while(p != null && (p.loc.x != loc.x || p.loc.y != loc.y)) {
			// q is the parent node
			q = p;
			count++;
			if(loc.x < p.loc.x && loc.y <= p.loc.y) {
				
				p = p.c1;
			}
			else if(loc.x <= p.loc.x && loc.y > p.loc.y) {
				p = p.c2;
			}
			else if(loc.x > p.loc.x && loc.y >= p.loc.y) {
				p = p.c3;
			}
			else if(loc.x >= p.loc.x && loc.y < p.loc.y) {
				p = p.c4;
			}
			else {
				break;
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
			count++;		}
		return count;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) { // need to be REVIEWED..... No Need Insha'allah
		// TODO Auto-generated method stub
		Integer count = 0;
		List<T> loclist = new LinkedList<T>();
		Pair<List<T>, Integer> pair = new Pair<List<T>, Integer>(loclist,count);
		
		TNode<T> p = root;
		if(loc == null) {
			return pair;
		}
		
		while(p != null && (p.loc.x != loc.x || p.loc.y != loc.y)) {
			count++;
			if(loc.x < p.loc.x && loc.y <= p.loc.y) {
				p = p.c1;
			}
			else if(loc.x <= p.loc.x && loc.y > p.loc.y) {
				p = p.c2;
			}
			else if(loc.x > p.loc.x && loc.y >= p.loc.y) {
				p = p.c3;
			}
			else if(loc.x >= p.loc.x && loc.y < p.loc.y) {
				
				p = p.c4;
			}
			else {
				break;
			}
		}
		
		if(p==null) {
			pair.second = count;
			return pair;
		}
		else {
			pair.first = p.data;
			pair.second = count+1;
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
		if(loc == null ) {
			return pair;
		}
		
		while(p != null && (p.loc.x != loc.x || p.loc.y != loc.y)) {
			pair.second++;
			if(loc.x < p.loc.x && loc.y <= p.loc.y) {
				
				p = p.c1;
			}
			else if(loc.x <= p.loc.x && loc.y > p.loc.y) {
				
				p = p.c2;
			}
			else if(loc.x > p.loc.x && loc.y >= p.loc.y) {
				
				p = p.c3;
			}
			else if(loc.x >= p.loc.x && loc.y < p.loc.y) {
				
				p = p.c4;
			}
			else {
				break;
			}
		}
		if(p == null) {
			return pair;
		}
		else {
			if(p.data.empty()) {
				return pair;
			}
			else {
				p.data.findFirst();
			
				while(!p.data.last()) {
				
					if(p.data.retrieve().equals(e)) {
					
					p.data.remove();
					pair.first = true;
					
					}
					else {
						p.data.findNext();
					}
				
				}
				if(p.data.retrieve() == e) {
				
					p.data.remove();
					pair.first = true;
				}
				return pair;
				}
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
		Pair<Location, List<T>> pair = new Pair<Location, List<T>>(Node.loc , Node.data);
		l.insert(pair);
		getAll_rec(Node.c1, l);
		getAll_rec(Node.c2, l);
		getAll_rec(Node.c3, l);
		getAll_rec(Node.c4, l);
		
		return l;
	
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		Integer count = 0;
		List<Pair<Location, List<T>>> listofpairs = new LinkedList<Pair<Location, List<T>>>();
		Pair<List<Pair<Location, List<T>>>, Integer> pair = new Pair<List<Pair<Location, List<T>>>, Integer>(listofpairs,count);
		if(lowerLeft ==null || upperRight == null) {
			return pair;
		}
		else {
			return rec_inRange(root, lowerLeft,  upperRight, pair, listofpairs  );
		}
		
	}
	
	private Pair<List<Pair<Location, List<T>>>, Integer> rec_inRange(TNode<T> Node,Location low, Location high, Pair<List<Pair<Location, List<T>>>, Integer> pair ,List<Pair<Location, List<T>>> listofpairs){
		if(Node == null) {
			return pair;
		}
		if((Node.loc.x >= low.x && Node.loc.y >= low.y)&&(Node.loc.x <= high.x && Node.loc.y <= high.y)) {
			
			Pair<Location, List<T>> sPair = new Pair<Location, List<T>>(Node.loc,Node.data);
			listofpairs.insert(sPair);
			pair.first = listofpairs;
			pair.second++;
			
			this.rec_inRange(Node.c1, low, high, pair,listofpairs);
			this.rec_inRange(Node.c2, low, high, pair,listofpairs);
			this.rec_inRange(Node.c3, low, high, pair,listofpairs);
			this.rec_inRange(Node.c4, low, high, pair,listofpairs);
		}
		//Corners
		
		
		//Bottom Left
		if(low.x > Node.loc.x && low.y > Node.loc.y) {
			pair.second++;
			this.rec_inRange(Node.c3, low, high, pair,listofpairs );
			
		}
		//Bottom Right
		if(high.x < Node.loc.x && low.y > Node.loc.y ) {
			pair.second++;
			this.rec_inRange(Node.c2, low, high, pair,listofpairs);
		}
		//Upper Left
		if(low.x > Node.loc.x && high.y < Node.loc.y ) {
			pair.second++;
			this.rec_inRange(Node.c4, low, high, pair,listofpairs);
		}
		//Upper Right
		if(high.x < Node.loc.x && high.y < Node.loc.y ) {
			pair.second++;
			this.rec_inRange(Node.c1, low, high, pair,listofpairs);
		}
		
		// Sides
		
		//Bottom
		if((high.x >= Node.loc.x && low.x <= Node.loc.x) && low.y > Node.loc.y) {
			if(Node.loc.x == high.x) {
				pair.second++;
				this.rec_inRange(Node.c2, low, high, pair,listofpairs );
			}
			else {
				pair.second++;
				this.rec_inRange(Node.c2, low, high, pair,listofpairs );
				this.rec_inRange(Node.c3, low, high, pair,listofpairs);
			}
			
		}
		//Upper 
		if((high.x >= Node.loc.x && low.x <= Node.loc.x) && high.y < Node.loc.y) {
			if(Node.loc.x == low.x) {
				pair.second++;
				this.rec_inRange(Node.c4, low, high, pair,listofpairs );
			}
			else {
				pair.second++;
				this.rec_inRange(Node.c1, low, high, pair,listofpairs );
				this.rec_inRange(Node.c4, low, high, pair,listofpairs);
			}
		
		}
		//Right
		if((high.y >= Node.loc.y && low.y <= Node.loc.y) && high.x < Node.loc.x) {
			if(Node.loc.y == high.y) {
				pair.second++;
				this.rec_inRange(Node.c1, low, high, pair,listofpairs );
			}
			else {
				pair.second++;
				this.rec_inRange(Node.c1, low, high, pair,listofpairs );
				this.rec_inRange(Node.c2, low, high, pair,listofpairs);
			}
			
		}
		//Left
		if((high.y >= Node.loc.y && low.y <= Node.loc.y) && low.x > Node.loc.x) {
			if(Node.loc.y == low.y) {
				pair.second++;
				this.rec_inRange(Node.c3, low, high, pair,listofpairs );
			}
			else {
				pair.second++;
				this.rec_inRange(Node.c3, low, high, pair,listofpairs );
				this.rec_inRange(Node.c4, low, high, pair,listofpairs);
			}
			
		}
		return pair;
	}
	
}
