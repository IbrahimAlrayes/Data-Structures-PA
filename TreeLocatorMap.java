
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	Map<K, Location> bst;
	Locator<K> treeloc;
	
	public TreeLocatorMap() {
		bst = new BST<K, Location>();
		treeloc = new TreeLocator<K>();
	}
	@Override
	public Map<K, Location> getMap() {
		// TODO Auto-generated method stub
		return bst;
	}

	@Override
	public Locator<K> getLocator() {
		// TODO Auto-generated method stub
		return treeloc;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		// TODO Auto-generated method stub
		Pair<Boolean, Integer> pair = bst.find(k);
		
		if(pair.first) {
			pair.first = false;
			return pair;
		}
		else {
			pair.second = treeloc.add(k, loc);
			pair.first = true;
			bst.insert(k, loc);
			return pair;
		}
		
		
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		// TODO Auto-generated method stub
		
		Pair<Boolean, Integer> a = new Pair<Boolean, Integer>(false,0);
		Pair<Location, Integer> b = new Pair<Location, Integer>(null,0);
		a = bst.remove(k);
		b = getLoc(k);
		if(a.first ) {
			a.first = treeloc.remove(k, b.first ).first ;
			a = bst.insert(k, loc);
			treeloc.add(k, loc);
			
		}
		return a;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		// TODO Auto-generated method stub
		Pair<Location, Integer> pair = new Pair<Location, Integer>(null , 0);
		
		if(bst.find(k).first) {
			pair.first = bst.retrieve();
		}
		pair.second = bst.find(k).second ;
		return pair;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		// TODO Auto-generated method stub
		if(k == null) {
			Pair<Boolean, Integer> pair1 = new Pair<Boolean, Integer>(false,0);
			return pair1;
		}
		
		Pair<Boolean, Integer> pair = bst.find(k);
		
		if(pair.first) {
			//pair.second = bst.find(k).second;
			treeloc.remove(k, bst.retrieve()); // Questionable...
			bst.remove(k);
			pair.first = true;
			return pair;
			
		}
		else {
			//pair.second = bst.find(k).second;
			return pair;
		}
	}

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub
		
		return bst.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) { // needs to be completed
		// TODO Auto-generated method stub
		
		List<K> list =  new LinkedList<K>(); //treeloc.inRange(lowerLeft, upperRight).first.retrieve().second;
		
		List<Pair<Location, List<K>>> listofpairs = treeloc.inRange(lowerLeft, upperRight).first;
		Location locs = treeloc.inRange(lowerLeft, upperRight).first.retrieve().first;

		listofpairs.findFirst();
		while(! listofpairs.last()) {
			List<K> innerlist = listofpairs.retrieve().second;
			innerlist.findFirst();
			while(!innerlist.last()) {
				list.insert(innerlist.retrieve());
				innerlist.findNext();
			}
			list.insert(innerlist.retrieve());
			listofpairs.findNext();
		}//End of outter Loop
		
		List<K> innerlist = listofpairs.retrieve().second;
		while(!innerlist.last()) {

			list.insert(innerlist.retrieve());
			innerlist.findNext();
		}
		list.insert(innerlist.retrieve());
		
		Integer count = treeloc.inRange(lowerLeft, upperRight).second;
		
		
		
		Pair<List<K>, Integer> pair = new Pair<List<K>, Integer>(list,count);
		
		
		return pair ;
	}

}
