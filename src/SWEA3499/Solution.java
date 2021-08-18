package SWEA3499;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(bReader.readLine());

    for (int testCase = 1; testCase <= T; testCase++) {
      int cardCount = Integer.parseInt(bReader.readLine());
      
      String[] answer = new String[cardCount];
      String[] input = bReader.readLine().split(" ");

      int offset = cardCount / 2;
      if (cardCount % 2 == 1) { offset++; };
      
      int count = 0;
      int left = 0;
      int right = offset;
      while (count < cardCount) {
        if (left < offset) {
          answer[count++] = input[left++];
        }

        if (right < cardCount) {
          answer[count++] = input[right++];
        }
      }

      bWriter.write(String.format("#%d ", testCase));
      for (String each : answer) {
        bWriter.write(String.format("%s ", each));
      }
      bWriter.write("\n");
    }


    bWriter.flush();

    bReader.close();
    bWriter.close();
  }
}
