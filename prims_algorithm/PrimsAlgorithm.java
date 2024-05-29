package prims_algorithm;

import java.util.Scanner;

public class PrimsAlgorithm {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int m = input.nextInt(), n = input.nextInt();

        Graph graph = new Graph(m);

        for (int i = 0; i < m; i++) {
            graph.addVertex(new Vertex(i));
        }

        for (int i = 0; i < n; ++i) {
            graph.addEdge(input.nextInt(), input.nextInt(), input.nextInt());
        }

        for(Vertex vertex: graph.adjacencyList) {
            for(Edge edge: vertex.neighborhood) {
                System.out.println(edge);
            }
        }

        // primsAlgorithm(graph, graph.adjacencyList[0]);

        // System.out.println(MSTEdgeSum(graph));

    }

    static int MSTEdgeSum(Graph graph) {
        int sum = 0;

        for (Vertex vertex : graph.adjacencyList) {
            sum += vertex.key;
        }

        return sum;
    }

    static void primsAlgorithm(Graph graph, Vertex root) {
        for (Vertex vertex : graph.adjacencyList) {
            vertex.key = Integer.MAX_VALUE;
            vertex.predecessor = null;
        }

        root.key = 0;

        MinHeap priorityQueue = new MinHeap(graph.order);
        for (Vertex vertex : graph.adjacencyList) {
            priorityQueue.insert(vertex);
        }

        while (priorityQueue.heapSize != 0) {
            Vertex u = priorityQueue.extractMin();
            for (Edge edge : graph.adjacencyList[u.label].neighborhood) {
                Vertex v = edge.neighbor;
                if (priorityQueue.indexOf(v) != -1 && edge.weight < v.key) {
                    v.predecessor = u;
                    v.key = edge.weight;
                    System.out.println(u + " : " + edge.weight);
                    priorityQueue.decreaseKey(v, edge.weight);
                }
            }
        }
    }
}
