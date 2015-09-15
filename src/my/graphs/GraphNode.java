package my.graphs;

public class GraphNode {
	
		private int val;
		private GraphNode parent;
		
		private Visited visited;
		private int depth;
		
		enum Visited{
			white, gray, black
		};
		
		public GraphNode(int val){
			this.val = val;
			parent = null;
			visited = visited.white;
			depth = -1;
		}
		
		// Getter functions
		
	    public int getValue(){
	    	return val;
	    }
	    
	    public GraphNode getParent(){
	    	return parent;
	    }
	    
	    public int getDepth(){
	    	return depth;
	    }
	    
	    public Visited getVisitedStatus(){
	    	return visited;
	    }
	    
	    //Setter functions
	    
	    public void setValue(int val){
	    	this.val = val;
	    }
  
	    public boolean setParent(GraphNode node){
	    	if(node == null){
	    		System.err.println("Parent cannot be null");
				return false;
			}
	    	
	    	parent = node;
	    	return true;
	    }
	    
	    public void setVisitedStatus(Visited visit){
	    	visited = visit;
	    }
	    
	    public void setDepth(int depth){
	    	this.depth = depth;
	    } 
	    
}