
public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	BSTNode<K,T> root, current;
	
	
	public BST() {
		root = current = null;
	}
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T retrieve() {
		// TODO Auto-generated method stub
		return current.data;
	}

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		// TODO Auto-generated method stub
		Pair<Boolean , Integer> pair = new Pair<Boolean, Integer>(false,0);
		if(root == null || key ==null) {
			return pair;
		}
		BSTNode<K,T> p = root;
		
		
		
		while(p != null) {
			if(key.compareTo(p.key) > 0) {
				p = p.right;
				pair.second++;
			}
			else if(key.compareTo(p.key) < 0) {
				p = p.left;
				pair.second++;
			}	
			else {
				current = p;
				pair.second++;
				pair.first = true;
				return pair;
			}
		}
		//current = q; current is unchanged
		
		
		return pair;
	
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {//needs to be completed.........
		
		Pair<Boolean , Integer> find = find(key);
		if(key ==null) {
			Pair<Boolean , Integer> pair1 = new Pair<Boolean, Integer>(find.first,find.second);
			return pair1 ;
		}
		BSTNode<K,T> oldcurr = current;
		Pair<Boolean , Integer> pair = new Pair<Boolean, Integer>(true,0);
		
		if(find.first) {
			current = oldcurr;
			pair.first = false;
			pair.second = find.second;  //// better make attention to this
			return pair;
		}
		BSTNode<K,T> newNode = new BSTNode<K,T>(key,data);
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = null;
		while(p != null) {
			q = p;
			if(key.compareTo(p.key) > 0) {
				p = p.right;
				pair.second++;
				}
			
			else {
				p = p.left;
				pair.second++;
				}
			}
		if(q == null) {
			root = current = newNode;
			pair.first = true;
			return pair;
		}
			
		
		if(p == null) {
			if(key.compareTo(q.key) > 0) {
				q.right = newNode;
				current = newNode;
				pair.first = true;
				pair.second++;
				return pair;
			}
			else {
				q.left = newNode;
				current = newNode;
				pair.first = true;
				pair.second++;
				return pair;
			}
		}
		return pair;
		}
			
	

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		// TODO Auto-generated method stub
		Boolean removed = false;
		Integer count = 0;
		Pair<Boolean, Integer> pair = new Pair<Boolean, Integer>(removed,count);
		if(key == null) {
			return pair;
		}
		BSTNode<K,T> p;
		p = remove_aux(key, root, pair);
		current = root = p;
		return pair;
	}
	// Helper method for removing
	private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, Pair<Boolean,Integer> pair) {
		BSTNode<K,T> q, child = null;
		if(p == null)
			return null;
		if(key.compareTo( p.key) < 0) {
			pair.second ++;
			p.left = remove_aux(key, p.left, pair);//go left
		}											
		else if(key.compareTo(p.key) > 0) {
			pair.second++;
			p.right = remove_aux(key, p.right, pair); //go right
		}
		else {
			pair.first = true;
			pair.second ++;
			if (p.left != null && p.right != null){ //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, pair);
			}
			else {
				if (p.right == null) //one child
					child = p.left;
				else if (p.left == null) //one child
					child = p.right;
				return child;
			}
		}
		return p;
	}
	// Helper method for removing
	private BSTNode<K,T> find_min(BSTNode<K,T> p){
		if(p == null)
			return null;
		
		while(p.left != null){
			p = p.left;
		}
		
		return p;
	}



	@Override
	public List<K> getAll() {
		List<K> List = new LinkedList<K>();
		return getAllrec(root,List);
		
	}
	// ***********A Recursive method************
	private List<K> getAllrec(BSTNode<K,T> Node, List<K> l){
		if(Node == null){
			return l;
		}
		getAllrec(Node.left, l);
		l.insert(Node.key);
		getAllrec(Node.right, l);
		return l;
	}
	
}
