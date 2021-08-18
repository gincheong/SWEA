package SWEA1861;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

  static int roomSize;
  static int[][] room;
  static int[][] roomMoveCount;
  private static int[][] dXY = new int[][] {
    {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // 상하좌우
  };

  public static void main(String[] args) throws IOException {
    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int testCaseCount = Integer.parseInt(bReader.readLine());

    for (int testCase = 1; testCase <= testCaseCount; testCase++) {
      // 새 테스트케이스 들어오면 정답 초기화
      int answerRoomNo = 0;
      int answerCount = 0;

      roomSize = Integer.parseInt(bReader.readLine());

      // 방 배열 초기화
      room = new int[roomSize][roomSize];
      roomMoveCount = new int[roomSize][roomSize];
      for (int i = 0; i < roomSize; i++) {
        String[] eachRow = bReader.readLine().split(" ");
        for (int j = 0; j < roomSize; j++) {
          room[i][j] = Integer.parseInt(eachRow[j]);
        }
      }

      // 각 위치를 방문하여 계산
      for (int i = 0; i < roomSize; i++) {
        for (int j = 0; j < roomSize; j++) {
          repeat(i, j);
          
          if (roomMoveCount[i][j] > answerCount) {
            answerCount = roomMoveCount[i][j];
            answerRoomNo = room[i][j];
          } else if (roomMoveCount[i][j] == answerCount) {
            answerRoomNo = Math.min(answerRoomNo, room[i][j]);
          }

        }
      }

      bWriter.write(String.format("#%d %d %d\n", testCase, answerRoomNo, answerCount));

    }
    
    bWriter.flush();

    bReader.close();
    bWriter.close();
  }

  private static void repeat(int startRow, int startCol) {
    if (roomMoveCount[startRow][startCol] > 0) {
      // 이미 이전에 방문해서, moveCount가 기록된 곳임
      return; // 또 계산할 필요 없음
    }

    roomMoveCount[startRow][startCol] = 1; // 기본적으로 처음 방문하면 1임
    
    for (int i = 0; i < dXY.length; i++) {
      int nextRow = startRow + dXY[i][0];
      int nextCol = startCol + dXY[i][1];

      if (nextRow >= 0 && nextRow < roomSize && nextCol >= 0 && nextCol < roomSize) {
        // 범위를 넘지 않는 4방에 대해서

        if (room[startRow][startCol] + 1 == room[nextRow][nextCol]) {
          // 정확히 1만큼 더한 방 번호가 발견된다면?
          
          repeat(nextRow, nextCol); // 다음 좌표도 이어진 숫자가 있나 확인

          roomMoveCount[startRow][startCol] = roomMoveCount[nextRow][nextCol] + 1;
        }

      }

    }

  }

}
