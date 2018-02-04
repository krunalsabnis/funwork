/**
 * 
 */
package com.kru.ds;

import com.kru.ds.tree.BST;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 */
public class BSTDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80
              \
              45         */
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        //bst.insert(39);
        bst.insert(45);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);
 
        System.out.println("Ascending order");
        // print inorder traversal of the BST
        bst.traverse(true);
        System.out.println("Descending order");
        // print postorder traversal of the BST
        bst.traverse(false);
        
        System.out.println("--- search ---");
        bst.search(70);
        bst.search(89);
        
        System.out.println("--- delete ---");
        bst.delete(80);
        bst.traverse(false);
        System.out.println("------");
        bst.traverse(true);
        System.out.println("------");
        bst.delete(30);
        bst.traverse(false);
        System.out.println("------");
        bst.traverse(true);

	}

}
