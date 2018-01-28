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

		if (root2 == null)
			root2 = new Node<T>(key);
		else if (key.compareTo(root2.getKey()) > 0)
			root2.setRight(insertNode(root2.getRight(), key));
		else if (key.compareTo(root2.getKey()) < 0)
			root2.setLeft(insertNode(root2.getLeft(), key));
		return root2;
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
