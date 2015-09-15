package my.linkedlist;

import java.util.HashMap;

import my.linkedlist.SingleLinkedList.Node;

public class Application {
	
	public static boolean removeDuplicate(Node head, Node prev, int value){
		
		Node n = head;
		
		while(n != prev && n.getValue() != value)
		    n = n.getNext();

		if(n.getValue() == value)
			prev.setNext(prev.getNext().getNext());
			
		return true;
	}
	
	public static boolean scanListForDuplicates(SingleLinkedList list){
		if(list.getHead() == null)
		    return false;
		
		Node head = list.getHead();
		Node n;
		
		if(head.getNext() != null)
		    n = head.getNext();
		else
			return true;
		
		Node prev = head;
		while(n != null) {
			removeDuplicate(head, prev, n.getValue());
			prev = n; n = n.getNext();
		}	
		return true;
	}
	
	public static boolean scanListForDuplicatesUsingHashMap(SingleLinkedList list){
		if(list.getHead() == null)
		    return false;
		
		HashMap<Integer, Boolean> hmap = new HashMap<Integer, Boolean>();
		
		Node n = list.getHead();
		Node prev = n;
		n = n.getNext();
		
		while(n != null){
			if(hmap.containsKey(n.getValue())){
				prev.setNext(n.getNext());
			} else {
				hmap.put(n.getValue(), true);
				prev = n;
			}
			
			n = n.getNext();
		}
		return true;		
	}
	
	public static Node nthToLast(SingleLinkedList list, int n) {
		Node head = list.getHead();
		
		int i=0;
		Node node = head;
		while(i<n && node.getNext() != null){
			node = node.getNext();i++;
		}
		
		if(node.getNext() == null)
			return null;
		
		Node runner = head;
		while(node != null){
			node = node.getNext();
			runner = runner.getNext();
		}
		
		System.out.println("nth->"+n+" to last->"+runner.getValue());
		return runner;		
	}
	
	
	public static void addTwoNumbers(SingleLinkedList num1, SingleLinkedList num2){
		if(num1 == null && num2 == null)
			return;
		if(num1 == null)
			return;
		if(num2 == null)
			return;
		
		SingleLinkedList ans = null;
		
		Node run1, run2;
		run1 = num1.getHead(); run2 = num2.getHead();
		int sum = 0,carry = 0;
		
		while(run1 != null && run2 != null){
			sum = run1.getValue() + run2.getValue() + carry;
			carry = sum/10;
			
			if(ans == null){
				ans = new SingleLinkedList(sum%10);
			} else {
				ans.insertNode(sum%10);
			}	
			
			run1 = run1.getNext(); run2 = run2.getNext();
		}
		
		while(run1 != null){
			sum = run1.getValue() + carry;
			carry = sum/10;
			ans.insertNode(sum%10);
			run1 = run1.getNext();
		}
		
		while(run2 != null){
			sum = run2.getValue() + carry;
			carry = sum/10;
			ans.insertNode(sum%10);
			run2 = run2.getNext();
		}
		
		if(carry != 0)
			ans.insertNode(carry);
		
		ans.printList();	
	}
	
	
	public static void nodeStartOfCircularLinkedList(SingleLinkedList list){
		if(list == null)
			return;
		
		Node n1, n2;
		n1 = list.getHead();
		n2 = list.getHead();
		while(n1 != null && n2 != null && n1 != n2){
			
			n1 = n1.getNext();
			
			if(n2.getNext() != null)
				n2 = n2.getNext().getNext();
			
		}
		
		if(n1 == null || n2 ==null){
			System.out.println("No cycle in the list reached the end of the list");
		    return;
		}
		
		
		if(n1 == n2){
			System.out.println("cycle starting point->"+n1.getValue()+" n2->"+n2.getValue());
			n1 = list.getHead();
			while(n1 != n2){
				n1= n1.getNext(); n2= n2.getNext();
			}
			System.out.println("cycle starting point->"+n1.getValue()+" n2->"+n2.getValue());
			return;
		}	
	}
	
}
