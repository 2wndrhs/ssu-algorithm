package DP;

public class Floyd {

  private static final int N = 5;
  // 교재의 입력 데이터
  private static final int[][] W = {
      {0, 1, 999, 1, 5},
      {9, 0, 3, 2, 999},
      {999, 999, 0, 4, 999},
      {999, 999, 2, 0, 3},
      {3, 999, 999, 999, 0}
  };
  // 자작 입력 데이터
  private static final int[][] W2 = {
      {0, 2, 999, 5, 999},
      {3, 0, 4, 9, 7},
      {999, 999, 0, 5, 2},
      {1, 999, 999, 0, 3},
      {999, 999, 999, 1, 0}
  };
  private static final int[][] D = new int[N][N];
  private static final int[][] P = new int[N][N];

  public static void main(String[] args) {
    floyd2(N, W2, D, P);

    System.out.println("D:");
    for (int[] row : D) {
      for (int col : row) {
        System.out.printf("%d ", col);
      }
      System.out.println();
    }

    System.out.println("P:");
    for (int[] row : P) {
      for (int col : row) {
        System.out.printf("%d ", col);
      }
      System.out.println();
    }

    System.out.println("path(1, 5):");
    path(1, 5);
  }

  static void floyd2(int n, int[][] w, int[][] d, int[][] p) {
    int i, j, k;

    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++) {
        p[i][j] = 0;
      }
    }

    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++) {
        d[i][j] = w[i][j];
      }
    }

    for (k = 0; k < n; k++) {
      for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
          if (d[i][k] + d[k][j] < d[i][j]) {
            p[i][j] = k + 1;
            d[i][j] = d[i][k] + d[k][j];
          }
        }
      }
    }
  }

  static void path(int q, int r) {
    if (P[q - 1][r - 1] != 0) {
      path(q, P[q - 1][r - 1]);
      System.out.printf("v%d ", P[q - 1][r - 1]);
      path(P[q - 1][r - 1], r);
    }
  }
}
