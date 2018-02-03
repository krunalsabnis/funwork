/**
 * 
 */
package com.kru.ds.hashtable;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 * Custom Implementation of HashMap using custom HashTable implementation
 * Hash Table uses LinkedList to handle collision - Separate Chaining method
 *  Internal Hash Table expands by doubling its size when 7% full.
 *  
 * @param <K>
 * @param <V>
 */

@AllArgsConstructor
@Data
public class Map<K, V> {
	private ArrayList<HashNode<K, V>> hashTable;
	private int tableSize = 10;
	private int currentSize;
	
	public Map() {
		currentSize = 0;
		init();
	}

	private void init() {
		hashTable = new ArrayList<>(tableSize);
		for (int i = 0; i < tableSize; i++)
			hashTable.add(null);
		/*IntStream.range(1, tableSize).parallel().forEach(
				x -> {
					hashTable.add(null);
				});*/
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
	
	public void display() {
		System.out.println("--------------------------------------");
		for (int i = 0; i < tableSize; i++) {
			HashNode<K, V> hashList = hashTable.get(i);
			if (hashList == null) {
				System.out.println(i + "| -> NULL");
			} else {
				StringBuilder sb = new StringBuilder(i + " |");
				while(hashList != null) {
					sb.append("-> Key : " + hashList.getKey());
					hashList = hashList.getNext();
				}
				System.out.println(sb);
			}
			
		}
	}
	
	
	private void growTableIfNeeded() {
		if (1.0 * currentSize/tableSize >= 0.7) {
			// double the table size whenever 70% filled
			tableSize = tableSize * 2;
			ArrayList<HashNode<K, V>> temp = new ArrayList<>(hashTable.size());
			
			// copy to temp arrayList first
			for(int i = 0; i  < hashTable.size(); i++) {
				temp.add(hashTable.get(i));
			}
			
			System.out.println("expanding internal table to " + tableSize);
			init();
			
			// for each entry in table
			for(int i = 0; i < temp.size(); i++) {
				HashNode<K, V> currentNode = temp.get(i);
					//for each entry in HashNode List at each index
				while(currentNode != null) {
					add(currentNode.getKey(), currentNode.getValue());
					currentNode = currentNode.getNext();
				}
			}
			display();
		}
	}
}
