package com.kru.ds.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node<T extends Comparable<T>> {
	private T key;
	private Node<T> left;
	private Node<T> right;

	public Node(T key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}
}
