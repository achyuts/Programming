package my.tree.binary;

public class BinaryTreeDriver {
	
	public static void main(String[] args){
		BinarySearchTree bst = new BinarySearchTree(7);
		bst.insertNode(4);
		bst.insertNode(12);
		bst.insertNode(8);
		bst.insertNode(44);
		
		System.out.print("Inorder : "); bst.inorder(bst.getRoot());
		System.out.print("Preorder : "); bst.preorder(bst.getRoot());
		System.out.print("Postorder : ");  bst.postorder(bst.getRoot());
		
		
		BinaryTreeNode node = bst.findSuccessor(8);
		if(node != null)
		    System.out.println("\nSuccessor of 8->"+node.getValue());
		else
			System.out.println("No Successor");
		
		node = bst.findSuccessor(12);
		if(node != null)
		    System.out.println("Successor of 12->"+node.getValue());
		else
			System.out.println("No Successor");
		
		node = bst.findSuccessor(44);
		if(node != null)
		    System.out.println("Successor of 44->"+node.getValue());
		else
			System.out.println("No Successor");
	}
	

}
