package my.geometry;

public class LineSegmentsIntersection {
	
	public static class Point implements Comparable<Point> {
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
	
	public static class LineSegment{
		private Point p1;
		private Point p2;
		private slopeDefined isSlopeDefined;
		private double slope;
		
		public LineSegment(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			if(p2.x != p1.x) {
			    slope = (p2.y - p1.y) / (p2.x - p1.x);
			    isSlopeDefined = slopeDefined.defined;
			} else {
				slope = Integer.MAX_VALUE;
				isSlopeDefined = slopeDefined.yparallel;
			}
		}
		
		public double getSlope(){
			return slope;
		}	
		
	}
	
	enum slopeDefined {
		yparallel, defined
	};
	
	public static Orientation getOrientation(Point p1, Point p2, Point p3){
		
		LineSegment l1 = new LineSegment(p1, p2);
		LineSegment l2 = new LineSegment(p2, p3);
		
		if (l1.isSlopeDefined == slopeDefined.defined && l2.isSlopeDefined == slopeDefined.defined) {
			if(l1.slope > l2.slope)
			    return Orientation.clockwise;
			else if(l1.slope < l2.slope)
			    return Orientation.couterclockwise;
			else	
			    return Orientation.collinear;		
		} else {
			
		}
	}
	
	public enum Orientation{
		clockwise, couterclockwise, collinear
	};
	
	public static boolean generalCaseOfIntersectionCheck(Orientation o1, Orientation o2, Orientation o3, Orientation o4){
		System.out.println("o1->"+o1+"o2->"+o2+"o3->"+o3+"o4->"+o4);
		
		if(o1 != o2 && o3 != o4){
			System.out.println("Line segments intersect General case true");
			return true;			
		}	

		return false;
	}
	
	public static boolean projectionAxisIntersect(Point p1, Point q1, Point p2, Point q2, boolean xProjection){
		
		int min1, max1, min2, max2;
		
		if(xProjection) {
		    min1 = Math.min(p1.x, q1.x);
		    max1 = Math.max(p1.x, q1.x);
		    min2 = Math.min(p2.x, q2.x);
		    max2 = Math.max(p2.x, q2.x);
		} else {
			min1 = Math.min(p1.y, q1.y);
		    max1 = Math.max(p1.y, q1.y);
		    min2 = Math.min(p2.y, q2.y);
		    max2 = Math.max(p2.y, q2.y);
		}
		
		if(min2>= min1 && min2<= max1)
			return true;
		else if(max2>= min1 && max2<= max1)
		    return true;
		else
			return false;
	}
	
	public static boolean specialCaseOfIntersectionCheck(Orientation o1, Orientation o2, Orientation o3, Orientation o4,
			Point p1, Point q1, Point p2, Point q2){
		
		if((o1 == o2) && (o2 == o3) && (o3 == o4)){
			// projection x should intersect
			boolean res1 = projectionAxisIntersect(p1,q1,p2,q2,true);
			if(res1 == false){
				System.out.println("No intersection Fail Special X projection");
				return false;
			} else {
				// projection y should intersect
				boolean res2 = projectionAxisIntersect(p1,q1,p2,q2,false);
				if(res2) {
					System.out.println("Special case is true Intersecting");
				    return true;
				} else {
					System.out.println("No intersection Fail Special Y projection");
					return false;
				}
			}			
		} else {
			System.out.println("No intersection Fail Special not all collinear");
			return false;
		}			
	}	
	
	public static void main(String[] args){
		Point p1 = new Point(1, 1);
		Point q1 = new Point(10, 1);
		Point p2 = new Point(1, 2);
		Point q2 = new Point(10, 2);
		
		// General case check
		Orientation o1 = getOrientation(p1,q1,p2);
		Orientation o2 = getOrientation(p1,q1,q2);
		Orientation o3 = getOrientation(p2,q2,p1);
		Orientation o4 = getOrientation(p2,q2,q1);
		
		boolean res = generalCaseOfIntersectionCheck(o1,o2,o3,o4);
		if(res == false){
			boolean ress = specialCaseOfIntersectionCheck(o1,o2,o3,o4, p1,q1,p2,q2);
		} 
		
		//System.out.println("Line segments intersect");
	}
}
