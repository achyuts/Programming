package my.Strings;

import java.util.Scanner;

/**
 * Permutations of a String
 * @author achyut
 *
 */

public class Permutation{
	
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		String str = s.next();
		char[] ss = str.toCharArray();
		
		if(ss.length >1)
			permute(ss,0, ss.length-1);
		else
			System.out.println(ss);
	}

	private static void swap(char[] s, int pos1, int pos2) {
		char c = s[pos1];
		s[pos1] = s[pos2];
		s[pos2] = c;
	}
	
	private static void permute(char[] str, int start, int end) {
        if(start == end) {
        	System.out.println(str);
        	return;
        } else {        
	        for(int i=start; i<=end; i++) {
		        swap(str, start, i);
		        permute(str, start+1, end);
		        swap(str, start, i);   
	        }
        }
	}	
}