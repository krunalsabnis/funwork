/**
 * 
 */
package com.kru.ds.hashtable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 */
@Data
@AllArgsConstructor
public class HashNode<K, V> {
	private K key;
	private V value;
	private HashNode<K, V> next;
}
