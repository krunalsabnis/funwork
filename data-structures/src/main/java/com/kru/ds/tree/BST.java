/**
 * 
 */
package com.kru.ds.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 */
@Data
@AllArgsConstructor
public class BST<T extends Comparable<T>> {
	private Node<T> root;

	public BST() {
		this.root = null;
	}

	public void insert(T key) {
		root = insertNode(root, key);
		System.out.println("inserted " + key);
	}


	private Node<T> insertNode(Node<T> root2, T key) {
		Node<T> newNode = null;
		if (root2 == null)
			root2 = new Node<T>(key);
		else if (key.compareTo(root2.getKey()) > 0) {
			newNode = insertNode(root2.getRight(), key);
			root2.setRight(newNode);
			newNode.setParent(root2);
		} else if (key.compareTo(root2.getKey()) < 0) {
			newNode = insertNode(root2.getLeft(), key);
			root2.setLeft(newNode);
			newNode.setParent(root2);
			newNode.setLeftChild(true);
		}
		return root2;
	}
	
	public Node<T> search(T key) {
		Node<T> result = search(root, key);
		String printStatement = result == null ? "Key : " + key + " not found" : "Key : " + key + " " + result;
		System.out.println(printStatement);
		return result;
	}

	public Node<T> delete(T key) {
		Node<T> node = search(key);
		if (node == null) {
			System.out.println("Nothing to delete");
			return node;
		}
		
		// Case 1: No Child for the node which is to be deleted
		if (node.getLeft() == null && node.getRight() == null) {
			node.getParent().setLeft(null);
			node.getParent().setRight(null);
			// Case 2: There is one child (left or right)
		} else if (node.getLeft() == null) {	// Case 2.1 No Left Child only Right one is present
			if (node.isLeftChild())
				// if node is left child, attached Right one to Left of Parent
				node.getParent().setLeft(node.getRight());	
			else
				// if node is Right child, attached Right one to Right of Parent
				node.getParent().setRight(node.getRight());	
		} else if (node.getRight() == null) {	// Case 2.2 No Right Child only Left one is present
			if (node.isLeftChild())
				node.getParent().setLeft(node.getLeft());
			else
				node.getParent().setRight(node.getLeft());
		} else {
			// Case 3. Node which is to be deleted has two children - Chose successor
			Node<T> successor = node.getRight();
			while(successor.getLeft() != null) {
				successor = successor.getLeft();
			}
			successor.setLeft(node.getLeft());
			if (successor.getKey() != node.getRight().getKey())
				successor.setRight(node.getRight());
			if (node.isLeftChild())
				node.getParent().setLeft(successor);
			else
				node.getParent().setRight(successor);
			
			if (successor.isLeftChild())
				successor.getParent().setLeft(null);
			else
				successor.getParent().setRight(null);
			System.out.println("Successor for " + key + " is " + successor.getKey());

		}
		return node;
	}

	private Node<T> search(Node<T> root2, T key) {
		if (root2 == null)
			return null;
		else if (key.compareTo(root2.getKey()) < 0)
			return search(root2.getLeft(), key);
		else if (key.compareTo(root2.getKey()) > 0)
			return search(root2.getRight(), key);
		else if (key.compareTo(root2.getKey()) == 0)
			return root2;

		return null;
	}

	private void traverse(Node<T> root1, boolean inorder) {
		if (root1 != null) {
			Node<T> first = inorder ? root1.getLeft() : root1.getRight();			
			Node<T> second = inorder ? root1.getRight() : root1.getLeft();

			traverse(first, inorder);
			System.out.println(root1.getKey());
			traverse(second, inorder);
		}
	}
	
	public void traverse(boolean inorder) {
		traverse(root, inorder);
	}
}
