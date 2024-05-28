package boruvkas_algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BoruvkasAlgorithm {
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

        Set<Edge> mstEdges = boruvkasAlgorithm(vertices, edges);

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

    static Set<Edge> boruvkasAlgorithm(Vertex[] vertices, Edge[] edges) {
        Set<Edge> mstEdges = new HashSet<>();
        Edge[] cheapest = new Edge[vertices.length];

        int numTrees = vertices.length;

        for (Vertex vertex : vertices) {
            DisjointSet.makeSet(vertex);
        }

        while (numTrees > 1) {

            for (Edge edge : edges) {

                Vertex firstSet = DisjointSet.findSet(edge.from);
                Vertex secondSet = DisjointSet.findSet(edge.to);

                if (firstSet != secondSet) {
                    if (cheapest[firstSet.id] == null || cheapest[firstSet.id].weight > edge.weight) {
                        cheapest[firstSet.id] = edge;
                    }

                    if (cheapest[secondSet.id] == null || cheapest[secondSet.id].weight > edge.weight) {
                        cheapest[secondSet.id] = edge;
                    }
                }
            }

            for (Vertex vertex : vertices) {
                Edge cheapEdge = cheapest[vertex.id];
                if (cheapEdge != null && DisjointSet.findSet(cheapEdge.to) != DisjointSet.findSet(cheapEdge.from)) {
                    DisjointSet.union(cheapEdge.to, cheapEdge.from);
                    mstEdges.add(cheapEdge);
                    numTrees--;
                }
            }

            for (int i = 0; i < cheapest.length; ++i) {
                cheapest[i] = null;
            }
        }

        return mstEdges;

    }
}
