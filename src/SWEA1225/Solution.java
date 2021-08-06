import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 암호생성기
public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    final int TESTCASE_COUNT = 10;
    final int PW_SIZE = 8;

    for (int testCase = 1; testCase <= TESTCASE_COUNT; testCase++) {
      bReader.readLine();

      // init numbers
      String[] numberString = bReader.readLine().split(" ");
      
      int minNumber = Integer.MAX_VALUE;
      int[] numbers = new int[PW_SIZE];

      for (int i = 0; i < numbers.length; i++) {
        numbers[i] = Integer.parseInt(numberString[i]);
        minNumber = Math.min(minNumber, numbers[i]);
      }

      /**
       * 1 ~ 5 를 순차적으로 빼다 보면 사이클이 돌면서 
       * 모든 원소가 균일하게 15씩 마이너스될 때가 온다
       * 최솟값을 기준으로 15씩 빼면서, 전체적으로 값을 낮춘 상태에서 로직을 진행할 수 있게 함
       */

      int offset = minNumber - (minNumber % 15) - 15;
      // 최소값이 두 개 이상인 경우를 위해, -15를 더 해서 1사이클 여유를 만듦
      // (0인 값이 동시에 두 개 생기지 않게)

      // 모든 원소를 대상으로 offset만큼 빼 주면 더 빠르게 0값을 찾을 수 있음
      for (int i = 0; i < numbers.length; i++) {
        numbers[i] -= offset;
      }

      int minusNum = 1; // 1 ~ 5 순환할 숫자
      boolean breakFlag = true;
      while (breakFlag) {
        for (int i = 0; i < numbers.length; i++) {
          numbers[i] -= minusNum++;
          
          if (minusNum >= 6) {  
            // 5를 넘으면, 1로 초기화
            minusNum = 1;
          }

          if (numbers[i] <= 0) {
            numbers[i] = 0;
            breakFlag = false;
            break;
          }
        }
      }

      StringBuilder sBuilderLeft = new StringBuilder();
      StringBuilder sBuilderRight = new StringBuilder();
      // 시작부터 0까지의 숫자들을 Right에 저장
      // 0 이 발견된 이후의 숫자들을 Left에 저장

      int cursor = 0;
      for (; cursor < numbers.length; cursor++) {
        sBuilderRight.append(String.format("%d ", numbers[cursor]));
        if (numbers[cursor] == 0) { 
          cursor++;
          break;
        }
      }

      for (; cursor < numbers.length; cursor++) {
        sBuilderLeft.append(String.format("%d ", numbers[cursor]));
      }
      bWriter.write(String.format("#%d %s%s\n", testCase, sBuilderLeft.toString(), sBuilderRight.toString()));
    }

    bWriter.flush();

    bReader.close();
    bWriter.close();
  }
}
