import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayDeque;
import datastructures.Edge;
import datastructures.Vertex;

public class GraphGenerator {

    public static Edge[] generateComplete(Vertex[] vertices, int maxWeight) {
        int n = vertices.length;
        Edge[] edges = new Edge[n * (n - 1) / 2];
        Random randomWeight = new Random();

        int k = 0;
        for (int i = 0; i < vertices.length - 1; ++i) {
            for (int j = i + 1; j < vertices.length; ++j) {
                edges[k++] = new Edge(vertices[i], vertices[j], randomWeight.nextInt(maxWeight));
            }
        }

        return edges;
    }

    public static ArrayList<Edge> generateRandom(Vertex[] vertices, int startVertexIndex, int maxWeight,
            double probability) {

        // the component which will be built into a graph by
        // adding vertices (singleton components) one by one
        ArrayList<Vertex> majorComponent = new ArrayList<>();

        // list of edges of the graph
        ArrayList<Edge> edges = new ArrayList<>();

        if(vertices.length < 2) {
            return edges;
        }

        // initialize the major component with an arbitrary vertex
        majorComponent.add(vertices[startVertexIndex]);

        // duplicate the vertex set V minus the initial vertex of the major component
        ArrayList<Vertex> duplicates = new ArrayList<>();
        for (int i = 0; i < vertices.length; ++i) {
            if (i == startVertexIndex) {
                continue;
            }

            duplicates.add(vertices[i]);
        }


        // shuffle (random sort) the duplicated set so that vertices may be added to the
        // major component randomly
        Collections.shuffle(duplicates);

        ArrayDeque<Vertex> queue = new ArrayDeque<>(duplicates);

        Vertex u;
        boolean hasEdge;
        Random random = new Random();

        while (!queue.isEmpty()) {
            u = queue.pop();
            hasEdge = false;

            // as long as there is not an edge between u and any vertex v in the major component
            while (!hasEdge) {

                // randomly add an edge between u and v
                for (Vertex v : majorComponent) {

                    if (random.nextDouble(0, 1) <= probability) {
                        edges.add(new Edge(u, v, random.nextInt(1, maxWeight)));
                        hasEdge = true;
                    }
                }
            }

            // include u in the major component
            majorComponent.add(u);
        }

        return edges;
    }

    public static ArrayList<ArrayList<Edge>> getAdjacencyLists(ArrayList<Edge> edges, int numOfVertices) {
        ArrayList<ArrayList<Edge>> adjLists = new ArrayList<>();

        for (int i = 0; i < numOfVertices; ++i) {
            adjLists.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjLists.get(edge.u.index).add(new Edge(null, edge.v, edge.weight));
            adjLists.get(edge.v.index).add(new Edge(null, edge.u, edge.weight));
        }

        return adjLists;
    }
}
