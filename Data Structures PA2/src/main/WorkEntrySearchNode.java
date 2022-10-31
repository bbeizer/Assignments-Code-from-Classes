/**
* <this class represents a WorkEntry search node. it holds a doubly linked list of WorkEntry nodes
* each node is associated with an activity key string
* the node has a queue that allows it to do a level order traversal of the entire tree
* each node left right and parent fields of other Work entry search nodes
* each node canfind the root and display the appropriated travesal>
* Known Bugs: <may have implemented some of the methods incorrectly>
*
* @author Benjamin Beizer
* <benjaminbeizer@brandeis.edu>
* <4, 12, 2020>
* COSI 21A PA2
*/
package main;

public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {

	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC

	public String activityKey;

	public DLL<Node<WorkEntry>> workEntries;

	public Queue q;

	/**
	 * constructor for a Work Entry node
	 * creates a queue for the node and sets the activity key
	 * O(1)
	 * @param activity
	 */
	public WorkEntrySearchNode(String activity) {
		activityKey = activity;
		q = new Queue();
	}


	
	/* 
	 * this method looks at two strings and calculates the numerical "distance" between
	 * the characters. used to rank nodes
	 * O(1) 
	 */
	public int compareTo(WorkEntrySearchNode other) {
		return this.activityKey.compareTo(other.activityKey);
	}

	/**
	 * @param node
	 * called on a the current node and searches for the specified node in the parameter
	 * @return desired node or last node encountered in seaech
	 * O(n)
	 */
	public WorkEntrySearchNode search(WorkEntrySearchNode node) {
		WorkEntrySearchNode pointer = this;
		while (pointer.left != null || pointer.right != null) {
			if (pointer.activityKey.compareTo(pointer.activityKey) == 0) {
				return pointer;
			} else if (pointer.activityKey.compareTo(node.activityKey) < 0) {
				pointer = this.right;
			} else {
				pointer = this.left;
			}
		} return pointer;

	}

	/**
	 * @param inserts a node rooted at this current node
	 * @return the inserted node
	 * O(n)
	 */
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		WorkEntrySearchNode searchedNode = this.search(node);
		if (searchedNode.activityKey.compareTo(node.activityKey) == 0) {
			searchedNode.splay();
			return searchedNode;
		} else if (searchedNode.activityKey.compareTo(node.activityKey) < 0) {
			node.parent = searchedNode;
			searchedNode.right = node;
			node.splay();
			return node;
		} else {
			node.parent = searchedNode;
			searchedNode.left = node;
			node.splay();
			return node;
		}
	}

	/**
	 * @param node
	 * recursively prints out and in order representation line by line of the tree
	 * @return a string of the inorder traversal of the tree
	 * O(n)
	 */
	public String toStringHelper(WorkEntrySearchNode node) {
		if (node == null) {
			return "";
		}
		return toStringHelper(node.left) + node.activityKey + "\n" + toStringHelper(node.right);
	}

	/**
	 * @param node
	 * recursively prints out the tree and its node in a string given the root
	 * O(n)
	 * @return a string
	 */
	public String getStructureHelper(WorkEntrySearchNode node) {
		if (node == null) {
			return "";
		}
		return "(" +getStructureHelper(node.left) + node.activityKey + getStructureHelper(right) + ")";	
	}

	/**
	 * recursively prints out and in order representation line by line of the tree given the root
	 * @return a string of the inorder traversal of the tree
	 * O(n)
	 */
	public String toString() {
		return toStringHelper(findRoot());
	}

	/**
	 * @param node
	 * recursively prints out the tree and its node in a string given the root
	 * O(n)
	 * @return a string
	 */
	public String getStructure() {
		return getStructureHelper(findRoot());
	}

	
	/**
	 * @param e
	 * inserts a workentry into the nodes doubly linked list
	 * O(1)
	 */
	public void add(WorkEntry e) {
		Node<WorkEntry> n = new Node<WorkEntry>(e);
		workEntries.insert(n);

	}

	/**
	 * deletes the ith work entry from the DLL
	 * if it deletes the last work entry, if it has a left subtree the max in the left
	 * subtree gets =splayed and attached to v.right
	 * if there no left subtree the right node is returned
	 * if the node is null, null is returned.
	 * if theres still more workentries the current node is returned
	 * O(n)
	 * @param i
	 * @return 
	 * returns the node if it still has work entries
	 * 
	 */
	public WorkEntrySearchNode del(int i) {
		if (workEntries.delete(i) == true) {
			return this;
		} else {
			if (this.left != null) {
				WorkEntrySearchNode pointer = this.left;
				while (pointer.right!= null) {
					pointer = pointer.right;
				}
				pointer.splay();
				pointer.right = this.right;
				return pointer;
			} else if (this.left == null && this.right != null) {
				return this.right;
			} else {
				return null;
			}
		}
	}

	/**
	 * brings a node to the top of a tree
	 * O(n)
	 */
	private void splay() {
		while (this.parent != null)
			if (this.parent.parent == null) {
				if(this == this.parent.left) {
					rotateRight(this.parent);
				} else {
					rotateLeft(this.parent);
				}
			} else {
				if(this == this.parent.right && this.parent == this.parent.parent.left) {
					rotateLeft(this.parent);
					rotateRight(this.parent.parent);
				} else if(this == this.parent.left && this.parent == this.parent.parent.right) {
					rotateRight(this.parent);
					rotateLeft(this.parent.parent);
				} else if(this == this.parent.left && this.parent == this.parent.parent.left) {
					rotateRight(this.parent.parent);
					rotateRight(this.parent);
				} else {
					rotateLeft(this.parent.parent);
					rotateLeft(this.parent.parent);
				}
			}
	}

	/**
	 * performs a single left AVL rotation on a single node
	 * O(1)
	 * 
	 */
	private void rotateLeft(WorkEntrySearchNode v) {
		WorkEntrySearchNode rightChild = v.right;
		v.right = rightChild.left;
		if(rightChild.left != null) {
			rightChild.left.parent = v;
		}
		rightChild.parent = v.parent;
		if(v.parent == null) {
		} else if (v == v.parent.left) {
			v.parent.left = rightChild;
		} else {
			v.parent.right = rightChild;
		}
		rightChild.left = v;
		v.parent = rightChild;
	}

	/**
	 *  performs a single right AVL rotation on a single node "v"
	 * O(1)
	 * 
	 * @param v
	 */
	private void rotateRight(WorkEntrySearchNode v) {
		WorkEntrySearchNode leftChild =  v.left;
		v.left = leftChild.right;
		if (leftChild.right != null) {
			leftChild.right.parent = v;
		}
		leftChild.parent = v.parent;
		if (v.parent == null) {

		} else if (v == v.parent.right) {
			v.parent.right = leftChild;
		} else {
			v.parent.left = leftChild;
		}
		leftChild.right = v;
		v.parent = leftChild;
	}

	/**
	 * prints all the work entries data for a given node
	 * O(n)
	 * @return
	 */
	public String getEntryData() {
		String str = "";
		Node<Node<WorkEntry>> pointer = workEntries.head;
		while (pointer !=null) {
			str = str + pointer.data.data.getDate();
			str = str + pointer.data.data.getTimeSpent();
			str = str + pointer.data.data.getTimeSpent();
			str = str + pointer.data.data.getActivity();
			str = str + pointer.data.data.getDescription();
			str = str + "\n";
			pointer = pointer.next;
		}
		return str;
	}

	/**
	 * method that finds the root of the splay tree
	 * O(n)
	 * @return
	 */
	public WorkEntrySearchNode findRoot(){
		WorkEntrySearchNode pointer = this;
		while(pointer.parent != null) {
			pointer = pointer.parent;
		}
		return pointer;
	}
	/**
	 * prints out a level order traversal of the splay tree using a queue
	 * O(n)
	 * @return
	 */
	public String getByRecent() {
		WorkEntrySearchNode root = findRoot();
		Node<WorkEntrySearchNode> rootNode = new Node<WorkEntrySearchNode>(root);
		q.enqueue(rootNode);
		String str = "";
		while(q.size>0) {
			int nodesOnLevel = q.size();
			for(int i= 0; i<nodesOnLevel; i++) {
				Node<WorkEntrySearchNode> node = q.dequeue();
				if (node.data.left != null) {
					Node<WorkEntrySearchNode> leftc = new Node<WorkEntrySearchNode>(node.data.left);
					q.enqueue(leftc);
				} if(node.data.right != null) {
					Node<WorkEntrySearchNode> rightc = new Node<WorkEntrySearchNode>(node.data.left);
					q.enqueue(rightc);
				}
				str = str + node.data.activityKey + "\n";
			}
		}
		return str;
	}



}
