import java.util.ArrayList;
import java.util.Arrays;
import datastructures.*;

public class MSTAlgorithms {

    public static ArrayList<Edge> kruskals(Vertex[] vertices, Edge[] edges) {
        ArrayList<Edge> minEdges = new ArrayList<>();

        for (Vertex vertex : vertices) {
            DisjointSet.makeSet(vertex);
        }

        Arrays.sort(edges);

        for (Edge edge : edges) {
            if (DisjointSet.findSet(edge.u) != DisjointSet.findSet(edge.v)) {
                minEdges.add(edge);
                DisjointSet.union(edge.u, edge.v);
            }
        }

        return minEdges;
    }

    public static void prims(Vertex[] vertices, ArrayList<ArrayList<Edge>> adjacencyList, Vertex root) {
        for (Vertex vertex : vertices) {
            vertex.key = Integer.MAX_VALUE;
            vertex.parent = null;
        }

        root.key = 0;

        MinHeap priorityQueue = new MinHeap(vertices.length);

        for (Vertex vertex : vertices) {
            priorityQueue.insert(vertex);
        }

        while (priorityQueue.heapSize != 0) {
            Vertex u = priorityQueue.extractMin();

            for (Edge edge : adjacencyList.get(u.index)) {
                Vertex v = edge.v;

                if (priorityQueue.indexOf(v) != -1 && edge.weight < v.key) {
                    v.parent = u;
                    v.key = edge.weight;
                    System.out.println(u.index + ", " + u.key + " : " + edge.weight);
                    priorityQueue.decreaseKey(v, edge.weight);
                }
            }
        }
    }

    public static ArrayList<Edge> boruvkas(Vertex[] vertices, Edge[] edges) {
        ArrayList<Edge> minEdges = new ArrayList<>();
        Edge[] cheapest = new Edge[vertices.length];

        int numTrees = vertices.length;

        for (Vertex vertex : vertices) {
            DisjointSet.makeSet(vertex);
        }

        while (numTrees > 1) {

            for (Edge edge : edges) {

                Vertex firstSet = DisjointSet.findSet(edge.u);
                Vertex secondSet = DisjointSet.findSet(edge.v);

                if (firstSet != secondSet) {
                    if (cheapest[firstSet.index] == null || cheapest[firstSet.index].weight > edge.weight) {
                        cheapest[firstSet.index] = edge;
                    }

                    if (cheapest[secondSet.index] == null || cheapest[secondSet.index].weight > edge.weight) {
                        cheapest[secondSet.index] = edge;
                    }
                }
            }

            for (Vertex vertex : vertices) {
                Edge cheapEdge = cheapest[vertex.index];
                if (cheapEdge != null && DisjointSet.findSet(cheapEdge.u) != DisjointSet.findSet(cheapEdge.v)) {
                    DisjointSet.union(cheapEdge.u, cheapEdge.v);
                    minEdges.add(cheapEdge);
                    numTrees--;
                }
            }

            for (int i = 0; i < cheapest.length; ++i) {
                cheapest[i] = null;
            }
        }

        return minEdges;
    }

    public static int sumOfMinEdges(ArrayList<Edge> edges) {
        int sum = 0;
        for (Edge edge : edges) {
            sum += edge.weight;
        }

        return sum;
    }

    public static int sumOfMinEdges(Vertex[] vertices) {
        int sum = 0;

        for (Vertex vertex : vertices) {
            sum += vertex.key;
        }

        return sum;
    }
}
