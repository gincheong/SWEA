package SWEA1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    for (int testCase = 1; testCase <= 10; testCase++) {
      int dumpCount = Integer.parseInt(reader.readLine());

      int[] blocks = new int[100];

      String[] inputString = reader.readLine().split(" ");
      for (int i = 0; i < inputString.length; i++) {
        blocks[i] = Integer.parseInt(inputString[i]);
      }

      for (int i = 0; i < dumpCount; i++) {
        int[] minMaxIdx = getMinMaxIndex(blocks);
        int minIdx = minMaxIdx[0];
        int maxIdx = minMaxIdx[1];
        
        if (blocks[maxIdx] - blocks[minIdx] <= 1) {
          break;
        }
        
        blocks[minIdx]++;
        blocks[maxIdx]--;
      }
      
      int[] minMaxIdx = getMinMaxIndex(blocks);
      int minIdx = minMaxIdx[0];
      int maxIdx = minMaxIdx[1];
      int answer = blocks[maxIdx] - blocks[minIdx];
      
      System.out.printf("#%d %d\n", testCase, answer);
    }

  }

  private static int[] getMinMaxIndex(int[] arr) {
    int minValue = Integer.MAX_VALUE;
    int maxValue = Integer.MIN_VALUE;

    int minIdx = 0;
    int maxIdx = 0;
    
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < minValue) {
        minValue = arr[i];
        minIdx = i;
      }

      if (arr[i] > maxValue) {
        maxValue = arr[i];
        maxIdx = i;
      }
    }

    return new int[]{minIdx, maxIdx};
  }
}
