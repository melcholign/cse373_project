import datastructures.Vertex;
import datastructures.Edge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        primsElapsedTime("prims.txt", 1000, 100, 0.5, 25, 100);
    }

    public static void kruskalsElapsedTime(String dataFilename, int maxVertices, int maxEdgeWeight, double probability,
            int intervalWidth, int cycleSize) {

        File dataFile = new File(dataFilename);

        Vertex[] vertices;
        Edge[] edgeList;
        ArrayList<Edge> edges;
        double startTime, endTime, totalElapsedTime, averageElapsedTime;

        try (FileWriter writer = new FileWriter(dataFile)) {

            for (int count = 0; count <= maxVertices; count += intervalWidth) {

                vertices = new Vertex[count];

                for (int i = 0; i < count; ++i) {
                    vertices[i] = new Vertex(i);
                }

                totalElapsedTime = 0;

                for (int j = 0; j < cycleSize; ++j) {
                    edges = GraphGenerator.generateRandom(vertices, 0, maxEdgeWeight, probability);
                    edgeList = new Edge[edges.size()];

                    for (int i = 0; i < edges.size(); ++i) {
                        edgeList[i] = edges.get(i);
                    }

                    startTime = System.nanoTime();

                    MSTAlgorithms.kruskals(vertices, edgeList);

                    endTime = System.nanoTime();

                    totalElapsedTime += (endTime - startTime);
                }

                averageElapsedTime = (totalElapsedTime) / (cycleSize * 1000000);

                System.out
                        .println("Average elapsed time for an input of " + count + " vertices: " + averageElapsedTime);

                writer.write(count + " " + averageElapsedTime + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void boruvkasElapsedTime(String dataFilename, int maxVertices, int maxEdgeWeight, double probability,
            int intervalWidth, int cycleSize) {

        File dataFile = new File(dataFilename);

        Vertex[] vertices;
        Edge[] edgeList;
        ArrayList<Edge> edges;
        double startTime, endTime, totalElapsedTime, averageElapsedTime;

        try (FileWriter writer = new FileWriter(dataFile)) {

            for (int count = 0; count <= maxVertices; count += intervalWidth) {

                vertices = new Vertex[count];

                for (int i = 0; i < count; ++i) {
                    vertices[i] = new Vertex(i);
                }

                totalElapsedTime = 0;

                for (int j = 0; j < cycleSize; ++j) {
                    edges = GraphGenerator.generateRandom(vertices, 0, maxEdgeWeight, probability);
                    edgeList = new Edge[edges.size()];

                    for (int i = 0; i < edges.size(); ++i) {
                        edgeList[i] = edges.get(i);
                    }

                    startTime = System.nanoTime();

                    MSTAlgorithms.boruvkas(vertices, edgeList);

                    endTime = System.nanoTime();

                    totalElapsedTime += (endTime - startTime);
                }

                averageElapsedTime = (totalElapsedTime) / (cycleSize * 1000000);

                System.out
                        .println("Average elapsed time for an input of " + count + " vertices: " + averageElapsedTime);

                writer.write(count + " " + averageElapsedTime + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void primsElapsedTime(String dataFilename, int maxVertices, int maxEdgeWeight, double probability,
            int intervalWidth, int cycleSize) {

        File dataFile = new File(dataFilename);

        Vertex[] vertices;
        ArrayList<Edge> edges;
        ArrayList<ArrayList<Edge>> adjacencyList;
        double startTime, endTime, totalElapsedTime, averageElapsedTime;

        try (FileWriter writer = new FileWriter(dataFile)) {

            for (int count = 0; count <= maxVertices; count += intervalWidth) {

                vertices = new Vertex[count];

                for (int i = 0; i < count; ++i) {
                    vertices[i] = new Vertex(i);
                }

                totalElapsedTime = 0;

                for (int j = 0; j < cycleSize; ++j) {
                    edges = GraphGenerator.generateRandom(vertices, 0, maxEdgeWeight, probability);
                    adjacencyList = GraphGenerator.getAdjacencyLists(edges, vertices.length);

                    startTime = System.nanoTime();

                    if (vertices.length != 0)
                        MSTAlgorithms.prims(vertices, adjacencyList, vertices[0]);

                    endTime = System.nanoTime();

                    totalElapsedTime += (endTime - startTime);
                }

                averageElapsedTime = (totalElapsedTime) / (cycleSize * 1_000_000);

                System.out
                        .println("Average elapsed time for an input of " + count + " vertices: " + averageElapsedTime);

                writer.write(count + " " + averageElapsedTime + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

}
