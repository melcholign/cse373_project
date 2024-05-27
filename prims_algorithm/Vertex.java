package prims_algorithm;

import java.util.ArrayList;

public class Vertex {
    int label;
    int key;
    Vertex predecessor;
    ArrayList<Edge> neighborhood;

    Vertex(int label) {
        this.label = label;
        neighborhood = new ArrayList<>();
    }

    Vertex(int label, int key) {
        this.label = label;
        this.key = key;
    }

    public String toString() {
        return "label: " + label + ", key: " + key; 
    }
}
