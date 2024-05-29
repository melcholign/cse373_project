package datastructures;

public class Edge implements Comparable<Edge> {
    public Vertex u;
    public Vertex v;
    public int weight;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
        this.weight = 0;
    }

    public Edge(Vertex u, Vertex v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    public String toString() {
        return v.index + " : "  + weight;
    }
}
