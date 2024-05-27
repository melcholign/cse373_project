package kruskals_algorithm;

public class DisjointSet {

    public static void makeSet(Vertex x) {
        x.parent = x;
        x.rank = 0;
    }

    public static Vertex findSet(Vertex x) {
        if (x != x.parent) {
            x.parent = findSet(x.parent);
        }

        return x.parent;
    }

    public static void link(Vertex x, Vertex y) {
        if (x.rank > y.rank) {
            y.parent = x;
        } else {
            x.parent = y;

            if (x.rank == y.rank) {
                y.rank++;
            }
        }
    }

    public static void union(Vertex x, Vertex y) {
        link(findSet(x), findSet(y));
    }

}
