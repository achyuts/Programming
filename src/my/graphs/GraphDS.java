package my.graphs;

import java.util.ArrayList;

public class GraphDS {

	private int graphSize;
	private ArrayList<GraphNode> graphNodes;
	
	// Graph Node structure
	public static class GraphNode{

		private int val;
		private ArrayList<GraphNode> adjacentNodes;
		
		public GraphNode(int val){
			this.val = val;
			adjacentNodes = null;
		}
		
	    public int getValue(){
	    	return val;
	    }
	    
	    public ArrayList<GraphNode> getAdjacentNodes(){
	    	return adjacentNodes;
	    }
	    
	    public void setValue(int val){
	    	this.val = val;
	    }
  
	    public void setAdjacentNode(GraphNode node){
	    	if(adjacentNodes == null){
				adjacentNodes = new ArrayList<GraphNode>();
			}
	    	
	    	adjacentNodes.add(node);
	    }	
	}
	
	public GraphDS(int v){
		graphSize = v;
		graphNodes = new ArrayList<GraphDS.GraphNode>();
		for(int i=1; i<=v; i++)
			graphNodes.add(new GraphNode(i));	
	}
	
	public ArrayList<GraphNode> getGraphNodeList(){
		return graphNodes;
	}
	
	public int getGraphSize(){
		return graphSize;
	}
	
	// Undirected Graph A-B, B-A
	public boolean insertEdge(GraphNode start, GraphNode end){
		if(start == null || end == null) {			
			return false;		
		}
		
		start.setAdjacentNode(end);
		end.setAdjacentNode(start);
		return true;
	}
	
	public boolean insertEdge(int startNode, int endNode){
		if(startNode > 0 && startNode <= graphSize && endNode > 0 && endNode <= graphSize){
			insertEdge(graphNodes.get(startNode), graphNodes.get(endNode));
			return true;
		}
		return false;
	}
	
	public boolean traverseAdjacentNodes(GraphNode node){
		if(node == null)
			return false;
		
		ArrayList<GraphNode> adjacentNodes = node.getAdjacentNodes();
		if(adjacentNodes == null) {
			System.out.println("no adjacent nodes");
			return true;
		}
		
		for(GraphNode adjNode: adjacentNodes){
			System.out.println(adjNode.getValue());
		}
		return true;
	}
	
	public boolean bfs(GraphNode node){	
	    if(node == null)
	    	return false;
	    
	    ArrayList<GraphNode> nodeQueue = new ArrayList<GraphNode>();
	    Queue
	    
	    
	    return true;
	    
	}
}
