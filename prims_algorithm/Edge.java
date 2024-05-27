package prims_algorithm;

public class Edge {
    Vertex neighbor;
    int weight;

    Edge(Vertex neighbor, int weight) {
        this.neighbor = neighbor;
        this.weight = weight;
    }
}
