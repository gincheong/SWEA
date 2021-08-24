import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// 무선 충전
public class Solution {
  private static class Position {
    int x, y;
    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static class User extends Position {
    public User(int x, int y) {
      super(x, y);
    }
  }

  private static class BatteryCharger extends Position {
    int range, power;
    public BatteryCharger(int x, int y, int range, int power) {
      super(x, y);
      this.range = range;
      this.power = power;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    int testCaseCount = Integer.parseInt(bReader.readLine());

    for (int T = 1; T <= testCaseCount; T++) {
      String[] inputString = bReader.readLine().split(" ");

      // 이동 횟수, 배터리 개수
      int moveCount = Integer.parseInt(inputString[0]);
      int chargerCount = Integer.parseInt(inputString[1]);
      
      // 각 사용자의 이동 경로 저장
      // 맨 마지막 상태에서 한번 더 계산하기 위해 + 1 -> 마지막 원소는 기본값인 0임
      int[] AMove = new int[moveCount + 1];
      int[] BMove = new int[moveCount + 1];

      User A = new User(1, 1);
      User B = new User(10, 10);

      // A사용자 이동 경로
      String[] AMoveString = bReader.readLine().split(" ");
      for (int i = 0; i < moveCount; i++) {
        AMove[i] = Integer.parseInt(AMoveString[i]);
      }

      // B사용자 이동 경로
      String[] BMoveString = bReader.readLine().split(" ");
      for (int i = 0; i < moveCount; i++) {
        BMove[i] = Integer.parseInt(BMoveString[i]);
      }

      // 배터리 초기화
      BatteryCharger[] chargers = new BatteryCharger[chargerCount];
      for (int i = 0; i < chargerCount; i++) {
        String[] chargerString = bReader.readLine().split(" ");
        int x = Integer.parseInt(chargerString[0]);
        int y = Integer.parseInt(chargerString[1]);
        int range = Integer.parseInt(chargerString[2]);
        int power = Integer.parseInt(chargerString[3]);

        chargers[i] = new BatteryCharger(x, y, range, power);
      }

      // x, y축이 반대임
      int[][] dxy = new int[][]{
        {0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}
      };
      
      int totalChargeAmount = 0;
      
      // 이동 시작
      for (int move = 0; move < AMove.length; move++) {
        // 각 배터리들과 사용자들의 거리 계산

        // 충전 가능한 충전기 목록을 담을 리스트 선언
        ArrayList<BatteryCharger> AChargers = new ArrayList<>();
        ArrayList<BatteryCharger> BChargers = new ArrayList<>();

        // A 사용자가 충전 가능한 배터리 찾기
        for (BatteryCharger charger : chargers) {
          // 각 사용자와, 배터리 사이 거리 계산
          int ADistance = getManhattanDistance(charger, A);
          
          // 충전 범위 내에 있다면
          if (ADistance <= charger.range) {
            // 충전 가능 리스트에 추가
            AChargers.add(charger);
          }
        }

        // B 사용자가 충전 가능한 배터리 찾기
        for (BatteryCharger charger : chargers) {
          // 각 사용자와, 배터리 사이 거리 계산
          int BDistance = getManhattanDistance(charger, B);

          // 충전 범위 내에 있다면
          if (BDistance <= charger.range) {
            // 충전 가능 리스트에 추가
            BChargers.add(charger);
          }
        }

        // 이번 이동 상태에서 최대 가능한 충전량
        int chargeAmount = 0;

        // 둘 다 충전이 가능한 경우
        for (int AIdx = 0; AIdx < AChargers.size(); AIdx++) {
          for (int BIdx = 0; BIdx < BChargers.size(); BIdx++) {
            if (!AChargers.get(AIdx).equals(BChargers.get(BIdx))) {
              // 선택된 충전기가 다른 경우
              chargeAmount = Math.max(chargeAmount, AChargers.get(AIdx).power + BChargers.get(BIdx).power);
            } else {
              // 같은 경우
              chargeAmount = Math.max(chargeAmount, AChargers.get(AIdx).power);
            }
          }
        }
        
        if (!AChargers.isEmpty() && BChargers.isEmpty()) {
          // A만 충전 가능한 경우
          for (BatteryCharger charger : AChargers) {
            chargeAmount = Math.max(chargeAmount, charger.power);
          }
          
        } else if (AChargers.isEmpty() && !BChargers.isEmpty()) {
          // B만 충전 가능한 경우
          for (BatteryCharger charger : BChargers) {
            chargeAmount = Math.max(chargeAmount, charger.power);
          }
        }
        
        // 이번 충전량 합산
        totalChargeAmount += chargeAmount;

        // 사용자 위치 이동
        A.x = A.x + dxy[AMove[move]][0];
        A.y = A.y + dxy[AMove[move]][1];

        B.x = B.x + dxy[BMove[move]][0];
        B.y = B.y + dxy[BMove[move]][1];
      }

      bWriter.write("#" + T + " " + totalChargeAmount + "\n");
    }
    
    bWriter.flush();
    bReader.close();
    bWriter.close();
  }

  private static int getManhattanDistance(Position p1, Position p2) {
    return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
  }
}
