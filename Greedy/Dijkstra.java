package Greedy;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

  // 교재 입력 데이터
  private static final int[][] W1 = {
      {0, 7, 4, 6, 1},
      {999, 0, 999, 999, 999},
      {999, 2, 0, 5, 999},
      {999, 3, 999, 0, 999},
      {999, 999, 999, 1, 0}
  };

  // 자작 데이터
  private static final int[][] W2 = {
      {0, 2, 8, 9, 999},
      {999, 0, 4, 4, 999},
      {999, 999, 0, 5, 2},
      {999, 999, 999, 0, 1},
      {999, 999, 999, 999, 0},
  };

  public static void main(String[] args) {
    List<int[]> F = new ArrayList<>();

    dijkstra(5, W2, F);

    for (int[] row : F) {
      for (int col = 0; col < row.length; col++) {
        if (col == 2) {
          System.out.printf("%d", row[col]);
          continue;
        }
        System.out.printf("v%d ", row[col] + 1);
      }
      System.out.println();
    }
  }

  private static void dijkstra(int n, int[][] W, List<int[]> F) {
    int[] edge;
    int vnear = 0;
    int[] touch = new int[n];
    int[] length = new int[n];

    // 모든 정점에 대해 v1을 v1 -> 정점의 최단 경로의 마지막 정점으로 초기화하고,
    // 해당 경로의 길이를 v1 -> 정점에 대한 가중치로 초기화
    for (int i = 1; i < n; i++) {
      touch[i] = 0;
      length[i] = W[0][i];
    }

    // n - 1개의 정점이 고려될 때까지 반복
    for (int i = 1; i < n; i++) {
      int min = 999;
      // 각 정점에 대해 최단 경로가 있는지 확인
      for (int j = 1; j < n; j++) {
        if (0 <= length[j] && length[j] < min) {
          min = length[j];
          vnear = j;
        }
      }

      // touch[vnear] 정점에서 vnear 정점으로 가는 간선 edge
      edge = new int[]{touch[vnear], vnear, W[touch[vnear]][vnear]};
      // edge를 해집합 F에 추가
      F.add(edge);

      // vnear 정점을 거쳐 가는 경로가 더 짧으면 최단 경로를 갱신
      for (int k = 1; k < n; k++) {
        if (length[vnear] + W[vnear][k] < length[k]) {
          length[k] = length[vnear] + W[vnear][k];
          touch[k] = vnear;
        }
      }

      // vnear 정점을 다시 고려하지 않도록 length[vnear]를 -1로 설정
      length[vnear] = -1;
    }
  }
}
