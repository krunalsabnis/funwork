/**
 * 
 */
package com.kru.ds.hashtable;

import java.util.ArrayList;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 * @param <K>
 *
 */
@AllArgsConstructor
@Data
public class Map<K, V> {
	private ArrayList<HashNode<K, V>> hashTable;
	private int tableSize;
	private int currentSize;
	
	public Map() {
		hashTable = new ArrayList<>();
		currentSize = 0;
		IntStream.range(0, 10).parallel().forEach(
				x -> {
					hashTable.add(null);
				});
		tableSize = hashTable.size();
	}

	
	public boolean isEmpty() {
		return currentSize == 0;
	}

	
	public int getBucketIndex(K key) {
		// shift by 31 bits to get non negative hashcode and so the index
		return (key.hashCode() & 0x7fffffff) % tableSize;
	}

	
	public V remove(K key) {
		int bucketIndex = getBucketIndex(key);
		HashNode<K, V> head = hashTable.get(bucketIndex);
		HashNode<K, V> prev = null;

		while(head != null) {
			if (head.getKey().equals(key)) {
				if (prev == null)
					hashTable.set(bucketIndex, head.getNext());
				else
					prev.setNext(head.getNext());

				return head.getValue();
			}

			prev = head;
			head = head.getNext();
		}

		return null;
	}
	
	
	public V getValue(K key) {
		int bucketIndex = getBucketIndex(key);
		HashNode<K, V> head = hashTable.get(bucketIndex);
		while(head != null) {
			if (head.getKey() == key) {
				currentSize--;
				return head.getValue();
			}
		}
		return null;
	}
	
	
	public void add(K key, V value) {
		int bucketIndex = getBucketIndex(key);
		System.out.println("bucket index " + bucketIndex + " for Key " + key);
		HashNode<K, V> head = hashTable.get(bucketIndex);
		while(head != null) {
			if (head.getKey().equals(key)) {
				head.setValue(value);
				return;
			}
			head = head.getNext();
		}
		currentSize++;
		head = hashTable.get(bucketIndex);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value, head);
		hashTable.set(bucketIndex, newNode);
		growTableIfNeeded();

	}
	
	
	private void growTableIfNeeded() {
		if(1.0 * currentSize/tableSize >= 0.7) {
			// double the table size whenever 70% filled
			tableSize = tableSize * 2;
			System.out.println("expanded internal table to " + tableSize);
			ArrayList<HashNode<K, V>> tempArray = new ArrayList<>(tableSize);
			
			// copy existing entries
			for(int i = 0; i < tableSize; i++) {
				if (i < hashTable.size())
					tempArray.add(hashTable.get(i));
				else
					tempArray.add(null);
			}
			hashTable = tempArray;
		}
	}
}
