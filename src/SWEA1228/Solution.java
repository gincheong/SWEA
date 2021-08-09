import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

// 암호문 - 1
public class Solution {
  public static void main(String[] args) throws IOException {
    final int TESTCASE_COUNT = 10;

    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    
    for (int T = 1; T <= TESTCASE_COUNT; T++) {
      StringBuilder sBuilder = new StringBuilder();

      bReader.readLine(); // 원본 암호문의 길이 
      
      String[] input = bReader.readLine().split(" "); // 원본 암호문

      LinkedList<String> password = new LinkedList<>();
      for (String each : input) {
        password.add(each);
      }

      int commandCount = Integer.parseInt(bReader.readLine()); // 명령어의 개수

      String[] commands = bReader.readLine().split(" "); // 명령어
      
      int count = 0;
      int index = 0;
      while (count < commandCount) {
        index++; // I 알파벳 부분 넘기기
        int insertIndex = Integer.parseInt(commands[index++]);
        int insertCount = Integer.parseInt(commands[index++]);

        LinkedList<String> temp = new LinkedList<>();
        for (int i = 0; i < insertCount; i++) {
          if (insertIndex + i >= 10) {
            break;
          }
          temp.add(commands[index + i]);
        }
        if (insertIndex < 10) {
          password.addAll(insertIndex, temp);
        }
        
        count++;
        index += insertCount;
      }

      sBuilder.append(String.format("#%d ", T));
      for (int i = 0; i < 10; i++) {
        sBuilder.append(String.format("%s ", password.get(i)));
      }
      sBuilder.append("\n");
      bWriter.write(sBuilder.toString());
    }

    bWriter.flush();

    bReader.close();
    bWriter.close();
  }
}
