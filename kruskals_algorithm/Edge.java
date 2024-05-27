package kruskals_algorithm;

public class Edge implements Comparable<Edge> {
    Vertex from;
    Vertex to;
    int weight;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    public Edge(Vertex from, Vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}
