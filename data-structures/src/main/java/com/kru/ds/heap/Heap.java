package com.kru.ds.heap;

import lombok.Data;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 */

@Data
public class Heap {
	private Node heap[];
	private int heapSize;

	private static final int ROOT = 0;
	private static final int MAX = 50;


	public Heap() {
		this.heap = new Node[MAX];
		this.heapSize = 0;
	}

	public int getHeapSize() {
		return this.heapSize;
	}

	public boolean insert(int key) {
		if (heapSize == MAX)
			return false;
		Node n = new Node(key);
		heap[heapSize] = n;
		trickleUp(heapSize++);
		return true;
	}
	
	public Node remove() {

		Node removedNode = heap[ROOT];

		heap[ROOT] = heap[--heapSize];
		trickleDown(ROOT);
		return removedNode;
	}

	private void trickleDown(int index) {
		Node topNode = heap[index];
		while(index < heapSize/2 ) {
			int leftChild = (index * 2) + 1;
			int rightChild = leftChild + 1;
			
			int largeChild = rightChild < heapSize && heap[leftChild].getKey() < heap[rightChild].getKey() ?
					rightChild : leftChild;
			
			if (topNode.getKey() >= heap[largeChild].getKey())
				break;
			
			heap[index] = heap[largeChild];
			index = largeChild;			
		}
		heap[index] = topNode;
		
	}

	private void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node newlyAdded = heap[index];
		while (index > 0 && heap[parent].getKey() < newlyAdded.getKey()) {
			heap[index] = heap[parent];
			index = parent;
			parent = (parent - 1) /  2;
		}
		heap[index] = newlyAdded;
	}
	
	public void display() {
		for(int i = 0; i < heapSize; i ++)
			System.out.println("[" + i + "]" + heap[i]);
	}

}
