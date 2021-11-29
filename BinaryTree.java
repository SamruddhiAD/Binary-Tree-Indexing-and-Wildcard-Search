import java.util.*;

/**
 * 
 * @author : Samruddhi Deshpande
 * a node in a binary search tree
 */
class BTNode{
	BTNode left, right;
	String term;
	ArrayList<Integer> docLists;
	
	/**
	 * Create a tree node using a term and a document list
	 * @param term the term in the node
	 * @param docList the ids of the documents that contain the term
	 */
	public BTNode(String term, ArrayList<Integer> docList)
	{
		this.term = term;
		this.docLists = docList;
	}
	
}

/**
 * 
 * Binary search tree structure to store the term dictionary
 */
public class BinaryTree {

	/**
	 * insert a node to a subtree 
	 * @param node root node of a subtree
	 * @param iNode the node to be inserted into the subtree
	 */
	public void add(BTNode node, BTNode iNode)
	{
		//TO BE COMPLETED
	if((iNode.term).compareTo(node.term) < 0){
		if(node.left != null){
			//recurrsive call
			add(node.left,iNode);
		}
		else{
			//insert to left node
			node.left=iNode;
		}
	}

	if((iNode.term).compareTo(node.term) > 0){
		if(node.right != null){
			//recurrsive call
			add(node.right,iNode);
		}
		else{
			//insert to right node
			node.right=iNode;
		}	

	}
}

      
	
	
	/**
	 * Search a term in a subtree
	 * @param n root node of a subtree
	 * @param key a query term
	 * @return tree nodes with term that match the query term or null if no match
	 */
	public BTNode search(BTNode n, String key)
	{
		//TO BE COMPLETED
		//	return null;
		while(n != null){
			if((n.term).compareTo(key) == 0 ){
				return n;
			}
			else{
				if((n.term).compareTo(key) < 0){
					n = n.right;
				}
				else{
					n = n.left;
				}
			}
		}
	return null;
	}
	
	/**
	 * Do a wildcard search in a subtree
	 * @param n the root node of a subtree
	 * @param key a wild card term, e.g., ho (terms like home will be returned)
	 * @return tree nodes that match the wild card
	 */
	ArrayList<BTNode> out = new ArrayList<BTNode>();
	public ArrayList<BTNode> wildCardSearch(BTNode n, String key)
	{
      //System.out.println(n);
		
		//TO BE COMPLETED
         if (n == null) {
			return out;
		   }
		  if(n.term.startsWith(key)){
		  wildCardSearch(n.left, key);
		  if (n.term.startsWith(key)) {
			  out.add(n);
		  }
		  wildCardSearch(n.right,key);
		  return out;
		  }
		  else{
			if(n.term.compareTo(key)<0){
				 wildCardSearch(n.right,key);
			}
			else{
				wildCardSearch(n.left,key);
			}
			return out;
		  }
		
	}
	
	/**
	 * Print the inverted index based on the increasing order of the terms in a subtree
	 * @param node the root node of the subtree
	 */
	public void printInOrder(BTNode node)
	{
		
		//TO BE COMPLETED
         if(node != null) {
         printInOrder(node.left);
         System.out.print(node.term + " ");
         printInOrder(node.right);
      }
	}
}

