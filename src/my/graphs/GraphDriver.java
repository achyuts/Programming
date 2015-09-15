package my.graphs;


public class GraphDriver {
	
	public static void main(String[] args){
		
		Graph g = new Graph(6, true);
		

		g.insertEdge(0, 1);
		g.insertEdge(1, 2);
		g.insertEdge(0, 2);
		//g.insertEdge(3, 0);
		
		g.insertEdge(3, 4);
		g.insertEdge(4, 5);
		
		
		g.bfs(0);
		
		g.dfs();
		
		g.bipartite_check(0);
		
		//g.dfs_cycle();
		
		CycleDetection.isCyclic(g, true);
		
	}
	

}