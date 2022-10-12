
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
		BSTNode<K,T> p = root;
		Integer count = 0; 
		Boolean result = false;
		
		if(empty())
			result = false;
		
		while(p != null) {
			if(key.compareTo(p.key) == 0) {
				current = p;
				count++;
				result = true;
				break;
			}
			else if(key.compareTo(p.key) < 0) {
				p = p.left;
				count++;
			}	
			else {
				p = p.right;
				count++;
			}
		}
		//current = q; current is unchanged
		
		Pair<Boolean , Integer> pair = new Pair<Boolean, Integer>(result,count);
		return pair;
	
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {//needs to be completed.........
		BSTNode<K,T> p = current;
		Boolean result = true;
		Integer count = 0; // does count = find(key).second ;?????
		
		Pair<Boolean , Integer> pair = new Pair<Boolean, Integer>(result,count);
		if(find(key).first) {
			pair.first = false;
			pair.second = find(key).second;
			return pair;
		}
		BSTNode<K,T> newNode = new BSTNode<K,T>(key,data);
		if(empty())
			root = current = newNode;
		
		
		if(key.compareTo(p.key) > 0) {
			p.right = newNode;
			pair.second++;
			current = newNode ;
		}
		else {
			p.left = newNode;
			pair.second++;
			current = newNode ;
		}
		
		return pair;
		}
			
	

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		// TODO Auto-generated method stub
		Boolean removed = false;
		Integer count = 0;
		Pair<Boolean, Integer> pair = new Pair<Boolean, Integer>(removed,count);
		BSTNode<K,T> p;
		p = remove_aux(key, root, pair);
		//current = root = p;
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
