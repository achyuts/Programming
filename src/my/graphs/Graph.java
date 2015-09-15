package my.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import my.graphs.GraphNode.Visited;

public class Graph {
	
	private int v; // no. of vertices
	private boolean isDirected;
	private ArrayList<GraphNode> nodes;
	private ArrayList<ArrayList<GraphNode>> adjacencyList; 
	
	// used for graph node coloring in bipartite matching
	enum NodeColor {
	    	red, blue
	};
	
	enum NodeVisited{
		white, black
	};
	
	public Graph(int v, boolean isDirected){
		this.v = v;
		this.isDirected = isDirected;
		
		nodes = new ArrayList<GraphNode>(v);
		for(int i=0; i<v; i++){
			nodes.add(new GraphNode(i));
		}
		
		adjacencyList = new ArrayList<ArrayList<GraphNode>>();
		for(int i=0; i<v; i++){
			adjacencyList.add(i, new ArrayList<GraphNode>());
		}
		
	}
	
	public int getVertices(){
		return v;
	}
	
	public boolean getisGraphDirected(){
		return isDirected;
	}
	
	
	public ArrayList<GraphNode> getGraphNodes(){
		return nodes;
	}
	
	public ArrayList<ArrayList<GraphNode>> getAdjacencyList(){
		return adjacencyList;
	}
	
	
	// adds undirected edges the graph (comment the statment for directed edges)
	public boolean insertEdge(int s, int e){
		if(s>= 0 && s<=v-1 && e>=0 && e<=v-1){
			
			GraphNode n1= null, n2= null;
						
			n1 = nodes.get(s);
			n2 = nodes.get(e);
			
			System.out.println("Adj size-->"+adjacencyList.size());
			
			// undirected graph
			adjacencyList.get(s).add(n2);
			
			if(!isDirected)
			    adjacencyList.get(e).add(n1);
			
			return true;
		}
		
		return false;
	}
	
	// BFS does not visit unconnected nodes from the source node
	// We can use instead of color to check for visited/unvisited vertices, a boolean array, so original graph node
	//    does not need to modified
	
	// Running time O(n+m)
	
	public boolean bfs(int startVertex){
		if(startVertex < 1 && startVertex > v){
			System.err.println("No such node");
			return false;
		}
		
		GraphNode startNode = nodes.get(startVertex);
		if(startNode == null){
			System.err.println("No such node");
			return false;
		}
		
		LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
		startNode.setVisitedStatus(Visited.gray);
		queue.add(startNode);
		
		List<GraphNode> ans = new ArrayList<GraphNode>();
		
		while(!queue.isEmpty()){
			
			GraphNode g = queue.remove();
			g.setVisitedStatus(Visited.black);
			ans.add(g);
			
			ArrayList<GraphNode> glist = adjacencyList.get(g.getValue());
			ListIterator<GraphNode> listIterator = glist.listIterator();
			
			while(listIterator.hasNext()) {
				GraphNode gnext = listIterator.next();
				if(gnext.getVisitedStatus() == Visited.white){
					gnext.setVisitedStatus(Visited.gray);
					queue.add(gnext);
				}
			}
		}
		
		System.out.println("BFS Result");
		Iterator<GraphNode> iterate = ans.iterator();
		while(iterate.hasNext()) {
			GraphNode gn = iterate.next();
			System.out.println(gn.getValue());
		}
		return true;
	}	
	
	public void dfs() {
		
		// Instead of color value to check for visited/unvisited vertices, we are using visited boolean array
		boolean[] visited = new boolean[v]; // default assigned to false
		ArrayList<GraphNode> ans = new ArrayList<GraphNode>();
		
		for(int i=0; i<v; i++) {
			if(visited[i] == false)
			    dfs_visit(nodes.get(i), visited, ans);
		}
		
		System.out.println("DFS Result");
		Iterator<GraphNode> iterate = ans.iterator();
		while(iterate.hasNext()) {
			GraphNode gn = iterate.next();
			System.out.println(gn.getValue());
		}
	}

	private void dfs_visit(GraphNode n, boolean[] visited, ArrayList<GraphNode> ans) {
		ans.add(n);
		visited[n.getValue()] = true;
		
		for(GraphNode node: adjacencyList.get(n.getValue())){
		    if(visited[node.getValue()] == false)	
			    dfs_visit(node, visited, ans);
		}
	}	
	
	// Running BFS without using the node color in the GraphNode mentioned
	public boolean bipartite_check(int startVertex){
		if(startVertex < 1 && startVertex > v){
			System.err.println("No such node");
			return false;
		}
		
		GraphNode startNode = nodes.get(startVertex);
		if(startNode == null){
			System.err.println("No such node");
			return false;
		}
		
		NodeVisited visitedNodes[] = new NodeVisited[v];
		NodeColor nodeColor[] = new NodeColor[v];
	    
	    for(int i=0; i<v; i++){
	    	visitedNodes[i] = NodeVisited.white;
	    	nodeColor[i]= NodeColor.red;
	    }
	   
		LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
		
		visitedNodes[startVertex] = NodeVisited.black;
		nodeColor[startVertex] = NodeColor.red;
		queue.add(startNode);
		
		List<GraphNode> ans = new ArrayList<GraphNode>();
		
		boolean notBipartite = false;
		
		while(!queue.isEmpty()){
			
			GraphNode g = queue.remove();
		
			ans.add(g);
			
			ArrayList<GraphNode> glist = adjacencyList.get(g.getValue());
			ListIterator<GraphNode> listIterator = glist.listIterator();
			
			while(listIterator.hasNext()) {
				GraphNode gnext = listIterator.next();
				if(visitedNodes[gnext.getValue()] == NodeVisited.white) {
					visitedNodes[gnext.getValue()] = NodeVisited.black;
					nodeColor[gnext.getValue()] = nodeColor[g.getValue()] == NodeColor.red? NodeColor.blue : NodeColor.red;
					queue.add(gnext);
					
					System.out.println("1-->"+g.getValue()+"color-->"+nodeColor[g.getValue()]+" 2-->"+gnext.getValue()+" color->"+nodeColor[gnext.getValue()]);
					
				} else if (visitedNodes[gnext.getValue()] == NodeVisited.black){
					if(nodeColor[gnext.getValue()] == nodeColor[g.getValue()]){
						System.out.println("Error DETECTED ADJACENT NODES SAME COLOR NOT BIPARTITE");
						System.out.println("1-->"+g.getValue()+"color-->"+nodeColor[g.getValue()]+" 2-->"+gnext.getValue()+" color->"+nodeColor[gnext.getValue()]);
						notBipartite = true;
						break;
					}
				}
			}
			
			if(notBipartite)
				break;
		}
		
		if(!notBipartite) {
			System.out.println("GRAPH IS BIPARTITE");
			Iterator<GraphNode> iterate = ans.iterator();
			while(iterate.hasNext()) {
				GraphNode gn = iterate.next();
				System.out.println(gn.getValue() + " " + nodeColor[gn.getValue()]);
			}
		}
		return true;
	}	
	
	
	
	
	
	
	
	
	// finding all connected components
	
	
	// how to find the topological ordering in DAG in O(m+n) time
	
	
	
	
	
	
	
}