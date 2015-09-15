package my.linkedlist;

public class SingleLinkedList {
	
	class Node{
		private int value;
		private Node next;
		
		public Node(int value){
			this.value = value;
			this.next = null;
		}
		
		public void setValue(int value){
			this.value = value;
		}
		
		public void setNext(Node next){
			this.next = next;
		}		
		
		public int getValue(){
			return value;
		}
		
		public Node getNext(){
			return next;
		}
		
	}
	
	Node head;
	
	public SingleLinkedList(int value){
		head = new Node(value);
	}
	
	public Node getHead(){
		return head;
	}
	
	public void insertNode(int value){
		if(head == null){
			head = new Node(value);
			return;
		}
		
		Node n = head;
		while(n.next != null){
			n = n.next;
		}
		
		n.next = new Node(value);
		return;
		
	}
	
	public void insertNode(Node node){
		
		if(head == null){
			head = node;
			return;
		}
		
		Node n = head;
		while(n.next != null){
			n = n.next;
		}
		
		n.next = node;
		return;
	}
	
	
	public boolean deleteNode(int value){
		if(head == null){
			System.err.println("No elements in linked list");
			return false;
		}
		
		Node n = head;
		while(n.next != null && n.next.value != value){
			n = n.next;
		}
		
		if(n.next.value == value){
			n.next = n.next.next;
			return true;
		}
		
		return false;		
	}
	
	public boolean printList(){
		if(head == null)
			return false;
		
		System.out.println();
		Node n = head;
		while(n != null){
			System.out.println(n.value);
			n = n.next;
		}
		
		return true;
		
	}	
}
