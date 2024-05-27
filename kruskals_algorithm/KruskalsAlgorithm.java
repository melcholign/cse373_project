package kruskals_algorithm;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class KruskalsAlgorithm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int m = input.nextInt(), n = input.nextInt();

        Vertex[] vertices = new Vertex[m];
        for (int i = 0; i < m; ++i) {
            vertices[i] = new Vertex(i);
        }

        Edge[] edges = new Edge[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new Edge(vertices[input.nextInt()], vertices[input.nextInt()], input.nextInt());
        }

        Set<Edge> mstEdges = kruskalsAlgorithm(vertices, edges);

        System.out.println(sumOfEdgeWeights(mstEdges));

        input.close();
    }

    public static int sumOfEdgeWeights(Set<Edge> edges) {
        int sum = 0;
        for(Edge edge: edges) {
            sum += edge.weight;
        }

        return sum;
    }

    public static Set<Edge> kruskalsAlgorithm(Vertex[] vertices, Edge[] edges) {
        Set<Edge> mstEdges = new HashSet<>();

        for (Vertex vertex : vertices) {
            DisjointSet.makeSet(vertex);
        }

        Arrays.sort(edges);

        for (Edge edge : edges) {
            if (DisjointSet.findSet(edge.from) != DisjointSet.findSet(edge.to)) {
                mstEdges.add(edge);
                DisjointSet.union(edge.from, edge.to);
            }
        }

        return mstEdges;
    }
}
