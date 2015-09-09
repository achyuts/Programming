package my.graphs;


public class GraphDriver {
	
	public static void main(String[] args){
		
		Graph g = new Graph(6);
		

		g.insertEdge(0, 1);
		g.insertEdge(0, 3);
		g.insertEdge(1, 3);
		
		g.insertEdge(4, 5);
		
		
		g.bfs(0);
		
		g.dfs();
	}
	

}
