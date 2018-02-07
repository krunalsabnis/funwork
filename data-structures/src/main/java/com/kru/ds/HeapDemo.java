/**
 * 
 */
package com.kru.ds;

import com.kru.ds.heap.Heap;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 */
public class HeapDemo {
	public static void main(String args[]) {
		Heap h = new Heap();
		
		h.insert(40);
		h.insert(98);
		h.insert(56);
		h.insert(32);
		h.insert(4);
		h.insert(72);
		h.insert(12);
		h.insert(87);
		
		h.display();
		
		
		for(int i = 0; i < 8; i++)
			System.out.println(h.remove());
		
	}

}
