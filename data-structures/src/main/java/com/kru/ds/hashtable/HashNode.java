/**
 * 
 */
package com.kru.ds.hashtable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 * To handle collision use open address or separate chaining method.
 * Here is LinkedList to implement seperate chaining method.
 * 
 * @param <K>
 * @param <V>
 */
@Data
@AllArgsConstructor
public class HashNode<K, V> {
	private K key;
	private V value;
	private HashNode<K, V> next;
}
