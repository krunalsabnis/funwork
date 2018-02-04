package com.kru.ds.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node<T extends Comparable<T>> {
	private T key;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	private boolean isLeftChild;

	public Node(T key) {
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.setLeftChild(false);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.getParent() != null)
			sb.append(" \t [ Parent:" + this.parent.getKey());
		if (this.getRight() != null)
			sb.append(" | Right:"  + this.getRight().getKey());
		if (this.getLeft() != null)
			sb.append(" | Left:" + this.getLeft().getKey());
		sb.append(" | IsLeft:" + this.isLeftChild() + " ]");
		return sb.toString();
	}
}
