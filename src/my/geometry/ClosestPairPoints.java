package my.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class ClosestPairPoints {
	
	public static class Point implements Comparable<Point>{
		public int x;
		public int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		// sorted by X coordinate
		public int compareTo(Point p) {			
			// ascending order on x
			return this.x - p.x;
			
			// descending order on x
			//return p.x - this.x;			 
		}
		
		// sorted by Y coordinate
		public static java.util.Comparator<Point> compareY = new java.util.Comparator<Point> (){
			public int compare(Point p1, Point p2) {				
				// ascending order
				return p1.y - p2.y;
				
				// descending order
				//return p2.y - p1.y;
			}			
		};
		
		public double distance(Point p){
			if(p == null)
				return -1;
			
			return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y, 2));			
		}			
	}
	
	/* Divide and Conquer Strategy
	 * 1) Sort the points according to the x coordinate
	 * 2) Find the closest pair by divide by conquer
	 * 3) 
	 */

	public static void main(String[] args){
		//Scanner s = new Scanner(System.in);
		int[][] coordinates = new int[][]{{2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}};
		Point[] points = new Point[coordinates.length];
		
		for(int i=0; i<coordinates.length; i++){
			points[i] = new Point(coordinates[i][0], coordinates[i][1]);
		}
		
		// check
	    System.out.println("Original");
		for(int i=0; i<coordinates.length; i++){
			System.out.println(points[i].x +" "+points[i].y);
		}	
		
		Arrays.sort(points);
		
		System.out.println("Sorted X");
		for(int i=0; i<coordinates.length; i++){
			System.out.println(points[i].x +" "+points[i].y);
		}
		
        /*Arrays.sort(points, Point.compareY);
		
		System.out.println("Sorted Y");
		for(int i=0; i<coordinates.length; i++){
			System.out.println(points[i].x +" "+points[i].y);
		}*/	
		
		double dis = closePoints(points, 0, points.length-1);
		System.out.println("Distance->"+dis);
	}	
	
	public static double closePoints(Point[] points, int start, int end){
		if(start == end)
			return Integer.MAX_VALUE;
		
		if(end - start == 1) {
			return points[start].distance(points[end]);
		}
		
		int mid = (start + end)/2;
		double d1 = closePoints(points, start, mid);
		double d2 = closePoints(points, mid+1, end);
		double delta = Math.min(d1,d2);
		
		ArrayList<Point> stripPoints = findStripPoints(points, start, mid, end, delta);
		Collections.sort(stripPoints, Point.compareY);
		
		int lengthStrip = stripPoints.size();
		double dmin = delta;
		for(int i=1; i< lengthStrip; i++){
			int j = i-1;
			while(j>= 0 && (stripPoints.get(i).y - stripPoints.get(j).y) < delta){
				Point p = stripPoints.get(i);
				double d = p.distance(stripPoints.get(j));
				if(d < dmin){
					dmin = d;
				}
				j--;
			}				
		}
		
		return Math.min(delta, dmin);		
	}

	private static ArrayList<Point> findStripPoints(Point[] points, int start, int mid, int end, double delta) {
		// Points are already sorted in ascending order according to the x coordinate
		
		ArrayList<Point> stripPoints = new ArrayList<Point>();  // using known fact cannot be more than 7 points
		
		// search in first part (start - mid)
		for(int i= mid-1; i>= start; i--) {
			if(points[mid].x - points[i].x <= delta){
				stripPoints.add(points[i]);
			}
		}
		
		for(int i= mid+1; i<= end; i++) {
			if(Math.abs(points[mid].x - points[i].x) <= delta) {
				stripPoints.add(points[i]);
			}
		}			
		return stripPoints;
	}	
}
