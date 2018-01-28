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
        Map<String, Integer>map = new Map<>();
        for (int i = 0; i < 20; i++) {
        	map.add("key-" + i, i * 100);
        }
        System.out.println(map.getCurrentSize());
        System.out.println(map.remove("key-20"));
        System.out.println(map.remove("key-19"));
        System.out.println("Total Elements in Table : " + map.getCurrentSize());
        System.out.println("HashTable size " + map.getTableSize());
        System.out.println("Is table Empty? " + map.isEmpty());

        for(int i = 0; i < map.getTableSize(); i++) {
        	System.out.println("index " + i + " : " +  map.getHashTable().get(i));
        }
	}

}
