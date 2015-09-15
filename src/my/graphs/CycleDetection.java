package my.graphs;

import java.util.ListIterator;

public class CycleDetection {
	
	
	// detected cycle in a directed graph
	
	public static boolean isCyclic(Graph g, boolean graphIsDirected){
		
		boolean visited[] = new boolean[g.getVertices()];
		MyStackList<GraphNode> recStack = new MyStackList<GraphNode>();
		
		for(int i=0; i<g.getVertices(); i++){
			visited[i] = false;
		}
		
		for(int i=0; i<g.getVertices(); i++){
			if(graphIsDirected){
			    if(checkCycleDirected(g, i, visited, recStack)){
				    return true;
			    } 
			} else {
				if(checkCycleUndirected(g, i, visited, recStack))
				    return true;
			}
		}
		
		System.out.println("No Cycle Detected");
		return false;
	}

	public static boolean checkCycleUndirected(Graph g, int i, boolean[] visited, MyStackList<GraphNode> recStack) {
		
		if(visited[i] == false) {
			visited[i] = true;
			recStack.add(g.getGraphNodes().get(i));
			
			if(recStack.size() > 0) {
				GraphNode n = recStack.top();
				
				for(GraphNode gn: g.getAdjacencyList().get(n.getValue())){
					if(!visited[gn.getValue()]){
						if(checkCycleUndirected(g, i, visited, recStack))
							return true;
					} else {
						
						ListIterator<GraphNode> riterate = recStack.listIterator(recStack.size());
						System.out.println("size of recursion stack->"+recStack.size());
						
						while(riterate.hasPrevious()) {
						    GraphNode nrec = riterate.previous();
							if(nrec == n) {
								System.out.print(nrec.getValue()+" ");
								return true;
							} else {
								System.out.print(nrec.getValue()+" ");
							}
						}
						return true;
					}
				}
				
				recStack.pop();
				return false;
				
			}
			
			
		}	
		return false;
	}

	private static boolean checkCycleDirected(Graph g, int i, boolean[] visited, MyStackList<GraphNode> recStack) {
        
		if(visited[i] == false){
			
			visited[i] = true;
			recStack.push(g.getGraphNodes().get(i));
			
			for(GraphNode n : g.getAdjacencyList().get(i)){				
				
				if(!visited[n.getValue()]){
					if(checkCycleDirected(g, n.getValue(), visited, recStack))
					    return true;
				} else if(recStack.contains(n)) {
					
					ListIterator<GraphNode> riterate = recStack.listIterator(recStack.size());
					System.out.println("size of recursion stack->"+recStack.size());
					
					while(riterate.hasPrevious()) {
					    GraphNode nrec = riterate.previous();
						if(nrec == n) {
							System.out.print(nrec.getValue()+" ");
							return true;
						} else {
							System.out.print(nrec.getValue()+" ");
						}
					}
					
					return true;
					
				}				
			}			
		}

		recStack.remove(g.getGraphNodes().get(i));		
		return false;
	}
}