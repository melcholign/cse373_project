import datastructures.Vertex;
import datastructures.Edge;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int m = input.nextInt(), n = input.nextInt();

        Vertex[] vertices = new Vertex[m];
        ArrayList<ArrayList<Edge>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < m; ++i) {
            vertices[i] = new Vertex(i);
            adjacencyList.add(new ArrayList<Edge>());
        }

        // Edge[] edges = new Edge[n];
        // for (int i = 0; i < n; ++i) {
        // edges[i] = new Edge(vertices[input.nextInt()], vertices[input.nextInt()],
        // input.nextInt());
        // }

        Vertex u, v;
        int weight;
        for (int i = 0; i < n; ++i) {
            u = vertices[input.nextInt()];
            v = vertices[input.nextInt()];
            weight = input.nextInt();

            adjacencyList.get(u.index).add(new Edge(null, v, weight));
            adjacencyList.get(v.index).add(new Edge(null, u, weight));
        }

        MSTAlgorithms.prims(vertices, adjacencyList, vertices[0]);

        System.out.println(MSTAlgorithms.sumOfMinEdges(vertices));

        input.close();
    }

}
