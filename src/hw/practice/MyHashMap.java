package hw.practice;

import java.util.Map.Entry;

public class MyHashMap<K, V> {
	// why static class here? --> nested class
	// static nested class belongs to MyHashMap class, no need to have an obj
	private static class MyEntry<K,V>{
		private final K key;
		private V value;
		private MyEntry<K, V> next;
	
		// nested class's constructor should be able to be accessed by outer class ???
		// public or default
		public MyEntry(K key, V value) {
				this.key = key;
				this.value = value;
		}
	
		public K getKey() {
			return key;
		}
	
		// key should be final, and should not be able to set
//		public V setKey(K key) {
//			this.key = key;
//		}
//	
		public V getValue() {
			return value;
		}
	
		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}
		
		public MyEntry<K, V> getNext() {
			return next;
		}
		
		public void setNext(MyEntry<K, V> next) {
			this.next = next;
		}

	}

	// starting the parts of MyHashMap	
	private static final int INITIAL_CAPACITY = 10;
	private static final float LOAD_FACTOR = 0.75f; // by default decimal values mean double, adding f at the end
	private static final int SCALE_FACTOR = 2;
	
	private MyEntry<K, V>[] buckets;
	private int size = 0;
	
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		return key.hashCode() & 0x7fffffff;
	}
	
	//	private int indexFor(int hash) {
	//		return hash % buckets.length;
	//	}
	// better to put hash inside indexFor, so get method does not need to call private methods twice.
	private int getIndex(K key) {
		int hash = hash(key);
		return hash % buckets.length;
	}
	
	private boolean equalsKey(K key1, K key2) {
//		if (key1 == null && key2 == null) {
//			return true;
//		} else if (key1 == null || key2 == null) {
//			return false;
//		} else {
//			return key1.equals(key2);
//		}
		return key1 == key2 || key1 != null && key1.equals(key2);
	}
	
	// ???, not necessary to write a separate one
	private MyEntry<K, V> findEntry(K key) {
		int index = getIndex(key);
		MyEntry<K, V> cur = buckets[index];
		while (cur != null) {
			if (equalsKey(key, cur.getKey())) {
				return cur;
			} 
			cur = cur.getNext();
		}
		return null;
	} 
	
	// !!! good style to put private members together, and public members after, do not mix.
	public MyHashMap() {
		buckets = (MyEntry<K, V>[])new MyEntry[INITIAL_CAPACITY]; // need to cast here !!! otherwise comple error.
	}
	
	// 1) if there is existing value? 
	// 2) if not, if it reaches the loading factor / initial size and need to resize??
	public V put(K key, V value) {
		int index = getIndex(key);
		MyEntry<K, V> node = buckets[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				V oldValue = node.getValue();
				node.setValue(value);
				return oldValue;
			}
			node = node.getNext();
		}
		// here no existing node is found, create a new one.
		MyEntry<K, V> newNode = new MyEntry(key, value);
		newNode.next = buckets[index];
		buckets[index] = newNode;
		size++; // !!! don't forget
		// check if need to resize or not
		if (needResize()) {
			rehash();
		}
		return null;
		
	}
	
	private boolean needResize() {
		return size > (buckets.length * LOAD_FACTOR);
	}
	
	private void rehash() {
		MyEntry<K, V>[] oldArr = buckets;
		buckets = (MyEntry<K, V>[]) new MyEntry[oldArr.length * SCALE_FACTOR];
		for (MyEntry<K, V> node : oldArr) {
			while (node != null) {
				MyEntry<K, V> next = node.getNext();
				int index = getIndex(node.getKey());
				node.setNext(buckets[index]);
				buckets[index] = node;
				node = next;
			}
		}
	}
	
	public V get(K key) {
		int index = getIndex(key);
		MyEntry<K, V> head = buckets[index];
		if (head == null) {
			return null;
		} else {
			MyEntry<K, V> cur = head;
			while (cur != null) {
				// !!! K should implement the equals method, so it can be a valid key type
				// !!! think about when key is null !!! 
				// !!! think about may need to reuse, define a separate method.
				// if (key.equals(cur.getKey())) {
				if (equalsKey(key, cur.getKey())) {
					return cur.getValue();
				} else {
					cur = cur.getNext();
				}
			}
			return null;
		}
	}
	
	public boolean containsKey(K key) {
		MyEntry<K, V> entry = findEntry(key);
		return entry != null;
	}
	
	public V remove(K key) {
		int index = getIndex(key);
		MyEntry<K, V> node = buckets[index];
		MyEntry<K, V> prev = null;
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				if (prev == null) {
					buckets[index] = node.next;
				} else {
					prev.setNext(node.getNext());
				}
				size--; //!!!! important, don't forget
				return node.getValue();
			}
			prev = node;
			node = node.getNext();
		}
		return null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
}
