import java.util.Scanner;

import datastructures.Vertex;

public class Test {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int V = input.nextInt(), W = input.nextInt(), C = input.nextInt();
        Vertex[] vertices = new Vertex[V];

        for (int i = 0; i < V; ++i) {
            vertices[i] = new Vertex(i);
        }

        long startTime, endTime;
        double elapsedTime, t1 = 0, t2 = 0;

        for (int i = 0; i < C; ++i) {

            startTime = System.nanoTime();
            GraphGenerator.generateComplete(vertices, W);
            endTime = System.nanoTime();
            elapsedTime = (double) (endTime - startTime) / 1_000_000;
            t1 += elapsedTime;

            System.out.println(
                    "** For cycle " + (i + 1) + ", it took " + elapsedTime + " ms to generate a complete graph");

            startTime = System.nanoTime();
            GraphGenerator.generateRandom(vertices, 0, W, 1.0);
            endTime = System.nanoTime();
            elapsedTime = (double) (endTime - startTime) / 1_000_000;
            t2 += elapsedTime;

            System.out
                    .println("// For cycle " + (i + 1) + ", it took " + elapsedTime + " ms to generate a random graph");
        }

        System.out.println("** On average, it took " + (t1 / C) + " ms to generate a complete graph");
        System.out.println("// On average, it took " + (t2 / C) + " ms to generate a random graph");
    }
}
