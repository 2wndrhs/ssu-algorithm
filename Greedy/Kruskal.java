package Greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {

  // number of vertices
  private static int N = 5;
  // number of edges
  private static int M = 7;
  // 교재의 입력 데이터
  private static int[][] set_of_edges = {
      {1, 2, 1},
      {3, 5, 2},
      {1, 3, 3},
      {2, 3, 3},
      {3, 4, 4},
      {4, 5, 5},
      {2, 4, 6}
  };

  // 자작 데이터
  private static int[][] set_of_edges2 = {
      {1, 2, 3},
      {1, 3, 5},
      {2, 3, 2},
      {2, 4, 6},
      {2, 5, 4},
      {3, 4, 1},
      {4, 5, 7}
  };
  private static ArrayList<int[]> F = new ArrayList<>();

  public static void main(String[] args) {
    kruskal(N, M, set_of_edges2, F);

    for (int[] row : F) {
      for (int col = 0; col < row.length; col++) {
        if (col == 2) {
          System.out.printf("%d", row[col]);
          continue;
        }
        System.out.printf("v%d ", row[col]);
      }
      System.out.println();
    }
  }

  private static void kruskal(int n, int m, int[][] setOfEdges, ArrayList<int[]> F) {
    int i, j; // index
    int p, q; // set_pointer
    int[] e; // edge

    // sort the m edges in E by weight in nondecreasing order
    sort(m, setOfEdges);

    // initialize disjoint sets
    DisjointSet ds = new DisjointSet(n);

    while (F.size() < n - 1) {
      // edges with least weight not yet considered
      e = setOfEdges[0];
      // indicies of vertices connected by e
      i = e[0] - 1;
      j = e[1] - 1;

      p = ds.find(i);
      q = ds.find(j);

      if (!ds.equal(p, q)) {
        ds.merge(p, q);
        // add e to F
        F.add(e);
      }

      // remove e from E
      setOfEdges = Arrays.copyOfRange(setOfEdges, 1, setOfEdges.length);
    }
  }

  private static void sort(int m, int[][] setOfEdges) {
    int i, j;
    int[] e;

    for (i = 0; i < m - 1; i++) {
      for (j = i + 1; j < m; j++) {
        int weight1 = setOfEdges[i][2];
        int weight2 = setOfEdges[j][2];

        if (weight1 > weight2) {
          e = setOfEdges[i];
          setOfEdges[i] = setOfEdges[j];
          setOfEdges[j] = e;
        }
      }
    }
  }
}
