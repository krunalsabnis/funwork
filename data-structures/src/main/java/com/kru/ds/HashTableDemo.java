/**
 * 
 */
package com.kru.ds;

import com.kru.ds.hashtable.Map;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 */

public class HashTableDemo {

	public static void main(String[] args) {
        Map<Integer, Integer>map = new Map<>();
        for (int i = 0; i < 20; i++) {
        	map.add(i, i * 100);
        	/*
        	 * to introduce collision adding key * 1000 - considering the fact
        	 * that our hash function is basic, and likely to derive same
        	 */
        	map.add(i * 1000, i * 100);
        }
        System.out.println(map.getCurrentSize());
        System.out.println(map.remove(20));
        System.out.println(map.remove(20000));
        System.out.println("Total Elements in Table : " + map.getCurrentSize());
        System.out.println("HashTable size " + map.getTableSize());
        System.out.println("Is table Empty? " + map.isEmpty());

        map.display();
	}

}
