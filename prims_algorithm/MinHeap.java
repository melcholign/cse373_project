package prims_algorithm;

public class MinHeap {

    Vertex[] heap;
    int heapSize;

    MinHeap(int maxLength) {
        heap = new Vertex[maxLength];
        heapSize = 0;
    }

    int parent(int i) {
        return (int) Math.floor(i / 2);
    }

    int left(int i) {
        return 2 * i;
    }

    int right(int i) {
        return 2 * i + 1;
    }

    void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;

        if (l < heapSize && heap[l].key < heap[i].key) {
            smallest = l;
        } else {
            smallest = i;
        }

        if (r < heapSize && heap[r].key < heap[smallest].key) {
            smallest = r;
        }

        if (smallest != i) {
            Vertex temp = heap[smallest];
            heap[smallest] = heap[i];
            heap[i] = temp;

            minHeapify(smallest);
        }
    }

    void decreaseKey(Vertex vertex, int newKey) {

        vertex.key = newKey;

        int i = indexOf(vertex);

        while (i > 0 && heap[parent(i)].key > heap[i].key) {
            Vertex temp = heap[parent(i)];
            heap[parent(i)] = heap[i];
            heap[i] = temp;
            i = parent(i);
        }

    }

    void insert(Vertex vertex) {
        heapSize++;
        int key = vertex.key;
        vertex.key = Integer.MAX_VALUE;
        heap[heapSize - 1] = vertex;
        decreaseKey(vertex, key);
    }

    Vertex extractMin() {
        Vertex min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;
        minHeapify(0);
        return min;
    }

    int indexOf(Vertex vertex) {
        for (int i = 0; i < heapSize; ++i) {
            if (heap[i].label == vertex.label) {
                return i;
            }
        }

        return -1;
    }

    public String toString() {

        String heapStr = "";

        for (int i = 0; i < heapSize; ++i) {
            heapStr += "(" + heap[i].label + ", " + heap[i].key + ") ";
        }

        return heapStr;
    }
}
