package my.tree.binary;

public class BinarySearchTree {
	
	private BinaryTreeNode root;	

	static int height = 0;
	
	public BinarySearchTree(BinaryTreeNode root){
		this.root = root;
	}
	
	public BinarySearchTree(int val){
		root = new BinaryTreeNode(val);
	}
	
	public BinaryTreeNode getRoot(){
		return root;
	}
	
	
	public void insertNode(int val){
		if(root == null) {
			root = new BinaryTreeNode(val);
		}
		
		insertNode(root, val);
		
	}

	private void insertNode(BinaryTreeNode node, int val) {
		BinaryTreeNode x,y;
		
		y = null;
		x =  node;
		
		while(x != null) {
			y = x;
			
			if(val < x.getValue())
				x = x.getLeftChild();
			else if (val > x.getValue())
				x = x.getRightChild();
		}
		
		if( y == null){
			root = new BinaryTreeNode(val);
			return;
		} else {
			if(val < y.getValue())
				y.setLeftChild(new BinaryTreeNode(val));
			else 
				y.setRightChild(new BinaryTreeNode(val));
		}
		
	}
	
	public void deleteNode(int val){
		BinaryTreeNode node = searchTree(val);
		
		if(node == null)
		
	}
	
	
	
	
	public BinaryTreeNode searchTree(int search){
		if(root == null || root.getValue() == search)
			return root;
		
		BinaryTreeNode x = root;
		while(x != null){
			if(search == x.getValue())
				return x;
			else if(search < x.getValue())
				x = x.getLeftChild();
			else
				x = x.getRightChild();				
		}
		
		return null;
	}
	
	public BinaryTreeNode minimumBST(){
		return minimumBST(root);
	}
	
	public BinaryTreeNode minimumBST(BinaryTreeNode node){
		if(node == null)
			return null;
		
		BinaryTreeNode x = node;
		
		while(x.getLeftChild() != null)
			x = x.getLeftChild();
		
		return x;
	}
	
	public BinaryTreeNode maximumBST(){
		return maximumBST(root);
	}
	
	public BinaryTreeNode maximumBST(BinaryTreeNode node){
		if(node == null)
			return null;
		
		BinaryTreeNode x = node;
		
		while(x.getRightChild() != null)
				x = x.getRightChild();

		return x;
	}
	
	
	public BinaryTreeNode findSuccessor(int val){
		BinaryTreeNode node = searchTree(val);
		if(node == null){
			System.err.println("Node not found in BST");
			return null;
		}
		
		if(node.getRightChild() != null){			
			return minimumBST(node.getRightChild());
		} else {
			
			// http://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
			
			BinaryTreeNode successor = null;
			BinaryTreeNode x = root;
			
			while(x != null) {
				if(val < x.getValue()){
					successor = x;
					x = x.getLeftChild();
				} else if (val > x.getValue()){
					x = x.getRightChild();
				} else
					break;
			}			
			return successor;
		}
	}
	
	
	public void inorder(BinaryTreeNode node){
		if(node == null)
			return;
		
		inorder(node.getLeftChild());
		System.out.print(node.getValue()+"  ");
		inorder(node.getRightChild());
	}
	
	public void preorder(BinaryTreeNode node){
		if(node == null)
			return;
		
		System.out.print(node.getValue()+"  ");
		preorder(node.getLeftChild());
		preorder(node.getRightChild());
	}
	
	public void postorder(BinaryTreeNode node){
		if(node == null)
			return;
		
		postorder(node.getLeftChild());
		postorder(node.getRightChild());
		System.out.print(node.getValue()+"  ");
	}
	
	

	public int findHeight(BinaryTreeNode node){
		if(node == null)
			return 0;

		
		height = Math.max(findHeight(node.getLeftChild())+1, findHeight(node.getRightChild())+1);
		System.out.println("Height->"+height);
		
		return height;
	}
	
	
	

}
