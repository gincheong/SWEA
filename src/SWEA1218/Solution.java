import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    final int TESTCASE_COUNT = 10;

    Map<String, String> closingBracketPair = new HashMap<String, String>() {{
      put(")", "(");
      put("]", "[");
      put("}", "{");
      put(">", "<");
    }};

    for (int testCase = 1; testCase <= TESTCASE_COUNT; testCase++) {
      bReader.readLine(); // 불필요 입력이라 제외

      String[] brackets = bReader.readLine().split("");
      
      boolean isValid = true;
      Stack<String> stack = new Stack<>();
      
      for (String each : brackets) {
        if (closingBracketPair.containsKey(each)) {
          // 닫히는 괄호인 경우
          String top = stack.peek();

          if (top.equals(closingBracketPair.get(each))) {
            // stack의 최상단에 같은 페어의 괄호가 있으면 상쇄함
            stack.pop();
          } else {
            // 닫는 괄호인데 페어가 맞지 않는다? 유효하지 않은 형태가 됨
            isValid = false;
            break;
          }

        } else {
          stack.push(each);
        }
      }

      // 스택이 비지 않으면 남은 괄호가 존재한다는 뜻
      if (!stack.isEmpty()) {
        isValid = false;
      }

      bWriter.write(String.format("#%d %d\n", testCase, isValid ? 1 : 0));
    }

    bWriter.flush();

    bReader.close();
    bWriter.close();
  }
}
