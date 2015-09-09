package my.tree.binary;

public class BinaryTreeNode {
		private int val;
		private BinaryTreeNode left, right;
		
		BinaryTreeNode(int val){
			this.val = val;
			left = right = null;
		}
		
		public void setLeftChild(BinaryTreeNode left){
			this.left = left;
		}
		
		public void setRightChild(BinaryTreeNode right){
			this.right = right;
		}
		
		
		public int getValue(){
			return val;
		}
		
		public BinaryTreeNode getLeftChild(){
			return left;
		}
		
		public BinaryTreeNode getRightChild(){
			return right;
		}	
}