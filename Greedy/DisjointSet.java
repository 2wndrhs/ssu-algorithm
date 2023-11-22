package Greedy;

public class DisjointSet {

  private final int SIZE;
  private Node[] universe;

  public DisjointSet(int n) {
    SIZE = n;
    universe = new Node[SIZE];
    initial(SIZE);
  }

  static class Node {

    int parent;
    int depth;

    Node(int parent, int depth) {
      this.parent = parent;
      this.depth = depth;
    }
  }

  void makeSet(int i) {
    universe[i] = new Node(i, 0);
  }

  int find(int i) {
    int j = i;

    while (universe[j].parent != j) {
      j = universe[j].parent;
    }

    return j;
  }

  void merge(int p, int q) {
    if (universe[p].depth == universe[q].depth) {
      universe[p].depth += 1;
      universe[q].parent = p;
    } else if (universe[p].depth < universe[q].depth) {
      universe[p].parent = q;
    } else {
      universe[q].parent = p;
    }
  }

  boolean equal(int p, int q) {
    if (p == q) {
      return true;
    } else {
      return false;
    }
  }

  void initial(int n) {
    for (int i = 0; i < n; i++) {
      makeSet(i);
    }
  }
}
