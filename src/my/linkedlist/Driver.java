package my.linkedlist;

import my.linkedlist.SingleLinkedList.Node;

public class Driver {
	
	public static void main(String[] args){
		SingleLinkedList list;
		
		list = new SingleLinkedList(2);
		list.insertNode(1);
		list.insertNode(4);
		list.insertNode(7);
		list.insertNode(1);
		
		System.out.println("New List");
		list.printList();
		
		list.deleteNode(7);
		list.deleteNode(4);
		
		System.out.println("After Deletion->");
		list.printList();
		
		
		Application.scanListForDuplicates(list);
		System.out.println("After Duplicate Removal->");
		list.printList();
		

		list.insertNode(5);
		list.insertNode(6);
		list.insertNode(5);
		list.insertNode(1);
		System.out.println("New List");
		list.printList();
		
		//System.out.println("After Duplicate Removal->");
		//Application.scanListForDuplicates(list);
		//list.printList();
		
		System.out.println("After Duplicate Removal->");
		Application.scanListForDuplicatesUsingHashMap(list);
		list.printList();
		
		System.out.println("Nth to last->");
		list.insertNode(8);
		list.insertNode(9);
		list.printList();
		Application.nthToLast(list, 2);
		Application.nthToLast(list, 3);
		
		
		// sum of two nos
		SingleLinkedList num1 = new SingleLinkedList(2);
		num1.insertNode(8);
		num1.insertNode(9);
		System.out.println("num1");num1.printList();
		
		SingleLinkedList num2 = new SingleLinkedList(9);
		num2.insertNode(8);
		num2.insertNode(9);
		System.out.println("num2");num2.printList();
		
		Application.addTwoNumbers(num1, num2);
		
		
		SingleLinkedList cycleTest = new SingleLinkedList(1);
		
		Node nn1 = cycleTest.new Node(2);
		cycleTest.insertNode(nn1);
		
		cycleTest.insertNode(3);
		cycleTest.insertNode(4);
		
		Node nn2 = cycleTest.new Node(5);
		nn2.setNext(nn1);
		
		cycleTest.insertNode(nn2);
		//cycleTest.printList();
		Application.nodeStartOfCircularLinkedList(cycleTest);
		
		
	}
	
	

}
