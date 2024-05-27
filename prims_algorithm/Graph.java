package prims_algorithm;

public class Graph {

    Vertex[] adjacencyList;
    int order;

    Graph(int maxOrder) {
        adjacencyList = new Vertex[maxOrder];
    }

    void addVertex(Vertex vertex) {
        adjacencyList[order] = vertex;
        ++order;
    }

    void addEdge(int u, int v, int weight) {
        adjacencyList[u].neighborhood.add(new Edge(adjacencyList[v], weight));
        adjacencyList[v].neighborhood.add(new Edge(adjacencyList[u], weight));
    }
}
